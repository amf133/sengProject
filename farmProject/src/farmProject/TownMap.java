package farmProject;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JButton;

public class TownMap {

	private JFrame frmTownMap;
	
	private WindowManager manager;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TownMap window = new TownMapGUI();
					window.frmTownMap.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	
	

	/**
	 * Create the application.
	 */
	public TownMap(WindowManager incomingManager) {
		manager = incomingManager;
		initialize();
		frmTownMap.setVisible(true);
	}
	
	public void storeAnimal() {
		manager.viewAnimalStore();
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTownMap = new JFrame();
		frmTownMap.setTitle("Town Map");
		frmTownMap.setBounds(100, 100, 450, 300);
		frmTownMap.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTownMap.getContentPane().setLayout(null);
		
		JLabel lblBal = new JLabel("Balance: $0");
		lblBal.setBounds(10, 11, 144, 14);
		frmTownMap.getContentPane().add(lblBal);
		
		JButton btnFarm = new JButton("Farm");
		btnFarm.setBounds(37, 184, 89, 23);
		frmTownMap.getContentPane().add(btnFarm);
		
		JButton btnStoreItem = new JButton("Item store");
		btnStoreItem.setBounds(37, 85, 89, 23);
		frmTownMap.getContentPane().add(btnStoreItem);
		
		JButton btnStoreAnimal = new JButton("Animal store");
		btnStoreAnimal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				storeAnimal();
			}
		});
		btnStoreAnimal.setBounds(294, 184, 104, 23);
		frmTownMap.getContentPane().add(btnStoreAnimal);
		
		JButton btnStoreCrop = new JButton("Crop store");
		btnStoreCrop.setBounds(294, 102, 104, 23);
		frmTownMap.getContentPane().add(btnStoreCrop);
	}
}
