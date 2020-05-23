package farmProject;
import static javax.swing.JOptionPane.showMessageDialog;

import java.util.Scanner;

/** 
* This class is the manager class for the CLA and is what links all other classes together
* @author Alec, Christian
*/

public class GameEnvironment {
    
    private int days;
    private int turns = 2;
    private Double EVENTCHANCE = 5.0;
    private Farmer farmerObject;
    private Farm farmObject;
    private Scanner scanner = new Scanner (System.in); // Scanner type allows user to enter scanner into console

    
    public GameEnvironment(){ //constructor
        String dayss = "";
        boolean valid = false;
        
        do{ //error trapping
        	try {
	            System.out.print("Enter number of days: ");
	            dayss = scanner.nextLine();
	            if (5 > Integer.parseInt(dayss) || Integer.parseInt(dayss) > 10){
	                System.out.println("Game must run between 5 to 10 days");
	            }
	            else {
	            	valid = true;
	            }
        	}
            catch (NumberFormatException e) {
    			System.out.println("Please enter a valid number.");
    		}
        }
        while (!(valid));
        
        days = Integer.parseInt(dayss);
        
        farmerObject = getFarmer();
        farmObject = getFarm();
    }
    
    public void editTurns(int i){
        turns += i;
    }
    
    
    
    /**
     * Gets farmer object after collecting user input
     * @return  farmerObject users farmer
     */
    private Farmer getFarmer(){ 
        //returns farmer object after collecting user input
        String nameF;
        String age;
        
        do{
            System.out.print("\nEnter farmer first name: ");
            nameF = scanner.nextLine();
            if (nameF.length() < 3 || nameF.length() > 15){
                System.out.println("First name must be between 3 and 15 characters long.");
            }
        }
        while (nameF.length() < 3 || nameF.length() > 15);
        
        boolean valid = false;
        do{
            System.out.print("\nEnter farmer age: ");
            age = scanner.nextLine();
            
            try {
	            if ( Integer.parseInt(age) < 0 ){
	                System.out.println("Please enter a valid age.");
	            }
	            else {
	            	valid = true;
	            }
            }
            catch (NumberFormatException e) {
    			System.out.println("Please enter a valid age.");
    		}
        }
        while (!(valid));
    	
        Farmer farmerObject = new Farmer(nameF, Integer.parseInt(age));
        return farmerObject;
    }
    
    
    
    /**
     * Gets farm object after collecting user input
     * @return farmObject users farm
     */
    private Farm getFarm(){ //returns farm object after collecting user input
        String farmInt;
        
        System.out.println("\nPlease select farm bonus using number");
        System.out.println("1: Grower (faster crop growth)\n2: Animal (happier animals)\n3: Starter (higher starting balance)\n4: Larger (larger farm capacity)");
        
        do{
            farmInt = scanner.nextLine();
            if (1 > Integer.parseInt(farmInt) || Integer.parseInt(farmInt) > 4){
                System.out.println("Please select a number between 1 and 4");
            }
        }
        while (1 > Integer.parseInt(farmInt) || Integer.parseInt(farmInt) > 4);
        String farmName;
        
        do{
            System.out.println("\nWhat is the name of your farm: ");
            farmName = scanner.nextLine();
            if (farmName.length() < 3 || farmName.length() > 15){
                System.out.println("Name must be between 3 and 15 characters long.");
            }
        }
        while (farmName.length() < 3 || farmName.length() > 15);
    	

        
        Farm farmObject = new Farm(farmName, Integer.parseInt(farmInt));
        return farmObject;
    }
    
    
    
    /**
     * Implements general store interactive, allows user to buy animals, crops and items.
     */
    private void generalStore(){
        String input = "0";
        do{
            farmObject.viewBal();
            System.out.println("1: Buy an animal, 2: Buy crops, 3: Buy an item, 4: View your items, 5: Exit store");
            input = scanner.nextLine();
            if (input.equals("1")){ //if buying an animal
                if (farmObject.getAnimals().size() < farmObject.maxAnimals){
                    storeAnimals();
                }
                else{
                    System.out.println("\nFarm at max animal capacity!");
                }
            }
            else if (input.equals("2")){ //if buying an item
                if (farmObject.getCrops().size() < farmObject.maxCrops){
                    storeCrops();
                }
                else{
                    System.out.println("\nFarm at max crop capacity!");
                }
            }
            else if (input.equals("3")){
                storeItems();
            }
            else if (input.equals("4")){
                farmObject.viewItems();
            }
            else if (input.equals("5")){
                System.out.println("Thanks for visitng " + farmerObject.getName());
            }
            else{
                System.out.println("Make sure to enter number between 1 and 5");
            }
        }
        while (!(input.equals("5")));
    }
    
    
    
    /**
     * Implements buying crops. User selects type of crop and how many. Balance is reduced and crops are added to farm object
     */
    private void storeCrops(){ // opens this method after selecting view crops in the general storeCrops
        
        String[] cropTypes = new String[]{"Carrot", "Wheat", "Potatoes", "Pumpkins", "Melon", "Beetroot"};
        Double[] cropCosts = new Double[]{5.0, 3.0, 2.0, 10.0, 8.0, 2.0}; // value of crops?
        String input = "0";
        String quantity = "0"; // how many crops
        
        // String decision = "0"; ignore
        
        do{
            for (int i = 1 ; i < cropTypes.length + 1 ; i++) {
                System.out.println("Crop " + i + ": " + cropTypes[i-1] + ": $" + cropCosts[i-1]);  
            }
            
            System.out.println("\nBuy crops using number or enter " + (cropTypes.length + 1) + " to exit store");
            
            input = scanner.nextLine();
            int inputt = Integer.parseInt(input); // Convert input to int at top so dont have to do all the way through
            
            if ( inputt >= 1 & inputt <= cropTypes.length ){ 
                System.out.println("How many " + cropTypes[inputt-1] + " do you want to buy?");
                quantity = scanner.nextLine();
                int quantityy = Integer.parseInt(quantity);//user selects crops
                if (farmObject.getBal() >= (cropCosts[inputt-1]) * quantityy){ //checks if enough money available
                    Crop a = new Crop(cropTypes[inputt-1], cropCosts[inputt-1], quantityy);
                    farmObject.addCrop(a);
                    
                    
                    farmObject.viewBal();
                    System.out.println("Cost of " + quantityy + " " + cropTypes[inputt-1] + " is: " + cropCosts[inputt-1] * quantityy);
                    farmObject.updateBal( (-cropCosts[Integer.parseInt(input)-1]) * Integer.parseInt(quantity));
                    System.out.println("New balance: " + farmObject.getBal());
                    
                    System.out.println("\nExiting crop section.");
                    break;
                    
                }
                else{
                    System.out.println("Cannot afford crops");
                    farmObject.viewBal(); 
                }

            }
            else if ( input.equals(Integer.toString(cropTypes.length + 1)) ){
                System.out.println("\nThanks for visiting " + farmerObject.getName());
            }
            else{
                System.out.println("Make sure to enter a number between 1 and " + (cropTypes.length + 1));
            }
        }
        while (!(input.equals(Integer.toString(cropTypes.length + 1)))); //if user enter a number one higher than the length of animalTypes  
    }
    
    
    
    /**
     * Implements buying items. User selects type of item. Balance is reduced and items are added to farm object
     */
    private void storeItems(){
        String[] itemTypes = new String[]{"Medicine", "Grub", "Seeds", "Fertilizer", "Sunlight", "Instant grow"};
        String[] itemDescriptions = new String[]{"Max health on all animals", "Increase all animals health by 20% of its max", "Increase all animals health by 50% of its max",
                                                 "Increase growth rate 20%", "Increase growth rate 50%", "Increase growth rate 100%"};
        Double[] itemCosts = new Double[]{20.0, 50.0, 10.0, 20.0, 40.0, 80.0}; // value of crops?
        String input = "0";
        
        do{
            System.out.println("\nDifferent items have diferent benefits"); //need to re-word this lol
            for (int i = 1 ; i < itemTypes.length + 1 ; i++){ //prints each animal and their costs 
                System.out.println("Item " + i + ": " + itemTypes[i-1] + ", cost: $" + itemCosts[i-1] + "\nDescription: " + itemDescriptions[i-1]);
            }
            
            System.out.println("\nBuy an item using number or enter " + (itemTypes.length + 1) + " to exit store");
            input = scanner.nextLine();
            
            if ( Integer.parseInt(input) >= 1 & Integer.parseInt(input) <= itemTypes.length ){ //if user selected an animal
                if (farmObject.getBal() >= itemCosts[Integer.parseInt(input)-1]){ //if farmer can afford animal
                    Item i;
                    if (input.equals("1")){
                        i = new FoodItem(itemTypes[Integer.parseInt(input)-1], itemDescriptions[Integer.parseInt(input)-1], 1.0);
                    }
                    else if (input.equals("2")){
                        i = new FoodItem(itemTypes[Integer.parseInt(input)-1], itemDescriptions[Integer.parseInt(input)-1], 0.2);
                    }
                    else if (input.equals("3")){
                        i = new FoodItem(itemTypes[Integer.parseInt(input)-1], itemDescriptions[Integer.parseInt(input)-1], 0.5);
                    }
                    else if (input.equals("4")){
                        i = new CropItem(itemTypes[Integer.parseInt(input)-1], itemDescriptions[Integer.parseInt(input)-1], 0.3);
                    }
                    else if (input.equals("5")){
                        i = new CropItem(itemTypes[Integer.parseInt(input)-1], itemDescriptions[Integer.parseInt(input)-1], 0.5);
                    }
                    else{
                        i = new CropItem(itemTypes[Integer.parseInt(input)-1], itemDescriptions[Integer.parseInt(input)-1], 1.0);
                    }
                    farmObject.addItem(i);
                    farmObject.updateBal( -itemCosts[Integer.parseInt(input)-1] );
                    System.out.println("\nExiting item section");
                    break;
                }
                else{
                    System.out.println("Cannot afford item");
                    farmObject.viewBal();
                }
                
            }
            else if ( input.equals((Integer.toString(itemTypes.length + 1)))){
                System.out.println("\nThanks for visiting " + farmerObject.getName());
            }
            else{
                System.out.println("Make sure to enter a number between 1 and " + (itemTypes.length+1));
            }
        }
        while (!(input.equals(Integer.toString(itemTypes.length + 1)))); //if user enter a number one higher than the length of itemTypes 
    }
    
    
    
    /**
     * Implements buying animals. User selects type of animal. Balance is reduced and animals are added to farm object
     */
    private void storeAnimals(){ //opens this method after selecting view animals in the general store
        String[] animalTypes = new String[]{"Sheep", "Cow", "Pig"};
        Double[] animalCosts = new Double[]{100.0, 120.0, 150.0};
        String input = "0";
        
        do{
            System.out.println("\nAnimals with higher cost provide more income"); //need to re-word this lol
            for (int i = 1 ; i < animalTypes.length + 1 ; i++){ //prints each animal and their costs 
                System.out.println("Animal " + i + ": " + animalTypes[i-1] + ", cost: $" + animalCosts[i-1]);
            }
            
            System.out.println("\nBuy an animal using number or enter " + (animalTypes.length + 1) + " to exit store");
            input = scanner.nextLine();
            
            if ( Integer.parseInt(input) >= 1 & Integer.parseInt(input) <= animalTypes.length ){ //if user selected an animal
                if (farmObject.getBal() >= animalCosts[Integer.parseInt(input)-1]){ //if farmer can afford animal
                    Animal a = new Animal(animalTypes[Integer.parseInt(input)-1], animalCosts[Integer.parseInt(input)-1]);
                    farmObject.addAnimal(a);
                    farmObject.updateBal( -animalCosts[Integer.parseInt(input)-1] );
                    System.out.println("\nExiting animal section");
                    break;
                }
                else{
                    System.out.println("Cannot afford animal");
                    farmObject.viewBal();
                }
                
            }
            else if ( input.equals((Integer.toString(animalTypes.length + 1)))){
                System.out.println("\nThanks for visiting " + farmerObject.getName());
            }
            else{
                System.out.println("Make sure to enter a number between 1 and " + (animalTypes.length+1));
            }
        }
        while (!(input.equals(Integer.toString(animalTypes.length + 1)))); //if user enter a number one higher than the length of animalTypes
    }
    
    
    
    /**
     * Implements choose action button. User chooses what action they want to perform on their farm
     */
    private void chooseAction(){
        
        String input;
        
        do{
            if (turns <= 0){
                break;
            }
            System.out.println("\nWhat action do you want to perform? Actions left: " + turns);
            System.out.println("1: Tend to Crops, 2: Feed Animals, 3: Play with Animals, 4: Harvest Crops, 5: Tend to Farm, 6: Quit Actions");
            input = scanner.nextLine();

            
            if (input.equals("1")){
                boolean crop = false;
                
                for (Item i : farmObject.getItems() ){
                    if (i instanceof CropItem & farmObject.getCrops().size() >= 1){
                        crop = true;
                        break;
                    }
                }       
                if (crop){
                    farmObject.viewCrops();
                    farmObject.chooseCrop(this);
                    farmObject.viewCrops();
                }
                else{
                    System.out.println("You have either no items or no crops");
                }

            }
            else if (input.equals("2")){
                boolean food = false;
                
                for (Item i : farmObject.getItems()){ 
                    if (i instanceof FoodItem & farmObject.getAnimals().size() >= 1){
                        food = true;
                        break;
                    }
                }
                
                if (food){ //if at least one food item and one animal
                    farmObject.viewAnimals();
                    farmObject.feedAnimal(this);
                    farmObject.viewAnimals();
                }
                else{
                    System.out.println("You have no items to feed animals");
                }
            }
            
            else if (input.equals("3")){
                if (farmObject.getAnimals().size() >= 1){
                	farmObject.playTime();
                    System.out.println("\nAll animals at max happiness!"); 
                    turns -= 1;
                } 
                else{
                    System.out.println("\nNo animals to play with :(");
                }

            }
            else if (input.equals("4")){
                boolean harvest = false;
                
                for (Crop c : farmObject.getCrops()){ //if at least one crop is ready to harvest
                    if (c.getGrowth() >= 1){
                        harvest = true;
                        break;
                    }
                }
                
                if (harvest){
                    farmObject.harvest();
                    farmObject.viewCrops();
                    turns -= 1;
                    System.out.println("New farm balance: " + farmObject.getBal());
                }
                else{
                    System.out.println("\nNo crops to harvest!");
                }
                
            }
            else if (input.equals("5")){
                tendFarm();
                turns -= 1;
            }
            
        }
        while (!(input.equals("6")) || turns <= 0);
        
    }
    
    
    /**
     * Implements new day. Random event occurrence is determined 
     */
    private void newDay(){
        turns = 2;
        days -= 1;
        
        if (days <= 0){
            endGame();
        }
        else{
            System.out.println("New day, days left: " + (days - 1));
            farmObject.newDay();
            double x = Farm.getRandomDoubleBetweenRange(1.0, EVENTCHANCE); //1 in EVENTCHANCE chance of an event occuring
            if (x == 1.0){
                double y = Farm.getRandomDoubleBetweenRange(1.0, 3.0); //randomly selecting which event to occur
                if (y == 1.0){
                    System.out.println("\nA drought has ruined some of your crops!");
                    farmObject.drought();
                }
                else if (y==2.0){
                    System.out.println("\nCongratulations! Your farm, " + farmObject.getName() + ", has won the county fair!");
                    farmObject.countyFair();
                    
                }
                else if (y==3.0){
                    System.out.println("\nA fence broke on the farm! Some animals ran away and the others arent too happy :(");
                    farmObject.brokenFence();
                }
            }
            farmObject.viewAnimals();
            farmObject.viewCrops();
            farmObject.viewBal();
        }
        
    }
    
    /**
     * Ends game and displays final score
     */
    private void endGame(){ //called when game ends
        double score = farmObject.getScore();
        System.out.println("\n GAME OVER");
        System.out.println("\n FARMER: " + farmerObject.getName());
        System.out.println("\n FARM: " + farmObject.getName());
        System.out.println("\n FINAL SCORE: " + score);
        System.exit(0);
    }
    
    /**
     * Main option selection for game
     */
    private void playGame(){ //option selection
        String input;
        
        do{
            System.out.println("\nUse numbers to select an option.");
            System.out.println("1: Actions, 2: View farm, 3: View bal, 4: Visit general store, 5: Next day, 6: exit game");
            input = scanner.nextLine();
            if (input.equals("1")){
                if (turns > 0){
                    chooseAction();
                }
                else{
                    System.out.print("\nYou have no more actions avaliable today");
                }
            }
            else if (input.equals("2")){
                farmObject.viewAnimals();
                farmObject.viewCrops();
            }
            else if (input.equals("3")){
                farmObject.viewBal();
            }
            else if (input.equals("4")){
                generalStore();
            }
            else if (input.equals("5")){
                newDay();
            }
        }
        while (!(input.equals("6")));
    }
            
    
    /**
     * Implements tend to farm actions. Adds extra space to farm and slows down animal happiness decline rate
     */
    private void tendFarm(){ //adds extra space to farm and slows down animal happiness decline rate
        System.out.println("\nAnimals feel more comfortable, now their happiness drains slower.");
        System.out.println("Animal capacity increased by 2, " + farmObject.getName() + " has room for " + (farmObject.maxAnimals - farmObject.getAnimals().size())  + " more animals!");
        System.out.println("Crop capacity increased by 20, " + farmObject.getName() + " has room for " + (farmObject.maxCrops - farmObject.numberCrops())  + " more crops!");
        farmObject.editHealth();
        farmObject.addSpace();
    }
    
    
    
    public static void main(String[] args) {
        GameEnvironment game = new GameEnvironment(); //initializes game environment
        
        System.out.println("\nStarting game with " + game.farmerObject.getName() +  "'s " + game.farmObject.getType() + " farm: " + game.farmObject.getName());
        
        game.playGame();
        
        System.out.println("\nCheers for playing " + game.farmerObject.getName());
        game.scanner.close(); //closes scanner to disallow extra input
    }
}
    
    
    