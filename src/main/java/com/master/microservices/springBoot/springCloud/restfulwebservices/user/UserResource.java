package com.master.microservices.springBoot.springCloud.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.aspectj.weaver.AjAttribute.MethodDeclarationLineNumberAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	@Autowired
	UserDaoService daoService;

	@GetMapping(path = "/users")
	public List<User> getAllUsers() {

		List<User> findAll = daoService.findAll();
		return findAll;
	}

	@GetMapping(path = "/users/{id}")
	/*
	 * commented due to HEATOAS implementation which return Resource<User> 
	 *  public User getSingleUser(@PathVariable int id) {
	 */

	public Resource<User> getSingleUser(@PathVariable int id) {
		User user = daoService.findOne(id);
		if (user == null) {
			throw new UserNotFoundException("id= " + id);
		}

		// HEEATOAS (Hypermedia As The Engine Of Application State) IMPLEMENTION
		// FOLLOWS, added MAVEN dependency POM

		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());

		resource.add(linkTo.withRel("all-users"));

		return resource;
	}

	@PostMapping(path = "/users")
	// this method is used to create a user and get response body back with URI
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = daoService.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping(path = "/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = daoService.deleteById(id);
		if (user == null) {
			throw new UserNotFoundException("User id Not Foound=   " + id);
		}
	}

	@PutMapping(path = "/users/{id}")
	public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable int id) {

		User user1 = daoService.findOne(id);

		if (user1 == null) {
			// if condition to throw HTTP 404 NOT FOUND
			// throw new UserNotFoundException("User id Not Foound= "+id);
			return ResponseEntity.notFound().build();
		}
		user.setId(id);
		// user.setName(user.getName());
		daoService.save(user);
		return ResponseEntity.noContent().build();

	}

}
