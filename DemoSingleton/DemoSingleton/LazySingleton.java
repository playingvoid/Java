package DemoSingleton;

public class LazySingleton 
{
	private static volatile LazySingleton instance;
	
	private LazySingleton()	{}
	
	public static LazySingleton getInstance()
	{
		if(instance == null)
		{
			synchronized(LazySingleton.class)
			{
				if(instance == null)
					instance = new LazySingleton();
			}
		}
		return instance;
	}
	
	//Improved by 25%, volatile accessed only once
	public static LazySingleton getInstanceImproved()
	{
		LazySingleton result = instance;
		if(result == null)
		{
			synchronized(LazySingleton.class)
			{
				result = instance;
				if(result == null)
					instance = result = new LazySingleton();
			}
		}
		return result;
	}
}

