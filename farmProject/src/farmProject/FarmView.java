package farmProject;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FarmView {

	private JFrame frmFarm;
	private JButton btnAnimals;
	private WindowManager manager;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FarmView window = new FarmView();
					window.frame.setVisible(true);
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
	public FarmView(WindowManager incomingManager) {
		manager = incomingManager;
		initialize();
		frmFarm.setVisible(true);
	}
	
	public void closeWindow() {
		frmFarm.dispose();
	}
	
	
	private void viewAnimals() {
		String animalString = manager.stringAnimals();
		showMessageDialog(null, animalString);
	}
	
	
	private void viewCrops() {
		String cropString = manager.stringCrops();
		showMessageDialog(null, cropString);
	}
	
	
	private void finishedWindow() {
		manager.toTownMap(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFarm = new JFrame();
		frmFarm.setTitle("Farm");
		frmFarm.setBounds(100, 100, 450, 300);
		frmFarm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFarm.getContentPane().setLayout(null);
		
		JLabel lblBal = new JLabel("Balance: $" + manager.farmObject.getBal());
		lblBal.setBounds(23, 11, 120, 14);
		frmFarm.getContentPane().add(lblBal);
		
		JButton btnTownMap = new JButton("Town Map");
		btnTownMap.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				finishedWindow();
			}
		});
		btnTownMap.setBounds(298, 39, 89, 23);
		frmFarm.getContentPane().add(btnTownMap);
		
		JButton btnCrops = new JButton("View Crops");
		btnCrops.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				viewCrops();
			}
		});
		btnCrops.setBounds(298, 141, 89, 23);
		frmFarm.getContentPane().add(btnCrops);
		
		btnAnimals = new JButton("View Animals");
		btnAnimals.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				viewAnimals();
			}
		});
		btnAnimals.setBounds(10, 104, 110, 23);
		frmFarm.getContentPane().add(btnAnimals);
		
		JButton btnNewDay = new JButton("Next Day");
		btnNewDay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				manager.newDay();
			}
		});
		btnNewDay.setBounds(23, 212, 89, 23);
		frmFarm.getContentPane().add(btnNewDay);
		
		JButton btnActions = new JButton("Actions");
		btnActions.setBounds(161, 120, 89, 23);
		frmFarm.getContentPane().add(btnActions);
	}
}