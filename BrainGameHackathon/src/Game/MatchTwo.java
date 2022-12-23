package Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import javax.swing.*;
import java.util.Collections;

public class MatchTwo {

    private JFrame frame;
    private Player player;
    static GraphicsConfiguration gc;
    private Timer timer;
    private Timer timer2;
    private int time;
    private int timeLeft=2000;
    private Character[] letters = {'A','A','B','B','C','C','D','D','E','E','F','F','G','G','H','H','I','I','J','J','K','K','L','L','M','M','N','N','O','O','P','P','Q','Q','R','R'};
    JButton[] buttons = new JButton[36];
    private Character[] newLetters = new Character[36];
    private int letterOne;
    private int letterTwo;
    private boolean foundOne = false;

    public MatchTwo(Player p){
        player = p;
        initialize();
    }

    public int findIndex(Object[] array, Object object){

        if(array == null) return -1;
        int l = array.length;
        int i = 0;
        while(i<l){
            if(object == array[i])
                return i;
            i++;
        }
        return -1;

    }

    public void initialize(){

        //shuffle the list of letters and put them in a List
        List<Character> listOfLetters = new ArrayList<>(Arrays.asList(letters));
        Collections.shuffle(listOfLetters);

        //make the frame
        frame = new JFrame(gc);

        //sets the frame's title
        frame.setTitle("Match-2");

        //make the progress bars
        JProgressBar pb = new JProgressBar(0, 2000);

        pb.setBounds(460, 100, 160, 35);
        pb.setValue(0);
        pb.setStringPainted(true);
        frame.add(pb);

        JProgressBar pb2 = new JProgressBar(0, 2000);

        pb2.setBounds(460, 185, 160, 35);
        pb2.setValue(2000);
        pb2.setStringPainted(true);
        frame.add(pb2);

        JLabel label = new JLabel();
        label.setText("Match-Two");
        label.setFont(new Font("Serif", Font.PLAIN, 28));
        label.setBounds(670, 130, 160, 50);
        frame.add(label);

        JTextArea label2 = new JTextArea();
        label2.setText("After a brief delay, these letters will all be hidden.        Reme" +
                "mber their places and guess them all before you    run out of time to win!");
        label2.setFont(new Font("Serif", Font.PLAIN, 14));
        label2.setBounds(500, 240, 320, 500);
        label2.setLineWrap(true);
        frame.add(label2);

        //make the timer
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                time+= player.getUserDifficulty()*10;
                pb.setValue(time);
                if(time == 2000){
                    hide();
                    timer2.start();
                }
            }
        });

        timer2 = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pb2.setValue(timeLeft);
                timeLeft-= 2.5* player.getUserDifficulty();
                if(timeLeft == 0){
                    gameOver();
                }
            }
        });

        //make and add the buttons
        int count = 0;
        for(int x = 0; x<6; x++){
            for(int i = 0; i<6; i++) {
                JButton b = new JButton(String.valueOf(listOfLetters.get(count)));
                b.setBounds(100 + 60 * i, 100+60*x, 50, 50);
                b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        b.setText(Character.toString(newLetters[findIndex(buttons, b)]));
                        if(!foundOne){
                            letterOne = findIndex(buttons, b);
                            foundOne = true;
                        }
                        else if(foundOne) {
                            letterTwo = findIndex(buttons, b);
                            check(letterOne, letterTwo);
                        }
                        checkGameOver();
                    }
                });
                frame.add(b);
                buttons[count] = b;
                count++;
            }
        }

        //set the frame's size
        frame.setSize(900,600);

        //show the frame
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        timer.start();

    }

    public void hide(){
        for(int i = 0; i < 36; i++){
            newLetters[i] = buttons[i].getText().charAt(0);
            buttons[i].setText("?");
        }
    }

    public void checkGameOver(){
        boolean gameOver = true;
        for(JButton b : buttons){
            if(b.getText().equals("?"))
                gameOver = false;

        }
        if(gameOver)
            gameOver();
    }

    public void gameOver(){

        boolean playerWon = false;
        for(JButton b : buttons){
            if(b.getText().equals("?")) {
                playerWon = false;
            }else {
                playerWon = true;
            }
        }
        if(!playerWon) {
            JOptionPane.showMessageDialog(null, "You lost!");
            player.setUserDifficulty(player.getUserDifficulty()-1);
            frame.dispose();

        }else {
            JOptionPane.showMessageDialog(null, "You won!");
            player.setUserDifficulty(player.getUserDifficulty()+1);
            frame.dispose();

        }
    }

    public void check(int one, int two){
        if(!buttons[one].getText().equals(buttons[two].getText())){
            buttons[one].setText("?");
            buttons[two].setText("?");
        }
        foundOne = false;
    }

}

