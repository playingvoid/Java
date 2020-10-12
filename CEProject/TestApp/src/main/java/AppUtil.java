import java.io.PrintWriter;
import java.io.StringWriter;

import com.google.gson.Gson;

public class AppUtil {
	public static String exceptionStackTrace(Exception e){
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return sw.toString();
	}
	
	public static String dataToJson(Object data) {
		Gson gson = new Gson();
		return gson.toJson(data);
	}
}
