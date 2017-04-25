package swe2.springbootstarter.game;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import swe2.springbootstarter.entities.Game;
<<<<<<< HEAD

public interface GameRepository extends CrudRepository<Game, Integer> {
=======
>>>>>>> f1c1b50b415379a691965e63f687a50f161186ed

public interface GameRepository extends CrudRepository<Game, Integer> {

	public List<Game> findByCourseId(Integer courseName);

}
