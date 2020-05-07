package farmProject;

public class WindowManager {

	public void launchSetupScreen() {
		SetupScreen setupWindow = new SetupScreen(this);
	}
	
	
	public void closeSetupScreen(SetupScreen setupWindow) {
		setupWindow.closeWindow();
		TownMap map = new TownMap(this);
	}
	
	
	public static void main(String[] args) {
		WindowManager manager = new WindowManager();
		manager.launchSetupScreen();
	}
}
