package swe2.springbootstarter.course;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
	
	@Autowired
	private CourseRepository courseRepository;
	
	
	public List<Course> getAllCourses(String teacherMail){
		
		ArrayList<Course> courses = new ArrayList<>();
		courseRepository.findByTeacherMail(teacherMail).forEach(courses::add);
		return courses;
	}
	
	public Course getCourse(String name){
		return courseRepository.findOne(name);
	}
	
	public void addCourse(Course course){
		courseRepository.save(course);
	}

	public void updateCourse(Course course) {
		courseRepository.save(course);	
	}

	public void deleteCourse(String name) {
		courseRepository.delete(name);	
		
	}
	public boolean isCourseExist(Course course) {
        return courseRepository.exists(course.getName());
	}
	
	
	
}
