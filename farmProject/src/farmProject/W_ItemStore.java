package farmProject;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static javax.swing.JOptionPane.showMessageDialog;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JScrollPane;
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
	
	public Map<String, String[]> getAItems(){
		Map<String, String[]> aItems = new HashMap<>();
		
		String[] grub = new String[] {"5.00", "Increase health 20%", "0.2"};
		String[] seed = new String[] {"15.00", "Increase health 50%", "0.5"};
		String[] med = new String[] {"30.00", "Max Health", "1.0"};
		aItems.put("Grub", grub);
		aItems.put("Seeds", seed);
		aItems.put("Medicine", med);
		return aItems;
	}
	
	public Map<String, String[]> getCItems(){
		Map<String, String[]> aCtems = new HashMap<>();
		
		String[] fert = new String[] {"20.00", "Increase growth rate 20%", "0.2"};
		String[] sun = new String[] {"40.00", "Increase growth rate 50%", "0.5"};
		String[] inst = new String[] {"80.00", "Increase growth rate 100%", "1.0"};
		aCtems.put("Fertilizer", fert);
		aCtems.put("Sunglight", sun);
		aCtems.put("Instant", inst);
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
		
		ArrayList<FoodItem> foodItems = farmObject.getFoodItems();
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
		lblBal.setText("Balance: " + new DecimalFormat("0.00").format(farmObject.getBal()));
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
		Map<String, String[]> animalItems = getAItems();
		for (String a : animalItems.keySet()) {
			animalBox.addItem(a + " - " + animalItems.get(a)[1] + " - $" + Double.parseDouble(animalItems.get(a)[0]) + " ea");
		}
		animalBox.setBounds(10, 103, 322, 22);
		frmItemStore.getContentPane().add(animalBox);
		
		JComboBox<String> cropBox = new JComboBox<>();
		Map<String, String[]> cropItemss = getCItems();
		for (String c : cropItemss.keySet()) {
			cropBox.addItem(c + " - " + cropItemss.get(c)[1] + " - $" + Double.parseDouble(cropItemss.get(c)[0]) + " ea");
		}
		cropBox.setBounds(10, 263, 322, 22);
		frmItemStore.getContentPane().add(cropBox);
		
		JButton btnConfirmA = new JButton("Confirm Food");
		btnConfirmA.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String arr[] = ((String) animalBox.getSelectedItem()).split(" ");
				String type = arr[0];
				
				
				Double cost = (Double) Double.parseDouble(animalItems.get(type)[0]);
				String description = (String) animalItems.get(type)[1];
				Double benefit = (Double) Double.parseDouble(animalItems.get(type)[2]);
				
				if(farmObject.getBal() < cost) {
					showMessageDialog(null, "You do not have enough money");
				}
				else {
					Item i = new FoodItem(type, description, benefit);
					farmObject.addItem(i);
					farmObject.updateBal(-cost);
					txtAItem.append("\n" + i.getType());
					lblBal.setText("Balance: " + new DecimalFormat("0.00").format(farmObject.getBal()));
				}
			}
		});
		btnConfirmA.setBounds(79, 136, 112, 23);
		frmItemStore.getContentPane().add(btnConfirmA);
		
		JButton btnConfirmC = new JButton("Confirm Crop");
		btnConfirmC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String arr[] = ((String) cropBox.getSelectedItem()).split(" ");
				String type = arr[0];

				Double cost = (Double) Double.parseDouble(cropItemss.get(type)[0]);
				String description = (String) cropItemss.get(type)[1];
				Double benefit = (Double) Double.parseDouble(cropItemss.get(type)[2]);
				
				if(farmObject.getBal() < cost) {
					showMessageDialog(null, "You do not have enough money");
				}
				else {
					Item i = new CropItem(type, description, benefit);
					farmObject.addItem(i);
					farmObject.updateBal(-cost);
					txtCItem.append("\n" + i.getType());
					lblBal.setText("Balance: " + new DecimalFormat("0.00").format(farmObject.getBal()));
				}
			}
		});
		btnConfirmC.setBounds(79, 296, 117, 23);
		frmItemStore.getContentPane().add(btnConfirmC);
		
		JLabel lblSelectCItem = new JLabel("Select Crop Item:");
		lblSelectCItem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSelectCItem.setBounds(81, 232, 163, 20);
		frmItemStore.getContentPane().add(lblSelectCItem);
		
		JLabel lblSelectAItem = new JLabel("Select Animal Item:");
		lblSelectAItem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSelectAItem.setBounds(79, 79, 165, 14);
		frmItemStore.getContentPane().add(lblSelectAItem);

	}
}
