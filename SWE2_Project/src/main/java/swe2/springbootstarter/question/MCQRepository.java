package swe2.springbootstarter.question;

import javax.transaction.Transactional;

import swe2.springbootstarter.entities.MCQ;

@Transactional
public interface MCQRepository extends QuestionBaseRepository<MCQ>{

}
