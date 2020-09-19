package socialNetwork;

import java.util.*;

/*
 They gave a sample frame code having 4 methods 
 1. MakeFriends() 
 2. removeFriends() 
 3. getDirectFriends()
 4. getIndirectFriends(). 
 Have to implement these methods. Also create unit testcases to check conditions.  
 
 */
public class SocialNetwork {

	private Map<Long, Set<Long>> friendNetwork;
	
	public SocialNetwork(){
		friendNetwork = new HashMap<Long, Set<Long>>();
	}
	
	public void makeFriend(long person1, long person2){
		if(person1 == person2){
			throw new RuntimeException("Can not make friendship between same person");
		}
		Set<Long> person1Friends = friendNetwork.getOrDefault(person1, new HashSet<Long>());
		Set<Long> person2Friends = friendNetwork.getOrDefault(person2, new HashSet<Long>());
		person1Friends.add(person2);
		person2Friends.add(person1);
		friendNetwork.put(person1, person1Friends);
		friendNetwork.put(person2, person2Friends);
	}
	
	public void removeFriends(long person1, long person2){
		if(person1 == person2){
			throw new RuntimeException("Can not remove friendship between same person");
		}
		Set<Long> person1Friends = friendNetwork.get(person1);
		Set<Long> person2Friends = friendNetwork.get(person2);
		if(null == person1Friends || null == person2Friends)
			throw new RuntimeException("Friend record not found");
		if(!person1Friends.contains(person2) 
		   || !person2Friends.contains(person1))
			throw new RuntimeException("Provided persons are not friends");
		
		person1Friends.remove(person2);
		person2Friends.remove(person1);
	}
	
	public Set<Long> getDirectFriends(Long person){
		return friendNetwork.get(person);
	}

	public Set<Long> getIndirectFriends(Long person) {
		Set<Long> directFriends = friendNetwork.get(person);
		if(null == directFriends)
			return null;
		Set<Long> indirectFriends = new HashSet<Long>();
		for(Long directFriend :  directFriends){
			DFSVisit(directFriend, person,  directFriends, indirectFriends);
		}
		return indirectFriends;
	}
	
	public long totalPersons(){
		return friendNetwork.size();
	}
	
	private void DFSVisit(long friend, long source, Set<Long> directFriends, Set<Long> indirectFriends){
		for(Long indFriend : friendNetwork.get(friend)){
			if( source != indFriend
				&& !directFriends.contains(indFriend) 
				&& !indirectFriends.contains(indFriend)){
				indirectFriends.add(indFriend);
				DFSVisit(indFriend, source, directFriends, indirectFriends);
			}
		}
	}
	
	public static void main(String[] args){
		SocialNetwork sn = new SocialNetwork();
		sn.makeFriend(10, 20);
		sn.makeFriend(10, 30);
		sn.makeFriend(10, 40);
		sn.makeFriend(20, 30);
		sn.makeFriend(30, 50);
		sn.makeFriend(50, 40);
		sn.makeFriend(40, 60);
		sn.makeFriend(40, 70);
		sn.makeFriend(70, 10);
		System.out.println(sn.getDirectFriends(10L));
		System.out.println(sn.getIndirectFriends(10L));
	}
}
