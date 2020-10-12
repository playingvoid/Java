package CEProject.Submission.marketplaceApp.src.main.java;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

//Singleton
public class AppConfig {
	public static final String INPUT_PLAN_DATA_FILE = "inputplandatafile";
	public static final String OUTPUT_LOG_FILE_DIR = "outputlogfiledir";
	private static final String CONFIG_FILE_NAME = "config.properties";
	private static volatile AppConfig instance = null;
	private Properties prop;

	private AppConfig() {
		prop = new Properties();
		InputStream inputStream = null;
		try {
			inputStream = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE_NAME);
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + CONFIG_FILE_NAME + "' not found in the classpath");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				inputStream.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	private static AppConfig getInstance() {
		if(instance == null){
			synchronized(AppConfig.class) {
				if(instance == null)
					instance = new AppConfig();
			}
		}
		return instance;
	}

	public static String getConfig(String property) {
		AppConfig ap = AppConfig.getInstance();
		return ap.prop.getProperty(property);
	}
}