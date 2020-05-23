package farmProject;

import static javax.swing.JOptionPane.showMessageDialog;

import java.text.DecimalFormat;
import java.util.ArrayList;



/** 
* This class is the main GUI manager window, this class brings all other classes together
* @author Alec, Christian
*/
public class WindowManager {
	public Farm farmObject; //need to be public
	Farmer farmerObject;
    private int days;
    private int turns = 2;
    private Double EVENTCHANCE = 5.0;
    private SetupScreen setupScreen = new SetupScreen(this);
    private TownMap map = new TownMap(this);
    private FarmView farmView = new FarmView(this);
    private WActions actionWindow = new WActions(this);
	
	/**
     * Opens action window
     */
	public void openActions() {
		actionWindow.show();
	}
	
	
	/**
     * Edits the daily turns allowed
     * @param incomingNum int to change daily turns by
     */
	public void editTurns(int incomingNum) {
		turns += incomingNum;
	}
	
	
	/**
     * Returns an int of the remaining turns left
     * @return turns number of turns remaining in day
     */
	public int getTurns() {
    	return turns;
    }
	
	
	/**
     * Returns an int of the days remaining
     * @return day number of days remaining in game
     */
	public int getDays() {
		return days;
	}
	
	
	/**
     * Returns a double of the game score
     * @return score users score of game end
     */
	public double getScore() {
		return farmObject.getScore();
	}
	
	
	/**
     * Closes map window and opens the animal store window
     */
	public void viewAnimalStore() {
		WAnimalStore aStore = new WAnimalStore(this, farmObject);
		map.closeWindow();
		aStore.show();
	}
	
	
	/**
     * Closes map window and opens the crop store window
     */
	public void viewCropStore() {
		WCropStore cStore = new WCropStore(this, farmObject);
		map.closeWindow();
		cStore.show();
	}
	
	
	/**
     * Closes map window and opens the item store window
     */
	public void viewItemStore() {
		WItemStore iStore = new WItemStore(this, farmObject);
		map.closeWindow();
		iStore.show();
	}
	
	
	/**
     * Harvests fully grown crops
     */
	public void harvestCrops() {
		farmObject.harvest();
        turns -= 1;
        showMessageDialog(null, "New farm balance: " + farmObject.getBal());
        farmView.updateLabels();
	}
	
	
	/**
     * Method to determine what happens at the start of a new day
     */
	public void newDay(){
        turns = 2;
        days -= 1;
        
        if (days <= 0){
            map.endWindow();
            farmView.endWindow();
            actionWindow.endWindow();
            WFinalScore finalFrame = new WFinalScore(farmObject.getName() ,farmerObject.getName(), String.valueOf(getScore()));
            
        }
        else{
        	showMessageDialog(null, "New day, days left: " + (days-1));
            farmObject.newDay();
            double x = Farm.getRandomDoubleBetweenRange(1.0, EVENTCHANCE); //1 in EVENTCHANCE chance of an event occurring
            if (x == 1.0){
                double y = Farm.getRandomDoubleBetweenRange(1.0, 3.0); //randomly selecting which event to occur
                if (y == 1.0){
                	showMessageDialog(null, "\nA drought has ruined some of your crops!");
                    farmObject.drought();
                }
                else if (y==2.0){
                	showMessageDialog(null, "\nCongratulations! Your farm, " + farmObject.getName() + ", has won the county fair!");
                    farmObject.countyFair();
                    
                }
                else if (y==3.0){
                	showMessageDialog(null, "\nA fence broke on the farm! Some animals ran away and the others arent too happy :(");
                    farmObject.brokenFence();
                }
            }
        }
    }
	
	
	/**
     * Returns a string of the available items in the farm
     * @return result available items
     */
	public String stringItems() {
		String result = "";
		ArrayList<Item> items = farmObject.getItems();
		
		if (items.isEmpty()){
			result = "\nNo items in farm";
		}
		else {
			for (Item i : items) {
				result += ("\nItem: " + i.getType() + " Description: " + i.getDescription());
			}
		}
		return result;
	}
	
	
	/**
     * Returns a string of the animals in the farm
     * @return result animals in farm
     */
	public String stringAnimals() {
		String result = "";
		
		ArrayList<Animal> animals = farmObject.getAnimals();
		
		if (animals.isEmpty()){
            result = "\nNo animals in farm";
        }
        else{
            Double bonus = 0.0;
            result += ("\n" + farmObject.getName() + " has capacity for " + (farmObject.maxAnimals - animals.size()) + " more animals");
            for (Animal a : animals){
                result += ("\nAnimal: " + a.getType() + " Happiness: " + new DecimalFormat("0.00").format(a.getHappy()) + " Health: " + new DecimalFormat("0.00").format(a.getHealth()));
                bonus += a.getDailyBonus();
            }
            result += ("\nCurrent Daily income: $" + new DecimalFormat("0.00").format(bonus));
        }
		return result;
	}
	
	
	/**
     * Returns a string of the crops in the farm
     * @return result crops in farm 
     */
	public String stringCrops() {
		String result = "";
		
		ArrayList<Crop> crops = farmObject.getCrops();
		
		if (crops.isEmpty()){
            result += "\nNo crops in farm";
        }
        else{
            result += ("\n" + farmObject.getName() + " has capacity for " + (farmObject.maxCrops - farmObject.numberCrops() + " more crops"));
            for (Crop c : crops){
            	result += ("\nCrop: " + c.getType() + " x " + c.getQuantity() + " Growth: " + new DecimalFormat("0.00").format(c.getGrowth()) + " Total worth: " + new DecimalFormat("0.00").format(c.getWorth()));
            }
        }
		return result;
	}
	
	
	/**
     * Closes map window and opens the farm window window
     */
	public void toFarm() {
		map.closeWindow();
		farmView.show();
	}
	
	
	/**
     * Closes farm window and opens the map window
     */
	public void toTownMap() {
		farmView.closeWindow();
		map.show();
	}
	
	
	/**
     * Closes the setup screen and initializes the game
     * @param incomingFarm farm object
     * @param incomingFarmer farmer object
     * @param incomingDays number of days user selected
     */
	public void closeSetupScreen(Farm incomingFarm, Farmer incomingFarmer, int incomingDays) {
		farmerObject = incomingFarmer;
		farmObject = incomingFarm;
		days = incomingDays;
		setupScreen.endWindow();
		map.show();
		showMessageDialog(null, "\nStarting game with " + this.farmerObject.getName() +  "'s " + this.farmObject.getType() + " farm: " + this.farmObject.getName());
	}
	
	
	/**
     * Launches the setup screen
     */
	public void launch() {
		setupScreen.show();
	}
	

	public static void main(String[] args) {
		WindowManager manager = new WindowManager();
		manager.setupScreen.show();
	}
}
