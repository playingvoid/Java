import java.util.HashSet;

/*
 * It encapsulates the InternetPlan in the in the system.
 * It is assumed that an Internet Plan is identified by its planname in
 * the system.
 * 
 * This is an observable - in following observer design pattern philosophy.
 * Whenever a the price change is detected, it notifies the observers (signedup users)
 * by registering those with NotificationSystem 
 */
public class InternetPlan {
	/*
	 * This class encapsulates the UserSignUp information for this InternetPlan
	 */
	public static class UserSignUp {
		private User user;
		private Money signUpThreshold;

		public UserSignUp(User user, Money signUpThreshold) {
			this(user);
			this.signUpThreshold = signUpThreshold;
		}

		public UserSignUp(User user) {
			this.user = user;
		}

		@Override
		public boolean equals(Object other) {
			if (other == this) {
				return true;
			}
			if (!(other instanceof UserSignUp)) {
				return false;
			}
			UserSignUp osp = (UserSignUp) other;
			return this.user.equals(osp.user);
		}

		@Override
		public int hashCode() {
			return this.user.hashCode();
		}
	}
	
	//Uniquely identifies a plan
	private String planName;
	private Tier tier;
	private Money pricePerMonth;
	private String dataUsage;

	// Observer pattern - InternetPlan is playing the role of observable
	// maintains a list of user who are signed up for notification
	private HashSet<UserSignUp> signedUpUsers;

	public String getPlanName() {
		return this.planName;
	}

	public Tier getTier() {
		return tier;
	}

	public Money getPricePerMonth() {
		return pricePerMonth;
	}

	public String getDataUsage() {
		return dataUsage;
	}

	public InternetPlan(String planName, String tier, Money pricePerMonth, String dataUsage) {
		this.planName = planName;
		this.tier = Tier.parseTier(tier);
		this.pricePerMonth = pricePerMonth;
		this.dataUsage = dataUsage;
		this.signedUpUsers = new HashSet<>();
	}

	/*
	 * Remove any existing signup and its notification information in the system
	 * and add a fresh signup information for this user
	 */
	public void signUpUserForNotification(User user, Money signUpThreshold) {
		removeUserNotificationSignUp(user);
		signedUpUsers.add(new UserSignUp(user, signUpThreshold));
	}

	public void removeUserNotificationSignUp(User user) {
		UserSignUp usp = new UserSignUp(user);
		signedUpUsers.remove(usp);
		NotificationSystem notificationSystem = NotificationSystem.getInstance();
		notificationSystem.removeUserPlan(user, this);
	}

	/*
	 * This function does following
	 * 1. Change the price per month for the plan
	 * 2. Register the signed up user with the notification system
	 * new price is less than the threshold set by the user at the
	 * time of sign up
	 * 3. UnRegister the signed up user with the notification
	 * system if the new price is more than the threshold value
	 * and user is already registered for this plan in the 
	 * notification system
	 */
	public void changePlanPricePerMonth(Money newPrice) {
		this.pricePerMonth = newPrice;

		// Send notification - update the notification system
		NotificationSystem notificationSystem = NotificationSystem.getInstance();
		for (UserSignUp signUpuser : signedUpUsers) {
			if (signUpuser.signUpThreshold.compareTo(newPrice) > 0) {
				// new price is less than the threshold set by user, register
				// this event with notification system
				notificationSystem.addUserPlan(signUpuser.user, this);
			} else {
				// If any notification is previously added then remove it. This
				// will help in price fluctuation around threshold - applicable 
				// only if notifications have been not sent already
				notificationSystem.removeUserPlan(signUpuser.user, this);
			}
		}
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if (!(other instanceof InternetPlan)) {
			return false;
		}
		InternetPlan oPlan = (InternetPlan) other;
		return this.planName.equals(oPlan.planName);
	}

	@Override
	public int hashCode() {
		return this.planName.hashCode();
	}

	@Override
	public String toString() {
		return planName + " [price = " + pricePerMonth + ", tier = " + tier.toString() 
				+ ", data usage = " + dataUsage	+ "]";
	}

	public HashSet<UserSignUp> getSignedUpusers() {
		return signedUpUsers;
	}
}
