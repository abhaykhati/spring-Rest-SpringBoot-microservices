package com.master.microservices.springBoot.springCloud.restfulwebservices.helloworld;

public class HelloWorldBean  {

	
	private String message;
	
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 */
	public HelloWorldBean(String message) {
		
		this.message=message; 
	}

	@Override
	public String toString() {
		return "HelloWorldBean [message=%s" + message + "]";
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
