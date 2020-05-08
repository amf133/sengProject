package farmProject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FarmView {

	private JFrame frame;
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
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	
	private void finishedWindow() {
		manager.toTownMap(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblBal = new JLabel("Balance: $" + manager.farmObject.getBal());
		lblBal.setBounds(23, 11, 120, 14);
		frame.getContentPane().add(lblBal);
		
		JButton btnTownMap = new JButton("Town Map");
		btnTownMap.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				finishedWindow();
			}
		});
		btnTownMap.setBounds(298, 39, 89, 23);
		frame.getContentPane().add(btnTownMap);
		
		JButton btnCrops = new JButton("View Crops");
		btnCrops.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				manager.farmObject.viewCrops();
			}
		});
		btnCrops.setBounds(298, 141, 89, 23);
		frame.getContentPane().add(btnCrops);
		
		btnAnimals = new JButton("View Animals");
		btnAnimals.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				manager.farmObject.viewAnimals();
			}
		});
		btnAnimals.setBounds(10, 104, 110, 23);
		frame.getContentPane().add(btnAnimals);
		
		JButton btnNewDay = new JButton("Next Day");
		btnNewDay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				manager.newDay();
			}
		});
		btnNewDay.setBounds(23, 212, 89, 23);
		frame.getContentPane().add(btnNewDay);
		
		JButton btnActions = new JButton("Actions");
		btnActions.setBounds(161, 120, 89, 23);
		frame.getContentPane().add(btnActions);
	}
}
