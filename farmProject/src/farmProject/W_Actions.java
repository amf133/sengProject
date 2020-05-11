package farmProject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class W_Actions {

	private JFrame frame;
	private WindowManager manager;

	
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
		btnCancel.setBounds(698, 410, 149, 23);
		frame.getContentPane().add(btnCancel);
		
		JButton btnHarvestCrops = new JButton("Harvest crops");
		btnHarvestCrops.setBounds(24, 161, 149, 23);
		frame.getContentPane().add(btnHarvestCrops);
		
		JButton btnPlayAnimals = new JButton("Play with animals");
		btnPlayAnimals.setBounds(93, 348, 149, 23);
		frame.getContentPane().add(btnPlayAnimals);
		
		JButton btnFeedAnimals = new JButton("Feed animals");
		btnFeedAnimals.setBounds(367, 325, 149, 23);
		frame.getContentPane().add(btnFeedAnimals);
		
		JButton btnTendToCrops = new JButton("Tend to crops");
		btnTendToCrops.setBounds(556, 72, 149, 23);
		frame.getContentPane().add(btnTendToCrops);
		
		JButton btnTendFarm = new JButton("Tend to farm land");
		btnTendFarm.setBounds(720, 269, 149, 23);
		frame.getContentPane().add(btnTendFarm);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(W_Actions.class.getResource("/Images/tractor.jpg")));
		lblNewLabel.setBounds(0, 0, 879, 474);
		frame.getContentPane().add(lblNewLabel);
	}
}
