package Game;

public class Player {
	 private String name;
	 private int userLevel;

	    public Player(){
	        name = "?";
	        userLevel = 0;
	    }

	    public Player(String n, int l){
	        name = n;
	        userLevel = l;
	    }

	    public Player(String n){
	        name = n;
	        userLevel = 5;
	    }

	    public String getName(){ return name; }

	    public int getUserDifficulty(){ return  userLevel; }

	    public void setName( String n ){ name = n; }

	    public void setUserDifficulty( int l ){ userLevel = l; }

	    public String toString(){ return "User: " + name;}
}
