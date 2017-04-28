package swe2.springbootstarter.game;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import swe2.springbootstarter.entities.Game;


public interface GameRepository extends CrudRepository<Game, Integer> {

	public List<Game> findByCourseId(Integer courseName);

}
