package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import farmProject.FoodItem;

class FoodTest {
	private FoodItem item;
	
	
	@BeforeEach
	public void init() {
		item = new FoodItem ("Grub", "Increase all animals health by 20% of its max", 0.2);
	}

	@Test
	public void testBenefit() {
		assertEquals(0.2, item.getBenefit());
	}
	
	@Test 
	public void toStringTest() {
		assertEquals("Grub", item.toString());
	}
}
