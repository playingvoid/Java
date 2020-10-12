import java.util.HashMap;

/*
 * Encapsulates the market place logic and its public APIs. 
 * It is designed as a singleton class which maintains all
 * the Internet plans and uses in the system.
 */
public class MarketPlace {
	private static volatile MarketPlace instance = null;
	private HashMap<String, InternetPlan> internetPlans;
	private HashMap<String, User> users;
	
	public static MarketPlace getInstance() {
		if(instance == null){
			synchronized(MarketPlace.class) {
				if(instance == null)
					instance = new MarketPlace();
			}
		}
		return instance;
	}
	
	private MarketPlace(){
		internetPlans = new HashMap<>();
		users = new HashMap<>();
	}
	
	/*
	 * Adds a plan to market place. A plan is identified by their plan name
	 */
	public void addPlan(InternetPlan internetPlan){
		internetPlans.put(internetPlan.getPlanName(), internetPlan);
	}
	
	/*
	 * Adds an user to market place. An user is identified in the system with its email address
	 */
	public void addUser(User user){
		users.put(user.getEmailAddress(), user);
	}
	
	/*
	 * SignUp an user for a plan and its threshold value
	 */
	public void signUpUserForNotification(String userEmailAddress, String planName, Money thresHold){
		InternetPlan plan = internetPlans.get(planName);
		if(null == plan){
			throw new RuntimeException("Plan not found in the system: " + planName);
		}
		
		User user = users.get(userEmailAddress);
		if(null == user){
			throw new RuntimeException("User not found in the system: " + userEmailAddress);
		}
		plan.signUpUserForNotification(user, thresHold);
	}
	
	/*
	 * Removes any sign up information for a user for a plan from the system
	 */
	public void removeUserNotificationSignUp(String userEmailAddress, String planName){
		InternetPlan plan = internetPlans.get(planName);
		if(null == plan){
			throw new RuntimeException("Plan not found in the system: " + planName);
		}
		
		User user = users.get(userEmailAddress);
		if(null == user){
			throw new RuntimeException("User not found in the system: " + planName);
		}
		plan.removeUserNotificationSignUp(user);
	}

	/*
	 * Changes a plan price (price per month). This operation updates the notification
	 * System. For all the users signed up for a plan and a threshold less than the
	 * new plan price will get register with the notification system
	 */
	public void changePlanPrice(String planName, Money newPrice){
		InternetPlan plan = internetPlans.get(planName);
		if(null == plan){
			throw new RuntimeException("Plan not found in the system: " + planName);
		}
		plan.changePlanPricePerMonth(newPrice);
	}
	
	/*
	 * Returns the Plan identified by a plan name
	 */
	public InternetPlan getInternetPlan(String planName){
		return internetPlans.get(planName);
	}
	
	/*
	 * Returns the User identified by an email address 
	 */
	public User getUser(String emailAddress){
		return users.get(emailAddress);
	}
}
