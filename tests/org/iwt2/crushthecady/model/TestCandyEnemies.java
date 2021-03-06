package org.iwt2.crushthecady.model;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.iwt2.crushthecady.model.Candy;
import org.iwt2.crushthecady.model.CandyColumn;
import org.iwt2.crushthecady.model.CandyEnemies;
import org.iwt2.crushthecady.view.Constants;

import org.junit.Before;
import org.junit.Test;

import unit.factory.Candies;
import unit.factory.CandyFactoryFactory;

public class TestCandyEnemies {

	private CandyEnemies ce;

	@Before
	public void setUp() throws Exception {
		this.ce = new CandyEnemies(8);
		this.ce.setCandyFactory(new CandyFactoryFactory().createCandyFactory());
	}

	@Test
	public void whenCreating_ItHas_8_EnemyColumns() {
		
		
		assertThat(ce.columns().size(), is(8));
		/*
		for (int i = 0; i < ce.columns().size(); i++) {
			assertTrue( (ce.columns().get(i) instanceof EnemyColumn ) );
		}
		*/
	}
	
	@Test
	public void addingOneCandy() {
		this.ce.addCandy(0, Candies.red());
		
		assertThat(ce.candies(0), is(1) );
		assertThat(ce.candies(1), is(0) );
	}
	
	@Test
	public void addOneRowOfCandies() {
		this.ce.addOneRow();
		
		for (CandyColumn ec: this.ce.columns()) {
			assertThat(ec.candies(), is(1));
		}
		CandyColumn ce = this.ce.columns().get(0);
		assertThat(ce.candies.get(0).getY(), is(500f));
		
		
		
		this.ce.addOneRow();
		
		for (CandyColumn ec: this.ce.columns()) {
			assertThat(ec.candies(), is(2));
			ec.act(10f);
		}
		
		
		//
		assertThat(ce.candies.get(0).getY(), is(450f));
		assertThat(ce.candies.get(1).getY(), is(500f));

	}
	
	@Test
	public void testFirstCandyInColumn() {
		this.ce.addCandy(0, Candies.red());
		
		Candy top = ce.firstCandyInColumn(0);
		assertThat(top.getY(), is((float) Constants.HEIGHT /*- Constants.CANDYHEIDHT*/) );
	}
	
	@Test
	public void whenAddingAShooteCandy_DontChateItsPosition() {
		Candy shooted = Candies.red();
		shooted.setPosition(32f, 42f);
		
		this.ce.addShootedCandy(0, shooted);
		
		assertThat(shooted.getX(), is(32f));
		assertThat(shooted.getY(), is(42f));
	}

}
