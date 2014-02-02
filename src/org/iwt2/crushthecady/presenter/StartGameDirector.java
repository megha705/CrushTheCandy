package org.iwt2.crushthecady.presenter;

import org.iwt2.crushthecady.model.CandyBullet;
import org.iwt2.crushthecady.model.CandyBulletFactory;
import org.iwt2.crushthecady.model.CandyEnemiesFactory;
import org.iwt2.crushthecady.model.Room;
import org.iwt2.crushthecady.view.Constants;
import org.iwt2.crushthecady.view.TextureDict;
import org.iwt2.crushthecady.view.TextureLoader;

public class StartGameDirector {

	TextureDict dict;
	private CandyFactory cf;

	public void create(Room room) {
		this.loadTextures();
		this.createCandyBullet(room);
		this.createCandyEnemies(room);
	}
	
	private void createCandyEnemies(Room room) {
		CandyEnemiesFactory cef = new CandyEnemiesFactory(this.cf);
		
			
		int columns = (Constants.WIDTH - Constants.STARTENEMIESX) / Constants.CANDYWIDHT;
		
		room.setCandyEnemies(cef.create(columns));
		
	}

	void loadTextures() {
		TextureLoader loader = new TextureLoader();
		this.dict = loader.getTextureDictionary();
	}
	
	void createCandyBullet(Room room) {
		CandyBulletFactory factory = candyBulletFactory();
		int numOfCandies = Constants.HEIGHT / Constants.CANDYHEIDHT;
		CandyBullet cb = factory.create(numOfCandies);
		//cb.setXY(0f, 0f);
		room.setCandyBullet(cb);
	}

	private CandyBulletFactory candyBulletFactory() {
		this.cf = new CandyFactory(this.dict);
		CandyBulletFactory factory = new CandyBulletFactory(cf);
		//factory.setColors(Constants.COLORS);
		return factory;
	}

}