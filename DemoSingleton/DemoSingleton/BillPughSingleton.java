package DemoSingleton;

public class BillPughSingleton 
{
	protected static final long serialVersionUID = 1L;
	
	private BillPughSingleton() {}
	
	private static class LazyHolder 
	{ 
		private static final BillPughSingleton instance = new BillPughSingleton();
	}
	
	public static BillPughSingleton getInstance()
	{
		return LazyHolder.instance;
	}
	
	protected Object readResolve() 
	{
        return getInstance();
    }
}
