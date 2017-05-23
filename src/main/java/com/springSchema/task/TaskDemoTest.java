package com.springSchema.task;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;

public class TaskDemoTest extends TestCase {

	public void testDoSomethingWith() {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-schema.xml");
	}

}
