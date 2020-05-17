package farmProject;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class W_TendCrops {

	private JFrame frame;
	private ArrayList<Crop> crops;
	private ArrayList<CropItem> cropItems;
	private W_Actions parent;

	
	/**
	 * Create the application.
	 */
	public W_TendCrops(ArrayList<Crop> cropss, ArrayList<CropItem> incomingItems, W_Actions incomingParent) {
		crops = cropss;
		cropItems = incomingItems;
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
		
		JLabel lblCrop = new JLabel("Select Crop:");
		lblCrop.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCrop.setBounds(56, 21, 90, 26);
		frame.getContentPane().add(lblCrop);
		
		JLabel lblNewLabel = new JLabel("Select Item:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(258, 27, 81, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JComboBox<Crop> cbCrop = new JComboBox<Crop>();
		for (Crop c : crops) {
			cbCrop.addItem(c);
		}
		cbCrop.setBounds(10, 65, 187, 22);
		frame.getContentPane().add(cbCrop);
		
		JComboBox<CropItem> cbItem = new JComboBox<CropItem>();
		for (CropItem i : cropItems) {
			cbItem.addItem(i);
		}
		cbItem.setBounds(238, 65, 112, 22);
		frame.getContentPane().add(cbItem);
		
		JButton btnApply = new JButton("Apply Item");
		btnApply.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Crop crop = (Crop) cbCrop.getSelectedItem();
				CropItem item = (CropItem) cbItem.getSelectedItem();
				int confirm = JOptionPane.showConfirmDialog(null, "Apply " + item.getType() + " to " + crop.getQuantity() + " " + crop.getType(),
						null, JOptionPane.YES_NO_CANCEL_OPTION);
		
				if (confirm == 0) {
					if ( item.getType() == "Instant" ) {
						crop.increaseGrowth(item.getBenefit());
					}
					else {
						crop.increaseRate(item.getBenefit());
					}
					cropItems.remove(item);
					parent.manager.farmObject.removeItem(item);
					cbItem.removeItem(item);
					parent.manager.editTurns(-1);
					parent.updateTurns();
					endWindow();
				}

			}
		});
		
		btnApply.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnApply.setBounds(148, 134, 111, 42);
		frame.getContentPane().add(btnApply);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.setBackground(Color.RED);
		btnReturn.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				endWindow();
			}
		});
		btnReturn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnReturn.setBounds(317, 227, 89, 23);
		frame.getContentPane().add(btnReturn);
		
		JLabel lblDesc = new JLabel("QUANTITY - TYPE - GROWTH RATE");
		lblDesc.setBounds(10, 98, 197, 14);
		frame.getContentPane().add(lblDesc);
	}
}
