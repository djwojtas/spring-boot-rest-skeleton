package es.jemerino.springboot.rest.skeleton.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.jemerino.springboot.rest.skeleton.constant.Mapping;
import es.jemerino.springboot.rest.skeleton.constant.Produces;
import es.jemerino.springboot.rest.skeleton.service.HelloWorldService;

@RestController
@RequestMapping(Mapping.API_VERSION_1)
public class HelloWorldController {

	@Autowired
	private HelloWorldService helloWorldService;
	
	@RequestMapping(value = Mapping.GET_HELLO_WORLD, 
					method = RequestMethod.GET,
					produces = Produces.TEXT_PLAIN)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody 
	public String getHelloWorld() {
		return helloWorldService.getHelloWorld();
	}
	
}
