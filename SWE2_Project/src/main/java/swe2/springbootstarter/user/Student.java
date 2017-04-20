package swe2.springbootstarter.user;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import swe2.springbootstarter.course.Course;

@Entity
public class Student extends Users
{
	
	@ManyToOne
	@JoinColumn
	Course course;
	
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
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
