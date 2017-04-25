package swe2.springbootstarter.question;

import javax.transaction.Transactional;

import swe2.springbootstarter.entities.Question;

@Transactional
public interface QuestionRepository extends QuestionBaseRepository<Question> {
	
	
}
