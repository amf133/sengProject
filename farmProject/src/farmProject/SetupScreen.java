package farmProject;

import java.awt.EventQueue;
import static javax.swing.JOptionPane.showMessageDialog;
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
	private JTextField txtFarmerFirst;
	private JTextField txtFarm;
	private JSlider sliderLength;
	private WindowManager manager;
	private ButtonGroup btnGroup;
	private JTextField txbFarmerAge;

	/**
	 * Create the application.
	 */
	public SetupScreen(WindowManager incomingManager) {
		manager = incomingManager;
		initialize();
	}
	
	
	
	public void endWindow() {
		frmFarmSetup.dispose();
	}
	
	public void show(){
		frmFarmSetup.setVisible(true);
	}
	
	
	private boolean isValid(String farmName, String farmerName, String farmerAge) {
		boolean valid = false;
		
		try {
			if ( farmerName.length() < 3 || farmerName.length() > 15 ) {
				showMessageDialog(null, "Farmer name must be between 3 and 15 characters long.");
	        }
			else if ( farmName.length() < 3 || farmName.length() > 15 ) {
				showMessageDialog(null, "Farm name must be between 3 and 15 characters long.");
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
		
		JLabel LengthLabel = new JLabel("Game Length (days):");
		LengthLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		LengthLabel.setBounds(21, 135, 138, 35);
		frmFarmSetup.getContentPane().add(LengthLabel);
		
		JLabel FarmLabel = new JLabel("Farm Name:");
		FarmLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		FarmLabel.setBounds(21, 183, 93, 42);
		frmFarmSetup.getContentPane().add(FarmLabel);
		
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
		
		JLabel BonusLabel = new JLabel("SELECT FARM BONUS");
		BonusLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		BonusLabel.setBounds(85, 236, 149, 14);
		frmFarmSetup.getContentPane().add(BonusLabel);
		
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
	    
	    JLabel lblBonus_1 = new JLabel("Faster crop growth");
	    lblBonus_1.setBounds(140, 261, 121, 14);
	    frmFarmSetup.getContentPane().add(lblBonus_1);
	    
	    JLabel lblBonus_2 = new JLabel("More starting cash");
	    lblBonus_2.setBounds(140, 294, 121, 14);
	    frmFarmSetup.getContentPane().add(lblBonus_2);
	    
	    JLabel lblBonus_3 = new JLabel("Happier animals");
	    lblBonus_3.setBounds(140, 331, 121, 14);
	    frmFarmSetup.getContentPane().add(lblBonus_3);
	    
	    JLabel lblBonus_4 = new JLabel("Larger farm capacity");
	    lblBonus_4.setBounds(140, 368, 121, 14);
	    frmFarmSetup.getContentPane().add(lblBonus_4);
	    btnGroup = group;
	}
}
