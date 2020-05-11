package farmProject;

import static javax.swing.JOptionPane.showMessageDialog;

import java.util.ArrayList;

public class WindowManager {
	public Farm farmObject; //need to be public
	Farmer farmerObject;
    private int days;
    private int turns = 2;
    private Double EVENTCHANCE = 5.0;
    private SetupScreen setupScreen = new SetupScreen(this);
    private TownMap map = new TownMap(this);
    private FarmView farmView = new FarmView(this);
    private W_Actions actionWindow = new W_Actions(this);
    


	public void launchSetupScreen() {
		setupScreen.show();
	}
	
	public void openActions() {
		actionWindow.show();
	}
	
	public void editTurns(int incomingNum) {
		turns += incomingNum;
	}
	
	public int getTurns() {
    	return turns;
    }
	
	public void viewAnimalStore() {
		W_AnimalStore aStore = new W_AnimalStore(this, farmObject);
		map.closeWindow();
		aStore.show();
	}
	
	public void viewCropStore() {
		W_CropStore cStore = new W_CropStore(this, farmObject);
		map.closeWindow();
		cStore.show();
	}
	
	public void viewItemStore() {
		W_ItemStore iStore = new W_ItemStore(this, farmObject);
		map.closeWindow();
		iStore.show();
	}
	
	public void harvestCrops() {
		boolean harvest = false;
        
        for (Crop c : farmObject.getCrops()){ //if at least one crop is ready to harvest
            if (c.getGrowth() >= 1){
                harvest = true;
                break;
            }
        }
        
        if (harvest){
            farmObject.harvest();
            turns -= 1;
            showMessageDialog(null, "New farm balance: " + farmObject.getBal());
        }
        else{
        	showMessageDialog(null, "\nNo crops to harvest!");
        }
	}
	
	
	public void newDay(){
        turns = 2;
        days -= 1;
        
        if (days <= 0){
            //if game ends must display some sort of score eg profit made + num animals display game duration, num crops/animals, money earner
        }
        else{
        	showMessageDialog(null, "New day, days left: " + (days-1));
            farmObject.newDay();
            double x = Farm.getRandomDoubleBetweenRange(1.0, EVENTCHANCE); //1 in EVENTCHANCE chance of an event occuring
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
                result += ("\nAnimal: " + a.getType() + " Happiness: " + a.getHappy() + " Health: " + a.getHealth());
                bonus += a.getDailyBonus();
            }
            result += ("\nCurrent Daily income: $" + bonus);
        }
		return result;
	}
	
	
	
	public String stringCrops() {
		String result = "";
		
		ArrayList<Crop> crops = farmObject.getCrops();
		
		if (crops.isEmpty()){
            result += "\nNo crops in farm";
        }
        else{
            result += ("\n" + farmObject.getName() + " has capacity for " + (farmObject.maxCrops - farmObject.numberCrops() + " more crops"));
            for (Crop c : crops){
            result += ("\nCrop: " + c.getType() + " x " + c.getQuantity() + " Growth: " + c.getGrowth() + " Total worth: " + c.getWorth());
            }
        }
		return result;
	}
	
	
	
	public void toFarm() {
		map.closeWindow();
		farmView.show();
	}
	
	
	public void toTownMap() {
		farmView.closeWindow();
		map.show();
	}
	
	
	public void closeSetupScreen(Farm incomingFarm, Farmer incomingFarmer, int incomingDays) {
		farmerObject = incomingFarmer;
		farmObject = incomingFarm;
		days = incomingDays;
		setupScreen.closeWindow();
		map.show();
		showMessageDialog(null, "\nStarting game with " + this.farmerObject.getName() +  "'s " + this.farmObject.getType() + " farm: " + this.farmObject.getName());
	}
	

	public static void main(String[] args) {
		WindowManager manager = new WindowManager();
		manager.launchSetupScreen();
	}



}
