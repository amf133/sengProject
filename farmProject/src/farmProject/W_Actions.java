package farmProject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class W_Actions {

	private JFrame frame;
	private WindowManager manager;
	private JLabel lblTurns;

	
	/**
	 * Create the application.
	 */
	public W_Actions(WindowManager incomingManager) {
		manager = incomingManager;
		initialize();
	}
	
	private void closeWindow() {
		frame.setVisible(false);
	}
	
	public void show() {
		frame.setVisible(true);
		updateTurns();
	}
	
	private void updateTurns() {
		lblTurns.setText("Turns left: " + manager.getTurns());
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
				manager.harvestCrops();
			}
		});
		btnHarvestCrops.setBounds(24, 161, 149, 23);
		frame.getContentPane().add(btnHarvestCrops);
		
		JButton btnPlayAnimals = new JButton("Play with animals");
		btnPlayAnimals.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
		});
		btnPlayAnimals.setBounds(93, 348, 149, 23);
		frame.getContentPane().add(btnPlayAnimals);
		
		JButton btnFeedAnimals = new JButton("Feed animals");
		btnFeedAnimals.setBounds(367, 325, 149, 23);
		frame.getContentPane().add(btnFeedAnimals);
		
		JButton btnTendToCrops = new JButton("Tend to crops");
		btnTendToCrops.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnTendToCrops.setBounds(556, 72, 149, 23);
		frame.getContentPane().add(btnTendToCrops);
		
		JButton btnTendFarm = new JButton("Tend to farm land");
		btnTendFarm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				manager.farmObject.editHappiness();
		        manager.farmObject.addSpace();
                manager.editTurns(-1);
				String result = ("Animal capacity increased by 2, " + manager.farmObject.getName() + " has room for " + (manager.farmObject.maxAnimals - manager.farmObject.getAnimals().size())  + " more animals!");
		        result += ("\nCrop capacity increased by 20, " + manager.farmObject.getName() + " has room for " + (manager.farmObject.maxCrops - manager.farmObject.numberCrops())  + " more crops!");
		        result += "\nAnimals feel more comfortable, now their happiness drains slower.";
				showMessageDialog(null, result);
				updateTurns();
			}
		});
		btnTendFarm.setBounds(720, 269, 149, 23);
		frame.getContentPane().add(btnTendFarm);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(W_Actions.class.getResource("/Images/tractor.jpg")));
		lblNewLabel.setBounds(0, 0, 879, 474);
		frame.getContentPane().add(lblNewLabel);
	}
}
