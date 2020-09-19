package SquareInterview;

import java.util.Arrays;
import java.util.List;

class Team {
	String name;
	int numWin;
	int numLoss;
	
	public Team(String name, int win, int loss){
		this.name = name;
		numWin = win;
		numLoss = loss;
	}
	
}

/*class Sports{
	
	List<Team> teams;
	
	boolean isValid(){
		
		int wins = 0, loss = 0;
		for(Team t : teams){
			wins += t.numWin;
			loss += t.numLoss;
		}
		
		return wins == loss;
	}
	
	public Sports(List<Team> teams){
		this.teams = teams;
	}
}

public class Interview {

	public static void main(String[] args){
		Team A = new Team("A", 4, 3);
		Team B = new Team("B", 5, 4);
		
		List<Team> team = Arrays.asList(new Team[]{A, B});
		Sports sport = new Sports(team);
		
		System.out.println(sport.isValid());
		
	}
}
*/

