package gui;

import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
		
	private void createAndShowGUI() {
		
		JFrame frame = new JFrame("Conway's Game of Life - kata");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = frame.getContentPane();
		        
        GamePanels gp = new GamePanels();
        gp.gamePanel( c );
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);      
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().createAndShowGUI();
            }
        });
	}
}


