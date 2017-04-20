package swe2.springbootstarter.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import swe2.util.CustomErrorType;



@RestController
@RequestMapping("/api")
public class UserController {
	
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
		
		@Autowired
		UserService userService;
	
		@RequestMapping (method=RequestMethod.POST,value="/create/teacher")

		public ResponseEntity<?> createTeacher(@RequestBody Teacher teacher , UriComponentsBuilder ucBuilder ){
			logger.info("Creating Teacher : {}", teacher);
			
			if (userService.isUserExist(teacher)) {
	            logger.error("Unable to register user already exist : {}" , teacher.getName());
	            return new ResponseEntity<>(new CustomErrorType("Unable to create.user with name " + 
	            	teacher.getName() + " already exist."),HttpStatus.CONFLICT);
	        }
			userService.addUser(teacher);
			
			 HttpHeaders headers = new HttpHeaders();
		     headers.setLocation(ucBuilder.path("/api/create/teacher").buildAndExpand(teacher.getName()).toUri());
		     return new ResponseEntity<String>(headers, HttpStatus.CREATED);
		}
		/////////////////////////////////////////////////////////////
		@RequestMapping (method=RequestMethod.PUT,value="/update/teacher")
		public void updateTeacher(@RequestBody Teacher teacher  ){
				
			userService.addUser(teacher);
			
		}
		///////////////////////////////////////////////////////////////
		@RequestMapping (method=RequestMethod.DELETE,value="/delete/teacher")
		public void deleteTeacher(@RequestBody Teacher teacher  ){
				
			userService.deleteUser(teacher.getMail());
			
		}
		
		///////////////////////////////////////////////////////////////
		@RequestMapping (method=RequestMethod.POST,value="/get/teacher")
		public ResponseEntity<?> getTeacher(@RequestBody Teacher teacher ){
			logger.info("Getting Teacher : {}", teacher);
			if(!userService.isUserExist(teacher)){
				logger.error("Teacher Does not exist : {}" , teacher.getName());
				 return new ResponseEntity<>(new CustomErrorType("Teacher does not exist  " + 
			            	teacher.getName() + " already exist."), HttpStatus.NO_CONTENT);
			}
			else{
			return new ResponseEntity<Users>(teacher, HttpStatus.CREATED);
			}
		}

		
		/////////////////////////////////////////////////////
		@RequestMapping (method=RequestMethod.POST,value="/create/student")
				public ResponseEntity<?> createStudent(@RequestBody Student student , UriComponentsBuilder ucBuilder ){
					logger.info("Creating Student : {}", student);
					
					if (userService.isUserExist(student)) {
			            logger.error("Unable to register user already exist : {}" , student.getName());
			            return new ResponseEntity<>(new CustomErrorType("Unable to create.user with name " + 
			            		student.getName() + " already exist."),HttpStatus.CONFLICT);
			        }
					userService.addUser(student);
					
					 HttpHeaders headers = new HttpHeaders();
				     headers.setLocation(ucBuilder.path("/api/create/student").buildAndExpand(student.getName()).toUri());
				     return new ResponseEntity<String>(headers, HttpStatus.CREATED);

		}
		//////////////////////////////////////////////////////////
		@RequestMapping (method=RequestMethod.PUT,value="/update/student")
		public void updateStudent(@RequestBody Student student  ){
				
			userService.addUser(student);
			
		}
		///////////////////////////////////////////////////////////
		@RequestMapping (method=RequestMethod.POST,value="/get/student")
		public ResponseEntity<?> getStudent(@RequestBody Student student ){
			logger.info("Getting Student : {}", student);
			if(!userService.isUserExist(student)){
				logger.error("Teacher Does not exist : {}" , student.getName());
				 return new ResponseEntity<>(new CustomErrorType("Student does not exist  " + 
						 student.getName() + " already exist."), HttpStatus.NO_CONTENT);
			}
			else{
			return new ResponseEntity<Users>(student, HttpStatus.CREATED);
			}
		}
		///////////////////////////////////////////////////////////
		@RequestMapping (method=RequestMethod.DELETE,value="/delete/student")
		public void deleteStudent(@RequestBody Student student  ){
				
			userService.deleteUser(student.getMail());
			
		}



}
