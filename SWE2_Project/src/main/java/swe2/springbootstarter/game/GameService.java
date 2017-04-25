package swe2.springbootstarter.game;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import swe2.springbootstarter.entities.Game;

@Service
public class GameService {
	
	@Autowired
	private GameRepository gameRepository;
	
	
	public List<Game> getAllGames(String courseName){
		
		ArrayList<Game> games = new ArrayList<>();
		gameRepository.findByCourseName(courseName).forEach(games::add);
		return games;
	}
	
	public Game getGame(Integer name){
		return gameRepository.findOne(name);
	}
	
	public void addGame(Game game){
		gameRepository.save(game);
	}

//	public void updateGame(Game game) {
//		gameRepository.save(game);	
//	}
//
//	public void deleteGame(Integer name) {
//		gameRepository.delete(name);	
//		
//	}
	public boolean isGameExist(Integer game) {
        return gameRepository.exists(game);
	}
	
	
}
