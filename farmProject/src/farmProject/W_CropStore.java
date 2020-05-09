package farmProject;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

public class W_CropStore {

	private JFrame frmCropStore;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					W_CropStore window = new W_CropStore();
					window.frmCropStore.setVisible(true);
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
	public W_CropStore(Farm farmObject) {
		initialize(farmObject);
		frmCropStore.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Farm farmObject) {
		frmCropStore = new JFrame();
		frmCropStore.setBounds(100, 100, 450, 300);
		frmCropStore.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCropStore.getContentPane().setLayout(null);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				frmCropStore.setVisible(false);
			}
		});
		btnReturn.setBounds(164, 122, 89, 23);
		frmCropStore.getContentPane().add(btnReturn);
	}
	


}
