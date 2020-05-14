package farmProject;


import java.math.BigDecimal;
import java.math.MathContext;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.BadLocationException;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.SystemColor;

public class W_AnimalStore {

	private JFrame frmAnimalStore;
	private WindowManager manager;
	private JTextArea txtView;
	private JLabel lblMoney;

	/**
	 * Create the application
	 */
	public W_AnimalStore(WindowManager incomingManager, Farm farmObject) {
		manager = incomingManager;
		initialize(farmObject);
	}
	
	private void updateWindow(Animal a) {
		
		
	}

	public void closeWindow() {
		frmAnimalStore.setVisible(false);
	}
	
	public void show() {
		frmAnimalStore.setVisible(true);
	}
	
	private void finishedWindow() {
		closeWindow();
		manager.toTownMap();
	}
	/**
	 * 
	public void updateBal() {
		lblMoney.setText("Current Balance: $" + manager.farmObject.getBal());
	}
	
	 * Initialize the contents of the frame.
	 */
	private void initialize(Farm farmObject) {
		
		
		frmAnimalStore = new JFrame();
		frmAnimalStore.setTitle("Animal Store");
		frmAnimalStore.setBounds(100, 100, 450, 300);
		frmAnimalStore.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAnimalStore.getContentPane().setLayout(null);
		
		JTextArea txtView = new JTextArea();
		txtView.setBackground(SystemColor.menu);
		
		txtView.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ArrayList<Animal> animals = farmObject.getAnimals();
		txtView.setText("Your Animals:");
		for (Animal a : animals) {
			txtView.append("\n" + a.getType() + ": Happiness: " + a.getHappy() + " Health: " + a.getHealth());
			}
		
		txtView.setBounds(206, 19, 203, 216);
		txtView.setEditable(false);
		frmAnimalStore.getContentPane().add(txtView);

		
		
		JLabel lblMoney = new JLabel();
		//BigDecimal Bal = new BigDecimal(farmObject.getBal());
		//Bal = Bal.round(new MathContext(2));
		lblMoney.setBounds(24, 21, 187, 14);
		lblMoney.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMoney.setText("Current Balance: $" + farmObject.getBal());
		frmAnimalStore.getContentPane().add(lblMoney);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.setBackground(Color.RED);
		btnReturn.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				finishedWindow();
			}
		});
		btnReturn.setBounds(49, 214, 91, 36);
		frmAnimalStore.getContentPane().add(btnReturn);
		
		// Button to buy cow
		JButton btnBuyCow = new JButton("Cow $120");
		btnBuyCow.setBackground(Color.CYAN);
		double cow_cost = 120.0;
		btnBuyCow.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				if (farmObject.getBal() < cow_cost) {
					showMessageDialog(null, "You do not have enough money!");
				}
				else {
					Animal a = new Animal("Cow", cow_cost);
					farmObject.addAnimal(a);
					farmObject.updateBal(-cow_cost);
					txtView.append("\n" + a.getType() + ": Happiness: " + a.getHappy() + " Health: " + a.getHealth());
					lblMoney.setText(("Current Balance: $" + farmObject.getBal()));
					
					
				}
			}
		});
		btnBuyCow.setBounds(24, 92, 130, 36);
		frmAnimalStore.getContentPane().add(btnBuyCow);
		
		// Button to buy pig
		JButton btnBuyPig = new JButton("Pig $150");
		btnBuyPig.setBackground(Color.PINK);
		double pig_cost = 150.0;
		btnBuyPig.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				if (farmObject.getBal() < pig_cost) {
					showMessageDialog(null, "You do not have enough money!");
				}
				else {
					Animal a = new Animal("Pig", pig_cost);
					manager.farmObject.addAnimal(a);
					manager.farmObject.updateBal(-pig_cost);
					lblMoney.setText(("Current Balance: $" + farmObject.getBal()));
					txtView.append("\n" + a.getType() + ": Happiness: " + a.getHappy() + " Health: " + a.getHealth());

				}
			}
		});
		btnBuyPig.setBounds(24, 139, 130, 36);
		frmAnimalStore.getContentPane().add(btnBuyPig);
		
		// Button to buy sheep
		JButton btnBuySheep = new JButton("Sheep $100");
		btnBuySheep.setBackground(Color.WHITE);
		double sheep_cost = 100.0;
		btnBuySheep.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				if (farmObject.getBal() < sheep_cost) {
					showMessageDialog(null, "You do not have enough money!");
				}
				else {
					Animal a = new Animal("Sheep", sheep_cost);
					farmObject.addAnimal(a);
					farmObject.updateBal(-sheep_cost);
					lblMoney.setText(("Current Balance: $" + farmObject.getBal()));
					txtView.append("\n" + a.getType() + ": Happiness: " + a.getHappy() + " Health: " + a.getHealth());
				}
			}
		});
		btnBuySheep.setBounds(24, 46, 130, 36);
		frmAnimalStore.getContentPane().add(btnBuySheep);
		
		
		// Testing transparent buttons
		/**
		btnBuyCow.setOpaque(false);
		btnBuyCow.setContentAreaFilled(false);
		btnBuySheep.setOpaque(false);
		btnBuySheep.setContentAreaFilled(false);
		btnBuyPig.setOpaque(false);
		btnBuyPig.setContentAreaFilled(false);
		
		
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setVerticalAlignment(SwingConstants.TOP);
		lblBackground.setIcon(new ImageIcon(TownMap.class.getResource("/Images/download.png")));
		lblBackground.setBounds(0, 0, 890, 444);
		frmAnimalStore.getContentPane().add(lblBackground);
		*/

	}
}
