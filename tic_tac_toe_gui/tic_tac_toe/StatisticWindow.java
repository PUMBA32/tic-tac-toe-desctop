package tic_tac_toe;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class StatisticWindow implements ActionListener {
	JButton updateButton;
	
	JLabel xWinsLabel;
	JLabel oWinsLabel;
	JLabel gamesLabel;
	JLabel fullGamesLabel;
	
	public StatisticWindow(int xWinsCount, int oWinsCount, int gamesCount, int fullGameCount) {
		
		// frame settings
		JFrame childFrame = new JFrame();
 		childFrame.setTitle("statistic");
 		childFrame.setSize(250, 300);
 		childFrame.setResizable(false);
 		childFrame.setLayout(null);
 		childFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
 		
 		
 		// count of x wins settings
 		xWinsLabel = new JLabel();
 		xWinsLabel.setFont(new Font("Comic Sans", Font.BOLD, 20));
 		xWinsLabel.setText("X wins: "+ xWinsCount);
 		xWinsLabel.setBounds(25,20,280,30);
 		
 		
 		// count of o wins label settings
 		oWinsLabel = new JLabel();
 		oWinsLabel.setFont(new Font("Comic Sans", Font.BOLD, 20));
 		oWinsLabel.setText("O wins: "+ oWinsCount);
 		oWinsLabel.setBounds(25,50,280,30);
 		
 		
 		// count of all games label settings
 		gamesLabel = new JLabel();
 		gamesLabel.setFont(new Font("Comic Sans", Font.BOLD, 20));
 		gamesLabel.setText("games played: "+ gamesCount);
 		gamesLabel.setBounds(25,80,280,30);
 		
 		
 		// count of full games played settings
 		fullGamesLabel = new JLabel();
 		fullGamesLabel.setFont(new Font("Comic Sans", Font.BOLD, 20));
 		fullGamesLabel.setText("full games: "+ fullGameCount);
 		fullGamesLabel.setBounds(25,110,280,30);
 		
 		
 		// update button settings
 		updateButton = new JButton("update");
 		updateButton.setFont(new Font("Comic Sans", Font.BOLD, 20));
 		updateButton.setBounds(65, 220, 100, 30);
 		updateButton.setFocusable(false);
 		updateButton.addActionListener(this);
 		
 		
 		// Adding all elements to the frame
 		childFrame.add(updateButton);
 		childFrame.add(fullGamesLabel);
 		childFrame.add(gamesLabel);
 		childFrame.add(oWinsLabel);
 		childFrame.add(xWinsLabel);
 		
 		childFrame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == updateButton) {
			updateData();
		}
	}
	
	public void updateData() {
		xWinsLabel.setText("X wins: "+ TicTacToe.getXWinsCount());
		oWinsLabel.setText("O wins: "+ TicTacToe.getOWinsCount());
		gamesLabel.setText("games played: "+ TicTacToe.getGamesCount());
		fullGamesLabel.setText("full games: "+ TicTacToe.getfullGameCount());
	}
}
