package swe2.springbootstarter.question;

import javax.transaction.Transactional;

@Transactional
public interface QuestionRepository extends QuestionBaseRepository<Question> {
	
	
}
