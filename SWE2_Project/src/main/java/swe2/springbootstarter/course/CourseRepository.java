package swe2.springbootstarter.course;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import swe2.springbootstarter.entities.Course;

public interface CourseRepository extends CrudRepository<Course, Integer>
{
	
		public List<Course> findByTeacher_Mail(String teacherMail);
		public List<Course> findByStudents_Mail(String studentMail);
}
