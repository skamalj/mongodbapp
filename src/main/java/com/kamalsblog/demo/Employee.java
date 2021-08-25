package com.kamalsblog.demo;

import org.springframework.data.mongodb.core.mapping.Document;

import com.kamalsblog.demo.DemoApplication.MyConstraint;

@Document(collection = "employees2")
public class Employee {

  private int id;
  private String name;
  
  @MyConstraint
  private int age;

  public Employee(String name, int age, int id) {
	this.id = id;
    this.name = name;
    this.age = age;
  }
  
  public Employee() {
	  
  }

  public int getId() {
    return id;
  }
  public String getName() {
    return name;
  }
  public int getAge() {
    return age;
  }

  @Override
  public String toString() {
    return "Person [id=" + id + ", name=" + name + ", age=" + age + "]";
  }
 
  
}