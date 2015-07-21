package Honeycomb;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HCApp 
{
	public static void main(String[] args) 
	{
		
		BufferedReader br = null;
		try
		{
			//Initialize Honeycomb
			Honeycomb hb = new Honeycomb(args[0]);
			
			//hb.print();
			//Read file of words and check it against Honeycomb, if found then add it
			//to a list and then sort the list
			List<String> foundWords = new ArrayList<String>();
			String word = null;
			
			br = new BufferedReader(new FileReader(args[1]));
			
			while((word = br.readLine()) != null)
			{
				if(hb.findWord(word))
					foundWords.add(word);
			}
			
			Collections.sort(foundWords);
			
			for(String w : foundWords)
				System.out.println(w);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		finally
		{
			try
			{
				if(null != br)
					br.close();
			}
			catch(Exception e)
			{}
		}
	}

}
