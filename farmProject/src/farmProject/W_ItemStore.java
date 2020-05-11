package farmProject;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JTextPane;

public class W_ItemStore {

	private JFrame frmItemStore;

	/**
	 * Launch the application.

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					W_ItemStore window = new W_ItemStore();
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
	public W_ItemStore(Farm farmObject) {
		initialize(farmObject);
		frmItemStore.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Farm farmObejct) {
		frmItemStore = new JFrame();
		frmItemStore.setTitle("Item Store");
		frmItemStore.setBounds(100, 100, 535, 440);
		frmItemStore.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmItemStore.getContentPane().setLayout(null);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				frmItemStore.setVisible(false);
			}
		});
		btnReturn.setBounds(386, 329, 117, 39);
		frmItemStore.getContentPane().add(btnReturn);
		
		JLabel lblAItems = new JLabel("Animal Items:");
		lblAItems.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAItems.setBounds(10, 53, 112, 20);
		frmItemStore.getContentPane().add(lblAItems);
		
		JLabel lblCItems = new JLabel("Crops Items:\r\n\r\n");
		lblCItems.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCItems.setBounds(10, 213, 112, 23);
		frmItemStore.getContentPane().add(lblCItems);
		
		JButton btnMedicine = new JButton("Medicine $20");
		btnMedicine.setHorizontalAlignment(SwingConstants.LEFT);
		btnMedicine.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnMedicine.setBounds(109, 53, 112, 23);
		frmItemStore.getContentPane().add(btnMedicine);
		
		JButton btnGrub = new JButton("Grub $50");
		btnGrub.setHorizontalAlignment(SwingConstants.LEFT);
		btnGrub.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGrub.setBounds(109, 121, 112, 23);
		frmItemStore.getContentPane().add(btnGrub);
		
		JButton btnSeeds = new JButton("Seeds $30");
		btnSeeds.setHorizontalAlignment(SwingConstants.LEFT);
		btnSeeds.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSeeds.setBounds(109, 87, 112, 23);
		frmItemStore.getContentPane().add(btnSeeds);
		
		JButton btnFertilizer = new JButton("Fertilizer  $25");
		btnFertilizer.setHorizontalAlignment(SwingConstants.LEFT);
		btnFertilizer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnFertilizer.setBounds(109, 213, 112, 23);
		frmItemStore.getContentPane().add(btnFertilizer);
		
		JButton btnSunlight = new JButton("Sunlight $50");
		btnSunlight.setHorizontalAlignment(SwingConstants.LEFT);
		btnSunlight.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSunlight.setBounds(108, 247, 112, 23);
		frmItemStore.getContentPane().add(btnSunlight);
		
		JButton btnInstant = new JButton("Instant $100");
		btnInstant.setHorizontalAlignment(SwingConstants.LEFT);
		btnInstant.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnInstant.setBounds(108, 279, 112, 23);
		frmItemStore.getContentPane().add(btnInstant);
		
		JLabel lblHealth = new JLabel("Health Given:");
		lblHealth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHealth.setBounds(264, 21, 103, 20);
		frmItemStore.getContentPane().add(lblHealth);
		
		JTextArea txtAItem = new JTextArea();
		txtAItem.setBounds(386, 52, 117, 97);
		frmItemStore.getContentPane().add(txtAItem);
		
		JTextArea txtCItem = new JTextArea();
		txtCItem.setBackground(Color.WHITE);
		txtCItem.setBounds(386, 212, 117, 89);
		frmItemStore.getContentPane().add(txtCItem);
		
		JLabel lblBal = new JLabel("Balance:");
		lblBal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBal.setBounds(10, 11, 80, 14);
		frmItemStore.getContentPane().add(lblBal);
		
		JLabel lblGrowthIncreae = new JLabel("Growth Increase %");
		lblGrowthIncreae.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGrowthIncreae.setBounds(264, 185, 123, 23);
		frmItemStore.getContentPane().add(lblGrowthIncreae);
	}
}
