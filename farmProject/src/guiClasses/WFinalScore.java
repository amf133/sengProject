package guiClasses;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/** 
* This class is a window which is displayed when the game ends 
* @author Alec, Christian
*/
public class WFinalScore {

	private JFrame frmFinalScore;
	private JLabel lblFarmNameShow, lblFarmerNameShow, lblFinalScoreShow, lblDays;

	/**
	 * Create the application.
	 * @param farmerName name of farmer
	 * @param farmName name of farm
	 * @param finalScore users final score on game ending
	 * @param days number of days user played for
	 */
	public WFinalScore(String farmerName, String farmName, String finalScore, int days) {
		initialize();
		lblFarmerNameShow.setText(farmerName);
		lblFarmNameShow.setText(farmName);
		lblFinalScoreShow.setText(finalScore);
		lblDays.setText(Integer.toString(days));
	}
	
	
	/**
     * Deletes the current window
     */
	private void endWindow() {
		frmFinalScore.dispose();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFinalScore = new JFrame();
		frmFinalScore.setBounds(100, 100, 510, 334);
		frmFinalScore.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFinalScore.getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("GAME OVER!");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTitle.setBounds(199, 22, 113, 22);
		frmFinalScore.getContentPane().add(lblTitle);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				endWindow();
			}
		});
		btnExit.setBounds(40, 239, 115, 23);
		frmFinalScore.getContentPane().add(btnExit);
		
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
		frmFinalScore.getContentPane().add(btnNew);
		
		JLabel lblFarmName = new JLabel("Farm name:");
		lblFarmName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFarmName.setBounds(115, 106, 80, 19);
		frmFinalScore.getContentPane().add(lblFarmName);
		
		JLabel lblFarmerName = new JLabel("Farmer name:");
		lblFarmerName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFarmerName.setBounds(115, 67, 86, 17);
		frmFinalScore.getContentPane().add(lblFarmerName);
		
		lblFarmerNameShow = new JLabel("null");
		lblFarmerNameShow.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblFarmerNameShow.setBounds(245, 108, 122, 14);
		frmFinalScore.getContentPane().add(lblFarmerNameShow);
		
		lblFarmNameShow = new JLabel("null");
		lblFarmNameShow.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblFarmNameShow.setBounds(245, 68, 122, 14);
		frmFinalScore.getContentPane().add(lblFarmNameShow);
		
		JLabel lblFinalScore = new JLabel("Score:");
		lblFinalScore.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFinalScore.setBounds(115, 184, 40, 17);
		frmFinalScore.getContentPane().add(lblFinalScore);
		
		lblFinalScoreShow = new JLabel("null");
		lblFinalScoreShow.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblFinalScoreShow.setBounds(245, 187, 122, 14);
		frmFinalScore.getContentPane().add(lblFinalScoreShow);
		
		JLabel lblDaysName = new JLabel("Days played:");
		lblDaysName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDaysName.setBounds(115, 141, 80, 19);
		frmFinalScore.getContentPane().add(lblDaysName);
		
		lblDays = new JLabel("<dynamic>");
		lblDays.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblDays.setBounds(245, 143, 122, 14);
		frmFinalScore.getContentPane().add(lblDays);
		frmFinalScore.setVisible(true);
	}
}
