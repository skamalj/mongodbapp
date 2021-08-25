package com.kamalsblog.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;


import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class DemoApplicationTests {
	
	@Autowired
	TestRestTemplate requestTemplate;

	@Test
	void getOneEmployee() {
		ResponseEntity<Employee> response = requestTemplate.getForEntity("/employees/2", Employee.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	void getAllEmployee() {
		ResponseEntity<Employee[]> response = requestTemplate.getForEntity("/employees", Employee[].class);
		assertThat(response.getBody().length).isEqualTo(2);
	}
}
