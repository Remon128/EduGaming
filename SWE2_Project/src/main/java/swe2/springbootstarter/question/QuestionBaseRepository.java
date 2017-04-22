package swe2.springbootstarter.question;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import swe2.springbootstarter.entities.Question;



@NoRepositoryBean
public interface QuestionBaseRepository<T extends Question> extends CrudRepository<T, Long> {

	public List<T> findByGameName(String gameName);
 
}
