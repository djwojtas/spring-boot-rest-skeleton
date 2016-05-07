package es.jemerino.springboot.rest.skeleton.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jemerino.springboot.rest.skeleton.dao.HelloWorldDAO;
import es.jemerino.springboot.rest.skeleton.service.HelloWorldService;

@Service
public class HelloWorldServiceImpl implements HelloWorldService {
	
	@Autowired
	private HelloWorldDAO helloWorldDAO;

	@Override
	public String getHelloWorld() {
		return helloWorldDAO.findHelloWorld();
	}

}
