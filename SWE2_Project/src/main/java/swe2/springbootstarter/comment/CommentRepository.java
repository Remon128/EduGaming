package swe2.springbootstarter.comment;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import swe2.springbootstarter.entities.Comment;


public interface CommentRepository extends CrudRepository<Comment, Integer> {
	
	public List<Comment> findByGameId(Integer id);
}
