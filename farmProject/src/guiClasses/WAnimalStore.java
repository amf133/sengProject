package guiClasses;



import java.text.DecimalFormat;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JLabel;
import static javax.swing.JOptionPane.showMessageDialog;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextArea;

import farmProject.Animal;
import farmProject.Farm;

import java.awt.Color;
import javax.swing.JComboBox;

/** 
* This class is a window for buying animals
* @author Alec, Christian
*/
public class WAnimalStore {

	private JFrame frmAnimalStore;
	private WindowManager manager;

	/**
	 * Create the application
	 * @param incomingManager window manager instance
	 * @param farmObject farm instance
	 */
	public WAnimalStore(WindowManager incomingManager, Farm farmObject) {
		manager = incomingManager;
		initialize(farmObject);
	}

	
	/**
     * Hides the current window
     */
	public void closeWindow() {
		frmAnimalStore.setVisible(false);
	}
	
	
	/**
     * Shows the current window
     */
	public void show() {
		frmAnimalStore.setVisible(true);
	}
	
	
	/**
     * Returns to town map
     */
	private void finishedWindow() {
		closeWindow();
		manager.toTownMap();
	}
	
	
	/**
     * Creates hashmap with animal type as key and cost as value, to be sold at store
     * @return animals map with animal tuples
     */
	public Map<String, Double> getAnimals(){
		Map<String, Double> animals = new HashMap<>();
		animals.put("Cow", 120.00);
		animals.put("Pig", 150.00);
		animals.put("Sheep", 100.00);
		return animals;
	}
	
	
	/**
     * Initializes all elements in the window
     * @param farmObject farm instance
     */
	private void initialize(Farm farmObject) {
		frmAnimalStore = new JFrame();
		frmAnimalStore.setTitle("Animal Store");
		frmAnimalStore.setBounds(100, 100, 471, 319);
		frmAnimalStore.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAnimalStore.getContentPane().setLayout(null);
		
		JTextArea txtView = new JTextArea();
		txtView.setBackground(Color.WHITE);
		
		txtView.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ArrayList<Animal> animals = farmObject.getAnimals();
		txtView.setText("Your Animals:");
		for (Animal a : animals) {
			txtView.append("\n" + a.getType() + ": Happiness: " + a.getHappy() + " Health: " + a.getHealth());
			}
		
		txtView.setBounds(206, 19, 203, 199);
		txtView.setEditable(false);
		frmAnimalStore.getContentPane().add(txtView);

		JLabel lblMoney = new JLabel();
		lblMoney.setBounds(24, 21, 187, 14);
		lblMoney.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMoney.setText("Balance: $" + new DecimalFormat("0.00").format(farmObject.getBal()));
		frmAnimalStore.getContentPane().add(lblMoney);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.setBackground(Color.RED);
		btnReturn.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				finishedWindow();
			}
		});
		btnReturn.setBounds(271, 233, 91, 36);
		frmAnimalStore.getContentPane().add(btnReturn);

		JComboBox<String> comboBox = new JComboBox<>();
		Map<String, Double> animal = getAnimals();
		for (String a : animal.keySet()) {
			comboBox.addItem(a + " $" + animal.get(a) + " ea");
		}
		comboBox.setBounds(24, 91, 130, 22);
		comboBox.setEditable(false);
		frmAnimalStore.getContentPane().add(comboBox);

		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String arr[] = ((String) comboBox.getSelectedItem()).split(" ");
				String type = arr[0];
				Double cost = (Double) animal.get(type);
				
				if (farmObject.getAnimals().size() >= farmObject.maxAnimals) {
					showMessageDialog(null, "No space available");
				}
				else if (farmObject.getBal() < cost) {
					showMessageDialog(null, "You do not have enough money");
				}
				else {
					Animal a = new Animal(type, cost);
					farmObject.addAnimal(a);
					farmObject.updateBal(-cost);
					txtView.append("\n" + a.getType());
					lblMoney.setText("Balance: " + new DecimalFormat("0.00").format(farmObject.getBal()));
				}
				
			}
		});
		btnConfirm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnConfirm.setBounds(24, 124, 130, 23);
		frmAnimalStore.getContentPane().add(btnConfirm);
	
		
		JLabel lblSelect = new JLabel("Select Animal:");
		lblSelect.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSelect.setBounds(24, 66, 128, 14);
		frmAnimalStore.getContentPane().add(lblSelect);
		

	}
}
