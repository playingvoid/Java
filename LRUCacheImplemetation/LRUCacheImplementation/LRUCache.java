package LRUCacheImplementation;

import java.util.HashMap;
import java.util.Map.Entry;

public class LRUCache 
{
	private BiNode head;
	private BiNode tail;
	private HashMap<Integer, BiNode> cacheDictionary;
	private int capacity;
	
	public LRUCache(int capacity)
	{
		this.capacity = capacity;
		cacheDictionary = new HashMap<Integer, BiNode>();
	}
	
	@Override
	public String toString() 
	{
		StringBuilder sb = new StringBuilder();
		BiNode curr = head;
		while(null != curr)
		{
			sb.append(curr + " -> ");
			curr = curr.next;
		}
		sb.append("NULL");
		sb.append("{");
		for(Entry<Integer, BiNode> e : cacheDictionary.entrySet())
		{
			sb.append(e.getKey() + ":"+e.getValue() + ",");
		}
		sb.append("}");
		return sb.toString();
		
	};
	//This function should try getting the value with the key.
	//IF the value is found then it should return the value.
	//	and make the value in the beginning of the list
	//Else it should return not found i.e null
	public String get(int key)
	{
		if(cacheDictionary.containsKey(key))
		{
			BiNode actionNode = removeNode(key);
			addFront(actionNode);
			return actionNode.value;
		}
		return null;
	}
	
	//Set a value associated with a key in LRU cache.
	//IF key already exists then throw exception
	//ELSE IF size of cache is less than the capacity then add to the front
	//ELSE remove the Oldest accessed record and add a new record in the front
	public void set(int key, String value)
	{
		if(cacheDictionary.containsKey(key))
			throw new RuntimeException("Can not perform set, value for input key already present: " + key);
		BiNode newNode = new BiNode(key, value);
		
		if(cacheDictionary.size() >= capacity)
		{
			removelastAccessednode();
		}
		addNode(newNode);
	}
	
	private void removelastAccessednode()
	{
		int lastAccessedKey = tail.key;
		removeNode(lastAccessedKey);
		//Drop from dictionary as well
		cacheDictionary.remove(lastAccessedKey);
	}
	
	private void addNode(BiNode node)
	{
		cacheDictionary.put(node.key, node);
		addFront(node);
	}
	
	//Note following function doesn't remove from dictionary
	private BiNode removeNode(int key)
	{
		BiNode curr = cacheDictionary.get(key);
		BiNode prev = curr.prev;
		BiNode next = curr.next;
		curr.prev = null;
		curr.next = null;
		if(prev != null)
			prev.next = next;
		else //i.e head got removed
			head = next;
		
		if(next != null)
			next.prev = prev;
		else
			tail = prev;
		
		return curr;
	}
	
	private void addFront(BiNode node)
	{
		if(null == head)
		{
			head = node;
			tail = node;
		}
		else
		{
			node.next = head;
			head.prev = node;
			head = node;
			head.prev = null;
		}
	}
}
