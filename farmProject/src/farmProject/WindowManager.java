package farmProject;

import static javax.swing.JOptionPane.showMessageDialog;

import java.util.ArrayList;

public class WindowManager {
	Farm farmObject; //need to be public
	Farmer farmerObject;
    private int days;
    private int turns = 2;
    private Double EVENTCHANCE = 5.0;
	
	
	

	public void launchSetupScreen() {
		SetupScreen setupScreen = new SetupScreen(this);
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
            farmObject.viewAnimals();
            farmObject.viewCrops();
            farmObject.viewBal();
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
	
	
	
	public void toFarm(TownMap window) {
		window.closeWindow();
		FarmView farmView = new FarmView(this);
	}
	
	
	public void toTownMap(FarmView window) {
		window.closeWindow();
		TownMap townMap = new TownMap(this);
	}
	
	
	public void closeSetupScreen(SetupScreen setupWindow, Farm incomingFarm, Farmer incomingFarmer, int incomingDays) {
		farmerObject = incomingFarmer;
		farmObject = incomingFarm;
		days = incomingDays;
		setupWindow.closeWindow();
		TownMap map = new TownMap(this);
		showMessageDialog(null, "\nStarting game with " + this.farmerObject.getName() +  "'s " + this.farmObject.getType() + " farm: " + this.farmObject.getName());
	}
	public void viewAnimalStore() {
		W_AnimalStore aStore = new W_AnimalStore(farmObject);
		
		
		
	}
	
	public static void main(String[] args) {
		WindowManager manager = new WindowManager();
		manager.launchSetupScreen();
	}



}
