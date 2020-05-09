package farmProject;



import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.JTextArea;

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
		
		JTextArea txtView = new JTextArea();
		txtView.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ArrayList<Animal> animals = farmObject.getAnimals();
		txtView.setText("Your Animals:");
		for (Animal a : animals) {
			txtView.append("\n" + a.getType() + ": Happiness: " + a.getHappy() + " Health: " + a.getHealth());
			}
		txtView.setBounds(168, 46, 203, 112);
		txtView.setEditable(false);
		frmAnimalStore.getContentPane().add(txtView);
		
		JLabel lblMoney = new JLabel("");
		lblMoney.setBounds(24, 21, 187, 14);
		lblMoney.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMoney.setText("Current Balance: $" + farmObject.getBal());
		frmAnimalStore.getContentPane().add(lblMoney);
		
		JButton btnReturn = new JButton("Back to map");
		btnReturn.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				frmAnimalStore.setVisible(false);
			}
		});
		btnReturn.setBounds(198, 169, 130, 64);
		frmAnimalStore.getContentPane().add(btnReturn);
		
		// Button to buy cow
		JButton btnBuyCow = new JButton("Cow $20");
		double cow_cost = 120.0;
		btnBuyCow.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				if (farmObject.getBal() < cow_cost) {
					showMessageDialog(null, "You do not have enough money!");
				}
				else {
					Animal a = new Animal("Cow", 120.0);
					farmObject.addAnimal(a);
					farmObject.updateBal(-cow_cost);
					lblMoney.setText(("Current Balance: $" + farmObject.getBal()));
					txtView.append("\n" + a.getType() + ": Happiness: " + a.getHappy() + " Health: " + a.getHealth());
				}
			}
		});
		btnBuyCow.setBounds(25, 58, 89, 23);
		frmAnimalStore.getContentPane().add(btnBuyCow);
		
		// Button to buy pig
		JButton btnBuyPig = new JButton("Pig $25");
		double pig_cost = 150.0;
		btnBuyPig.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				if (farmObject.getBal() < pig_cost) {
					showMessageDialog(null, "You do not have enough money!");
				}
				else {
					Animal a = new Animal("Pig", 150.0);
					farmObject.addAnimal(a);
					farmObject.updateBal(-pig_cost);
					lblMoney.setText(("Current Balance: $" + farmObject.getBal()));
					txtView.append("\n" + a.getType() + ": Happiness: " + a.getHappy() + " Health: " + a.getHealth());

				}
			}
		});
		btnBuyPig.setBounds(25, 126, 89, 23);
		frmAnimalStore.getContentPane().add(btnBuyPig);
		
		// Button to buy sheep
		JButton btnBuySheep = new JButton("Sheep $100");
		double sheep_cost = 150.0;
		btnBuySheep.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				if (farmObject.getBal() < sheep_cost) {
					showMessageDialog(null, "You do not have enough money!");
				}
				else {
					Animal a = new Animal("Sheep", 120.0);
					farmObject.addAnimal(a);
					farmObject.updateBal(-sheep_cost);
					lblMoney.setText(("Current Balance: $" + farmObject.getBal()));
					txtView.append("\n" + a.getType() + ": Happiness: " + a.getHappy() + " Health: " + a.getHealth());
				}
			}
		});
		btnBuySheep.setBounds(24, 92, 89, 23);
		frmAnimalStore.getContentPane().add(btnBuySheep);
		
		

	}
}
