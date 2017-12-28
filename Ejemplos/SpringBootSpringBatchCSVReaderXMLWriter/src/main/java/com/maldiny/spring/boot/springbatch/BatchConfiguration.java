package com.maldiny.spring.boot.springbatch;
import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.maldiny.spring.boot.springbatch.model.Personaje;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;

    // tag::readerwriterprocessor[]
    @Bean
    public FlatFileItemReader<Personaje> reader() {
        FlatFileItemReader<Personaje> reader = new FlatFileItemReader<Personaje>();
        reader.setResource(new ClassPathResource("personajes.csv"));
        reader.setLineMapper(new DefaultLineMapper<Personaje>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "nombre", "edad", "genero" });
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Personaje>() {{
                setTargetType(Personaje.class);
            }});
        }});
        return reader;
    }

    @Bean
    public PersonajeItemProcessor processor() {
        return new PersonajeItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Personaje> writer() {
        JdbcBatchItemWriter<Personaje> writer = new JdbcBatchItemWriter<Personaje>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Personaje>());
        writer.setSql("INSERT INTO personajes (nombre, edad, genero) VALUES (:nombre, :edad, :genero)");
        writer.setDataSource(dataSource);
        return writer;
    }
    // end::readerwriterprocessor[]

    // tag::jobstep[]
    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Personaje, Personaje> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
    // end::jobstep[]
}