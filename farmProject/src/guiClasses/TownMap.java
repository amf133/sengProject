package guiClasses;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JPanel;


/** 
* This class is a window of the town map, seen when playing the game
* @author Alec, Christian
*/

@SuppressWarnings("unused")
public class TownMap {

	private JFrame frmTownMap;
	private JLabel lblBal;
	private WindowManager manager;

	/**
	 * Create the application.
	 * @param incomingManager window manager instance
	 */
	public TownMap(WindowManager incomingManager) {
		manager = incomingManager;
		initialize();
	}
	
	
	/**
	 * Goes to the animal store
	 */
	public void storeAnimal() {
		manager.viewAnimalStore();
	}
	
	
	/**
	 * Goes to the crop store
	 */
	public void storeCrop() {
		manager.viewCropStore();
	}
	
	
	/**
	 * Deletes the window
	 */
	public void endWindow() {
		frmTownMap.dispose();
	}
	
	
	/**
	 * Goes to the item store
	 */
	public void storeItem() {
		manager.viewItemStore();
	}
	
	
	/**
	 * Shows the current window
	 */
	public void show() {
		frmTownMap.setVisible(true);
		updateBal();
	}
	
	
	/**
	 * Updates the balance label
	 */
	private void updateBal() {
		lblBal.setText("Balance: $" + new DecimalFormat("0.00").format(manager.farmObject.getBal()));
	}
		
	
	/**
	 * Hides the current window
	 */
	public void closeWindow() {
		frmTownMap.setVisible(false);
	}
	
	
	/**
	 * Goes to the farm
	 */
	private void finishedWindow() {
		manager.toFarm();
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTownMap = new JFrame();
		frmTownMap.setTitle("Town Map");
		frmTownMap.setBounds(100, 100, 795, 476);
		frmTownMap.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTownMap.getContentPane().setLayout(null);
		
		JButton btnFarm = new JButton("Farm");
		btnFarm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				finishedWindow();
			}
		});
		
		lblBal = new JLabel("Balance: $0");
		lblBal.setBounds(21, 11, 243, 22);
		frmTownMap.getContentPane().add(lblBal);
		lblBal.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBal.setForeground(Color.WHITE);
		btnFarm.setBounds(159, 233, 130, 23);
		frmTownMap.getContentPane().add(btnFarm);
			
		JButton btnStoreAnimal = new JButton("Animal store");
		btnStoreAnimal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				storeAnimal();
			}
		});
		btnStoreAnimal.setBounds(497, 233, 130, 23);
		frmTownMap.getContentPane().add(btnStoreAnimal);
		
		JButton btnStoreCrop = new JButton("Crop store");
		btnStoreCrop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				storeCrop();
			}
		});
		btnStoreCrop.setBounds(474, 98, 130, 23);
		frmTownMap.getContentPane().add(btnStoreCrop);
		
		JButton btnStoreItem = new JButton("Item store");
		btnStoreItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				storeItem();
			}
		});
		btnStoreItem.setBounds(284, 351, 130, 23);
		frmTownMap.getContentPane().add(btnStoreItem);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 259, 43);
		frmTownMap.getContentPane().add(panel);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setVerticalAlignment(SwingConstants.TOP);
		lblBackground.setIcon(new ImageIcon(TownMap.class.getResource("/Images/shocker.jpg")));
		lblBackground.setBounds(0, 0, 890, 444);
		frmTownMap.getContentPane().add(lblBackground);
	}
}
