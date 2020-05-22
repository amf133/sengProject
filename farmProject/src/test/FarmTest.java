package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import farmProject.Animal;
import farmProject.Crop;
import farmProject.CropItem;
import farmProject.Farm;
import farmProject.FoodItem;

class FarmTest {
	private Farm farm1;
	private Farm farm2;
	private Farm farm3;
	private Farm farm4;
	
	@BeforeEach
	public void init() {
		farm1 = new Farm("Test1", 1); //grower farm
		farm2 = new Farm("Test2", 2); //animal farm
		farm3 = new Farm("Test3", 3); //starter farm
		farm4 = new Farm("Test4", 4); //larger farm
	}
	
	
	@Test
	public void testGetName() {
		assertEquals("Test3", farm3.getName());
	}
	
	
	@Test
	public void testGetType() {
		assertEquals("Starter", farm3.getType());
	}
	
	
	@Test
	public void testAddAnimal() {
		//determines if there has been one animal added to the farm
		Animal a = new Animal("Sheep", 100.0);
		
		int initial = farm3.getAnimals().size();
		farm3.addAnimal(a);
		assertEquals(initial+1, farm3.getAnimals().size());
	}
	
	
	@Test
	public void testAddCrop() {
		//determines if there has been 20 crops added to the farm
		Crop c = new Crop("Carrot", 5.0, 20);
		
		int initial = farm3.numberCrops();
		farm3.addCrop(c);
		assertEquals(initial+20, farm3.numberCrops());
	}
	
	
	@Test
	public void testAddItem() {
		//determines if there has been one animal added to the farm
		CropItem i = new CropItem("Fertilizer", "Increase crop growth rate", 0.25);
		
		int initial = farm3.getItems().size();
		farm3.addItem(i);
		assertEquals(initial+1, farm3.getItems().size());
	}
	
	
	@Test
	public void testAddFoodItem() {
		//determines if there has been one animal added to the farm
		FoodItem i = new FoodItem("Grub", "Increase all animals health by 20% of its max", 0.2);
		
		int initial = farm3.getFoodItems().size();
		farm3.addItem(i);
		assertEquals(initial+1, farm3.getFoodItems().size());
	}
	
	
	@Test
	public void testAddCropItem() {
		//determines if there has been one animal added to the farm
		CropItem i = new CropItem("Fertilizer", "Increase crop growth rate", 0.25);
		
		int initial = farm3.getCropItems().size();
		farm3.addItem(i);
		assertEquals(initial+1, farm3.getCropItems().size());
	}
	
	
	@Test
	public void testUpdateBal() {
		farm3.updateBal(250.0);
		
		assertEquals(1000.0, farm3.getBal());
	}
	
	
	@Test
	public void testEditHealth() {
		//edit health is called when the farm is tended to
		double initial = farm3.getHealth();
		farm3.editHealth();
		
		assertEquals(initial * 0.5, farm3.getHealth());
	}
	
	
	@Test
	public void testAddSpace() {
		int initialAnimals = farm3.maxAnimals;
		int initialCrops = farm3.maxCrops;
		
		farm3.addSpace();
		
		assertEquals(initialAnimals + 2, farm3.maxAnimals);
		assertEquals(initialCrops + 20, farm3.maxCrops);
	}
	
	
	@Test
	public void testCountyFair() {
		//calculation to update balance = getAnimals().size() * 40.0) + (numberCrops() * 4.0)
		Crop c = new Crop("Carrot", 5.0, 20);
		farm3.addCrop(c);
		Animal a = new Animal("Sheep", 100.0);
		farm3.addAnimal(a);
		
		double initialBal = farm3.getBal();
		farm3.countyFair();
		
		assertTrue(initialBal < farm3.getBal());
	}
	
	
	@Test
	public void testPlayTime() {
		Animal sheep = new Animal("Sheep", 100.0);
		Animal cow = new Animal("Cow", 120.0);
		cow.editHappiness(-0.5);
		sheep.editHappiness(-0.5);
		farm3.addAnimal(cow);
		farm3.addAnimal(sheep);
		
		farm3.playTime();
		
		for (Animal a : farm3.getAnimals()){        
            assertEquals(1.0, a.getHealth()); 
        }
	}
	
	
	@Test
	public void testGetCrops() {
		Crop c = new Crop("Carrot", 5.0, 20);
		farm3.addCrop(c);
		
		boolean test = false;
		
		for (Crop crop : farm3.getCrops()) {
			test = true;
		}
		
		assertTrue(test);
	}
	
	
	@Test
	public void testGetScore() {
		Crop c = new Crop("Beetroot", 2.0, 20);
		farm3.addCrop(c);
		Animal a = new Animal("Sheep", 100.0);
		farm3.addAnimal(a);
		assertTrue(0 < farm3.getScore());
	}
	
	
	@Test
	public void testRemoveItem() {
		FoodItem i = new FoodItem("Grub", "Increase all animals health by 20% of its max", 0.2);
		farm3.addItem(i);
		
		farm3.removeItem(i);
		
		assertEquals(0, farm3.getItems().size());
	}
	
	
	@Test
	public void testnewDay() {
		//Beetroot grows overnight so we can see if it is fully grown after one new day call
		//animals health goes down each day
		Crop c = new Crop("Beetroot", 2.0, 20);
		farm3.addCrop(c);
		Animal a = new Animal("Sheep", 100.0);
		farm3.addAnimal(a);
		
		farm3.newDay();
		
		for (Crop crop : farm3.getCrops()) {
			assertEquals(1.0, crop.getGrowth());
		}
		
		for (Animal animal : farm3.getAnimals()) {
			assertTrue(1.0 < animal.getHealth());
		}
	}
	
	
	@Test
	public void testHarvest() {
		Crop c = new Crop("Beetroot", 2.0, 20);
		c.increaseGrowth(2.0);
		farm3.addCrop(c);
		int initialCrops = farm3.numberCrops();
		double initialBal = farm3.getBal();
		
		farm3.harvest();
		assertTrue(initialBal < farm3.getBal());
		assertTrue(initialCrops < farm3.numberCrops());
		
	}
}
