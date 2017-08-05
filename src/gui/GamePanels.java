package gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import rules.GameRules;

public class GamePanels {
	
	List<ArrayList<Boolean>> biDirectionalMemoryRows = new ArrayList<>();
		
	final Icon live=new ImageIcon("C:\\Users\\Joseph\\Desktop\\kata\\live_cell.jpg");
	final Icon dead=new ImageIcon("C:\\Users\\Joseph\\Desktop\\kata\\dead_cell.jpg");
	
	JPanel panel;
	JButton[] btns;
	JButton stepForewardBtn, stepBackwardBtn, resetBtn;
	boolean stop;
	int currentLife = 0;
	
	public void gamePanel(Container pane) {
	
		Insets i = new Insets(0,0,0,0);
		pane.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc = new GridBagConstraints( 0,
                					  1,
                					  6,
                					  6,
                					  0.0,
                					  0.0,
                					  GridBagConstraints.NORTH,
                					  GridBagConstraints.BOTH,
                					  i,
                					  0,
                					  0 );

		pane.add(imageButtonPanel(), gbc);

		gbc = new GridBagConstraints( 2,
				  					  7,
				  					  4,
				  					  2,
				  					  0.0,
				  					  0.0,
				  					  GridBagConstraints.CENTER,
				  					  GridBagConstraints.BOTH,
				  					  i,
				  					  0,
				  					  0 );

		pane.add(buttonPanel(), gbc);	
	}
	
	public JPanel imageButtonPanel() {
		
		panel = new JPanel(new GridLayout(6,8));
		ArrayList<Boolean> rowColumns = new ArrayList<>();
		
		btns = new JButton[48];
					    
		for (int x = 0; x < btns.length; x++) {
			btns[x] = new JButton( live );
			btns[x].setContentAreaFilled(false);
			btns[x].addActionListener(listener);
			rowColumns.add( new Boolean( true ) );
			panel.add(btns[x]);
		}
		
		biDirectionalMemoryRows.add( rowColumns );
				
		return panel;
	}
	
	public JPanel buttonPanel() {
		
		JPanel btnPanel = new JPanel(new GridLayout(1, 2, 15, 15));
		stepForewardBtn = new JButton("<html><CENTER><b>STEP FOREWARD<br>THROUGH CELL LIFE</b></CENTER></html>");
		stepForewardBtn.addActionListener(listener); 
		stepBackwardBtn = new JButton("<html><CENTER><b>STEP BACKWARD<br>THROUGH CELL LIFE</b></CENTER></html>");
		stepBackwardBtn.addActionListener(listener);
		resetBtn = new JButton("<html><CENTER><b>RESET CELL LIFE</b></CENTER></html>");
		resetBtn.addActionListener(listener);
		resetBtn.setEnabled(true);

		btnPanel.add(stepForewardBtn);
		btnPanel.add(stepBackwardBtn);
		btnPanel.add(resetBtn);
				
		return btnPanel;
	}
	
	ActionListener listener = new ActionListener() {
        
        public void actionPerformed(ActionEvent ae) {
			
        	List<JButton> gameList = Arrays.asList(btns);
        	
        	// allows player to initialize cell pattern.
        	if ( gameList.contains(ae.getSource()) ) {
        		
        		for ( int x = 0; x < btns.length; x++ ) {
        			
        			if ( (ae.getSource() == btns[x]) && (biDirectionalMemoryRows.get(currentLife).get(x) == true) ) {
    					btns[x].setIcon( dead );
    					biDirectionalMemoryRows.get(currentLife).set(x, false);
    				} else if ( (ae.getSource() == btns[x]) && (biDirectionalMemoryRows.get(currentLife).get(x) == false) ) {
    					btns[x].setIcon( live );
    					biDirectionalMemoryRows.get(currentLife).set(x, true );
    				}
    			}	
        		
        	// goes to the next cell life pattern if exists or creates one..	
        	} else if ( ae.getSource() == stepForewardBtn ) {
        		
        		if ( currentLife == (biDirectionalMemoryRows.size() - 1) ) {
        			GameRules gr = new GameRules( biDirectionalMemoryRows.get( currentLife ) );
        			boolean[] newResults = gr.ruleResultsReturn();
        			addNewButtonImages( newResults );	
        			currentLife++;
        		} else if ( currentLife < (biDirectionalMemoryRows.size() - 1) ) {	
        			currentLife++;
        			setButtonImages( currentLife );
        		}
        	
        	// goes to the previous cell life pattern if exists.
        	} else if ( ae.getSource() == stepBackwardBtn ) {
        		
        		int outerListLength = biDirectionalMemoryRows.size();
        		if ( currentLife > 0 ) {
        			currentLife--;
        		
        			if ( outerListLength > 0 ) {      			
        				setButtonImages( currentLife );
        			}
        		} else {
        			JOptionPane.showMessageDialog(panel,"You've reached the first cell frame.");
        		}
        	// resets game to beginning cell life pattern of all living cells.
        	} else if ( ae.getSource() == resetBtn ) {

        		clearMemoryOfPreviousCellLifes();
        		resetButtonToAllLive();
        	}			
		}
	};
	
	protected List<ArrayList<Boolean>> clearLastInnerArrayListFromOuterList( List<ArrayList<Boolean>> rowsColumns ) {
		
		List<ArrayList<Boolean>> newArrayList = new ArrayList<>();
		
		newArrayList.add( rowsColumns.get(0) );
		
		return newArrayList;
	}
	
	protected void resetButtonToAllLive() {
		
		ArrayList<Boolean> rowColumns = new ArrayList<>();
		
		for ( int x = 0; x < btns.length; x++ ) {
			btns[x].setIcon(live);
			rowColumns.add( new Boolean( true ) );
		}
		biDirectionalMemoryRows.add( rowColumns );
		currentLife = 0;
	}

	protected void clearMemoryOfPreviousCellLifes() {
		
		int outterSize = biDirectionalMemoryRows.size();
		
		for ( int x = 0; x < outterSize; x++ ) {
			
			biDirectionalMemoryRows.get(x).clear();
		}
		biDirectionalMemoryRows = new ArrayList<>();
	}
	
	protected void addNewButtonImages(boolean[] newResults) {
		
		ArrayList<Boolean> rowColumns = new ArrayList<>();
		
		for ( int x = 0; x < newResults.length; x++ ) {
		
			if ( newResults[x] == true ) {
				btns[x].setIcon(live);
				rowColumns.add( new Boolean( true ) );
			} else {
				btns[x].setIcon(dead);
				rowColumns.add( new Boolean( false ) );
			}
		}	
		biDirectionalMemoryRows.add( rowColumns );
	}
	
	protected void setButtonImages( int index) {
		
		int innerListLength = biDirectionalMemoryRows.get( index ).size();
		
		for ( int y = 0; y < innerListLength; y++ ) {
				
			boolean preBit = biDirectionalMemoryRows.get( (index) ).get(y);
			
			if ( preBit == true ) {
				btns[y].setIcon(live);
			} else {
				btns[y].setIcon(dead);
			}
		}
	}
}
