package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class MentalMathGame implements ActionListener {
	Random rand = new Random();
	
	//labels buttons and frame to create GUI
	JFrame frame;
	JButton check;
	JLabel welcomeLabel; 
	JLabel resultLabel;
	JLabel questionLabel;
	JLabel livesLabel;
	JLabel scoreLabel;
	JTextField answer;
	
	//player
	Player player;
	
	//variables for the game
	float userAnswer;
	float actualAnswer;
	float score;
	float operand1;
	float operand2;
	byte lives;
	byte rounds;
	
	//constructor 
	public MentalMathGame(Player p1) {
		this.player = p1;
		this.userAnswer = 0;
		this.actualAnswer = 0;
		this.score = 0;
		this.lives = (byte)3;
		
		this.frame = new JFrame("Mental Math");
		this.frame.setSize(900, 600);
		this.frame.setLayout(null);
		
		this.check = new JButton("Submit");
		this.check.addActionListener(this); 
		this.check.setSize(150,50);
		this.check.setLocation(350,300);
		this.frame.add(check);
		
		this.welcomeLabel = new JLabel("Welcome to word Game");
		this.welcomeLabel.setSize(150,50);
		this.welcomeLabel.setLocation(360,25);
		this.frame.add(welcomeLabel);
		
		this.resultLabel = new JLabel("result");
		this.resultLabel.setSize(150,100);
		this.resultLabel.setLocation(400,150);
		this.frame.add(resultLabel);

		this.questionLabel = new JLabel("what is x * x");
		this.questionLabel.setSize(150,100);
		this.questionLabel.setLocation(385,100);
		this.frame.add(questionLabel);
		
		this.livesLabel = new JLabel("Lives: " + this.lives);
		this.livesLabel.setSize(150,100);
		this.livesLabel.setLocation(600,50);
		this.frame.add(livesLabel);
		
		this.scoreLabel = new JLabel("Score: " + this.score);
		this.scoreLabel.setSize(150,100);
		this.scoreLabel.setLocation(600,100);
		this.frame.add(scoreLabel);
		
		
		this.answer = new JTextField(30);
		this.answer.setSize(150,30);
		this.answer.setLocation(350, 250);
		
		this.frame.add(answer);
		
		
		this.frame.setVisible(true);
		
		getGameDiff();//method to set game difficulty
		//a little but of instruction to play the game
		JOptionPane.showMessageDialog(frame,"To play the game look at the question and leave your answer in the text box, for division question leave out the remainder");  
		start();//calls this method to play the game
	}
	
	public float getRandom() {
		
		float random = rand.nextInt(20);
		
		return random;
	}
	
	//randomly gets a Operator
	public String getOperator() {
		String Operator = "";
		byte OperatorNum = (byte) rand.nextInt(4);
		
		if (OperatorNum == 0) {
			Operator = "+";
		}
		else if (OperatorNum == 1){
			Operator = "-";
		}
		else if (OperatorNum == 2) {
			Operator = "*";
		}
		else if (OperatorNum == 3) {
			Operator = "/";
		}
	
		return Operator;
		
	}

	//gets the question 
	public void getQuestion() {
		String Operator = getOperator(); 
		float operand1 = getRandom();//gets random operands 
		float operand2 = getRandom();
		
		if (operand1 < operand2) {//dont want negative numbers so i change the spots
			float temp = operand1;
			operand1 = operand2;
			operand2 = temp;
		}
		
		//the code checks the operator then does the math and outputs the math the user should be doing 
		if (Operator.equals("*")) {
			questionLabel.setText("what is " + (int)operand1 + " " + Operator +" "+ (int)operand2);
			actualAnswer = operand1 * operand2;
		}
		else if (Operator.equals("/")) {
			questionLabel.setText("what is " + (int)operand1 + " " + Operator +" "+ (int)operand2);
			
			//hard to divide numbers that will give u decimals so i have it round 
			if (operand2 == 0) {
				actualAnswer = 0;
			}
			else {
				actualAnswer =  Math.round((operand1/operand2)*100)/100;
			}
			
		}
		else if (Operator.equals("+")) {
			questionLabel.setText("what is " + (int)operand1 + " " + Operator +" "+ (int)operand2);
			actualAnswer = operand1 + operand2;
		}
		else if (Operator.equals("-")) {
			questionLabel.setText("what is " + (int)operand1 + " " + Operator +" "+ (int)operand2);
			actualAnswer = operand1 - operand2;
		}

		scoreLabel.setText("Answer: " + actualAnswer + " Score: " + this.score);//changes the label
	}	
	
	public void start() {//tbh i dont need this code
		getQuestion();
	}
	
	public float getScore() {//gets the score
		return this.score;
	}
	
	//method to set the size
	public void setSize(int x, int y) {
		this.frame.setSize(x,y);
	}
	
	//changes game difficulty depending on the player level
	public void getGameDiff() {
		
		if (player.getUserDifficulty() < 15) {
			rounds = 5;
		}
		else if (player.getUserDifficulty() > 14 && player.getUserDifficulty() < 30) {
			rounds = 10;
		}
		else {
			rounds = 20;
		}	
		
	}

	public void actionPerformed(ActionEvent e) {
		userAnswer = Float.parseFloat(answer.getText());
		
		answer.setText("");
		
		if (userAnswer == actualAnswer) {
			score++;
			resultLabel.setText("Right!");
		}
		else {
			resultLabel.setText("Wrong");
			lives--;
			livesLabel.setText("Lives: " + lives);
		}
		
		if (score == rounds || lives == 0) {
			String temp = "";
			welcomeLabel.setText("GAME OVER");
			
			if (score == rounds) {
				temp = "WIN";
			}
			else {
				temp = "LOSE";
			}
			
			JOptionPane.showMessageDialog(frame,"GAME OVER YOU " + temp + "\nFinal score: " + this.score + "\nLives Left: " + lives);  
			player.setUserDifficulty(player.getUserDifficulty() + 1);
			System.out.println(player.getUserDifficulty());
			frame.dispose();
		}
		else {
			start();
		}
	}
}
