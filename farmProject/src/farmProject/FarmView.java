package farmProject;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;

public class FarmView {

	private JFrame frmFarm;
	private JButton btnAnimals;
	private WindowManager manager;
	private JLabel lblBal;
	private JButton btnNewDay;

	
	/**
	 * Create the application.
	 */
	public FarmView(WindowManager incomingManager) {
		manager = incomingManager;
		initialize();
	}
	
	public void closeWindow() {
		frmFarm.setVisible(false);
	}
	
	public void endWindow() {
		frmFarm.dispose();
	}
	
	public void show() {
		frmFarm.setVisible(true);
		updateLabels();
	}
	
	private void viewItems() {
		String itemString = manager.stringItems();
		showMessageDialog(null, itemString);
	}
	
	private void viewAnimals() {
		String animalString = manager.stringAnimals();
		showMessageDialog(null, animalString);
	}
	
	
	private void viewCrops() {
		String cropString = manager.stringCrops();
		showMessageDialog(null, cropString);
	}
	
	public void updateLabels() {
		lblBal.setText("Balance: $" + manager.farmObject.getBal());
		if (manager.getDays() <= 1)
			btnNewDay.setText("Finish game");
	}
	
	
	private void finishedWindow() {
		manager.toTownMap();
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFarm = new JFrame();
		frmFarm.setTitle("Farm");
		frmFarm.setBounds(100, 100, 818, 490);
		frmFarm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFarm.getContentPane().setLayout(null);
		
		lblBal = new JLabel("Balance: $0");
		lblBal.setForeground(Color.WHITE);
		lblBal.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBal.setBounds(23, 11, 197, 14);
		frmFarm.getContentPane().add(lblBal);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 259, 43);
		frmFarm.getContentPane().add(panel);
		
		JButton btnTownMap = new JButton("Town Map");
		btnTownMap.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				finishedWindow();
			}
		});
		btnTownMap.setBounds(635, 225, 120, 23);
		frmFarm.getContentPane().add(btnTownMap);
		
		JButton btnCrops = new JButton("View Crops");
		btnCrops.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				viewCrops();
			}
		});
		btnCrops.setBounds(623, 356, 120, 23);
		frmFarm.getContentPane().add(btnCrops);
		
		JButton btnItems = new JButton("View Item");
		btnItems.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				viewItems();
			}
		});
		btnItems.setBounds(487, 254, 105, 23);
		frmFarm.getContentPane().add(btnItems);
		
		
		btnAnimals = new JButton("View Animals");
		btnAnimals.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				viewAnimals();
			}
		});
		btnAnimals.setBounds(55, 308, 139, 23);
		frmFarm.getContentPane().add(btnAnimals);
		
		btnNewDay = new JButton("Next Day");
		btnNewDay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				manager.newDay();
				updateLabels();
			}
		});
		btnNewDay.setBounds(330, 194, 113, 23);
		frmFarm.getContentPane().add(btnNewDay);
		
		JButton btnActions = new JButton("Actions");
		btnActions.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				manager.openActions();
			}
		});
		btnActions.setBounds(369, 337, 89, 23);
		frmFarm.getContentPane().add(btnActions);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(FarmView.class.getResource("/Images/farm.jpg")));
		lblBackground.setBounds(0, 0, 802, 451);
		frmFarm.getContentPane().add(lblBackground);
	}
}
