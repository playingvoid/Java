package CEProject.Submission.marketplaceApp.src.main.java;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
	private static volatile Logger instance = null;
	private String logFileName;
	private static Logger getInstance() {
		if(instance == null){
			synchronized(Logger.class) {
				if(instance == null)
					instance = new Logger();
			}
		}
		return instance;
	}
	private Logger(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
		logFileName = AppConfig.getConfig(AppConfig.OUTPUT_LOG_FILE_DIR) + "/" + sdf.format(date) + ".txt" ;
	}
	
	private static String getCurrentTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HH:mm:ss.SSS");
		return sdf.format(new Date());
	}
	
	private static void logRaw(String type, String output) {
		String outputLogFile = getInstance().logFileName;
		try (FileWriter fw = new FileWriter(outputLogFile, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println(String.format("[%s] [%s] [%s]: %s", type, getCurrentTime(), Thread.currentThread().getId(), output));
		} catch (IOException e) {
			System.out.print("Not able to log error: " + output);
		}
	}
	public static void logInfo(String output){
		logRaw("INFO", output);
	}
	public static void logError(String output){
		logRaw("ERROR", output);
	}
}
