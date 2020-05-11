package farmProject;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

public class W_CropStore {

	private JFrame frmCropStore;
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
	public W_CropStore(WindowManager incomingManager, Farm farmObject)  {
		manager = incomingManager;
		initialize(farmObject);
	}
	public void closeWindow() {
		frmCropStore.setVisible(false);
	}
	
	public void show() {
		frmCropStore.setVisible(true);
	}
	
	private void finishedWindow() {
		closeWindow();
		manager.toTownMap();
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
		btnReturn.setBackground(Color.RED);
		btnReturn.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				finishedWindow();
			}
		});
		btnReturn.setBounds(164, 122, 89, 23);
		frmCropStore.getContentPane().add(btnReturn);
	}
	


}
