package swe2.springbootstarter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api")
public class UserController {
	
	
		@Autowired
		UserService userService;
	
		@RequestMapping (method=RequestMethod.POST,value="/create/teacher")
		public void createTeacher(@RequestBody Teacher teacher  ){
				
			userService.addUser(teacher);
			
		}
		
		@RequestMapping (method=RequestMethod.PUT,value="/update/teacher")
		public void updateTeacher(@RequestBody Teacher teacher  ){
				
			userService.addUser(teacher);
			
		}
		
		@RequestMapping (method=RequestMethod.DELETE,value="/delete/teacher")
		public void deleteTeacher(@RequestBody Teacher teacher  ){
				
			userService.deleteUser(teacher.getMail());
			
		}
		

		@RequestMapping (method=RequestMethod.POST,value="/get/teacher")
		public Users getTeacher(@RequestBody Teacher teacher ){
				
			return userService.getUser(teacher.getMail());
			
		}
		
		/////////////////////////////////////////////////////
		@RequestMapping (method=RequestMethod.POST,value="/create/student")
		public void createStudent(@RequestBody Student student  ){
				
			userService.addUser(student);
			
		}
		
		@RequestMapping (method=RequestMethod.PUT,value="/update/student")
		public void updateStudent(@RequestBody Student student  ){
				
			userService.addUser(student);
			
		}

		@RequestMapping (method=RequestMethod.POST,value="/get/student")
		public Users getStudent(@RequestBody Student student ){
				
			return userService.getUser(student.getMail());
			
		}
		@RequestMapping (method=RequestMethod.DELETE,value="/delete/student")
		public void deleteStudent(@RequestBody Student student  ){
				
			userService.deleteUser(student.getMail());
			
		}


}
