package graphProblems;

/*
Find Order Of Characters From Alien Dictionary

Given a sorted dictionary of an alien language, find the order of characters in the alphabet.

Example One
Input: ["baa", "abcd", "abca", "cab", "cad"]
Output: "bdac"

Example Two
Input: words = ["caa", "aaa", "aab"]
Output: "cab"
 */
import java.util.*;

public class AlienDictionary {

    public static void insertOrdering(HashMap<Character, HashSet<Character>> graph, String one, String two){
        int i = 0;
        while (i<one.length() && i < two.length()){
            if(one.charAt(i) == two.charAt(i))
                i++;
            else
                break;
        }
        if(i == one.length() || i == two.length())
            return;
        graph.putIfAbsent(one.charAt(i), new HashSet<>());
        graph.putIfAbsent(two.charAt(i), new HashSet<>());
        graph.get(one.charAt(i)).add(two.charAt(i));
    }
    public static ArrayList<Character> orderCharacter(String[] order){
        HashMap<Character, HashSet<Character>> graph = new HashMap<>();
        for(int i = 0; i< order.length - 1; i++){
           insertOrdering(graph, order[i], order[i+1]);
        }
        return null;
    }

    public static void main(String[] args){
        orderCharacter(new String[]{"baa", "abcd", "abca", "cab", "cad"});
    }
}
