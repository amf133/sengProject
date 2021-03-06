package guiClasses;


import javax.swing.JFrame;
import javax.swing.JLabel;

import farmProject.Animal;
import farmProject.Crop;
import farmProject.CropItem;
import farmProject.FoodItem;

import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Font;


/** 
* This class is the actions window, clicking the actions button from the farm opens this window
* @author Alec, Christian
*/
public class WActions {

	private JFrame frame;
	WindowManager manager;
	private JLabel lblTurns;

	
	/**
	 * Create the application.
	 * @param incomingManager window manager instance
	 */
	public WActions(WindowManager incomingManager) {
		manager = incomingManager;
		initialize();
	}
	
	
	/**
	 * Hides the current window
	 */
	private void closeWindow() {
		frame.setVisible(false);
	}
	
	
	/**
	 * Shows the current window
	 */
	public void show() {
		frame.setVisible(true);
		updateTurns();
	}
	
	
	/**
	 * Updates the labels in the window
	 */
	public void updateTurns() {
		lblTurns.setText("Turns left: " + manager.getTurns());
	}
	
	
	/**
	 * Closes the window
	 */
	public void endWindow() {
		frame.dispose();
	}
	
	
	/**
	 * Opens a window to tend to crops
	 * @param crops list of crops in farm
	 * @param cropItems list of crop items in farm
	 */
	private void tendCrops(ArrayList<Crop> crops, ArrayList<CropItem> cropItems) {
		WTendCrops window = new WTendCrops(crops, cropItems, this);
	}
	
	
	/**
	 * Opens a window to feed animals
	 * @param animals list of animals in farm
	 * @param foodItems list of food items in farm
	 */
	private void feedAnimals(ArrayList<Animal> animals, ArrayList<FoodItem> foodItems) {
		WFeedAnimals window = new WFeedAnimals(animals, foodItems, this);
	}

	
	/**
	 * Returns if there is any turns left
	 * @return boolean
	 */
	private boolean anyActionsLeft() {
		return ( manager.getTurns() > 0);
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 895, 513);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				closeWindow();
			}
		});
		
		lblTurns = new JLabel("Turns left: 2");
		lblTurns.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTurns.setBounds(641, 32, 149, 14);
		frame.getContentPane().add(lblTurns);
		btnCancel.setBounds(698, 410, 149, 23);
		frame.getContentPane().add(btnCancel);
		
		JButton btnHarvestCrops = new JButton("Harvest crops");
		btnHarvestCrops.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if ( anyActionsLeft() ) {
					boolean harvest = false;
			        
			        for (Crop c : manager.farmObject.getCrops()){ //if at least one crop is ready to harvest
			            if (c.getGrowth() >= 1){
			                harvest = true;
			                break;
			            }
			        }
			        
			        if (harvest){
			            manager.harvestCrops();
						updateTurns();
			        }
			        else{
			        	showMessageDialog(null, "\nNo crops to harvest!");
			        }
				}
				else {
					showMessageDialog(null, "No actions left!");
				}
				
			}
		});
		btnHarvestCrops.setBounds(24, 161, 149, 23);
		frame.getContentPane().add(btnHarvestCrops);
		
		JButton btnPlayAnimals = new JButton("Play with animals");
		btnPlayAnimals.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if ( anyActionsLeft() ) {
					if (manager.farmObject.getAnimals().size() >= 1){
	                    manager.farmObject.playTime();
	                    manager.editTurns(-1);
	                    showMessageDialog(null, "All animals are now at their happiest!");
	                    updateTurns();
	                } 
	                else{
	                	showMessageDialog(null, "No animals to play with :(");
	                }
				}
				else {
					showMessageDialog(null, "No actions left!");
				}
				
			}
		});
		btnPlayAnimals.setBounds(93, 348, 149, 23);
		frame.getContentPane().add(btnPlayAnimals);
		
		JButton btnFeedAnimals = new JButton("Feed animals");
		btnFeedAnimals.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if ( anyActionsLeft() ) {
					// created method to get particular items
					ArrayList<FoodItem> foodItems = manager.farmObject.getFoodItems();
					ArrayList<Animal> animals = manager.farmObject.getAnimals();
					
					
					if ( foodItems.size() > 0 ) {
						if (animals.size() > 0) {
							feedAnimals(animals, foodItems);
						}
						else {
							showMessageDialog(null, "No animals to feed.");
						}
					}
					else {
						showMessageDialog(null, "No food items avaliable.");
					}
				}
				else {
					showMessageDialog(null, "No actions left!");
				}
			}
		});
		btnFeedAnimals.setBounds(367, 325, 149, 23);
		frame.getContentPane().add(btnFeedAnimals);
		
		JButton btnTendToCrops = new JButton("Tend to crops");
		btnTendToCrops.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if ( anyActionsLeft() ) {
					ArrayList<Crop> crops = manager.farmObject.getCrops();
					ArrayList<CropItem> cropItems = manager.farmObject.getCropItems();
					if (crops.size() > 0) {
						tendCrops(crops, cropItems);
					}
					else {
						showMessageDialog(null, "No crops to tend to.");
					}
				}
				else {
					showMessageDialog(null, "No actions left!");	
				}
			}
		});
		btnTendToCrops.setBounds(556, 72, 149, 23);
		frame.getContentPane().add(btnTendToCrops);
		
		JButton btnTendFarm = new JButton("Tend to farm land");
		btnTendFarm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if ( anyActionsLeft() ) {
					manager.farmObject.editHealth();
			        manager.farmObject.addSpace();
	                manager.editTurns(-1);
					String result = ("Animal capacity increased by 2, " + manager.farmObject.getName() + " has room for " + (manager.farmObject.maxAnimals - manager.farmObject.getAnimals().size())  + " more animals!");
			        result += ("\nCrop capacity increased by 20, " + manager.farmObject.getName() + " has room for " + (manager.farmObject.maxCrops - manager.farmObject.numberCrops())  + " more crops!");
			        result += "\nAnimals feel more comfortable, now their happiness drains slower.";
					showMessageDialog(null, result);
					updateTurns();
				}
				else {
					showMessageDialog(null, "No actions left!");
				}
			}
		});
		btnTendFarm.setBounds(720, 269, 149, 23);
		frame.getContentPane().add(btnTendFarm);
		
		JLabel lblTractorImage = new JLabel("");
		lblTractorImage.setIcon(new ImageIcon(WActions.class.getResource("/Images/tractor.jpg")));
		lblTractorImage.setBounds(0, 0, 879, 474);
		frame.getContentPane().add(lblTractorImage);
	}
}
