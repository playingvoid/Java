package Utility;

import java.nio.file.Paths;

public class Utility 
{
	//Function assumes package to be present
	public static String getFileNameInWorkingDirectory(Class<?> cls, String fileName)
	{
		String cwd = System.getProperty("user.dir");
		String pkg = cls.getPackage().getName();
		return Paths.get(cwd, "bin", pkg, fileName).toString();
	}
}
