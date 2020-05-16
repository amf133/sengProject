package farmProject;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
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

public class TownMap {

	private JFrame frmTownMap;
	private JLabel lblBal;
	private WindowManager manager;

	/**
	 * Create the application.
	 */
	public TownMap(WindowManager incomingManager) {
		manager = incomingManager;
		initialize();
	}
	
//<<<<<<< HEAD
	public void storeAnimal() {
		manager.viewAnimalStore();
	}
	
	public void storeCrop() {
		manager.viewCropStore();
	}
	
	
	public void endWindow() {
		frmTownMap.dispose();
	}
	
	public void storeItem() {
		manager.viewItemStore();
	}
	
	public void show() {
		frmTownMap.setVisible(true);
		updateBal();
	}
	
	private void updateBal() {
		lblBal.setText("Balance: $" + new DecimalFormat("0.00").format(manager.farmObject.getBal()));
	}
		
//=======
	
	public void closeWindow() {
		frmTownMap.setVisible(false);
	}
	
	private void finishedWindow() {
		manager.toFarm();
//>>>>>>> branch 'master' of https://github.com/amf133/sengProject.git
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
