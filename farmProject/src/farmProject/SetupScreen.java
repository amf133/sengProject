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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SetupScreen {

	private JFrame frmFarmSetup;
	private JTextField txtFarmer;
	private JTextField txtFarm;
	private JTextField txtBonus;
	private WindowManager manager;

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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFarmSetup = new JFrame();
		frmFarmSetup.setTitle("Farm Setup");
		frmFarmSetup.setBounds(100, 100, 450, 300);
		frmFarmSetup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFarmSetup.getContentPane().setLayout(null);
		
		JLabel FarmerLabel = new JLabel("Farmer Name:");
		FarmerLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		FarmerLabel.setBounds(21, 11, 93, 35);
		frmFarmSetup.getContentPane().add(FarmerLabel);
		
		JLabel LengthLabel = new JLabel("Game Length:");
		LengthLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		LengthLabel.setBounds(21, 45, 93, 35);
		frmFarmSetup.getContentPane().add(LengthLabel);
		
		JLabel FarmLabel = new JLabel("Farm Name:");
		FarmLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		FarmLabel.setBounds(21, 80, 93, 42);
		frmFarmSetup.getContentPane().add(FarmLabel);
		
		txtFarmer = new JTextField();
		txtFarmer.setBounds(124, 20, 138, 20);
		frmFarmSetup.getContentPane().add(txtFarmer);
		txtFarmer.setColumns(10);
		
		txtFarm = new JTextField();
		txtFarm.setBounds(124, 93, 138, 20);
		frmFarmSetup.getContentPane().add(txtFarm);
		txtFarm.setColumns(10);
		
		JSlider sdrLength = new JSlider();
		sdrLength.setValue(3);
		sdrLength.setMinimum(3);
		sdrLength.setMaximum(10);
		sdrLength.setBounds(124, 54, 138, 26);
		frmFarmSetup.getContentPane().add(sdrLength);
		
		JLabel BonusLabel = new JLabel("SELECT FARM BONUS");
		BonusLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		BonusLabel.setBounds(153, 130, 149, 14);
		frmFarmSetup.getContentPane().add(BonusLabel);
		
		JButton btnGrower = new JButton("GROWER");
		btnGrower.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnGrower.setBounds(30, 155, 83, 42);
		frmFarmSetup.getContentPane().add(btnGrower);
		
		JButton btnAnimal = new JButton("ANIMAL");
		btnAnimal.setBounds(125, 155, 83, 42);
		frmFarmSetup.getContentPane().add(btnAnimal);
		
		JButton btnStart = new JButton("STARTER");
		btnStart.setBounds(218, 155, 83, 42);
		frmFarmSetup.getContentPane().add(btnStart);
		
		JButton btnLarger = new JButton("LARGER");
		btnLarger.setBounds(308, 155, 83, 42);
		frmFarmSetup.getContentPane().add(btnLarger);
		
		JLabel lblBonus = new JLabel("Selected Bonus:");
		lblBonus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBonus.setBounds(21, 221, 105, 14);
		frmFarmSetup.getContentPane().add(lblBonus);
		
		txtBonus = new JTextField();
		txtBonus.setText("NONE");
		txtBonus.setBounds(132, 220, 86, 20);
		frmFarmSetup.getContentPane().add(txtBonus);
		txtBonus.setColumns(10);
		
		JButton btnBEGIN = new JButton("BEGIN");
		btnBEGIN.setBounds(228, 208, 163, 34);
		frmFarmSetup.getContentPane().add(btnBEGIN);
	}
}
