package user_stud_teach.user;

import org.springframework.data.repository.CrudRepository;

public interface user_Repositry extends CrudRepository<user,String>
{

   public <S> S save(user userobj);	
  
	
}
