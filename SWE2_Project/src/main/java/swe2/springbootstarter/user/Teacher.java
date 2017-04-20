package swe2.springbootstarter.user;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import swe2.springbootstarter.course.Course;



@Entity
public class Teacher extends Users
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

	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Teacher(String name, String mail, String password) {
		super(name, mail, password);
		// TODO Auto-generated constructor stub
	}

	
	
	
   

	
    
    


	
	
}
