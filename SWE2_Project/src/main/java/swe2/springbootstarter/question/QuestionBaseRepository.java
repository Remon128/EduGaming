package swe2.springbootstarter.question;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;



@NoRepositoryBean
public interface QuestionBaseRepository<T extends Question> extends CrudRepository<T, String> {

	public List<T> findByGameName(String gameName);
 
}
