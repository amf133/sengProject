package farmProject;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static javax.swing.JOptionPane.showMessageDialog;

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
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class W_ItemStore {

	private JFrame frmItemStore;
	private WindowManager manager;


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
	public W_ItemStore(WindowManager incomingManager, Farm farmObject)  {
		manager = incomingManager;
		initialize(farmObject);
	}
	public void closeWindow() {
		frmItemStore.setVisible(false);
	}
	
	public void show() {
		frmItemStore.setVisible(true);
	}
	
	private void finishedWindow() {
		closeWindow();
		manager.toTownMap();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Farm farmObject) {
		
		frmItemStore = new JFrame();
		frmItemStore.setTitle("Item Store");
		frmItemStore.setBounds(100, 100, 535, 440);
		frmItemStore.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmItemStore.getContentPane().setLayout(null);
		
		ArrayList<Item> Items = farmObject.getItems();
		
		JTextArea txtAItem = new JTextArea();
		txtAItem.setBounds(386, 52, 117, 97);
		frmItemStore.getContentPane().add(txtAItem);
		
		JTextArea txtCItem = new JTextArea();
		txtCItem.setBackground(Color.WHITE);
		txtCItem.setBounds(386, 212, 117, 89);
		frmItemStore.getContentPane().add(txtCItem);

		JLabel lblBal = new JLabel("Balance:");
		lblBal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBal.setBounds(10, 11, 158, 14);
		lblBal.setText("Current Balance: $" + farmObject.getBal());
		frmItemStore.getContentPane().add(lblBal);
		
		// BUTTONS ---------------------------------------------------------------------------------------------------------
		
		// Button to return to town map
		JButton btnReturn = new JButton("Return");
		btnReturn.setBackground(Color.RED);
		btnReturn.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				finishedWindow();
			}
		});
		btnReturn.setBounds(386, 329, 117, 39);
		frmItemStore.getContentPane().add(btnReturn);
		
		// Button to buy medicine
		JButton btnMedicine = new JButton("Medicine $50");
		btnMedicine.setHorizontalAlignment(SwingConstants.LEFT);
		btnMedicine.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnMedicine.setBounds(109, 121, 112, 23);
		frmItemStore.getContentPane().add(btnMedicine);
		
		// Button to buy grub
		JButton btnGrub = new JButton("Grub $20");
		btnGrub.setBackground(Color.CYAN);
		double grub_cost = 20.0;
		String description = "Restore 0.2 health";
		btnGrub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (farmObject.getBal() < grub_cost) {
					showMessageDialog(null, "You do not have enough money!");
				}
				else {
					Item i = new FoodItem("Grub", description , 0.2);
					farmObject.addItem(i);
					farmObject.updateBal(-grub_cost);
				}
			}
		});
		btnGrub.setHorizontalAlignment(SwingConstants.LEFT);
		btnGrub.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGrub.setBounds(109, 52, 112, 23);
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
		
		
		// LABELS ---------------------------------------------------------------------------------------------------------
		
		JLabel lblAItems = new JLabel("Animal Items:");
		lblAItems.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAItems.setBounds(10, 53, 112, 20);
		frmItemStore.getContentPane().add(lblAItems);
		
		JLabel lblCItems = new JLabel("Crops Items:\r\n\r\n");
		lblCItems.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCItems.setBounds(10, 213, 112, 23);
		frmItemStore.getContentPane().add(lblCItems);
		
		JButton btnInstant = new JButton("Instant $100");
		btnInstant.setHorizontalAlignment(SwingConstants.LEFT);
		btnInstant.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnInstant.setBounds(108, 279, 112, 23);
		frmItemStore.getContentPane().add(btnInstant);
		
		JLabel lblHealth = new JLabel("Health Given:");
		lblHealth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHealth.setBounds(251, 22, 103, 20);
		frmItemStore.getContentPane().add(lblHealth);
		
		JLabel lblGrowthIncreae = new JLabel("Growth Increase %");
		lblGrowthIncreae.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGrowthIncreae.setBounds(231, 185, 123, 23);
		frmItemStore.getContentPane().add(lblGrowthIncreae);
		
		JLabel lblGFert = new JLabel("0.25");
		lblGFert.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGFert.setBounds(269, 213, 46, 14);
		frmItemStore.getContentPane().add(lblGFert);
		
		JLabel lblGSun = new JLabel("0.5");
		lblGSun.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGSun.setBounds(269, 247, 46, 14);
		frmItemStore.getContentPane().add(lblGSun);
		
		JLabel lblGIns = new JLabel("1");
		lblGIns.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGIns.setBounds(269, 285, 46, 14);
		frmItemStore.getContentPane().add(lblGIns);
		
		JLabel lblHGrub = new JLabel("0.2\r\n\r\n");
		lblHGrub.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHGrub.setBounds(269, 58, 25, 14);
		frmItemStore.getContentPane().add(lblHGrub);
		
		JLabel lblHSeed = new JLabel("0.5");
		lblHSeed.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHSeed.setBounds(269, 93, 46, 14);
		frmItemStore.getContentPane().add(lblHSeed);
		
		JLabel lblHMed = new JLabel("1");
		lblHMed.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHMed.setBounds(269, 127, 46, 14);
		frmItemStore.getContentPane().add(lblHMed);
	}
}
