package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import farmProject.Animal;


class AnimalsTest {
	private Animal testAnimal;
	
	@BeforeEach
	public void init() {
		testAnimal = new Animal ("Cow", 100.0);
	}
	
	// Getters and setters
	
	@Test
	public void typeTest() {
		assertEquals("Cow", testAnimal.getType());
	}
	
	@Test
	public void worthTest() {
		assertEquals(100.0, testAnimal.getWorth());
	}
	
	@Test 
	public void healthTest() {
		assertEquals(1.0, testAnimal.getHealth());
	}
	
	@Test
	public void happyTest() {
		assertEquals(1.0, testAnimal.getHappy());
	}
	
	@Test
	public void editHappinessTest() {
		
		// Happiness is changed right amount
		testAnimal.editHappiness(-0.1);
		assertEquals(0.9, testAnimal.getHappy());
		
		// Happiness can't go below 0.0
		testAnimal.editHappiness(-1.0);
		assertEquals(0.0, testAnimal.getHappy());
		
		// Happiness can't go above 1.0
		testAnimal.editHappiness(1.1);
		assertEquals(1.0, testAnimal.getHappy());		
	}
	
	@Test
	public void getDailyBonusTest() {
		double bonus = testAnimal.getWorth() * 0.2;
		assertEquals(bonus, testAnimal.getDailyBonus());
	}
	
	@Test
	public void editHealthTest() {
		assertEquals(1.0, testAnimal.getHealth());
		
		//ensuring method works as intended
		testAnimal.editHealth(-0.5);
		assertEquals(0.5, testAnimal.getHealth());
		
		//ensuring health can't go above 1.0
		testAnimal.editHealth(0.6);
		assertEquals(1.0, testAnimal.getHealth());
		
		//ensuring health can't go below 0.0
		testAnimal.editHealth(-1.1);
		assertEquals(0.0, testAnimal.getHealth());
	}
	
	@Test 
	public void toStringTest() {
		assertEquals("Cow health: 1.0", testAnimal.toString());
	}
	

}
