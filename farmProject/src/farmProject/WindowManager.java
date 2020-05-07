package farmProject;

public class WindowManager {
	private Farm farmObject;
	private Farmer farmerObject;
    private int days;
    private int turns = 2;
    private Double EVENTCHANCE = 5.0;
	
	
	

	public void launchSetupScreen() {
		SetupScreen setupWindow = new SetupScreen(this);
	}
	
	
	public void closeSetupScreen(SetupScreen setupWindow, Farm incomingFarm, Farmer incomingFarmer, int incomingDays) {
		farmerObject = incomingFarmer;
		farmObject = incomingFarm;
		days = incomingDays;
		setupWindow.closeWindow();
		TownMap map = new TownMap(this);
	}
	
	
	public static void main(String[] args) {
		WindowManager manager = new WindowManager();
		manager.launchSetupScreen();
	}
}
