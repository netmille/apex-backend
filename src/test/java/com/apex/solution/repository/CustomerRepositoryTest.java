package com.apex.solution.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.apex.solution.controller.CustomerController;

/**
 * 
 * @author turik.campbell
 *
 */
@TestInstance(Lifecycle.PER_CLASS)
public class CustomerRepositoryTest {

	private DataSource dataSource;

	
	
	@BeforeAll
	public void init() {

		dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScript("db/test-schema.sql")
				.addScript("db/test-data.sql").build();
	}

	@Test
	public void testGetPurchases() throws Exception {

		JdbcTemplate template = new JdbcTemplate();

		template.setDataSource(dataSource);

		CustomerRepository repository = new CustomerRepository(template);

		Date startDate = new SimpleDateFormat("MM-yyyy").parse("12-2022");
		Date endDate = new SimpleDateFormat("MM-yyyy").parse("01-2023");

		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(endDate);

		calEnd.add(Calendar.MONTH, 1);
		endDate = calEnd.getTime();
		int size = repository.getPurchases(startDate, endDate).size();

		assertEquals(3, size);
	}
}
