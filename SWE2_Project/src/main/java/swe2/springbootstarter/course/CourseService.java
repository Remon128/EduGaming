package swe2.springbootstarter.course;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import swe2.springbootstarter.entities.Course;

@Service
public class CourseService {
	
	@Autowired
	private CourseRepository courseRepository;
	
	
	public List<Course> getCoursesByTeacherMail(String teacherMail){
		
		ArrayList<Course> courses = new ArrayList<>();
		courseRepository.findByTeacher_Mail(teacherMail).forEach(courses::add);
		return courses;
	}
	public List<Course> getAllCourses(){
		
		ArrayList<Course> courses = new ArrayList<>();
		courseRepository.findAll().forEach(courses::add);
		return courses;
	}
	
	public List<Course> getCoursesByStudentMail(String studentMail){
		
		ArrayList<Course> courses = new ArrayList<>();
		courseRepository.findByStudents_Mail(studentMail).forEach(courses::add);
		return courses;
	}
	
	public Course getCourse(Integer id){
		return courseRepository.findOne(id);
	}
	
	public void addCourse(Course course){
		courseRepository.save(course);
	}

	public void updateCourse(Course course) {
		courseRepository.save(course);	
	}
//
//	public void deleteCourse(String name) {
//		courseRepository.delete(name);	
//		
//	}
	public boolean isCourseExist(Integer course) {
        return courseRepository.exists(course);
	}
	
	
	
}
