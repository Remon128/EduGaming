package swe2.springbootstarter.user;

import javax.transaction.Transactional;

@Transactional
public interface TeacherRepository extends UserBaseRepository<Teacher>{

}
