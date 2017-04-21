package swe2.springbootstarter.user;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import swe2.springbootstarter.course.Course;

@Entity
public class Student extends Users
{
	
	@ManyToMany(mappedBy = "students")
	Set<Course> courses;
	
	

	public Set<Course> getCourse() {
		return courses;
	}

	public void setCourse(Set<Course> course) {
		this.courses = course;
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(String name, String mail, String password) {
		super(name, mail, password);
		// TODO Auto-generated constructor stub
	}

	

	

}
