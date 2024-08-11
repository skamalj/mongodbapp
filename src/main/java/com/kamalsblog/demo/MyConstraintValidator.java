package com.kamalsblog.demo;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import com.kamalsblog.demo.DemoApplication.MyConstraint;

@Component
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Integer> {

	/**
	 * This Validator validates only Person instances
	 */
	public boolean supports(Class<?> clazz) {
		return Employee.class.equals(clazz);
	}

	@Override
	public boolean isValid(Integer age, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return age <= 100;
	}

}
