package swe2.springbootstarter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
		public void createTeacher(@RequestBody Teacher user  ){
				
			userService.addUser(user);
			
		}
		

		@RequestMapping (method=RequestMethod.POST,value="/get/teacher")
		public Users getTeacher(@RequestBody Teacher teacher ){
				
			return userService.getUser(teacher.getMail());
			
		}
		

		@RequestMapping (method=RequestMethod.POST,value="/create/student")
		public void createStudent(@RequestBody Teacher user  ){
				
			userService.addUser(user);
			
		}
		

		@RequestMapping (method=RequestMethod.POST,value="/get/student")
		public Users getStudent(@RequestBody Student student ){
				
			return userService.getUser(student.getMail());
			
		}


}
