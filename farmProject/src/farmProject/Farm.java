package farmProject;
import java.util.ArrayList;
import java.util.Scanner;

/** 
* This class implements the Farm (need more here lol)
* @author Alec, Christian
*/

public class Farm {
    private String name;
    int maxAnimals = 10;
    int maxCrops = 100;
    private ArrayList<Crop> crops = new ArrayList<>(maxCrops); //setting max size?
    private ArrayList<Animal> animals = new ArrayList<>(maxAnimals);
    private ArrayList<Item> items = new ArrayList<>();
    private Double bal = 500.0; //need to decide a starting value
    private Double GROWRATE = 1.0; //changed if grower farm selected
    private Double HAPPYDECLINE = 0.2;
    private String type;
    private Scanner scanner = new Scanner (System.in);

    
    public Farm(String namee, int typee) //init method, throws error if incorrect values
    {    
        if (3 <= namee.length() & namee.length() <= 30){
            
            if (typee == 1){ //grower
                GROWRATE += 0.2;
                type = "Grower";
            }
            else if (typee == 2){ //animal
                HAPPYDECLINE = 0.1;
                type = "Animal";
            }
            else if (typee == 3){ //starter
                bal += 250;
                type = "Starter";
            }
            else if (typee == 4){ //large
                maxAnimals += 5;
                maxCrops += 50;
                crops = new ArrayList<>(maxCrops);
                animals = new ArrayList<>(maxAnimals);
                type = "Large";
            }
            else{
                throw new IllegalArgumentException("Make sure the farm exists!");
            }
            name = namee;
        }
        
        else{
            throw new IllegalArgumentException("Name must be between 3 and 30 chars");
        }
    }
    
    /**
    * Gets the name of the farm
    * @return name farm name
    */
    public String getName(){
        return name;
    }
    
    
    public double getScore() {
    	double finalScore = bal;
    	
    	for (Animal a : animals) {
    		finalScore += a.getWorth();
    	}
    	
    	for (Crop c : crops) {
    		finalScore += c.getWorth();
    	}
    	
    	finalScore *= GROWRATE;
    	finalScore *= 1-HAPPYDECLINE;
    	
    	return finalScore;
    }
    
    /** 
    * Gets the type of farm, the starting bonus the user selected
    * @return type 
    */
    public String getType(){
        return type;
    }
    
    /** 
    * Gets all the animals currently stored in farm
    * @return animals array list of animals
    */
    public ArrayList<Animal> getAnimals(){
        return animals;
    }
    
    /**
     * Gets a list of all the food items currently owned
     * @return foodItems list of food items
     */
    public ArrayList<FoodItem> getFoodItems() {
    	ArrayList<FoodItem> foodItems = new ArrayList<FoodItem>();
    	for (Item i : items) {
    		if (i instanceof FoodItem) {
    			FoodItem r = (FoodItem) i;
    			foodItems.add(r);
    		}
    	}
    	return foodItems;
    }
    
    /**
     * Gets a list of all the crop items currently owned
     * @return cropItem list of crop items
     */
    public ArrayList<CropItem> getCropItems(){
    	ArrayList<CropItem> cropItems = new ArrayList<CropItem>();
    	for (Item i : items) {
    		if (i instanceof CropItem) {
    			CropItem c = (CropItem) i;
    			cropItems.add(c);
    		}
    	}
    	return cropItems;
    }
    /** 
    * Feeds all the animals currently stored in the farm. User selects item from their items owned
    * loops through animal list and applies benefit of item to each animals happiness. Removes item
    * from users item list
    * @param game removes 1 from action remaining
    */
    public void feedAnimal(GameEnvironment game){
    	ArrayList<FoodItem> foodItems = getFoodItems();
        int iter = 0;
        for (FoodItem r : foodItems) {
        	iter += 1;
        	System.out.println("Food item: " + iter);
        	r.printDetails();
        }
        
        /**
        for (Item i : items){ //creating a list of food items
            if (i instanceof FoodItem){
                FoodItem r = (FoodItem) i;
                foodItems.add(r);
                iter += 1;
                System.out.println("Food item: " + iter);
                r.printDetails();
            }
        }
        */
        
        String input = "0";
        FoodItem choice;
        do{
            System.out.println("\nSelect a food item to use using number");
            input = scanner.nextLine();
            
            if ( Integer.parseInt(input) >=1 & Integer.parseInt(input) <= foodItems.size() ){
                choice = foodItems.get( Integer.parseInt(input)-1 );
                for (Animal a : animals){
                    a.editHappiness(choice.getBenefit());
                }
                items.remove(choice);
                game.editTurns(-1);
                System.out.println("\nFood item consumed");
                break;
            }
            else if (input.equals(Integer.toString(foodItems.size() + 1))){
                System.out.println("\nCancelling action ");
            }
            else{
                System.out.println("\nPlease select a valid food item");
            }
        }
        
        
        
        while (!(input.equals(Integer.toString(foodItems.size() + 1))));
    }
    
    /** 
    * Gets all the crops currently stored in farm
    * @return crops array list of crops
    */
    public ArrayList<Crop> getCrops(){
        return crops;
    }
    
    /** 
    * Gets all the items currently stored in farm
    * @return items array list of items
    */
    public ArrayList<Item> getItems(){
        return items;
    }
    
    /** 
    * Adds animal to list of animals farm has
    * @param a animal to be added
    */
    public void addAnimal(Animal a){
        animals.add(a);
    }
    
    /** 
    * Adds item to list of items farm has
    * @param i item to be added
    */
    public void addItem(Item i){
        items.add(i);
    }
    
    /** 
    * Adds crop to list of crops farm has
    * @param c crop to be added
    */
    public void addCrop(Crop a){
        crops.add(a);
    }
    
    /**
    * Gets the monetary balance of the farm
    * @return bal balance of farm
    */
    public Double getBal(){
        return bal;
    }
    
    
    public Double getHappy(){
        return HAPPYDECLINE;
    }
    
    /** 
    * Updates the farms money balance
    * @param amount value to be added to balance
    */
    public void updateBal(Double amount){
        bal += amount;
    }
    
    /** 
    * Views the balance of farm 
    */
    public void viewBal(){ //prints farm balance
        System.out.println("\nBalance: " + String.valueOf(getBal()));
    }
    
    /** 
    * Loops through farms item list and prints all items
    */
    public void viewItems(){ // Lets user see items the own
        
        System.out.println("\nCurrent owned items:");
        for (Item i : items){
            i.printDetails();
        }
        
    }
    
    /** 
    * Prints all animals, shows income generated by current animals
    * Shows how many more animals farm can fit
    */
    public void viewAnimals(){ //prints animals in farm
        if (animals.isEmpty()){
            System.out.println("\nNo animals in farm");
        }
        else{
            Double bonus = 0.0;
            System.out.println("\n" + name + " has capacity for " + (maxAnimals - animals.size()) + " more animals");
            for (Animal a : animals){
                System.out.println("Animal: " + a.getType() + " Happiness: " + a.getHappy() + " Health: " + a.getHealth());
                bonus += a.getDailyBonus();
            }
            System.out.println("Current Daily income: $" + bonus);
        }
       
    }
    
    /** 
    * Prints all crops and their growth rate and worth
    * Shows how many more crops the farm can fit
    */ 
    public void viewCrops(){ //prints crops in farm
        if (crops.isEmpty()){
            System.out.println("\nNo crops in farm");
        }
        else{
            System.out.println("\n" + name + " has capacity for " + (maxCrops - numberCrops() + " more crops"));
            for (Crop c : crops){
            System.out.println("Crop: " + c.getType() + " x " + c.getQuantity() + " Growth: " + c.getGrowth() + " Total worth: " + c.getWorth());
            }
        }
    }
    
    
    /** 
    * Get the number of crops currently growing
    * @return numCrops number of crops
    */
    public double numberCrops(){
        Double numCrops = 0.0;
        for (int i = 1; i < crops.size() + 1; i++ ){
            numCrops += crops.get(i-1).getQuantity();
        }
        return numCrops;
    }
    
    /** 
    * User chooses item and applies item to the choosen crop. Can either be crop item currently owned by farm
    * or can use water for free. If item used, it is removed from the users owned items
    * the items benefit is added to the crops growth rate
    * @param crop the crop to have the item applied
    */
    public void applyItem(Crop crop){
          
        // THIS METHODS ALLOWS THE USER TO SELECT AN ITEM THE WANT TO USE ON THEIR CHOOSEN CROP
        // HAVE TO ALLOW USER TO JUST CHOOSE WATER eg 1: WATER \N 2: ITEM and if user selects '1' just apply water bonus to growth rate
        // if pick go to for loop and choose item
        
        System.out.println("You have choosen to tend " + crop.getQuantity() + " " + crop.getType());
        ArrayList<CropItem> cropItems = getCropItems();
        int iter = 0;
        for (CropItem c : cropItems) {
        	iter += 1;
        	System.out.println("Crop item: " + iter);
        	c.printDetails();
        }
        /**
        for (Item i : items){ // PRINTS CROP ITEMS AND DESCRIPTIONS
            if (i instanceof CropItem){
                CropItem c = (CropItem) i;
                cropItems.add(c);
                j += 1;
                System.out.println("Crop Item: " + j);
                c.printDetails();
            }
        }
        */
        
        String input = "0";
        CropItem choice;
        
        do{
            System.out.println("What item do you want to use? Enter '0' to use free water!"); // USER SELECTED CROP ITEM
            input = scanner.nextLine();
            
            if (Integer.parseInt(input) == 0){
                crop.increaseRate(0.15); // Benefit of water
                System.out.println("\nCrop have been watered");
                break;
            }
                               
            if (Integer.parseInt(input) >= 1 & Integer.parseInt(input) <= cropItems.size()){
                choice = cropItems.get(Integer.parseInt(input)-1 );
                crop.increaseRate(choice.getBenefit()); // ADD BENEFIT OF ITEMS TO CHOOSEN CROPS GROWTH RATE -- HAVE TO CHANGE increaseRate METHOD IN CROP CLASS
                items.remove(choice); // REMOVE ITEM FROM USERS ITEM LIST
                // once action has been completed, remove 1 from action counter
                System.out.println("\nCrop item consumed");
                break;
                }
            else if (input.equals(Integer.toString(cropItems.size() + 1))){
                System.out.println("\nCancelling action ");
            }
            else{
                System.out.println("\nPlease select a valid crop item");
            }
        }
        while (!(input.equals(Integer.toString(cropItems.size() + 1))));
    }
    
        
    /** 
    * User chooses which crop to tend to, only one type of crop can be choosen. User choice is sent to applyItem method
    * @param game remove 1 from action counter
    */
    public void chooseCrop(GameEnvironment game){
        // ALLOWS THE USER TO SELECT TO CROP THEY WANT TO APPLY ITEM TO
        int n = 0;

        for (Crop c : crops){ // PRINT AVAILABLE CROPS TO TEND TO
            n = n + 1;
            System.out.println("(" + n + ")" + " : " + c.getQuantity() + " " + c.getType());
        }
        
        String input = "0";
        Crop choice;
            
        do{
            System.out.println("What crop do you want to treat? (Using number)");
            input = scanner.nextLine();
            
            if (0 < Integer.parseInt(input) || Integer.parseInt(input) < crops.size()){
                choice = crops.get(Integer.parseInt(input) - 1);
                applyItem(choice);
                game.editTurns(-1);
                
                // SEND CHOOSEN CROP TO APPLY ITEM CLASS
                break;
            }
            else{
                System.out.println("Plase select a vaild crop to treat!");
            }
        }
        while (0 > Integer.parseInt(input)|| Integer.parseInt(input) > crops.size()); 
        }

    /**
    * Changes the base happiness decline of all animals
    */
    public void editHappiness(){
        HAPPYDECLINE *= 0.5;
    }
    
    /**
    * Plays with animals and increases each animals happiness
    * Prints the happiness increase of each animals
    */
    public void playTime(){
        for (Animal a : animals){        
            a.editHappiness(1.0);
            //System.out.println("PLayed with " + a.getType() + ". New happiness = " + a.getHappy());  
        }
    }
    
    /**
    * Increase the number of animals the farm can store and the number of crops the farm can growth
    */
    public void addSpace(){
        maxAnimals += 2;
        maxCrops += 20;
        crops.ensureCapacity(maxCrops);
        animals.ensureCapacity(maxAnimals);
        //System.out.println("Animal capacity increased by 2, " + name + " has room for " + (maxAnimals - animals.size())  + " more animals!");
        //System.out.println("Crop capacity increased by 20, " + name + " has room for " + (maxCrops - numberCrops())  + " more crops!");
    }
    
    
    /**
    * Harvest all fully grown crops and adds it worth to farm balance
    */
    public void harvest(){ 
        
        for (Crop c : crops){
            if (c.getGrowth() >= 1){
                bal += c.getWorth();
                crops.remove(c);
                harvest();
                break;
            }
        }
    }
    
    /** 
    * Intializes new day, get daily income from all animals and add it to balance
    * reduce the happiness of animals and apply growthrate of crops to current growthrate
    */
    public void newDay(){
        Double income = 0.0;
        
        for (Animal a : animals){
            income += a.getDailyBonus();
            a.editHappiness(-HAPPYDECLINE);
        }
        bal += income;
        
        for (Crop c : crops){
            c.newDay(GROWRATE);
        }
    }
    
    /** 
    * Generates random number to be used for random events
    * @param min lowest val for random number
    * @param max highest val for random number
    * @return x random number generated
    */
    public static double getRandomDoubleBetweenRange(double min, double max){
        double x = (int)(Math.random()*((max-min)+1))+min;
        return x; 
    }
    
    /**
    * Random event, remove random animal from farm and decrease happiness of other animals
    */
    public void brokenFence(){
        double x = getRandomDoubleBetweenRange(1,3);
        
        for (int i=0 ; i < x ; i++){
            if (animals.size() > 0){
            	animals.remove(0);
            }
        }
    }
    
    /**
    * Random event, destroy half crops at random
    */
    public void drought(){ //random number of crops will be halfed in quantity
        double x = getRandomDoubleBetweenRange(1, crops.size()); //how many different crops will be halved
        double i = 0.0;
        
        for (Crop c : crops){
            if (i < x){
                c.halfQuantity();
            }
            i += 1;
        }
    }
    
    /**
    * Random event, generate bonus based of number of animals and crops in farm. Upate balance with bonus
    */
    public void countyFair(){
        Double bonus = 0.0;
        bonus += ((getAnimals().size() * 40.0) + (numberCrops() * 4.0));
        updateBal(bonus);
    }
    
}
