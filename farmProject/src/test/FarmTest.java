package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import farmProject.Animal;
import farmProject.Crop;
import farmProject.CropItem;
import farmProject.Farm;
import farmProject.FoodItem;

class FarmTest {
	private Farm farm;
	
	
	public void init() {
		farm = new Farm("Test", 3); //starter farm
	}
	
	
	@Test
	public void testGetName() {
		assertEquals("Test", farm.getName());
	}
	
	
	@Test
	public void testGetType() {
		assertEquals("Starter", farm.getType());
	}
	
	
	@Test
	public void testAddAnimal() {
		//determines if there has been one animal added to the farm
		Animal a = new Animal("Sheep", 100.0);
		
		int initial = farm.getAnimals().size();
		farm.addAnimal(a);
		assertEquals(initial+1, farm.getAnimals().size());
	}
	
	
	@Test
	public void testAddCrop() {
		//determines if there has been 20 crops added to the farm
		Crop c = new Crop("Carrot", 5.0, 20);
		
		int initial = farm.numberCrops();
		farm.addCrop(c);
		assertEquals(initial+20, farm.numberCrops());
	}
	
	
	@Test
	public void testAddItem() {
		//determines if there has been one animal added to the farm
		CropItem i = new CropItem("Fertilizer", "Increase crop growth rate", 0.25);
		
		int initial = farm.getItems().size();
		farm.addItem(i);
		assertEquals(initial+1, farm.getItems().size());
	}
	
	
	@Test
	public void testAddFoodItem() {
		//determines if there has been one animal added to the farm
		FoodItem i = new FoodItem("Grub", "Increase all animals health by 20% of its max", 0.2);
		
		int initial = farm.getFoodItems().size();
		farm.addItem(i);
		assertEquals(initial+1, farm.getFoodItems().size());
	}
	
	
	@Test
	public void testAddCropItem() {
		//determines if there has been one animal added to the farm
		CropItem i = new CropItem("Fertilizer", "Increase crop growth rate", 0.25);
		
		int initial = farm.getCropItems().size();
		farm.addItem(i);
		assertEquals(initial+1, farm.getCropItems().size());
	}
	
	
	@Test
	public void testUpdateBal() {
		farm.updateBal(250.0);
		
		assertEquals(1000.0, farm.getBal());
	}
	
	
	@Test
	public void testEditHealth() {
		//edit health is called when the farm is tended to
		double initial = farm.getHealth();
		farm.editHealth();
		
		assertEquals(initial, farm.getHealth());
	}
	
	
	@Test
	public void testAddSpace() {
		int initialAnimals = farm.maxAnimals;
		int initialCrops = farm.maxCrops;
		
		farm.addSpace();
		
		assertEquals(initialAnimals, farm.maxAnimals);
		assertEquals(initialCrops, farm.maxCrops);
	}
	
	
	@Test
	public void testCountyFair() {
		//calculation to update balance = getAnimals().size() * 40.0) + (numberCrops() * 4.0)
		Crop c = new Crop("Carrot", 5.0, 20);
		farm.addCrop(c);
		Animal a = new Animal("Sheep", 100.0);
		farm.addAnimal(a);
		
		double initialBal = farm.getBal();
		farm.countyFair();
		
		assertTrue(initialBal < farm.getBal());
	}

}
