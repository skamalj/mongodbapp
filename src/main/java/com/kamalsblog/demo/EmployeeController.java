package com.kamalsblog.demo;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	private final MongoTemplate mongoTemplate;
	private final MongoOperations mongoops;
	
	
	@Autowired
	public EmployeeController (MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
		this.mongoops = mongoTemplate;
	}
	
	
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    Employee addEmployee(@Valid @RequestBody Employee p, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()) {
    		throw new InvalidEmployeeDataException(p);  
    	}
    	try {
    		mongoops.insert(p);
    		return p;
    	} catch (Exception e){
    		throw new DuplicateEmployeeIDException(p.getId());
    	}
    }
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    List<Employee> getEmployees() {   	
        return mongoTemplate.findAll(Employee.class);
    }
    
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    Employee getEmployee(@PathVariable Integer id) {   	
    	Employee p = mongoops.findById(id,Employee.class);
        if (p == null) throw new EmployeeIDNotFoundException(id);
        return p;
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    String deleteEmployee(@PathVariable Integer id) {   	
        Employee p = mongoops.findById(id,Employee.class);
        if (p == null) throw new EmployeeIDNotFoundException(id);
        mongoops.remove(p);
        return "Employee " + p.getName() + " deleted";
    }
	
	@SuppressWarnings("serial")
	public class DuplicateEmployeeIDException extends RuntimeException {
		DuplicateEmployeeIDException(int id){
			super("Duplicate employee ID: " + id);
		}
	}
	
	@SuppressWarnings("serial")
	public class EmployeeIDNotFoundException extends RuntimeException {
		EmployeeIDNotFoundException(int id){
			super("Employee ID not found: "+id);
		}
	}
	
	@SuppressWarnings("serial")
	public class InvalidEmployeeDataException extends RuntimeException {
		InvalidEmployeeDataException(Employee p) {
			super(new String("Invalid employee data(Age should be < 100)" + p));
		}
	}
	
}
