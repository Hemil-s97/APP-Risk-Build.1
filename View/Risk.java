package view;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;

import model.RiskPlayers;

public class Risk {
	private JFrame frame;
	RiskPlayers players;
	Panels gamePanels;

	/**
	 * Launch the application.
	 *@param args String array for commandline arguments
	 *
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Risk startup = new Risk();
					startup.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Risk() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		gamePanels = new Panels();
		frame = new JFrame();
		frame.setTitle("Risk");
		frame.setPreferredSize(new Dimension(300, 300));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.add(gamePanels.mainMenu(frame,players));
		frame.pack();

	}
}
