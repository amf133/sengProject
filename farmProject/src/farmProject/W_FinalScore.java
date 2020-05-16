package farmProject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class W_FinalScore {

	private JFrame frame;
	private JLabel lblFarmNameShow, lblFarmerNameShow, lblFinalScoreShow;

	/**
	 * Create the application.
	 */
	public W_FinalScore(String farmerName, String farmName, String finalScore) {
		initialize();
		lblFarmerNameShow.setText(farmerName);
		lblFarmNameShow.setText(farmName);
		lblFinalScoreShow.setText(finalScore);
		frame.setVisible(true);
	}
	
	
	/**
     * Deletes the current window
     */
	private void endWindow() {
		frame.dispose();
		System.exit(0);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 510, 334);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("GAME OVER!");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTitle.setBounds(199, 22, 113, 22);
		frame.getContentPane().add(lblTitle);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				endWindow();
			}
		});
		btnExit.setBounds(40, 239, 115, 23);
		frame.getContentPane().add(btnExit);
		
		JButton btnNew = new JButton("New game");
		btnNew.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				WindowManager manager = new WindowManager();
				manager.launch();
				endWindow();
			}
		});
		btnNew.setBounds(330, 239, 115, 23);
		frame.getContentPane().add(btnNew);
		
		JLabel lblFarmName = new JLabel("Farm name:");
		lblFarmName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFarmName.setBounds(115, 106, 80, 19);
		frame.getContentPane().add(lblFarmName);
		
		JLabel lblFarmerName = new JLabel("Farmer name:");
		lblFarmerName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFarmerName.setBounds(115, 67, 86, 17);
		frame.getContentPane().add(lblFarmerName);
		
		lblFarmerNameShow = new JLabel("null");
		lblFarmerNameShow.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblFarmerNameShow.setBounds(245, 108, 122, 14);
		frame.getContentPane().add(lblFarmerNameShow);
		
		lblFarmNameShow = new JLabel("null");
		lblFarmNameShow.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblFarmNameShow.setBounds(245, 68, 122, 14);
		frame.getContentPane().add(lblFarmNameShow);
		
		JLabel lblFinalScore = new JLabel("Score:");
		lblFinalScore.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFinalScore.setBounds(115, 154, 40, 17);
		frame.getContentPane().add(lblFinalScore);
		
		lblFinalScoreShow = new JLabel("null");
		lblFinalScoreShow.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblFinalScoreShow.setBounds(245, 157, 122, 14);
		frame.getContentPane().add(lblFinalScoreShow);
	}
}
