package farmProject;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
import javax.swing.JComboBox;

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
	public void closeWindow() {
		frmItemStore.setVisible(false);
	}
	
	public void show() {
		frmItemStore.setVisible(true);
	}
	
	private void finishedWindow() {
		closeWindow();
		manager.toTownMap();
	}
	
	public Map<String, Double> getAItems(){
		Map<String, Double> aItems = new HashMap<>();
		aItems.put("Grub", 20.0);
		aItems.put("Seeds", 30.0);
		aItems.put("Medicine", 40.0);
		return aItems;
	}
	
	public Map<String, Double> getCItems(){
		Map<String, Double> aCtems = new HashMap<>();
		aCtems.put("Fertilizer", 20.0);
		aCtems.put("Sunglight", 50.0);
		aCtems.put("Instant Growth", 100.0);
		return aCtems;
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
			
		JComboBox<String> animalBox = new JComboBox<>();
		Map<String, Double> animalItems = getAItems();
		for (String i : animalItems.keySet()) {
			animalBox.addItem(i + " $" + animalItems.get(i) + " ea");
		}
		animalBox.setBounds(10, 89, 89, 22);
		frmItemStore.getContentPane().add(animalBox);
		
		JComboBox<String> cropBox = new JComboBox<>();
		Map<String, Double> cropItemss = getAItems();
		for (String i : cropItemss.keySet()) {
			cropBox.addItem(i + " $" + cropItemss.get(i) + " ea");
		}
		cropBox.setBounds(10, 249, 88, 22);
		frmItemStore.getContentPane().add(cropBox);
		
		JButton btnConfirmA = new JButton("Confirm Food");
		btnConfirmA.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String arr[] = ((String) animalBox.getSelectedItem()).split(" ");
				String type = arr[0];
				Double cost = (Double) cropItemss.get(type);
				
				if(farmObject.getBal() < cost) {
					showMessageDialog(null, "You do not have enough money");
				}
				else {
					Item f = new FoodItem(type, cost);
					farmObject.addItem(f);
				}
		btnConfirmA.setBounds(113, 89, 112, 23);
		frmItemStore.getContentPane().add(btnConfirmA);
		
		JButton btnConfirmC = new JButton("Confirm Crop");
		btnConfirmC.setBounds(108, 249, 112, 23);
		frmItemStore.getContentPane().add(btnConfirmC);
		
		
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
