package swe2.springbootstarter.question;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question, String> {

	public List<Question> findByGameName(String gameName);

}
