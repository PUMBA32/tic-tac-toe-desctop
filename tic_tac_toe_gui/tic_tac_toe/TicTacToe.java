package tic_tac_toe;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TicTacToe implements ActionListener{
	
	private Random random = new Random();
	
	private JFrame frame = new JFrame();
	
	private JPanel panel = new JPanel();
	private JPanel title_panel = new JPanel();
	private JPanel button_panel = new JPanel();
	private JPanel secondButton_panel = new JPanel();
	
	private JLabel textfield = new JLabel();
	
	private JButton[] buttons = new JButton[9];
	private JButton againButton = new JButton();
	private JButton statisticButton = new JButton();
	
	private boolean player1_turn;
	private boolean gameFlow = false;
	
	private static int xWinsCount = 0;
	private static int oWinsCount = 0;
	private static int gamesCount = 1;
	private static int fullGameCount = 0;
	
	public TicTacToe() {
		// settings for frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 600);
		frame.getContentPane().setBackground(new Color(50,50,50));
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);
		
		// settings for textfield (JLabel)
		textfield.setBackground(new Color(25,25,25));
		textfield.setForeground(new Color(25,255,0));
		textfield.setFont(new Font("Comic Sans", Font.BOLD, 35));
		textfield.setHorizontalAlignment(JLabel.CENTER);
		textfield.setText("Tic-Tac-Toe");
		textfield.setOpaque(true);
		
		// settings for title panel
		title_panel.setLayout(new BorderLayout());
		title_panel.setBounds(0,0,700,100);
		
		title_panel.add(textfield);
		
		// setting button panel
		button_panel.setLayout(new GridLayout(3,3));
		button_panel.setBackground(new Color(150,150,150));
		
		// adding buttons
		for(int i = 0; i < 9; i++) {
			buttons[i] = new JButton();
			button_panel.add(buttons[i]);
			buttons[i].setFont(new Font("MV Boli", Font.BOLD, 100));
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);
		}
		
		// Setting again button
		againButton.setText("play again");
		againButton.setFocusable(false);
		againButton.setFont(new Font("Comic Sans", Font.BOLD, 25));
		againButton.addActionListener(this);
		
		// Setting statistic button
		statisticButton.setText("statistic");
		statisticButton.setFocusable(false);
		statisticButton.setFont(new Font("Comic Sans", Font.BOLD, 25));
		statisticButton.addActionListener(this);
		
		// Setting second button panel
		secondButton_panel.setLayout(new GridLayout(1,2));
		secondButton_panel.add(againButton);
		secondButton_panel.add(statisticButton);
		
		// Adding all elements to the frame
		frame.add(title_panel, BorderLayout.NORTH);
		frame.add(button_panel);
		frame.add(secondButton_panel, BorderLayout.SOUTH);
		frame.setVisible(true);
		
		firstTurn();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < 9; i++) {
			if(e.getSource() == buttons[i] && gameFlow) {
				if(player1_turn) {
					if(buttons[i].getText() == "") {
						buttons[i].setForeground(new Color(255,0,0));
						buttons[i].setText("X");
						player1_turn = false;
						textfield.setText("O turn");
					}
				} 
				checkWinner();
				
				// Computer step, that going after player step
				if(gameFlow) {
					botTurn();
				}
			}
		}
		
		if(e.getSource() == againButton) {
			again();
		}
		
		if(e.getSource() == statisticButton) { 
			// Creating new window with statistic
			StatisticWindow window = new StatisticWindow(xWinsCount, oWinsCount, gamesCount, fullGameCount);
		}
	}
	
	public void firstTurn() {
		// Time delay at the start of the game
		try {
			Thread.sleep(2000);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		
//		if(random.nextInt(2) == 1) {
//			player1_turn = true;
//			textfield.setText("X turn");
//		} else {
//			player1_turn = false;
//			textfield.setText("O turn");
//		}
		
		// start of the game settings
		player1_turn = true;
		gameFlow = true;
	}
	
	public void botTurn() {
		while(true) {
			int rTurn = (int)(Math.random()*9); // getting random number of sell
			
			if(buttons[rTurn].getText().equals("")) { // if the cell is not occupied
				buttons[rTurn].setForeground(new Color(0,0,255));
				buttons[rTurn].setText("O");
				player1_turn = true;
				textfield.setText("X turn");
				break;
			}
		}
		checkWinner();
	}
	
	public void checkWinner() {
		String[] btnValues = new String[9];
		
		for(int i = 0; i < 9; i++) {
			btnValues[i] = buttons[i].getText();
			System.out.print(btnValues[i]+"");
		}
		
		// Horizontal
		if(btnValues[0] == btnValues[1] && btnValues[1] == btnValues[2] && btnValues[0] != "") {
			setWinner(0,1,2, btnValues[0]);
		}
		else if(btnValues[3] == btnValues[4] && btnValues[4] == btnValues[5] && btnValues[3] != "") {
			setWinner(3,4,5, btnValues[3]);
		}
		else if(btnValues[6] == btnValues[7] && btnValues[7] == btnValues[8] && btnValues[6] != "") {
			setWinner(6,7,8, btnValues[6]);
		}
		
		// Vertical
		if(btnValues[0] == btnValues[3] && btnValues[3] == btnValues[6] && btnValues[0] != "") {
			setWinner(0,3,6, btnValues[0]);
		}
		else if(btnValues[1] == btnValues[4] && btnValues[4] == btnValues[7] && btnValues[1] != "") {
			setWinner(1,4,7, btnValues[1]);
		}
		else if(btnValues[2] == btnValues[5] && btnValues[5] == btnValues[8] && btnValues[2] != "") {
			setWinner(2,5,8, btnValues[2]);
		}
		
		// Diagonals
		if(btnValues[0] == btnValues[4] && btnValues[4] == btnValues[8] && btnValues[0] != "") {
			setWinner(0,4,8, btnValues[0]);
		}
		else if(btnValues[2] == btnValues[4] && btnValues[4] == btnValues[6] && btnValues[2] != "") {
			setWinner(2,4,6, btnValues[2]);
		}
	}

	public void setWinner(int a, int b, int c, String winner) {
		// Color change from white to green
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);
		
		if(winner.equals("O")) {
			textfield.setText("O won! You lose :(");
			this.oWinsCount++;
		}
		else {
			textfield.setText("You won!");
			this.xWinsCount++;
		}
		
		this.gameFlow = false;
		this.fullGameCount++;
	}
	
	// Restart game 
	public void again() {
		this.gamesCount++;
		for(int i = 0; i < 9; i++) {
			buttons[i].setText("");
			buttons[i].setBackground(new Color(255,255,255));
		}
		gameFlow = true;
	}
	
	
	// Just four getters
	
	public static int getXWinsCount() {
		return xWinsCount;
	}
	
	public static int getOWinsCount() {
		return oWinsCount;
	}
	
	public static int getGamesCount() {
		return gamesCount;
	}
	
	public static int getfullGameCount() {
		return fullGameCount;
	}
}
