package swe2.springbootstarter.game;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, String> {

	public List<Game> findByCourseName(String courseName);

}
