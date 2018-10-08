package com.master.microservices.springBoot.springCloud.restfulwebservices.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="All Details of the User")
@Entity
@Table(name="user")
public class User {
	
	public User() {
	}
	
	@Id
	@GeneratedValue
	private Integer id;	
	
	@OneToMany(mappedBy="user")
	private List<Post> post;
	
	public List<Post> getPost() {
		return post;
	}


	public void setPost(List<Post> post) {
		this.post = post;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	@Size(min=2,message="name should have at least 2 characters")
	@ApiModelProperty(notes="NAME must have at least 2 Characters")
	@Column(name="name")
	private String name;
	
	@Past
	@ApiModelProperty(notes="birth date must be in the PAST")
	@Column(name="birth_date")
	private Date birthDate;

	public Integer getId() {
		return id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User(Integer id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
	
	

}
