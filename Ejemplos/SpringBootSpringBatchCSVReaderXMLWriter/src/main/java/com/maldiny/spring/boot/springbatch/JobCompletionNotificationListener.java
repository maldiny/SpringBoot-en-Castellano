package com.maldiny.spring.boot.springbatch;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.maldiny.spring.boot.springbatch.model.Personaje;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

	private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("!!! JOB FINISHED! Time to verify the results");

			List<Personaje> results = jdbcTemplate.query("SELECT nombre, edad, genero FROM personajes", new RowMapper<Personaje>() {
				@Override
				public Personaje mapRow(ResultSet rs, int row) throws SQLException {
					return new Personaje(rs.getString(1), rs.getInt(2), rs.getString(3));
				}
			});

			for (Personaje Personaje : results) {
				log.info("Found <" + Personaje + "> in the database.");
			}

		}
	}
}