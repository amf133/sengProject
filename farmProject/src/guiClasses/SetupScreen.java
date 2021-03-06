package guiClasses;

import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import farmProject.Farm;
import farmProject.Farmer;

import javax.swing.JSlider;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;
import java.awt.Color;

/** 
* This class is the window the user will first see when starting the game
* @author Alec, Christian
*/

public class SetupScreen {

	private JFrame frmFarmSetup;
	private JTextField txtFarmerFirst;
	private JTextField txtFarm;
	private JSlider sliderLength;
	private WindowManager manager;
	private ButtonGroup btnGroup;
	private JTextField txbFarmerAge;

	/**
	 * Create the application.
	 * @param incomingManager window manager instance
	 */
	public SetupScreen(WindowManager incomingManager) {
		manager = incomingManager;
		initialize();
	}
	
	
	/**
	 * Deletes the current window
	 */
	public void endWindow() {
		frmFarmSetup.dispose();
	}
	
	
	/**
	 * Shows the current window
	 */
	public void show(){
		frmFarmSetup.setVisible(true);
	}
	
	
	/**
	 * Determines whether the input strings are valid
	 * @param farmName a string of the farm name
	 * @param farmerName a string of the farmers name
	 * @param farmerAge a string of the farmers age
	 * @return boolean which determines whether the input strings are valid
	 */
	private boolean isValid(String farmName, String farmerName, String farmerAge) {
		boolean valid = false;
		
		try {
			if ( farmerName.length() < 3 || farmerName.length() > 15 ) {
				showMessageDialog(null, "Farmer name must be between 3 and 15 characters long.");
	        }
			else if ( farmName.length() < 3 || farmName.length() > 15 ) {
				showMessageDialog(null, "Farm name must be between 3 and 15 characters long.");
			}
			
			else if (!farmerName.matches("[a-zA-Z]+")){
				showMessageDialog(null, "Farmer name contains illicit characters");
			}
			else if ( Integer.parseInt(farmerAge) < 0 ) {
				
				showMessageDialog(null, "Please enter a valid age.");
			}
			else {
				valid = true;
			}
		}
		catch (NumberFormatException e) {
			showMessageDialog(null, "Please enter a valid age.");
		}
		return valid;
	}
	
	
	/**
	 * Closes the current window and passes the farm, farmer to the window manager
	 */
	private void finishedWindow() {
		Farmer farmerObject;
		String farmerName = txtFarmerFirst.getText();
		String farmerAge = txbFarmerAge.getText();
		
		Farm farmObject;
		String farmName = txtFarm.getText();
		
		int days = sliderLength.getValue();
		int farmType;
		
		if ( isValid(farmName, farmerName, farmerAge) ) {
			farmerObject = new Farmer(farmerName, Integer.parseInt(farmerAge));
			farmType = Integer.parseInt(btnGroup.getSelection().getActionCommand()); //currently selected radio button as a number
			farmObject = new Farm(farmName, farmType);
			manager.closeSetupScreen(farmObject, farmerObject, days);
		}
		else {
			showMessageDialog(null, "Error! Could not begin game");
		}
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFarmSetup = new JFrame();
		frmFarmSetup.setTitle("Farm Setup");
		frmFarmSetup.setBounds(100, 100, 351, 494);
		frmFarmSetup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFarmSetup.getContentPane().setLayout(null);
		
		JLabel lblFirst = new JLabel("Farmer Name (first):");
		lblFirst.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFirst.setBounds(21, 33, 123, 17);
		frmFarmSetup.getContentPane().add(lblFirst);
		
		JLabel lengthLabel = new JLabel("Game Length (days):");
		lengthLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lengthLabel.setBounds(21, 135, 138, 35);
		frmFarmSetup.getContentPane().add(lengthLabel);
		
		JLabel farmLabel = new JLabel("Farm Name:");
		farmLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		farmLabel.setBounds(21, 183, 93, 42);
		frmFarmSetup.getContentPane().add(farmLabel);
		
		txtFarmerFirst = new JTextField();
		txtFarmerFirst.setBounds(178, 33, 138, 20);
		frmFarmSetup.getContentPane().add(txtFarmerFirst);
		txtFarmerFirst.setColumns(10);
		
		txtFarm = new JTextField();
		txtFarm.setBounds(178, 196, 138, 20);
		frmFarmSetup.getContentPane().add(txtFarm);
		txtFarm.setColumns(10);
		
		JSlider sdrLength = new JSlider();
		sdrLength.setForeground(Color.BLACK);
		sdrLength.setMajorTickSpacing(1);
		sdrLength.setMinorTickSpacing(1);
		sdrLength.setPaintTicks(true);
		sdrLength.setPaintLabels(true);
		sdrLength.setValue(5);
		sdrLength.setMinimum(5);
		sdrLength.setMaximum(10);
		sdrLength.setBounds(178, 122, 138, 63);
		frmFarmSetup.getContentPane().add(sdrLength);
		sliderLength = sdrLength;
		
		JLabel bonusLabel = new JLabel("SELECT FARM BONUS");
		bonusLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bonusLabel.setBounds(85, 236, 149, 14);
		frmFarmSetup.getContentPane().add(bonusLabel);
		
		JButton btnBEGIN = new JButton("BEGIN");
		btnBEGIN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				finishedWindow();
			}
		});
		btnBEGIN.setBounds(113, 410, 123, 34);
		frmFarmSetup.getContentPane().add(btnBEGIN);
		
		JRadioButton rdbtnGrower = new JRadioButton("Grower");
		rdbtnGrower.setBounds(48, 257, 76, 23);
		frmFarmSetup.getContentPane().add(rdbtnGrower);
		rdbtnGrower.setSelected(true);
		rdbtnGrower.setActionCommand("1");
		
		JRadioButton rdbtnStarter = new JRadioButton("Starter");
		rdbtnStarter.setBounds(48, 290, 66, 23);
		frmFarmSetup.getContentPane().add(rdbtnStarter);
		rdbtnStarter.setActionCommand("3");
		
		JRadioButton rdbtnAnimal = new JRadioButton("Animal");
		rdbtnAnimal.setBounds(48, 327, 66, 23);
		frmFarmSetup.getContentPane().add(rdbtnAnimal);
		rdbtnAnimal.setActionCommand("2");
		
		JRadioButton rdbtnLarge = new JRadioButton("Large");
		rdbtnLarge.setBounds(48, 364, 66, 23);
		frmFarmSetup.getContentPane().add(rdbtnLarge);
		rdbtnLarge.setActionCommand("4");
		
		ButtonGroup group = new ButtonGroup();
	    group.add(rdbtnGrower);
	    group.add(rdbtnStarter);
	    group.add(rdbtnAnimal);
	    group.add(rdbtnLarge);
	    
	    JLabel lblAge = new JLabel("Farmer Age:");
	    lblAge.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblAge.setBounds(21, 79, 121, 17);
	    frmFarmSetup.getContentPane().add(lblAge);
	    
	    txbFarmerAge = new JTextField();
	    txbFarmerAge.setColumns(10);
	    txbFarmerAge.setBounds(178, 79, 138, 20);
	    frmFarmSetup.getContentPane().add(txbFarmerAge);
	    
	    JLabel lblBonus1 = new JLabel("Faster crop growth");
	    lblBonus1.setBounds(140, 261, 121, 14);
	    frmFarmSetup.getContentPane().add(lblBonus1);
	    
	    JLabel lblBonus2 = new JLabel("More starting cash");
	    lblBonus2.setBounds(140, 294, 121, 14);
	    frmFarmSetup.getContentPane().add(lblBonus2);
	    
	    JLabel lblBonus3 = new JLabel("Happier animals");
	    lblBonus3.setBounds(140, 331, 121, 14);
	    frmFarmSetup.getContentPane().add(lblBonus3);
	    
	    JLabel lblBonus4 = new JLabel("Larger farm capacity");
	    lblBonus4.setBounds(140, 368, 121, 14);
	    frmFarmSetup.getContentPane().add(lblBonus4);
	    btnGroup = group;
	}
}
