package swe2.springbootstarter.user;

import javax.transaction.Transactional;

import swe2.springbootstarter.entities.Teacher;

@Transactional
public interface TeacherRepository extends UserBaseRepository<Teacher>{

}
