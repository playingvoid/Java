package StupidHashMap;

import java.util.LinkedList;

public class StupidHashMap<Key, Value> 
{
	private static class Cell<K,V>
	{
		private K originalKey;
		private V originalValue;
		
		private Cell(K ok, V ov)
		{
			originalKey = ok;
			originalValue = ov;
		}
	}
	
	private static final int HASH_SIZE = 100;
	
	LinkedList<Cell<Key, Value>>[] storage;
	
	@SuppressWarnings("unchecked")
	public StupidHashMap()
	{
		storage =  (LinkedList<Cell<Key, Value>>[]) new LinkedList[HASH_SIZE];
	}
	
	public void put(Key key, Value value)
	{
		int index = getHashCode(key);
		
		if(storage[index] == null)
			storage[index] = new LinkedList<Cell<Key, Value>>();
		
		LinkedList<Cell<Key, Value>> hashList = storage[index];
		
		for(Cell<Key, Value> c : hashList)
		{
			if(c.originalKey.equals(key))
			{
				hashList.remove(c);
				break;
			}
		}
		
		hashList.add(new Cell<Key, Value>(key, value));
	}
	
	public Value get(Key key)
	{
		int index = getHashCode(key);
		if(storage[index] == null) return null;
		LinkedList<Cell<Key, Value>> hashList = storage[index];
		
		for(Cell<Key, Value> c : hashList)
		{
			if(c.originalKey.equals(key))
				return c.originalValue;
		}
		return null;
	}
	
	//A stupid hash code function
	//made it public just to test hash codes for different keys
	public int getHashCode(Key k)
	{
		String kStr = k.toString();
		return ((kStr.length() * 7) + ((kStr.length() > 0) ? ((kStr.charAt(0) * 111) + (kStr.charAt(kStr.length()-1) * 23))
														 : 0)) % 100;
	}
}
