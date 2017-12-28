package com.maldiny.spring.boot.springbatch;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.maldiny.spring.boot.springbatch.domain.Personajes;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private EntityManagerFactory entityManagerFactory;
    
    @Autowired
    private Tasklet1 tasklet1;

    @Bean
    public JpaPagingItemReader<Personajes> reader(){
    	JpaPagingItemReader<Personajes> reader = new JpaPagingItemReader<Personajes>();
    	reader.setQueryString("SELECT i from Personajes i");
    	reader.setPageSize(2);
    	reader.setEntityManagerFactory(entityManagerFactory);
    	return reader;
    }

    @Bean
    public PersonajeItemProcessor processor() {
        return new PersonajeItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Personajes> writer() {
        JdbcBatchItemWriter<Personajes> writer = new JdbcBatchItemWriter<Personajes>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Personajes>());
        writer.setSql("UPDATE Personajes SET NOMBRE = :nombre, edad = :edad, genero = :genero where id = :id");
        writer.setDataSource(dataSource);
        return writer;
    }

    @Bean
    public Job job(JobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("job")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .start(step1())
                .next(step2())
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
        		.tasklet(tasklet1)
                .build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .<Personajes, Personajes> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
}