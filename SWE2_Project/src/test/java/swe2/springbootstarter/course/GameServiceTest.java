package swe2.springbootstarter.course;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import swe2.springbootstarter.entities.Course;
import swe2.springbootstarter.entities.Game;
import swe2.springbootstarter.game.GameService;

public class GameServiceTest {

	@DataProvider (name = "get All Games")
	public static Object[][] getAllGameDP(){
		Game g=new Game(); Game g2=new Game(); Game g3=new Game();
		g.setId(123);	 g2.setId(124); g3.setId(125);
		g.setName("Game A"); g2.setName("Game B"); g3.setName("Game C");
		Course x=new Course();
		x.setId(1);
		x.setName("Math");
		g.setCourse(x);		g2.setCourse(x);
		GameService gs =new GameService();
		gs.addGame(g);	gs.addGame(g2);		gs.addGame(g3);
		ArrayList<Game> Alg=new ArrayList<Game>();
		Alg.add(g);	Alg.add(g2);
		ArrayList<Game> Alg2=new ArrayList<Game>();
		return new Object[][] {{Alg,x.getId()},{Alg2,2}};
	}
  @Test (dataProvider = "get All Games")
  public void getAllGames(ArrayList<Game> g , Integer CourseID) {
	  GameService gs=new GameService();
	  Assert.assertEquals(g, gs.getAllGames(CourseID));
  }
	@DataProvider (name = "get Game")
	public static Object[][] getGameDP(){
		Game g=new Game(); Game g2=new Game();
		g.setId(123);	 g2.setId(124); 
		g.setName("Game A"); g2.setName("Game B");
		GameService gs =new GameService();
		gs.addGame(g);	gs.addGame(g2);
		return new Object[][] {{g,g.getId()},{g2,g2.getId()},{null,125}};
	}

  @Test (dataProvider = "get Game")
  public void getGame(Game g,Integer ID) {
	  GameService gs=new GameService();
	  Assert.assertEquals(g, gs.getGame(ID));
  }
  @DataProvider (name = "Is Game Exist")
	public static Object[][] IsGameExistDP(){
		Game g=new Game(); Game g2=new Game();
		g.setId(123);	 g2.setId(124); 
		g.setName("Game A"); g2.setName("Game B");
		GameService gs =new GameService();
		gs.addGame(g);	gs.addGame(g2);
		return new Object[][] {{true,g.getId()},{true,g2.getId()},{false,125}};
	}
  
  @Test (dataProvider = "Is Game Exist")
  public void isGameExist(boolean result , Integer Id) {
	  GameService gs=new GameService();
	  Assert.assertEquals(result, gs.isGameExist(Id));
	  
  }
}
