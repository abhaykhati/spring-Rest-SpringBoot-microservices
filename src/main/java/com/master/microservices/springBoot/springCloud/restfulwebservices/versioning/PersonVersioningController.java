package com.master.microservices.springBoot.springCloud.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;

public class PersonVersioningController {

	@GetMapping(path = "v1/person")
	// it is demo of URI version
	public PersonV1 personV1() {
		return new PersonV1("Richard king");
	}

	@GetMapping(path = "v2/person")
	// it is demo of URI version
	public PersonV2 personV2() {
		return new PersonV2(new Name("Kila", "queen"));
	}
	
	//====================================================

	@GetMapping(path = "person/param", params = "version=1")
	// while giving URL in postman use it like
	// http://localhost:8080/person/param?version=1
	// it is demo of request PARAM version
	public PersonV1 paramV1() {
		return new PersonV1("Richard king");
	}

	@GetMapping(path = "person/param", params = "version=2")
	// while giving URL in postman use it like
	// http://localhost:8080/person/param?version=2
	// it is demo of request PARAM version
	public PersonV2 paramV2() {
		return new PersonV2(new Name("Kila", "queen"));
	}
	
	//====================================================

	@GetMapping(path = "person/header", headers = "X-API-VERSION=1")
	// while giving URL in postman use it like http://localhost:8080/person/header
	// then go to header part of postman below the URL
	// select key as X-API-VERSION and value as 1
	// it is demo of header version PARAM
	public PersonV1 headerV1() {
		return new PersonV1("Richard king");
	}

	@GetMapping(path = "person/header", headers = "X-API-VERSION=2")
	// while giving URL in postman use it like http://localhost:8080/person/header
	// then go to header part of postman below the URL
	// select key as X-API-VERSION and value as 2
	// it is demo of header version PARAM
	public PersonV2 headerV2() {
		return new PersonV2(new Name("Kila", "queen"));
	}

	//==================================================== 
	
	@GetMapping(path = "person/produces", produces = "application/vnd.company.app-v1+json")
	// while giving URL in postman use it like http://localhost:8080/person/produces
	// then go to header part of postman below the URL
	// select key as Accept and value as application/vnd.company.app-v1+json
	// this produces type is called as content Negotiation or Accept HEADER
	// Versioning
	// also called as MIME type version as well
	public PersonV1 producesV1() {
		return new PersonV1("Richard king");
	}

	@GetMapping(path = "person/produces", produces = "application/vnd.company.app-v2+json")
	// while giving URL in postman use it like http://localhost:8080/person/produces
	// then go to header part of postman below the URL
	// select key as Accept and value as application/vnd.company.app-v2+json
	// this produces type is called as content Negotiation or Accept HEADER
	// Versioning
	// also called as MIME type version as well
	public PersonV2 producesV2() {
		return new PersonV2(new Name("stella", "queen"));
	}

}
