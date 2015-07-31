package LruCacheImplPkg;

public class TestLRUCache 
{
	public static void testSimple()
	{
		LRUCache lruCache = new LRUCache(5);
		System.out.println(lruCache);
		System.out.println(lruCache.get(3));
		lruCache.set(3, "Three");
		System.out.println(lruCache.get(3));
		lruCache.set(4, "four");
		lruCache.set(5, "five");
		System.out.println(lruCache);
		System.out.println(lruCache.get(3));
		System.out.println(lruCache);
		lruCache.set(1, "one");
		lruCache.set(2, "two");
		System.out.println(lruCache.get(4));
		System.out.println(lruCache);
		lruCache.set(6, "six");
		System.out.println(lruCache);
		lruCache.set(7, "seven");
		System.out.println(lruCache);
		System.out.println(lruCache.get(4));
		System.out.println(lruCache);
	}
	public static void main(String[] args) 
	{
		testSimple();
	}

}
