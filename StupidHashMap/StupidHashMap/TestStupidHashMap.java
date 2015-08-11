package StupidHashMap;

public class TestStupidHashMap 
{
	
	public static void main(String[] args)
	{
		testHashCodes();
		System.out.println("======================================");
		testMap();
	}
	
	private static void testHashCodes()
	{
		String[] keys = new String[] {"ram", "shyam", "ghanshaym", "a", "b", "A", "one", "tow", "two",
								"nani", "Nani", "Nany", "mummy", "papa", "bike", "mike", "juice", "pic", "potassium",
								"oal", "obl", "ocl"};
		StupidHashMap<String, String> justATestMap = new StupidHashMap<String, String>();
		for(String k : keys)
			System.out.println("Key: " + k + "\t\t hashCode = " + justATestMap.getHashCode(k));
	}
	
	private static void testMap()
	{
		StupidHashMap<String, String> map = new StupidHashMap<String, String>();
		map.put("oal", "value1");
		map.put("bol", "value2");
		System.out.println(map.get("oal"));
		System.out.println(map.get("bol"));
		map.put("obl", "value3"); //same hashkey as aol
		System.out.println(map.get("obl"));
		map.put("oal", "value4"); //Set to a different value
		System.out.println(map.get("oal"));
	}
}
