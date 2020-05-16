package farmProject;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;

public class W_FeedAnimals {

	private JFrame frame;
	private ArrayList<FoodItem> foodItems;


	/**
	 * Create the application.
	 */
	public W_FeedAnimals(ArrayList<FoodItem> incomingItems) {
		foodItems = incomingItems;
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
		
		JComboBox cbxItems = new JComboBox();
		cbxItems.setBounds(88, 84, 28, 20);
		frame.getContentPane().add(cbxItems);
		
		JLabel lblTitle = new JLabel("Select an item to use on your animals");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitle.setBounds(88, 27, 256, 17);
		frame.getContentPane().add(lblTitle);
	}
}
