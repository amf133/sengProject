package farmProject;



import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.Font;
import javax.swing.JButton;

public class W_AnimalStore {

	private JFrame frmAnimalStore;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					W_AnimalStore window = new W_AnimalStore();
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
	public W_AnimalStore(Farm farmObject) {
		initialize(farmObject);
		frmAnimalStore.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Farm farmObject) {
		frmAnimalStore = new JFrame();
		frmAnimalStore.setTitle("Animal Store");
		frmAnimalStore.setBounds(100, 100, 450, 300);
		frmAnimalStore.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAnimalStore.getContentPane().setLayout(null);

		
		JLabel lblMoney = new JLabel("");
		lblMoney.setBounds(24, 21, 187, 14);
		lblMoney.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMoney.setText("Current Balance: $" + farmObject.getBal());
		frmAnimalStore.getContentPane().add(lblMoney);
		
		JLabel lblResponse = new JLabel("No Animals Bought");
		lblResponse.setBounds(35, 162, 124, 64);
		frmAnimalStore.getContentPane().add(lblResponse);
		
		JButton btnReturn = new JButton("Back to map");
		btnReturn.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				frmAnimalStore.setVisible(false);
			}
		});
		btnReturn.setBounds(169, 137, 138, 64);
		frmAnimalStore.getContentPane().add(btnReturn);
		
		JButton btnBuyCow = new JButton("Cow $20");
		double cost = 20.0;
		btnBuyCow.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				if (farmObject.getBal() < cost) {
					lblResponse.setText("You do not have enough money for cow");
				}
				else {
					Animal a = new Animal("Cow", 20.0);
					farmObject.addAnimal(a);
					farmObject.updateBal(-cost);
					lblMoney.setText(("Current Balance: $" + farmObject.getBal()));
					lblResponse.setText("You bought cow");
				}
			}
		});
		btnBuyCow.setBounds(25, 58, 89, 23);
		frmAnimalStore.getContentPane().add(btnBuyCow);
		
		JButton btnBuyPig = new JButton("Pig $25");
		btnBuyPig.setBounds(25, 126, 89, 23);
		frmAnimalStore.getContentPane().add(btnBuyPig);
		
		JButton btnBuySheep = new JButton("Sheep $20");
		btnBuySheep.setBounds(24, 92, 89, 23);
		frmAnimalStore.getContentPane().add(btnBuySheep);
		


	}
}
