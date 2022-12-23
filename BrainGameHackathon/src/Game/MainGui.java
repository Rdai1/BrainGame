package Game;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MainGui extends java.awt.Frame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1653493455942405520L;
	
	Player player1;

	Button btnMylesGame;
	Button btnSahalGame;
	Button btnJunhengGame;
	Button btnRaymondGame;
	
	TextField mainUserInput = new TextField();
	
	Label title;
	Label l1;
	
	Label playerStats;
	
	public MainGui() {
		setTitle("TYBSA");
		title = new Label("Train Your Brain Strikes Again");
		l1 = new Label("Enter your name");
		playerStats = new Label("                                                   ");
		addWindowListener(new WindowListener() { // making the close button actually close the frame

			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub

			}
		});
		setSize(900, 600);
		setLayout(new FlowLayout());
		setLocationRelativeTo(null); // frame always opens at center of screen

		
		mainUserInput.setPreferredSize(new Dimension(360,50));;
		mainUserInput.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				player1 = new Player(mainUserInput.getText());
				playerStats.setText(player1.toString());
				mainUserInput.setText("");
				btnJunhengGame.setEnabled(true);
				btnSahalGame.setEnabled(true);
				btnRaymondGame.setEnabled(true);
				btnMylesGame.setEnabled(true);
			}
		});
		
		
		//buttons
		btnJunhengGame = new Button("Jigsaw");
		btnJunhengGame.setEnabled(false);
		btnJunhengGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameGui game = new GameGui();
				game.showImage();
				playerStats.setText(player1.toString());
			}
		});
		
		btnMylesGame = new Button("Match 2");
		btnMylesGame.setEnabled(false);
		btnMylesGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MatchTwo game1 = new MatchTwo(player1);
				playerStats.setText(player1.toString());
			}
		});
		
		btnRaymondGame = new Button("Mental Math Game");
		btnRaymondGame.setEnabled(false);
		btnRaymondGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MentalMathGame game2 = new MentalMathGame(player1);
				playerStats.setText(player1.toString());

			}
		});
		
		btnSahalGame = new Button("Unscramble");
		btnSahalGame.setEnabled(false);
		btnSahalGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Unscramble game3 = new Unscramble(player1);
				playerStats.setText(player1.toString());
			}
		});
		
		
		//everything else
		
		Font myFont = new Font("Arial",Font.BOLD,24);
		title.setFont(myFont);
		title.setAlignment(1);
		
		
		
		//adds
		
		add(title);
		add(l1);
		add(mainUserInput);
		
		add(btnJunhengGame);
		add(btnMylesGame);
		add(btnSahalGame);
		add(btnRaymondGame);
		
		add(playerStats);
		
		setVisible(true);
		setResizable(false);
	}

	public static void main(String[] args) {
		MainGui MainGui = new MainGui();
		
	}
}
