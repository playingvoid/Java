package BST;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;


//client is represented by a string id
public class AddCredits13 {
	private Map<String, Integer> clientCredit;
	private NavigableMap<Integer, Set<String>> creditToClients;
	private Integer wrapperCredit;
	
	public AddCredits13(){
		clientCredit = new HashMap<>();
		creditToClients = new TreeMap<Integer, Set<String>>();
		wrapperCredit = 0;
	}
	
	public void insert(String clientId, Integer credit){
		if(credit == null || credit < 0){
			throw new RuntimeException("Invalid Credit amount");
		}
		if(clientId == null || clientId == ""){
			throw new RuntimeException("Invalid Client ID");
		}
		if(clientCredit.containsKey(clientId)){
			int previousCredit = clientCredit.get(clientId);
			creditToClients.get(previousCredit).remove(clientId);
		}		
		int absoluteCredit = credit - wrapperCredit;
		clientCredit.put(clientId, absoluteCredit);
		if(!creditToClients.containsKey(absoluteCredit)){
			creditToClients.put(absoluteCredit, new HashSet<>());
		}
		creditToClients.get(absoluteCredit).add(clientId);
	}
	
	public void remove(String clientId){
		if(clientCredit.containsKey(clientId)){
			int absoluteCredit = clientCredit.get(clientId);
			clientCredit.remove(clientId);
			creditToClients.get(absoluteCredit).remove(clientId);
		}	
	}
	
	public Integer lookUp(String clientId){
		if(clientCredit.containsKey(clientId)){
			return clientCredit.get(clientId) + wrapperCredit;
		}
		return null;
	}
	
	public void addToAll(Integer credit){
		if(credit == null || credit < 0)
			throw new RuntimeException("Invalid Credit amount");
		wrapperCredit += credit;
	}
	
	public String maxCreditClient(){
		if(creditToClients.size() == 0)
			return null;
		return creditToClients.lastEntry().getValue().iterator().next();
	}
	
	@Override 
	public String toString(){
		return "<<\n" + clientCredit.toString() + " \n " + creditToClients.toString() + " \n>>";
	}
	
	public void iterate(){
		Iterator<Entry<Integer, Set<String>>> itr = creditToClients.descendingMap().entrySet().iterator();
		while(itr.hasNext()){
			System.out.println(itr.next());
		}
	}
	
	public static void main(String[] arg){
		AddCredits13 addCredit = new AddCredits13();
		addCredit.insert("c1",  30);
		addCredit.insert("c2",  40);
		addCredit.insert("c3",  40);
		addCredit.insert("c4",  10);
		addCredit.insert("c5",  70);
		addCredit.insert("c6",  50);
		//System.out.println(addCredit);
		addCredit.iterate();
	}
}

