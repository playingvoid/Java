import static spark.Spark.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import spark.Request;
import spark.Response;

public class Main {
	private static void intializeMarketPlace() {
		JSONParser parser = new JSONParser();
		try {
			MarketPlace marketPlace = MarketPlace.getInstance();
			String configFile = AppConfig.getConfig(AppConfig.INPUT_PLAN_DATA_FILE);
			Object obj = parser.parse(new FileReader(configFile));
			JSONArray plans = (JSONArray) obj;

			for (Object o : plans) {
				JSONObject plan = (JSONObject) o;
				String name = (String) plan.get("name");
				String tier = (String) plan.get("tier");
				String pricePerMonth = (String) plan.get("ppm");
				String dataUsage = (String) plan.get("ttdu");
				try {
					Money price = Money.dollars(new BigDecimal(pricePerMonth));
					InternetPlan iPlan = new InternetPlan(name, tier, price, dataUsage);
					Logger.logInfo("Created Plan: " + iPlan);
					marketPlace.addPlan(iPlan);
				} catch (Exception e) {
					Logger.logError("Unable to create plan: " + plan + "|| Description:" + e);
				}
			}
		} catch (Exception e) {
			String stackTrace = AppUtil.exceptionStackTrace(e);
			Logger.logError("Problem in staring server: " + stackTrace);
			throw new RuntimeException("Problem in staring server: " + stackTrace);
		}
	}

	private static String addUser(Request req, Response resp) {
		JSONParser parser = new JSONParser();
		try {
			MarketPlace marketPlace = MarketPlace.getInstance();
			Object obj = parser.parse(req.body());
			JSONObject userObj = (JSONObject) obj;
			String emailAddress = (String) userObj.get("emailaddress");
			String firstName = (String) userObj.get("firstname");
			String lastName = (String) userObj.get("lastname");
			User user = new User(emailAddress, firstName, lastName);
			marketPlace.addUser(user);
			Logger.logInfo("Added User: " + user);
			resp.status(200);
			return AppUtil.dataToJson("user added: " + user);
		} catch (Exception e) {
			resp.status(400);
			return AppUtil.dataToJson("addUser():" + e.toString());
		}
	}

	private static String signUpUser(Request req, Response resp) {
		JSONParser parser = new JSONParser();
		try {
			MarketPlace marketPlace = MarketPlace.getInstance();
			Object obj = parser.parse(req.body());
			JSONObject userObj = (JSONObject) obj;
			String emailAddress = (String) userObj.get("emailaddress");
			String planName = (String) userObj.get("planname");
			String thresHold = (String) userObj.get("threshold");
			Money tMoney = Money.dollars(new BigDecimal(thresHold));
			marketPlace.signUpUserForNotification(emailAddress, planName, tMoney);
			String msg = "Signedup User: " + emailAddress + ", " + planName + ", " + thresHold;
			Logger.logInfo(msg);
			resp.status(200);
			return AppUtil.dataToJson(msg);
		} catch (Exception e) {
			resp.status(400);
			return AppUtil.dataToJson("signUpUser():" + e.toString());
		}
	}

	private static String getInternetPlan(Request req, Response resp) {
		try {
			String planName = req.params(":planname");
			InternetPlan plan = MarketPlace.getInstance().getInternetPlan(planName);
			String planDetails = plan == null ? "Plan not found" : plan.toString();
			return AppUtil.dataToJson(planDetails);
		} catch (Exception e) {
			resp.status(400);
			return AppUtil.dataToJson("getInternetPlan():" + e.toString());
		}
	}

	private static String changePlanPrice(Request req, Response resp) {
		JSONParser parser = new JSONParser();
		try {
			MarketPlace marketPlace = MarketPlace.getInstance();
			Object obj = parser.parse(req.body());
			JSONObject userObj = (JSONObject) obj;
			String planName = (String) userObj.get("planname");
			String pricePerMonth = (String) userObj.get("pricepermonth");
			Money newPrice = Money.dollars(new BigDecimal(pricePerMonth));
			marketPlace.changePlanPrice(planName, newPrice);
			;
			String msg = "Plan Changed : " + marketPlace.getInternetPlan(planName);
			Logger.logInfo(msg);
			resp.status(200);
			return AppUtil.dataToJson(msg);
		} catch (Exception e) {
			resp.status(400);
			return AppUtil.dataToJson("changePlanPrice():" + e.toString());
		}
	}

	private static String postNotifications(Request req, Response resp) {
		try {
			NotificationSystem ns = NotificationSystem.getInstance();
			ArrayList<String> notifications = ns.notifyUser();
			Logger.logInfo("Notifications have been sent to all users");
			resp.status(200);
			return AppUtil.dataToJson(notifications);
		} catch (Exception e) {
			resp.status(400);
			return AppUtil.dataToJson("postNotifications():" + e.toString());
		}
	}

	private static void enableCORS() {
		options("/*", (request, response) -> {
			String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
			if (accessControlRequestHeaders != null) {
				response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
			}
			String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
			if (accessControlRequestMethod != null) {
				response.header("Access-Control-Allow-Methods", "GET, POST, PUT");
			}
			return "OK";
		});

		before((request, response) -> {
			response.header("Access-Control-Allow-Origin", "*");
			response.type("application/json");
		});
	}
	
	private static void enableServices() {
		// Adds user
		put("/user", (request, response) -> {
			return addUser(request, response);
		});

		// Sign up a user for a plan
		post("/signup", (request, response) -> {
			return signUpUser(request, response);
		});

		get("/internetplan/:planname", (request, response) -> {
			return getInternetPlan(request, response);
		});
		// Change a plan price, this sends notifications
		post("/planprice", (request, response) -> {
			return changePlanPrice(request, response);
		});

		// Get notifications, could be used by an email server
		// Ideally should
		post("/notifications", (request, response) -> {
			return postNotifications(request, response);
		});
	}

	public static void main(String[] args) {
		Logger.logInfo("STARTING SERVER ... ");
		System.out.println("STARTING SERVER ... ");
		intializeMarketPlace();
		enableCORS();
		enableServices();
		Logger.logInfo("SERVER STARTED");
		System.out.println("SERVER STARTED");
	}
}