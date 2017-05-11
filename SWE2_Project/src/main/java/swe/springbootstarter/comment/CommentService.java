package swe.springbootstarter.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import swe2.springbootstarter.entities.Comment;

public class CommentService {
	
	@Autowired
	private CommentRepository commentRepo;
	
	public void addComment(Comment comment){
		commentRepo.save(comment);
	}
	
	public List<Comment> getComments(Integer id){
		return commentRepo.findByGame_Id(id);
	}
	
	
}
