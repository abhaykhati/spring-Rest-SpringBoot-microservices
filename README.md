### Spring-Rest-SpringBoot-Microservices
This Repository is used to showcase spring Rest-Spring Boot Tools-Microservices- JPA

It includes code for spring boot starter web, jpa, H2 etc.

It has implementation for spring boot tools like Swagger, Internationalization, Postman, Heteoas etc.

### Swagger Documentation URL
Add following dependencies to POM.xml

	<dependency>
	  <groupId>io.springfox</groupId>
	  <artifactId>springfox-swagger2</artifactId>
	  <version>2.4.0</version>
	 </dependency>
	
	<dependency>
	  <groupId>io.springfox</groupId>
	  <artifactId>springfox-swagger-ui</artifactId>
	  <version>2.4.0</version>
	</dependency>


To access SWAGGER API documentation use http://localhost:8080/v2/api-docs

To access VISUAL documentation use      http://localhost:8080/swagger-ui.html 

### Spring Boot Actuator Dependencies and Hal Browser URL

	    <dependency>
          	<groupId>org.springframework.boot</groupId>
          	<artifactId>spring-boot-starter-actuator</artifactId>
	   </dependency>
    
	  <dependency>
          	 <groupId>org.springframework.data</groupId>
           	<artifactId>spring-data-rest-hal-browser</artifactId>                 
	  </dependency>

After adding above dependency Spring will auto-configure the browser and make it available via the default endpoint.

> http://localhost:8080/browser/index.html#/

or use

> http://localhost:8080/  which will redirect the user to above mentioned URL.

### URL Versioning

Following methods are created for URL Versioning in 
### PersonVersioningController.java

### Versioning through URI

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

### Versioning through Request PARAM

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
		return new PersonV2(new Name("Mila", "queen"));
	}

### Versioning through Header PARAM

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
		return new PersonV2(new Name("Mila", "queen"));
	}

### Versioning through Content Negotiation or Accept Header or MIME type 

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

### Basic Security
Added following dependency which will create default password for the application

	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-security</artifactId>
	</dependency>

A password will be generated on console each time you run the application.
Which you need to provide while accessing any URL in POSTMAN with the 
Username : user

### JPA H2 REST URI
Most of the code is added to following files.

src/main/java/com/master/microservices/springBoot/springCloud/restfulwebservices/user/Post.java

src/main/java/com/master/microservices/springBoot/springCloud/restfulwebservices/user/PostRepository.java

src/main/java/com/master/microservices/springBoot/springCloud/restfulwebservices/user/UserJPAResource.java

src/main/java/com/master/microservices/springBoot/springCloud/restfulwebservices/user/UserRepository.java

src/main/resources/data.sql







###### For formatting options of GitHub ReadMe.md file use 
 https://guides.github.com/features/mastering-markdown/
