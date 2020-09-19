package brproblems;

import java.util.*;
public class HackLandElection {
	
	static void createCountMap(String[] votes, HashMap<String, Integer> voteCount){
		for(String vote : votes){
			Integer count = voteCount.getOrDefault(vote, 0);
			voteCount.put(vote, count+1);
		}
	}
	static String getMaxedVoteSorted(HashMap<String, Integer> voteCount){
		int maxCount = 0;
		String name = "0";
		for(Map.Entry<String, Integer> entry : voteCount.entrySet()){
			if(entry.getValue() > maxCount){
				name = entry.getKey();
				maxCount = entry.getValue();
			} else if(entry.getValue() == maxCount && entry.getKey().compareTo(name) > 1){
				name = entry.getKey();
			}
		}
		return name;
	}
	static String electionWinner(String[] votes) {
		HashMap<String, Integer> voteCount = new HashMap<>();
		createCountMap(votes, voteCount);
		return getMaxedVoteSorted(voteCount);	
	}
	
	public static void main(String[] args){
		String[] input1 = new String[]{"Alex", "Michael", "Harry","Dave","Michael", "Victor", "Harry", "Alex", "Mary", "Mary"};
		String[] input2 = new String[]{"Victor","Veronica","Ryan","Dave","Maria","Maria","Farah","Farah","Ryan","Veronica"};
		System.out.println(electionWinner(input1));
		System.out.println(electionWinner(input2));
	}
}
