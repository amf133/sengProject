package guiClasses;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import static javax.swing.JOptionPane.showMessageDialog;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import farmProject.Crop;
import farmProject.Farm;

import javax.swing.JTextArea;


/** 
* This class is a window where the user buys crops
* @author Alec, Christian
*/
public class WCropStore {

	private JFrame frmCropStore;
	private WindowManager manager;
	private JTextField txtQuant;
	private JTextArea txtCrops;
	
	
	/**
	 * Create the application.
	 * @param incomingManager window manager instance
	 * @param farmObject farm instance
	 */
	public WCropStore(WindowManager incomingManager, Farm farmObject)  {
		manager = incomingManager;
		initialize(farmObject);
	}
	
	
	/**
     * Hides current window
     */
	public void closeWindow() {
		frmCropStore.setVisible(false);
	}
	
	
	/**
     * Shows current window
     */
	public void show() {
		frmCropStore.setVisible(true);
	}
	
	
	/**
     * Returns to town map
     */
	private void finishedWindow() {
		closeWindow();
		manager.toTownMap();
	}
	
	
	/**
     * Creates hash map with crop type as key and its cost as value, to be sold at store
     * @return cropTypes maps with crop tuples
     */
	public Map<String, Double> getCropTypes(){
		Map<String, Double> cropTypes = new HashMap<>();
		cropTypes.put("Carrot", 5.00);
		cropTypes.put("Wheat", 3.00);
		cropTypes.put("Potatoes", 2.00);
		cropTypes.put("Pumpkins", 10.00);
		cropTypes.put("Melon", 8.00);
		cropTypes.put("Beetroot", 2.00);
		return cropTypes;
	}	

	/**
	 * Initialize the contents of the frame.
	 * @param farmObject farmInstance
	 */
	private void initialize(Farm farmObject) {
		frmCropStore = new JFrame();
		frmCropStore.setBounds(100, 100, 450, 300);
		frmCropStore.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCropStore.getContentPane().setLayout(null);
		
		JLabel lblBal = new JLabel("Balance:");
		lblBal.setText("Balance: " + new DecimalFormat("0.00").format(farmObject.getBal()));
		lblBal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBal.setBounds(10, 11, 110, 22);
		frmCropStore.getContentPane().add(lblBal);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.setBackground(Color.RED);
		btnReturn.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				finishedWindow();
			}
		});
		btnReturn.setBounds(273, 214, 89, 23);
		frmCropStore.getContentPane().add(btnReturn);
		
		JLabel lblCrop = new JLabel("Select Crop:");
		lblCrop.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCrop.setBounds(10, 50, 75, 23);
		frmCropStore.getContentPane().add(lblCrop);
		
		
		
		JComboBox<String> comboBox = new JComboBox<>();
		Map<String, Double> cropType = getCropTypes();
		for (String crop  : cropType.keySet()) {
			comboBox.addItem(crop + " $" + cropType.get(crop) + "ea");
		}
		comboBox.setBounds(96, 52, 133, 22);
		comboBox.setEditable(false);
		frmCropStore.getContentPane().add(comboBox);

		
		JLabel lblQuantity = new JLabel("How many?");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQuantity.setBounds(10, 107, 75, 23);
		frmCropStore.getContentPane().add(lblQuantity);
		
		txtQuant = new JTextField();
		txtQuant.setText("0");
		txtQuant.setBounds(96, 110, 89, 20);
		frmCropStore.getContentPane().add(txtQuant);
		txtQuant.setColumns(10);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String arr[] = ((String) comboBox.getSelectedItem()).split(" ");
				String crop = arr[0];
				Double cost = (Double) cropType.get(crop);
				int quantity = Integer.parseInt(txtQuant.getText());
				double totalPrice = cost * quantity;
				
				int space = (int) (farmObject.maxCrops - farmObject.numberCrops());
				
				
				if (quantity > space){
					if (space == 0) {
						showMessageDialog(null, "You have no more space in farm");
					}
					else {
						showMessageDialog(null, "You only have room for " + space + " more crops");
					}
				}
				else if (quantity <= 0 || quantity > 100) {
					showMessageDialog(null, "Please select quantity between 1 - 50 ");
				}
				else {

					if (totalPrice <= farmObject.getBal()) {
						Crop c = new Crop(crop, cost, quantity);
						farmObject.addCrop(c);
						farmObject.updateBal(-totalPrice);
						txtCrops.append("\n" + c.getQuantity() + " " + c.getType());
						lblBal.setText("Balance: " + new DecimalFormat("0.00").format(farmObject.getBal()));	
					}
					else {
						showMessageDialog(null, "You do not have enough money");	
					}		
				}
			}
		});
		btnConfirm.setBounds(96, 168, 89, 23);
		frmCropStore.getContentPane().add(btnConfirm);
		
		
		txtCrops = new JTextArea();
		txtCrops.setText("Your Crops:");
		for (Crop c : farmObject.getCrops()) {
			txtCrops.append("\n" + c.getQuantity() + " " + c.getType());
		}
		txtCrops.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCrops.setEditable(false);
		txtCrops.setBounds(248, 51, 157, 139);
		frmCropStore.getContentPane().add(txtCrops);
		

	}
}
