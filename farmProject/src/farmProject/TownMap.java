package farmProject;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	
//<<<<<<< HEAD
	public void storeAnimal() {
		manager.viewAnimalStore();
	}
	
	public void storeCrop() {
		manager.viewCropStore();
	}
	
	public void storeItem() {
		manager.viewItemStore();
	}
		
//=======
	
	public void closeWindow() {
		frmTownMap.dispose();
	}
	
	private void finishedWindow() {
		manager.toFarm(this);
//>>>>>>> branch 'master' of https://github.com/amf133/sengProject.git
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
		
		JLabel lblBal = new JLabel("Balance: $" + manager.farmObject.getBal());
		lblBal.setBounds(10, 11, 144, 14);
		frmTownMap.getContentPane().add(lblBal);
		
		JButton btnFarm = new JButton("Farm");
		btnFarm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				finishedWindow();
			}
		});
		btnFarm.setBounds(37, 184, 89, 23);
		frmTownMap.getContentPane().add(btnFarm);
			
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
		btnStoreCrop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				storeCrop();
			}
		});
		btnStoreCrop.setBounds(294, 102, 104, 23);
		frmTownMap.getContentPane().add(btnStoreCrop);
		
		JButton btnStoreItem = new JButton("Item store");
		btnStoreItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				storeItem();
			}
		});
		btnStoreItem.setBounds(37, 85, 89, 23);
		frmTownMap.getContentPane().add(btnStoreItem);
	}
}
