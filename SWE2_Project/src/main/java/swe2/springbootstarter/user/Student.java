package swe2.springbootstarter.user;

import javax.persistence.Entity;

@Entity
public class Student extends Users
{
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(String name, String mail, String password) {
		super(name, mail, password);
		// TODO Auto-generated constructor stub
	}

	

	

}
