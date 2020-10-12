package CodingProblems.dpProblems.wordBreak;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class WordBreak 
{
	static Set<String> givenWords
		= new HashSet<String>(Arrays.asList("i", "a", "am", "man"));
	
	private static boolean isBreakable(String word)
	{
		int wordLen = word.length();
		//Following array is automatically initialized to false at all locations
		boolean[][] breakable = new boolean[wordLen][wordLen];
				
		for(int len = 1; len <= wordLen; len++)
		{
			for(int i=0; i <= wordLen - len; i++)
			{
				int j = i + len -1;
				if(givenWords.contains(word.substring(i, j + 1)))
				{
					breakable[i][j] = true;
				}
				else
				{
					for(int k = i; k < j; k++)
					{
						if(breakable[i][k] == true && breakable[k+1][j] == true)
						{
							breakable[i][j] = true;
							break;
						}
					}
				}
			}
		}
		
		//printBreakable(breakable);
		
		return breakable[0][wordLen-1];
	}
	
	
	protected static void printBreakable(boolean[][] breakable)
	{
		for(int i= 0;i<breakable.length;i++)
		{
			for(int j=0;j<breakable.length;j++)
			{
				System.out.print(breakable[i][j] + " ");
			}
			System.out.println();
		}
	}
	public static void main(String[] args)
	{
		System.out.println(isBreakable("iamman"));
		System.out.println(isBreakable("i"));
		System.out.println(isBreakable("aaaaa"));
		System.out.println(isBreakable("aaaaaman"));
		System.out.println(isBreakable("manman"));
		System.out.println(isBreakable("iamamanm"));
		System.out.println(isBreakable("iamam"));
	}
}
