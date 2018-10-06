package com.master.microservices.springBoot.springCloud.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
 private static int userCount=4;
	
 private static	List<User> userList=new ArrayList<>(Arrays.asList
						(new User(1, "adam", new Date()),
						 new User(2, "venkat", new Date()),
						 new User(3, "sally", new Date()),
						 new User(4, "viccky", new Date())
						 ));

 
  public List<User> findAll(){
	return  userList;
  }
  
  public User save(User user) {
	  if(user.getId() ==null) {
		  user.setId(++userCount);
	  }
	  userList.add(user);
	 return user; 
  }
  
  public User findOne(int id) {
	  
	  for(User user:userList) {
		  if(user.getId() ==id) {
			  return user;
		  }
	  }
	  return null;
  }
  
 public User deleteById(int id) {
	   Iterator<User> iterator = userList.iterator();
	   while(iterator.hasNext()) {
		   User user = iterator.next(); 
		   if(user.getId()==id) {
			   iterator.remove();
			   return user;
		   }
	   }
	  
	  return null;
  }
  
  
}
