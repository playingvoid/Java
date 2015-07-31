package LruCacheImplPkg;

//Should be a static nested class inside LRUCache cache
public class BiNode 
{
	//fields are made public just to just not to get into the coding complexity inside the cache
	//Purpose of this is just to show the basic algo and implementation of LRU cache and not
	//to demonstrate the coding or design
	public int key;
	public String value;
	public BiNode next;
	public BiNode prev;
	
	public BiNode(int key, String value)
	{
		this.key = key;
		this.value = value;
		next = null;
		prev = null;
	}
	
	@Override
	public String toString() 
	{
		return "[" + key + "," + value + "]";
	}
}
