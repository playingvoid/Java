package DemoSingleton;

public class BigPughSingleton 
{
	protected static final long serialVersionUID = 1L;
	
	private BigPughSingleton() {}
	
	private static class LazyHolder 
	{ 
		private static final BigPughSingleton instance = new BigPughSingleton();
	}
	
	public static BigPughSingleton getInstance()
	{
		return LazyHolder.instance;
	}
	
	protected Object readResolve() 
	{
        return getInstance();
    }
}
