package com.kamalsblog.demo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@ComponentScan(basePackages= {"com.kamalsblog.events","com.kamalsblog.demo"})
@EnableAspectJAutoProxy
public class DemoApplication {
	
	@Autowired
	private EmployeeRepository personRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner initializeDB() {
		return args -> { 
			Employee[] p = {new Employee("Kamal",40,1), 
					new Employee("Superman",80,2) };
			personRepository.saveAll(Arrays.asList(p));
		};	
	}

	@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
	@Retention(RetentionPolicy.RUNTIME)
	@Constraint(validatedBy = {MyConstraintValidator.class} )
	public @interface MyConstraint {
	    String message() default "Age Constraint failed";
	     
	    Class<?>[] groups() default {};
	     
	    Class<? extends Payload>[] payload() default {};
	}
	
}
