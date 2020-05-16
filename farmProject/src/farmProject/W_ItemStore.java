package farmProject;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static javax.swing.JOptionPane.showMessageDialog;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;

public class W_ItemStore {

	private JFrame frmItemStore;
	private WindowManager manager;

	
	/**
	 * Create the application.
	 */
	public W_ItemStore(WindowManager incomingManager, Farm farmObject)  {
		manager = incomingManager;
		initialize(farmObject);
	}
	
	
	/**
     * Hides the current window
     */
	public void closeWindow() {
		frmItemStore.setVisible(false);
	}
	
	
	/**
     * Shows the current window
     */
	public void show() {
		frmItemStore.setVisible(true);
	}
	
	
	/**
     * Returns to town map
     */
	private void finishedWindow() {
		closeWindow();
		manager.toTownMap();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Farm farmObject) {
		
		frmItemStore = new JFrame();
		frmItemStore.setTitle("Item Store");
		frmItemStore.setBounds(100, 100, 565, 440);
		frmItemStore.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmItemStore.getContentPane().setLayout(null);
		
		
		
		ArrayList<FoodItem> foodItems = new ArrayList<FoodItem>();
		JTextArea txtAItem = new JTextArea();
		JScrollPane sp1 = new JScrollPane(txtAItem, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		txtAItem.setText("Your Animal items:");
		for (FoodItem f : foodItems) {
			txtAItem.append("\n" + f.getType());
		}
		txtAItem.setBounds(373, 52, 153, 107);
		txtAItem.setEditable(false);
		sp1.setBounds(373, 52, 153, 107);
		sp1.getViewport().setBackground(Color.WHITE);
		sp1.setViewportView(txtAItem);
		frmItemStore.getContentPane().add(sp1);
		
		
		ArrayList<CropItem> cropItems = farmObject.getCropItems();
		JTextArea txtCItem = new JTextArea();
		JScrollPane sp2 = new JScrollPane(txtCItem, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		txtCItem.setText("Your Crop items:");
		for (CropItem c : cropItems) {
			txtCItem.append("\n" + c.getType());
		}
		txtCItem.setBounds(373, 212, 153, 107);
		txtCItem.setEditable(false);
		sp2.setBounds(373, 212, 153, 107);
		sp2.getViewport().setBackground(Color.WHITE);
		sp2.setViewportView(txtCItem);
		frmItemStore.getContentPane().add(sp2);

		
		
		
		
		JLabel lblBal = new JLabel("Balance:");
		lblBal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBal.setBounds(10, 11, 158, 14);
		lblBal.setText("Current Balance: $" + farmObject.getBal());
		frmItemStore.getContentPane().add(lblBal);


		// BUTTONS ---------------------------------------------------------------------------------------------------------
		
		// Button to return to town map
		JButton btnReturn = new JButton("Return");
		btnReturn.setBackground(Color.RED);
		btnReturn.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				finishedWindow();
			}
		});
		btnReturn.setBounds(386, 329, 117, 39);
		frmItemStore.getContentPane().add(btnReturn);
		
		// Button to buy medicine
		JButton btnMedicine = new JButton("Medicine $50");
		double med_cost = 20.0;
		String Mdesc = "Restore all health";
		btnMedicine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (farmObject.getBal() < med_cost) {
					showMessageDialog(null, "You do not have enough money!");
				}
				else {
					Item i = new FoodItem("Medicine", Mdesc , 1.0);
					farmObject.addItem(i);
					farmObject.updateBal(-med_cost);
					txtAItem.append("\n" + i.getType());
					lblBal.setText("Current Balance: $" + farmObject.getBal());
				}
			}
		});
		btnMedicine.setBackground(Color.CYAN);
		btnMedicine.setHorizontalAlignment(SwingConstants.LEFT);
		btnMedicine.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnMedicine.setBounds(109, 121, 121, 23);
		frmItemStore.getContentPane().add(btnMedicine);
		
		// Button to buy grub
		JButton btnGrub = new JButton("Grub $20");
		double grub_cost = 20.0;
		String Gdesc= "Restore 0.2 health";
		btnGrub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (farmObject.getBal() < grub_cost) {
					showMessageDialog(null, "You do not have enough money!");
				}
				else {
					Item i = new FoodItem("Grub", Gdesc , 0.2);
					farmObject.addItem(i);
					farmObject.updateBal(-grub_cost);
					txtAItem.append("\n" + i.getType());
					lblBal.setText("Current Balance: $" + farmObject.getBal());
				}
			}
		});
		btnGrub.setBackground(Color.CYAN);
		btnGrub.setHorizontalAlignment(SwingConstants.LEFT);
		btnGrub.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGrub.setBounds(109, 52, 121, 23);
		frmItemStore.getContentPane().add(btnGrub);
		
		// Button to buy grub
		JButton btnSeeds = new JButton("Seeds $30");
		double seed_cost = 20.0;
		String Sdesc= "Restore 0.5 health";
		btnSeeds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (farmObject.getBal() < seed_cost) {
					showMessageDialog(null, "You do not have enough money!");
				}
				else {
					Item i = new FoodItem("Seed", Sdesc , 0.2);
					farmObject.addItem(i);
					farmObject.updateBal(-seed_cost);
					txtAItem.append("\n" + i.getType());
					lblBal.setText("Current Balance: $" + farmObject.getBal());
				}
			}
		});
		btnSeeds.setBackground(Color.CYAN);
		btnSeeds.setHorizontalAlignment(SwingConstants.LEFT);
		btnSeeds.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSeeds.setBounds(109, 87, 121, 23);
		frmItemStore.getContentPane().add(btnSeeds);
		
		JButton btnFertilizer = new JButton("Fertilizer  $25");
		double fert_cost = 20.0;
		String Fdesc= "Increase growth rate by 20%";
		btnFertilizer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (farmObject.getBal() < fert_cost) {
					showMessageDialog(null, "You do not have enough money!");
				}
				else {
					Item i = new CropItem("Fertilizer" , Fdesc , 0.2);
					farmObject.addItem(i);
					farmObject.updateBal(-fert_cost);
					txtCItem.append("\n" + i.getType());
					lblBal.setText("Current Balance: $" + farmObject.getBal());
				}
			}
		});
		btnFertilizer.setHorizontalAlignment(SwingConstants.LEFT);
		btnFertilizer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnFertilizer.setBounds(109, 213, 121, 23);
		frmItemStore.getContentPane().add(btnFertilizer);
		
		JButton btnSunlight = new JButton("Sunlight $50");
		double sun_cost = 50.0;
		String SuDesc = "Increase growth rate by 50%";
		btnSunlight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (farmObject.getBal() < sun_cost) {
					showMessageDialog(null, "You do not have enough money!");
				}
				else {
					Item i = new CropItem("Sunlight" , SuDesc , 0.5);
					farmObject.addItem(i);
					farmObject.updateBal(-sun_cost);
					txtCItem.append("\n" + i.getType());
					lblBal.setText("Current Balance: $" + farmObject.getBal());
				}
			}
		});
		btnSunlight.setHorizontalAlignment(SwingConstants.LEFT);
		btnSunlight.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSunlight.setBounds(108, 247, 122, 23);
		frmItemStore.getContentPane().add(btnSunlight);
		
		JButton btnInstant = new JButton("Instant $100");
		double inst_cost = 100.0;
		String Idesc= "Instantly grow crops";
		btnInstant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (farmObject.getBal() < inst_cost) {
					showMessageDialog(null, "You do not have enough money!");
				}
				else {
					Item i = new CropItem("Instant" , Idesc , 1.0);
					farmObject.addItem(i);
					farmObject.updateBal(-inst_cost);
					txtCItem.append("\n" + i.getType());
					lblBal.setText("Current Balance: $" + farmObject.getBal());
				}
			}
		});
		btnInstant.setHorizontalAlignment(SwingConstants.LEFT);
		btnInstant.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnInstant.setBounds(108, 279, 122, 23);
		frmItemStore.getContentPane().add(btnInstant);
		
		
		// LABELS ---------------------------------------------------------------------------------------------------------
		

		JLabel lblAItems = new JLabel("Animal Items:");
		lblAItems.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAItems.setBounds(10, 53, 112, 20);
		frmItemStore.getContentPane().add(lblAItems);
		
		JLabel lblCItems = new JLabel("Crops Items:\r\n\r\n");
		lblCItems.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCItems.setBounds(10, 213, 112, 23);
		frmItemStore.getContentPane().add(lblCItems);
		
		JLabel lblHealth = new JLabel("Health Given:");
		lblHealth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHealth.setBounds(260, 22, 103, 20);
		frmItemStore.getContentPane().add(lblHealth);
		
		JLabel lblGrowthIncreae = new JLabel("Growth Increase %");
		lblGrowthIncreae.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGrowthIncreae.setBounds(240, 185, 123, 23);
		frmItemStore.getContentPane().add(lblGrowthIncreae);
		
		JLabel lblGFert = new JLabel("0.25");
		lblGFert.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGFert.setBounds(278, 213, 46, 14);
		frmItemStore.getContentPane().add(lblGFert);
		
		JLabel lblGSun = new JLabel("0.5");
		lblGSun.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGSun.setBounds(278, 247, 46, 14);
		frmItemStore.getContentPane().add(lblGSun);
		
		JLabel lblGIns = new JLabel("1");
		lblGIns.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGIns.setBounds(278, 285, 46, 14);
		frmItemStore.getContentPane().add(lblGIns);
		
		JLabel lblHGrub = new JLabel("0.2\r\n\r\n");
		lblHGrub.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHGrub.setBounds(278, 58, 25, 14);
		frmItemStore.getContentPane().add(lblHGrub);
		
		JLabel lblHSeed = new JLabel("0.5");
		lblHSeed.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHSeed.setBounds(278, 93, 46, 14);
		frmItemStore.getContentPane().add(lblHSeed);
		
		JLabel lblHMed = new JLabel("1");
		lblHMed.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHMed.setBounds(278, 127, 46, 14);
		frmItemStore.getContentPane().add(lblHMed);
		

	}
}
