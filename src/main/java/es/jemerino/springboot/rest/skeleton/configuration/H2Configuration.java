package es.jemerino.springboot.rest.skeleton.configuration;

import org.h2.server.web.WebServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.jemerino.springboot.rest.skeleton.constant.Mapping;

@Configuration
public class H2Configuration {
	
	@Bean
	public ServletRegistrationBean h2ServletRegistration() {
		WebServlet webServlet = new WebServlet();
		ServletRegistrationBean bean = new ServletRegistrationBean(webServlet);
		bean.addUrlMappings(Mapping.H2_CONSOLE);
		return bean;
	}

}
