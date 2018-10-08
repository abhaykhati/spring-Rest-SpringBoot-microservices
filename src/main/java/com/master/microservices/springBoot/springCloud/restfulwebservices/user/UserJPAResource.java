package com.master.microservices.springBoot.springCloud.restfulwebservices.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@Transactional
public class UserJPAResource {

	/*
	 * @Autowired UserDaoService daoService;
	 */

	@Autowired
	private UserRepository userRepository;
	private PostRepository postRepository;

	// the following code is for H2 DATABASE operations

	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/jpa/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);

		if (!user.isPresent())
			throw new UserNotFoundException("id-" + id);

		// "all-users", SERVER_PATH + "/users"
		// retrieveAllUsers
		Resource<User> resource = new Resource<User>(user.get());

		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());

		resource.add(linkTo.withRel("all-users"));

		// HATEOAS

		return resource;
	}

	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}
	//
	// input - details of user
	// output - CREATED & Return the created URI

	// HATEOAS

	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}

	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrieveAllUsers(@PathVariable int id) {
		Optional<User> userOptional = userRepository.findById(id);

		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}

		return userOptional.get().getPost();
	}

	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {

		Optional<User> userOptional = userRepository.findById(id);

		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}

		User user = userOptional.get();

		post.setUser(user);

		postRepository.save(post);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}

	/*
	 * commented due to non H2 DATABASE OPERATION (A static list operations)
	 * 
	 * @GetMapping(path = "/jpa/users") public List<User> getAllUsers() {
	 * 
	 * return userRepository.findAll(); }
	 * 
	 * @GetMapping(path = "/jpa/users/{id}/post")
	 * 
	 * commented due to HEATOAS implementation which return Resource<User> public
	 * User getSingleUser(@PathVariable int id) {
	 * 
	 * 
	 * public Resource<User> getSingleUser(@PathVariable int id) { Optional<User>
	 * user = userRepository.findById(id); if (!user.isPresent()) { throw new
	 * UserNotFoundException("id= " + id); }
	 * 
	 * // HEEATOAS (Hypermedia As The Engine Of Application State) IMPLEMENTION //
	 * FOLLOWS, added MAVEN dependency POM
	 * 
	 * Resource<User> resource = new Resource<User>(user.get());
	 * ControllerLinkBuilder linkTo =
	 * linkTo(methodOn(this.getClass()).getAllUsers());
	 * 
	 * resource.add(linkTo.withRel("all-users"));
	 * 
	 * return resource; }
	 * 
	 * @PostMapping(path = "/jpa/users") // this method is used to create a user and
	 * get response body back with URI public ResponseEntity<Object>
	 * createUser(@Valid @RequestBody User user) { User savedUser =
	 * userRepository.save(user);
	 * 
	 * URI location =
	 * ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand
	 * (savedUser.getId()) .toUri();
	 * 
	 * return ResponseEntity.created(location).build(); }
	 * 
	 * @DeleteMapping(path = "/jpa/users/{id}") public void deleteUser(@PathVariable
	 * int id) { userRepository.deleteById(id);
	 * 
	 * if (user == null) { throw new UserNotFoundException("User id Not Foound=   "
	 * + id); }
	 * 
	 * }
	 * 
	 * @PutMapping(path = "/jpa/users/{id}") public ResponseEntity<Object>
	 * updateUser(@RequestBody User user, @PathVariable int id) {
	 * 
	 * Optional<User> user1 = userRepository.findById(id);
	 * 
	 * 
	 * if (user1 == null) { // if condition to throw HTTP 404 NOT FOUND // throw new
	 * UserNotFoundException("User id Not Foound= "+id); return
	 * ResponseEntity.notFound().build(); }
	 * 
	 * user.setId(id); // user.setName(user.getName()); userRepository.save(user);
	 * return ResponseEntity.noContent().build();
	 * 
	 * }
	 */

}
