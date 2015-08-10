package DemoSingleton;

public class StaticBlockSingleton 
{
    private static final StaticBlockSingleton instance;
 
    static 
    {
        try 
        {
        	instance = new StaticBlockSingleton();
        } 
        catch (Exception e) 
        {
            throw new RuntimeException("Uffff, i was not expecting this!", e);
        }
    }
 
    public static StaticBlockSingleton getInstance() { return instance; }
 
    private StaticBlockSingleton() { }
}