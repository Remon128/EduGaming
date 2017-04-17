package swe2.springbootstarter.user;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;

import swe2.springbootstarter.course.Course;

@Entity
@Inheritance
public abstract class Users {
	
	@Id
	private String mail;
	private String name;
	private String password;
	
	
	@OneToMany
	private Set<Course> courses ;
	
	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Users(String mail, String name, String password) {
		super();
		this.mail = mail;
		this.name = name;
		this.password = password;
	}
	
	

}
