package swe2.springbootstarter.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UserBaseRepository<T extends Users> 
extends CrudRepository<T, String> {

  
 
}
