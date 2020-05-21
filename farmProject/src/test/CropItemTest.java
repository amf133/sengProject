package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import farmProject.CropItem;

class CropItemTest {
	private CropItem testCropItem;

	@BeforeEach
	public void init() {
		testCropItem =  new CropItem ("Fertilizer", "Increase crop growth rate", 0.25 );
	}
	
	@Test
	public void typeTest() {
		assertEquals("Fertilizer", testCropItem.getType());
	}
	
	@Test
	public void toStringTest() {
		assertEquals("Fertilizer", testCropItem.toString());
	}
	
	@Test
	public void descrTest() {
		assertEquals("Increase crop growth rate", testCropItem.getDescription());
	}
	
	@Test
	public void benefitTest() {
		assertEquals(0.25, testCropItem.getBenefit());
	}
}
