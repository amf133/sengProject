package guiClasses;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import farmProject.Animal;
import farmProject.FoodItem;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/** 
* This class is a window where the user can feed their animals
* @author Alec, Christian
*/
public class WFeedAnimals {

	private JFrame frame;
	private ArrayList<FoodItem> foodItems;
	private ArrayList<Animal> animals;
	private WActions parent;


	/**
	 * Create the application.
	 * @param incomingAnimals animals in farm
	 * @param incomingItems items in farm
	 * @param incomingParent parent window
	 */
	public WFeedAnimals(ArrayList<Animal> incomingAnimals, ArrayList<FoodItem> incomingItems, WActions incomingParent) {
		foodItems = incomingItems;
		animals = incomingAnimals;
		parent = incomingParent;
		initialize();
		frame.setVisible(true);
	}
	
	
	/**
     * Shows current window
     */
	public void show() {
		frame.setVisible(true);
	}
	
	
	/**
     * Deletes current window
     */
	public void endWindow() {
		frame.dispose();
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JComboBox<Animal> cbxAnimals = new JComboBox<Animal>();
		for (Animal a : animals) {
			cbxAnimals.addItem(a);
		}
		cbxAnimals.setBounds(22, 84, 133, 20);
		frame.getContentPane().add(cbxAnimals);
		
		JLabel lblTitle = new JLabel("Select an item to use on an animal");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitle.setBounds(88, 27, 256, 17);
		frame.getContentPane().add(lblTitle);
		
		JComboBox<FoodItem> cbxItems = new JComboBox<FoodItem>();
		for (FoodItem i : foodItems) {
			cbxItems.addItem(i);
		}
		cbxItems.setBounds(260, 84, 133, 20);
		frame.getContentPane().add(cbxItems);
		
		JLabel lblSelectAnimal = new JLabel("Select Animal:");
		lblSelectAnimal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSelectAnimal.setBounds(40, 57, 90, 26);
		frame.getContentPane().add(lblSelectAnimal);
		
		JLabel lblSelectItem = new JLabel("Select Item:");
		lblSelectItem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSelectItem.setBounds(285, 55, 90, 26);
		frame.getContentPane().add(lblSelectItem);
		
		JButton btnApply = new JButton("Apply Item");
		btnApply.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Animal animal = (Animal) cbxAnimals.getSelectedItem();
				FoodItem item = (FoodItem) cbxItems.getSelectedItem();
				int confirm = JOptionPane.showConfirmDialog(null, "Apply " + item.getType() + " to " + animal.getHealth() + " " + animal.getType(),
						null, JOptionPane.YES_NO_CANCEL_OPTION);
		
				if (confirm == 0) {
					animal.editHealth(item.getBenefit());
					foodItems.remove(item);
					parent.manager.farmObject.removeItem(item);
					cbxItems.removeItem(item);
					parent.manager.editTurns(-1);
					parent.updateTurns();
					endWindow();
				}
			}
		});
		btnApply.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnApply.setBounds(148, 161, 111, 42);
		frame.getContentPane().add(btnApply);
	}
}
