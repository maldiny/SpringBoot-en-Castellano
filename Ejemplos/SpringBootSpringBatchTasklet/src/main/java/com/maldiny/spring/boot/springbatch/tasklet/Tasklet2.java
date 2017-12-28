package com.maldiny.spring.boot.springbatch.tasklet;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;
 
@Component
public class Tasklet2 implements Tasklet{
 
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		try{
    		System.out.println(">>>>>>>>>>>>>>>>>>>>>>> Tasklet2 <<<<<<<<<<<<<<<<<<<<<<<<");
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		return null;
	}
}