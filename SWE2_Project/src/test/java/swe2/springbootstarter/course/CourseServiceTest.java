package swe2.springbootstarter.course;

import org.testng.annotations.Test;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import swe2.springbootstarter.entities.Course;
import swe2.springbootstarter.entities.Teacher;



public class CourseServiceTest {
	  @DataProvider(name = "getcourse")
	  public static Object[][] getCourseDP(){
		 Course c=new Course();
		 Teacher a=new Teacher("Ahmed","AhmedAhmed@gmail.com","123456789");
		 CourseService cs=new CourseService();
		 c.setDescription("This is a Description");
		 c.setId(123);
		 c.setName("Course A");
		 c.setTeacher(a);
		 
		 Course c2=new Course();	c2.setDescription("Desc"); c2.setId(123); c2.setName("Course C");
		 c2.setTeacher(a);
		 cs.addCourse(c);
		 cs.addCourse(c2);
		 ArrayList<Course> x=new ArrayList<Course>();
		 x.add(c);
		 x.add(c2);
		 ArrayList<Course> y=new ArrayList<Course>();
		  return new Object[][] {{x,a.getMail()},{y,"aaa@gmail.com"}};
	  }
		
		
		
	  @Test(dataProvider = "getcourse")
	  public void getCourseByTeacherMail(ArrayList<Course> result, String TeacherMail ) {
		  CourseService cs=new CourseService();
		  AssertJUnit.assertEquals(result, cs.getCoursesByTeacherMail(TeacherMail));
	  }

	}
