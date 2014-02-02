package org.iwt2.crushthecady.presenter;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.hamcrest.CoreMatchers.*;

import org.iwt2.crushthecady.CrushTheCandy;
import org.iwt2.crushthecady.model.CandyBullet;
import org.iwt2.crushthecady.model.CandyEnemies;
import org.iwt2.crushthecady.model.EnemyColumn;
import org.iwt2.crushthecady.model.Room;
import org.iwt2.crushthecady.presenter.StartGameDirector;
import org.iwt2.crushthecady.view.Constants;
import org.junit.Before;
import org.junit.Test;

import unit.factory.FakeTextureDict;
import unit.factory.RoomFactory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;


/**
 * feature Game Start
	As a player
	I want to start a game
	to have fun
	
 * @author Javier
 *
 */
public class TestStartGameFeature {

	private CrushTheCandy game;
	private Room room;

	@Before
	public void setUp() throws Exception {
		this.game = new CrushTheCandy(1);
		StartGameDirectorWithoutTextures startGame = new StartGameDirectorWithoutTextures();
		this.room = RoomFactory.createRoom();
		game.setStartDageDirector(startGame);
		game.setRoom(room);
	}

	
	/**
	 * 	scenario new game
		given A new game of 750, 500
		when starting a game
		then I have a column with 10 random candies from 0, 0 to 75, 500
		
		TODO:
		 1. Create the start game presenter
		 2. Create the room class
		 3. Create the candy class that extends actor
		 4. Load the list of candies
		 5. Create a factory to creta the initial object of CandyBullet
		 6. Crete the class of candy bullets
	 */
	@Test
	public void testCreateCandyBullet() {
		
		game.create();
		
		
		CandyBullet candyBullet = room.getCandyBullet();
		int numOfCandyBullets = Constants.HEIGHT / Constants.CANDYHEIDHT;
		assertThat(candyBullet.candies(), is(numOfCandyBullets) );
	}
	
	/**
	 * 	scenario new game
		given A new game of 750, 500
		when starting a game
		then I have three rows of random candies from  125, 500 to 675, 350
		 and each row has 9 candies 
	
		1) Factory to create an initial enemycandies (using te candy factory)
		
		
	 */
	@Test
	public void testCreateCandyEnemies() {
		game.create();
		
		
		CandyEnemies candyEnemies = room.getCandyEnemies();
		for(EnemyColumn ec: candyEnemies.columns()) {
			assertThat(ec.candies(), is(3) );
		}
	}
	
	//-----------------------------------------------------------
	
	class StartGameDirectorWithoutTextures extends StartGameDirector {
		
		@Override
		void loadTextures() {
			this.dict = new FakeTextureDict();
			System.out.println("Creating fake texture.");
		}
	}

}