package Honeycomb;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import Utility.Utility;

public class HCApp 
{
	public static void main(String[] args) 
	{
		BufferedReader br = null;
		try
		{			
			String honeyCombFile = null, dictionaryFile = null;
			if(args != null && args.length > 0)
				honeyCombFile = args[0];
			else
				honeyCombFile = Utility.getFileNameInWorkingDirectory(HCApp.class, "honeycomb.txt");
			
			if(args != null && args.length > 1)
				dictionaryFile = args[1];
			else
				dictionaryFile = Utility.getFileNameInWorkingDirectory(HCApp.class, "dictionary.txt");
			
			//Initialize Honeycomb
			Honeycomb hb = new Honeycomb(honeyCombFile);
			
			//hb.print();
			//Read file of words and check it against Honeycomb, if found then add it
			//to a list and then sort the list
			List<String> foundWords = new ArrayList<String>();
			String word = null;
			
			br = new BufferedReader(new FileReader(dictionaryFile));
			
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
