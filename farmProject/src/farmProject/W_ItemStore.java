package farmProject;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.BorderLayout;

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
		frmItemStore.setBounds(100, 100, 450, 300);
		frmItemStore.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmItemStore.getContentPane().setLayout(null);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				frmItemStore.setVisible(false);
			}
		});
		btnReturn.setBounds(168, 121, 89, 23);
		frmItemStore.getContentPane().add(btnReturn);
	}
}
