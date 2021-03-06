package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import farmProject.Crop;

class CropsTest {
	private Crop testCrop;
	
	@BeforeEach
	public void init() {
		testCrop = new Crop ("Carrot", 20.0, 10);
	}
	
	// Getters and setters
	
	@Test
	public void typeTest() {
		assertEquals("Carrot", testCrop.getType());
	}
	
	@Test
	public void worthTest() {
		
		assertEquals(0, testCrop.getWorth());
		
		// Rate increased but no new day
		testCrop.increaseRate(10.0);
		assertEquals(0, testCrop.getWorth());
		
		// New day so crops have grown
		testCrop.newDay(1.0);
		assertEquals(400.0, testCrop.getWorth());
	}	
	
	@Test
	public void growthTest() {
		//ensuring default value is 0
		assertEquals(0.0, testCrop.getGrowth());
		
		//ensuring crop growth can be altered
		testCrop.increaseGrowth(0.5);
		assertEquals(0.5, testCrop.getGrowth());
		
		//ensuring crop growth can't be negative
		testCrop.increaseGrowth(-0.51);
		assertEquals(0.0, testCrop.getGrowth());
		
		//ensuring crop growth can't go above 1.0
		testCrop.increaseGrowth(1.01);
		assertEquals(1.0, testCrop.getGrowth());
	}
	
	@Test
	public void growthrateTest() {
		assertEquals(0.2, testCrop.getRate());
		
		testCrop.increaseRate(0.3);
		assertEquals(0.26, testCrop.getRate());
		
		// Ensuring growth rate does not go above 1.0
		testCrop.increaseRate(10.0);
		assertEquals(1.0, testCrop.getRate());
		
		// Ensuring growth rate does not go below 0.0
		testCrop.increaseRate(-1.1);
		assertEquals(0.0, testCrop.getRate());
	}
	
	@Test
	public void droughtTest() {
		
		testCrop.halfQuantity();
		assertEquals(5, testCrop.getQuantity());
		
		// Ensures can't get decimal crops
		testCrop.halfQuantity();
		assertNotEquals(2.5, testCrop.getQuantity());
		assertEquals(2, testCrop.getQuantity());
	}
	
	@Test
	public void toStringTest() {
		assertEquals("10 Carrot 0.20", testCrop.toString());
	}


}