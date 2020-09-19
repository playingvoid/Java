import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

//Singleton
public class NotificationSystem {
	
	private static volatile NotificationSystem instance = null;
	
	private HashMap<User, HashSet<InternetPlan>> userPlanNotifications;
	
	public static NotificationSystem getInstance() {
		if(instance == null){
			synchronized(NotificationSystem.class) {
				if(instance == null)
					instance = new NotificationSystem();
			}
		}
		return instance;
	}

	private NotificationSystem(){
		userPlanNotifications = new HashMap<>();
	}
	
	public void addUserPlan(User user, InternetPlan plan){
		HashSet<InternetPlan> plans = userPlanNotifications.getOrDefault(user, new HashSet<InternetPlan>());
		plans.add(plan);
		userPlanNotifications.put(user, plans);
	}
	
	public void removeUserPlan(User user, InternetPlan plan){
		HashSet<InternetPlan> plans = userPlanNotifications.getOrDefault(user, new HashSet<InternetPlan>());
		plans.remove(plan);
		if(plans.isEmpty()){
			userPlanNotifications.remove(user);
		}
	}
	
	public ArrayList<String> notifyUser(){
		ArrayList<String> notifications = new ArrayList<>();
		for(Map.Entry<User, HashSet<InternetPlan>> entry : userPlanNotifications.entrySet()){
			notifications.add(entry.getKey().getEmailAddress() + " -> " + entry.getValue());
		}
		userPlanNotifications.clear();
		return notifications;
	}
	
	public HashSet<InternetPlan> getCurrentPlansForNotification(User user){
		return userPlanNotifications.get(user);
	}
	
	public HashSet<InternetPlan> getCurrentPlansForNotification(String userEmail){
		User user = new User(userEmail, "", "");
		return getCurrentPlansForNotification(user);
	}
	
	public HashMap<User, HashSet<InternetPlan>> getAllNotificationInfo(){
		return userPlanNotifications;
	}
}
