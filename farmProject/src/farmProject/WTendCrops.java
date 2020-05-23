package farmProject;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JComboBox;
import javax.swing.JButton;


/** 
* This class is the window where the user can tend to their crops
* @author Alec, Christian
*/
public class WTendCrops {

	private JFrame frame;
	private ArrayList<Crop> crops;
	private ArrayList<CropItem> cropItems;
	private WActions parent;

	
	/**
	 * Create the application.
	 * @param cropss list of crops in farm
	 * @param incomingItems list of crop items in farm
	 * @param incomingParent parent window
	 */
	public WTendCrops(ArrayList<Crop> cropss, ArrayList<CropItem> incomingItems, WActions incomingParent) {
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
		
		JLabel lblSelect = new JLabel("Select Item:");
		lblSelect.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSelect.setBounds(258, 27, 81, 14);
		frame.getContentPane().add(lblSelect);
		
		JComboBox<Crop> cbxCrop = new JComboBox<Crop>();
		for (Crop c : crops) {
			cbxCrop.addItem(c);
		}
		cbxCrop.setBounds(10, 65, 187, 22);
		frame.getContentPane().add(cbxCrop);
		
		JComboBox<CropItem> cbxItem = new JComboBox<CropItem>();
		if (cropItems.size() == 0) {
			cbxItem.setToolTipText("No items available!");
		}
		else {
			for (CropItem i : cropItems) {
				cbxItem.addItem(i);
		}

		}
		cbxItem.setBounds(238, 65, 112, 22);
		frame.getContentPane().add(cbxItem);
		
		JButton btnApply = new JButton("Apply Item");
		btnApply.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (cropItems.size() >= 1) {
					Crop crop = (Crop) cbxCrop.getSelectedItem();
					CropItem item = (CropItem) cbxItem.getSelectedItem();
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
						cbxItem.removeItem(item);
						parent.manager.editTurns(-1);
						parent.updateTurns();
						endWindow();
					}
				}
				else {
					showMessageDialog(null, "You have no items to apply!");
				}
			}
		});
		btnApply.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnApply.setBounds(238, 111, 111, 42);
		frame.getContentPane().add(btnApply);
		
		
		JButton btnWater = new JButton("Water (Free)");
		btnWater.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Crop crop = (Crop) cbxCrop.getSelectedItem();
				int confirm = JOptionPane.showConfirmDialog(null, "Apply water to " + crop.getQuantity() + " " + crop.getType(),
						null, JOptionPane.YES_NO_CANCEL_OPTION);
		
				if (confirm == 0) {
					crop.increaseRate(0.2);
					parent.manager.editTurns(-1);
					parent.updateTurns();
					endWindow();
				}

			}
		});
		
		btnWater.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnWater.setBounds(238, 164, 112, 42);
		frame.getContentPane().add(btnWater);

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
