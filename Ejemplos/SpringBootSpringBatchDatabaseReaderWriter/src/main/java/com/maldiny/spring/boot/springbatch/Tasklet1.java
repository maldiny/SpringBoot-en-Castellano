package com.maldiny.spring.boot.springbatch;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
 
@Component
public class Tasklet1 implements Tasklet{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private int cursor = 0;
	
	private String[] querys = {
			"INSERT INTO Personajes (id, nombre, edad, genero) VALUES (1, 'Hommer', '40', 'Masculino')",
			"INSERT INTO Personajes (id, nombre, edad, genero) VALUES (2, 'Bart', '8', 'Masculino')",
			"INSERT INTO Personajes (id, nombre, edad, genero) VALUES (3, 'Lisa', '7', 'Femenino')",
			};
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		try{
			jdbcTemplate.update(querys[cursor]);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		cursor++;
		if(cursor == querys.length){
			return null;
		}
		return RepeatStatus.CONTINUABLE;
	}
}