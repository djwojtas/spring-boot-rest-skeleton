package es.jemerino.springboot.rest.skeleton.dao.impl;

import org.springframework.stereotype.Repository;

import es.jemerino.springboot.rest.skeleton.dao.HelloWorldDAO;

@Repository
public class HelloWorldDAOImpl implements HelloWorldDAO {

	@Override
	public String findHelloWorld() {
		return "Hello World!";
	}

}
