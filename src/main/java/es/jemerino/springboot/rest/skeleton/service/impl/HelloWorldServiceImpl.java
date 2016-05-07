package es.jemerino.springboot.rest.skeleton.service.impl;

import org.springframework.stereotype.Service;

import es.jemerino.springboot.rest.skeleton.service.HelloWorldService;

@Service
public class HelloWorldServiceImpl implements HelloWorldService {

	@Override
	public String getHelloWorld() {
		return "Hello World!";
	}

}
