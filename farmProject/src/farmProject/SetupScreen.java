package farmProject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;
import java.awt.Color;

public class SetupScreen {

	private JFrame frmFarmSetup;
	private JTextField txtFarmer;
	private JTextField txtFarm;
	private JSlider sliderLength;
	private WindowManager manager;
	private ButtonGroup btnGroup;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SetupScreen window = new SetupScreen();
					window.frmFarmSetup.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/

	/**
	 * Create the application.
	 */
	public SetupScreen(WindowManager incomingManager) {
		manager = incomingManager;
		initialize();
		frmFarmSetup.setVisible(true);
	}
	
	
	
	public void closeWindow() {
		frmFarmSetup.dispose();
	}
	
	
	private boolean isValid(String farmName, String farmerName) { //need to add correct popups here
		boolean valid = false;
		if ( farmerName.length() < 3 || farmerName.length() > 15 ) {
        	System.out.println("Farmer name must be between 3 and 15 characters long.");
        }
		else if ( farmName.length() < 3 || farmName.length() > 15 ) {
			System.out.println("Farm name must be between 3 and 15 characters long.");
		}
		else {
			valid = true;
		}
		return valid;
	}
	
	
	
	private void finishedWindow() { //need to add code here to check if fiends have been entered correctly
		Farmer farmerObject;
		String farmerName = txtFarmer.getText();
		
		Farm farmObject;
		String farmName = txtFarm.getText();
		
		int days = sliderLength.getValue();
		int farmType;
		
		if ( isValid(farmName, farmerName) ) {
			farmerObject = new Farmer(farmerName);
			farmType = Integer.parseInt(btnGroup.getSelection().getActionCommand()); //currently selected radio button as a number
			farmObject = new Farm(farmName, farmType); //need to get selected button here
			manager.closeSetupScreen(this, farmObject, farmerObject, days);
		}
		else {
			System.out.println("Error! Could not begin farm");
		}
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFarmSetup = new JFrame();
		frmFarmSetup.setTitle("Farm Setup");
		frmFarmSetup.setBounds(100, 100, 351, 300);
		frmFarmSetup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFarmSetup.getContentPane().setLayout(null);
		
		JLabel FarmerLabel = new JLabel("Farmer Name:");
		FarmerLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		FarmerLabel.setBounds(21, 11, 93, 35);
		frmFarmSetup.getContentPane().add(FarmerLabel);
		
		JLabel LengthLabel = new JLabel("Game Length (days):");
		LengthLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		LengthLabel.setBounds(21, 64, 138, 35);
		frmFarmSetup.getContentPane().add(LengthLabel);
		
		JLabel FarmLabel = new JLabel("Farm Name:");
		FarmLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		FarmLabel.setBounds(21, 112, 93, 42);
		frmFarmSetup.getContentPane().add(FarmLabel);
		
		txtFarmer = new JTextField();
		txtFarmer.setBounds(178, 20, 138, 20);
		frmFarmSetup.getContentPane().add(txtFarmer);
		txtFarmer.setColumns(10);
		
		txtFarm = new JTextField();
		txtFarm.setBounds(178, 125, 138, 20);
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
		sdrLength.setBounds(178, 51, 138, 63);
		frmFarmSetup.getContentPane().add(sdrLength);
		sliderLength = sdrLength;
		
		JLabel BonusLabel = new JLabel("SELECT FARM BONUS");
		BonusLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		BonusLabel.setBounds(85, 165, 149, 14);
		frmFarmSetup.getContentPane().add(BonusLabel);
		
		JButton btnBEGIN = new JButton("BEGIN");
		btnBEGIN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				finishedWindow();
			}
		});
		btnBEGIN.setBounds(193, 216, 123, 34);
		frmFarmSetup.getContentPane().add(btnBEGIN);
		
		JRadioButton rdbtnGrower = new JRadioButton("Grower");
		rdbtnGrower.setBounds(21, 186, 76, 23);
		frmFarmSetup.getContentPane().add(rdbtnGrower);
		rdbtnGrower.setSelected(true);
		rdbtnGrower.setActionCommand("1");
		
		JRadioButton rdbtnStarter = new JRadioButton("Starter");
		rdbtnStarter.setBounds(109, 186, 66, 23);
		frmFarmSetup.getContentPane().add(rdbtnStarter);
		rdbtnStarter.setActionCommand("2");
		
		JRadioButton rdbtnAnimal = new JRadioButton("Animal");
		rdbtnAnimal.setBounds(182, 186, 66, 23);
		frmFarmSetup.getContentPane().add(rdbtnAnimal);
		rdbtnAnimal.setActionCommand("3");
		
		JRadioButton rdbtnLarge = new JRadioButton("Large");
		rdbtnLarge.setBounds(250, 186, 66, 23);
		frmFarmSetup.getContentPane().add(rdbtnLarge);
		rdbtnLarge.setActionCommand("4");
		
		ButtonGroup group = new ButtonGroup();
	    group.add(rdbtnGrower);
	    group.add(rdbtnStarter);
	    group.add(rdbtnAnimal);
	    group.add(rdbtnLarge);
	    btnGroup = group;
	}
}
