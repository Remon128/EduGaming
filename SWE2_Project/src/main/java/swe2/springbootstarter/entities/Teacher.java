package swe2.springbootstarter.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;



@Entity
public class Teacher extends Users
{
	@OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
	Set<Course> courses;
    
	@ManyToMany(cascade = CascadeType.ALL)				
	@JoinTable(name = "teacher_game", joinColumns = @JoinColumn(name = "teacher_mail", referencedColumnName = "mail"), inverseJoinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id"))     
	private Set<Game> games;
	

//	public Set<Course> getCourses() {
//		return courses;
//	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
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
