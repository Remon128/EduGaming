package swe2.springbootstarter.course;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import swe2.springbootstarter.user.Teacher;
import swe2.springbootstarter.user.UserService;
import swe2.springbootstarter.user.Users;
import swe2.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class CourseController {
	
	public static final Logger logger = LoggerFactory.getLogger(CourseController.class);
	
	@Autowired
	private CourseService courseService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/course/getByMail", method = RequestMethod.POST)
    public ResponseEntity<List<Course>> listAllCourses(@RequestBody Users user) {
        List<Course> courses = courseService.getAllCourses(user.getMail());
        if (courses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
    }
	///////////////////////////////////////////////
//	@RequestMapping("/course/{name}")
//	public ResponseEntity<?> getCourse(@PathVariable String name){
//		logger.info("getting Course with name {}", name);
//		Course course = courseService.getCourse(name);
//		if(course == null){
//			logger.error("Course with name {} not found.", name);
//			return new ResponseEntity<>(new CustomErrorType("Course with name " + name 
//                    + " not found"), HttpStatus.NOT_FOUND);
//		}
//		 return new ResponseEntity<Course>(course, HttpStatus.OK);
//	}
	////////////////////////////////////////////////
	 @RequestMapping(value = "/create/course", method = RequestMethod.POST)
	    public ResponseEntity<?> createCourse(@RequestBody Course course, @RequestBody Teacher teacher , UriComponentsBuilder ucBuilder) {
	        logger.info("Creating Course : {}", course);
	 
	        if (courseService.isCourseExist(course)) {
	            logger.error("Unable to create. A Course with name {} already exist", course.getName());
	            return new ResponseEntity<>(new CustomErrorType("Unable to create. A Course with name " + 
	            course.getName() + " already exist."),HttpStatus.CONFLICT);
	        }
	        course.setTeacher((Teacher) userService.getUser(teacher.getMail()));
	        courseService.addCourse(course);
	 
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/api/createCourse").buildAndExpand(course.getName()).toUri());
	        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	    }
	/////////////////////////////////////////////////
	 @RequestMapping(value = "/update/course", method = RequestMethod.PUT)
	    public ResponseEntity<?> updateCourse(@RequestBody Teacher teacher, @RequestBody Course course) {
	        logger.info("Updating Course with id {}", teacher.getMail());
	        
	        course.setTeacher((Teacher)userService.getUser(teacher.getMail()));
	        courseService.updateCourse(course);
	        return new ResponseEntity<Course>(course, HttpStatus.OK);
	    }
	///////////////////////////////////////////////////
	 @RequestMapping(value = "/delete/course/{name}", method = RequestMethod.DELETE)
	    public ResponseEntity<?> deleteCourse(@PathVariable("name") String name) {
	        logger.info("Fetching & Deleting Course with id {}", name);
	 
	        Course course = courseService.getCourse(name);
	        if (course == null) {
	            logger.error("Unable to delete. Course with id {} not found.", name);
	            return new ResponseEntity<>(new CustomErrorType("Unable to delete. Course with id " + name + " not found."),
	                    HttpStatus.NOT_FOUND);
	        }
	        courseService.deleteCourse(name);
	        return new ResponseEntity<Course>(HttpStatus.NO_CONTENT);
	    }
}
