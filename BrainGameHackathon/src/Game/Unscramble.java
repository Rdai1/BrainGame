package Game;

import javax.swing.JOptionPane;
import java.util.Random;
public class Unscramble
{
    //Strings to hold user input along with other strings----------------
    private String strInput;

    Player player;
    private byte bytGuess = 3;

    private String[] strWords = {"car","cat","hat","bat","lip"};
    private String[] strWordsScrambled = {"rca","act","tha","tba","pli"};

    private String[] strWords2 = {"apple","salty","accept","action","grape"};
    private String[] strWordsScrambled2 = {"paple","ysatl","ecacpt","oitcan","eprag"};

    private String[] strWords3 = {"absolute","academic","concrete","conflict","educated"};
    private String[] strWordsScrambled3 = {"bstauleo","caiamecd","ontcerec","oncciltf","dueetadc"};

    public Unscramble(Player p1)
    {

        this.player = p1;
        startGame();
    }

    public void startGame()
    {
        JOptionPane.showMessageDialog(null,"Welcome To Unscramble\n" +
                "Depending on your level I'll give you a word and you'll have 3 chances to unscramble it");

        if(player.getUserDifficulty()<10)
        {
            beginnerLevel();
        }
        else if(player.getUserDifficulty()>10 && player.getUserDifficulty()<20)
        {
            intermediateLevel();
        }
        else
            expertLevel();
    }

    public void beginnerLevel()
    {
        Random rand = new Random();
        int randNum = rand.nextInt(5);


        while(bytGuess>0)
        {
            strInput = JOptionPane.showInputDialog(strWordsScrambled[randNum]);

            if(strInput.equalsIgnoreCase(strWords[randNum]))
            {
                JOptionPane.showMessageDialog(null,"Good job!");
                player.setUserDifficulty(player.getUserDifficulty()+1);
                System.out.println(player.getUserDifficulty());
                return;
            }
            else
                bytGuess--;

        }
        JOptionPane.showMessageDialog(null,"you suck");
    }

    public void intermediateLevel()
    {
        Random rand = new Random();
        int randNum = rand.nextInt(5);


        while(bytGuess>0)
        {
        	strInput = JOptionPane.showInputDialog(strWordsScrambled2[randNum]);

            if(strInput.equalsIgnoreCase(strWords2[randNum]))
            {
                JOptionPane.showMessageDialog(null,"Good job!");
                player.setUserDifficulty(player.getUserDifficulty()+1);
                System.out.println(player.getUserDifficulty());
                return;
            }
            else
                bytGuess--;

        }
        JOptionPane.showMessageDialog(null,"you suck");
    }

    public void expertLevel()
    {
        Random rand = new Random();
        int randNum = rand.nextInt(5);


        while(bytGuess>0)
        {
        	strInput = JOptionPane.showInputDialog(strWordsScrambled3[randNum]);

            if(strInput.equalsIgnoreCase(strWords3[randNum]))
            {
                JOptionPane.showMessageDialog(null,"Good job!");
                player.setUserDifficulty(player.getUserDifficulty()+1);
                System.out.println(player.getUserDifficulty());
                return;
            }
            else
                bytGuess--;

        }
        JOptionPane.showMessageDialog(null,"you suck");
    }

}

