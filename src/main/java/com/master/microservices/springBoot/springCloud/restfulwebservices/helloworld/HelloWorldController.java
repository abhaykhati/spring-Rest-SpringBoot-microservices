package com.master.microservices.springBoot.springCloud.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messagesource;
	
	//@RequestMapping(method=RequestMethod.GET, path="/hello-world" )
	// or
	@GetMapping(path="/hello-world" )
	public String helloWorld() {
		
		return "Hello World  From Abhay";
	}
	
	@GetMapping(path="/hello-world-bean" )
	public HelloWorldBean helloWorldBean() {
		
		return new HelloWorldBean("Hello World Bean From Abhay");
	}
	
	
	// hello-world/path-variable/{in28minutes} 
	
	@GetMapping(path="/hello-world/path-variable/{name}" )
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		
		return new HelloWorldBean(String.format("Hello World %s", name));
	}
	
	@GetMapping(path="/hello-world-internationalized")
	public String helloWorldInternationalized(@RequestHeader(name="Accept-Language", required=false) Locale locale) {
		
		return messagesource.getMessage("good.morning.message",null,locale);
		
	}
}
