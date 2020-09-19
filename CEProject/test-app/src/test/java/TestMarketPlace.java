import mockit.*;
import org.junit.*;
import org.junit.rules.ExpectedException;
import static org.hamcrest.Matchers.startsWith;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;

public class TestMarketPlace {
	private static MarketPlace marketPlaceInstance;
	private static NotificationSystem notificationSystemInstance;
	private static String email1, email2, email3, email4, email5, plan1, plan2;

	@BeforeClass
	public static void intializeTestSuite() {
		email1 = "email1@domain.com";
		email2 = "email2@domain.com";
		email3 = "email3@domain.com";
		email4 = "email4@domain.com";
		email5 = "email5@domain.com";
		plan1 = "p1";
		plan2 = "p2";
		marketPlaceInstance = MarketPlace.getInstance();
		notificationSystemInstance = NotificationSystem.getInstance();
	}

	@Before
	public void intializeSingletonInstanceForTest()
			throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		// Reset instance for market place instance
		Field mktInstanceField = MarketPlace.class.getDeclaredField("instance");
		mktInstanceField.setAccessible(true);
		mktInstanceField.set(null, null);
		// Reset instance for notification system
		Field nsInstanceField = NotificationSystem.class.getDeclaredField("instance");
		nsInstanceField.setAccessible(true);
		nsInstanceField.set(null, null);
		// Reinitialize instance for market place and notification system
		marketPlaceInstance = MarketPlace.getInstance();
		notificationSystemInstance = NotificationSystem.getInstance();
	}

	/*
	 * Add couple of uses and internet plans to system
	 */
	private static void initializedUserPlanForMarketPlace() {
		User usr1 = new User(email1, "fn1", "ln1");
		User usr2 = new User(email2, "fn2", "ln2");
		marketPlaceInstance.addUser(usr1);
		marketPlaceInstance.addUser(usr2);
		InternetPlan ip1 = new InternetPlan(plan1, "residential", Money.dollars(new BigDecimal(30)), "10");
		InternetPlan ip2 = new InternetPlan(plan2, "smb", Money.dollars(new BigDecimal(40)), "20");
		marketPlaceInstance.addPlan(ip1);
		marketPlaceInstance.addPlan(ip2);
	}

	/*
	 * add some more users to system
	 */
	private static void addMoreUsersToMarketPlace() {
		User usr3 = new User(email3, "fn3", "ln3");
		User usr4 = new User(email4, "fn4", "ln4");
		User usr5 = new User(email5, "fn5", "ln5");
		marketPlaceInstance.addUser(usr3);
		marketPlaceInstance.addUser(usr4);
		marketPlaceInstance.addUser(usr5);
	}

	/*
	 * SignUp users for plans, all users to plan 1 and 2, only user with email
	 * email1 and email2 to plan 2
	 */
	private static void initializeSignUpUsersForPlans() {
		// p1 price: 30
		marketPlaceInstance.signUpUserForNotification(email1, plan1, Money.dollars(new BigDecimal(28)));
		marketPlaceInstance.signUpUserForNotification(email2, plan1, Money.dollars(new BigDecimal(32)));
		marketPlaceInstance.signUpUserForNotification(email3, plan1, Money.dollars(new BigDecimal(32)));
		marketPlaceInstance.signUpUserForNotification(email4, plan1, Money.dollars(new BigDecimal(32)));
		// p2 price: 40
		marketPlaceInstance.signUpUserForNotification(email1, plan2, Money.dollars(new BigDecimal(38)));
		marketPlaceInstance.signUpUserForNotification(email2, plan2, Money.dollars(new BigDecimal(36)));

	}

	@Rule
	public ExpectedException notFoundThrown = ExpectedException.none();

	/*
	 * testing for user signing up for a plan when plan doesn't exist in the
	 * market place
	 */
	@Test
	public void testSignupUserPlanNotFound(@Mocked Money money) {
		initializedUserPlanForMarketPlace();
		notFoundThrown.expect(RuntimeException.class);
		notFoundThrown.expectMessage(startsWith("Plan not found in the system"));
		marketPlaceInstance.signUpUserForNotification(email1, "pname", money);
	}

	/*
	 * testing for user signing up for a plan when user doesn't exist in the
	 * market place
	 */
	@Test
	public void testSignupUserUserNotFound(@Mocked Money money) {
		initializedUserPlanForMarketPlace();
		notFoundThrown.expect(RuntimeException.class);
		notFoundThrown.expectMessage(startsWith("User not found in the system"));
		marketPlaceInstance.signUpUserForNotification("abc@domain.com", plan1, money);
	}

	/*
	 * Testing for user removing sign up for a plan when plan doesn't exist
	 * in the market place
	 */
	@Test
	public void testRemoveUserSignUpPlanNotFound(@Mocked Money money) {
		initializedUserPlanForMarketPlace();
		notFoundThrown.expect(RuntimeException.class);
		notFoundThrown.expectMessage(startsWith("Plan not found in the system"));
		marketPlaceInstance.removeUserNotificationSignUp(email1, "pname");
	}

	/*
	 * Testing for user removing sign up for a plan when user doesn't exist in
	 * the market place
	 */
	@Test
	public void testRemoveUserSignupUserNotAdded(@Mocked Money money) {
		initializedUserPlanForMarketPlace();
		notFoundThrown.expect(RuntimeException.class);
		notFoundThrown.expectMessage(startsWith("User not found in the system"));
		marketPlaceInstance.removeUserNotificationSignUp("abc@domain.com", plan1);
	}

	/*
	 * More like integration testing, Testing entire notification market place
	 * is populated with correct SignUp information
	 */
	@Test
	public void testUserSignUpSuccess(@Mocked Money anyMoney) {
		initializedUserPlanForMarketPlace();
		InternetPlan ip = marketPlaceInstance.getInternetPlan(plan1);
		User user = marketPlaceInstance.getUser(email1);
		new Expectations(ip, user) {
			{
				ip.signUpUserForNotification(user, anyMoney);
				times = 1;
			}
		};
		marketPlaceInstance.signUpUserForNotification(email1, plan1, anyMoney);
	}

	/*
	 * More like integration testing, testing signup without mocked function
	 * call Test whether all the plans have correct number of users for
	 * notifications or not
	 */
	@Test
	public void testUserSignUpSuccessNoMock() {
		initializedUserPlanForMarketPlace();
		addMoreUsersToMarketPlace();
		initializeSignUpUsersForPlans();
		InternetPlan p1 = marketPlaceInstance.getInternetPlan(plan1);
		assertNotNull("Plan p1 is found as null", p1);
		InternetPlan p2 = marketPlaceInstance.getInternetPlan(plan2);
		assertNotNull("Plan p2 is found as null", p2);
		HashSet<InternetPlan.UserSignUp> p1Users = p1.getSignedUpusers();
		assertEquals(4, p1Users.size());
		HashSet<InternetPlan.UserSignUp> p2Users = p2.getSignedUpusers();
		assertEquals(2, p2Users.size());
	}

	/*
	 * More like integration testing, testing signup without mocked function
	 * call. Test whether all the plans have correct number of users for
	 * notifications or not after user remove their sighup information
	 */
	@Test
	public void testUserSignUpRemovalSuccessNoMock() {
		initializedUserPlanForMarketPlace();
		addMoreUsersToMarketPlace();
		initializeSignUpUsersForPlans();
		InternetPlan p1 = marketPlaceInstance.getInternetPlan(plan1);
		assertNotNull("Plan p1 is found as null", p1);
		InternetPlan p2 = marketPlaceInstance.getInternetPlan(plan2);
		assertNotNull("Plan p2 is found as null", p2);

		marketPlaceInstance.removeUserNotificationSignUp(email1, plan1);
		HashSet<InternetPlan.UserSignUp> p1Users = p1.getSignedUpusers();
		assertEquals(3, p1Users.size());
		marketPlaceInstance.removeUserNotificationSignUp(email1, plan2);
		marketPlaceInstance.removeUserNotificationSignUp(email2, plan2);
		HashSet<InternetPlan.UserSignUp> p2Users = p2.getSignedUpusers();
		assertEquals(0, p2Users.size());
	}

	/*
	 * More like integration test, test the fluctuation in price before we
	 * generate any notification from the system
	 */
	@Test
	public void testUserNotificationAddSuccessNoMock() {
		initializedUserPlanForMarketPlace();
		addMoreUsersToMarketPlace();
		initializeSignUpUsersForPlans();

		// This should not insert any notification because price increased from
		// all the
		// user threshold in the system
		marketPlaceInstance.changePlanPrice(plan1, Money.dollars(new BigDecimal(37)));
		assertEquals(0, notificationSystemInstance.getAllNotificationInfo().size());

		// Should generate notification for email2, email3 and email4 but not
		// for email1
		marketPlaceInstance.changePlanPrice(plan1, Money.dollars(new BigDecimal(31)));
		assertEquals(3, notificationSystemInstance.getAllNotificationInfo().size());
		assertNotNull(notificationSystemInstance.getCurrentPlansForNotification(email2));
		assertNotNull(notificationSystemInstance.getCurrentPlansForNotification(email3));
		assertNotNull(notificationSystemInstance.getCurrentPlansForNotification(email4));
		assertNull(notificationSystemInstance.getCurrentPlansForNotification(email1));

		// This should again remove all notifications
		marketPlaceInstance.changePlanPrice(plan1, Money.dollars(new BigDecimal(32)));
		assertEquals(0, notificationSystemInstance.getAllNotificationInfo().size());

		// this should generate notification for all email1, email2, email3 and
		// email4
		marketPlaceInstance.changePlanPrice(plan1, Money.dollars(new BigDecimal(24)));
		assertEquals(4, notificationSystemInstance.getAllNotificationInfo().size());
		assertNotNull(notificationSystemInstance.getCurrentPlansForNotification(email1));
		assertNotNull(notificationSystemInstance.getCurrentPlansForNotification(email2));
		assertNotNull(notificationSystemInstance.getCurrentPlansForNotification(email3));
		assertNotNull(notificationSystemInstance.getCurrentPlansForNotification(email4));

		// This should only remove email1 from the notification system and let
		// email2,
		// email3 and email4 be there
		marketPlaceInstance.changePlanPrice(plan1, Money.dollars(new BigDecimal(29)));
		assertEquals(3, notificationSystemInstance.getAllNotificationInfo().size());
		assertNotNull(notificationSystemInstance.getCurrentPlansForNotification(email2));
		assertNotNull(notificationSystemInstance.getCurrentPlansForNotification(email3));
		assertNotNull(notificationSystemInstance.getCurrentPlansForNotification(email4));
		assertNull(notificationSystemInstance.getCurrentPlansForNotification(email1));

		// This should push notification for email1 and email2 as well
		marketPlaceInstance.changePlanPrice(plan2, Money.dollars(new BigDecimal(35)));

		// So the current state of system should be
		// email1 - one notification for p2
		// email2 - two notification for both p1 and p2
		// email3 - only one notification for p1
		// email4 - only one notification for p1
		// Assert notification should exists for all 4 users
		InternetPlan p1 = marketPlaceInstance.getInternetPlan(plan1);
		InternetPlan p2 = marketPlaceInstance.getInternetPlan(plan2);
		assertEquals(4, notificationSystemInstance.getAllNotificationInfo().size());
		HashSet<InternetPlan> u1Plans = notificationSystemInstance.getCurrentPlansForNotification(email1);
		assertEquals(1, u1Plans.size());
		assertEquals(true, u1Plans.contains(p2));

		HashSet<InternetPlan> u2Plans = notificationSystemInstance.getCurrentPlansForNotification(email2);
		assertEquals(2, u2Plans.size());
		assertEquals(true, u2Plans.contains(p1));
		assertEquals(true, u2Plans.contains(p2));

		HashSet<InternetPlan> u3Plans = notificationSystemInstance.getCurrentPlansForNotification(email3);
		assertEquals(1, u3Plans.size());
		assertEquals(true, u3Plans.contains(p1));

		HashSet<InternetPlan> u4Plans = notificationSystemInstance.getCurrentPlansForNotification(email4);
		assertEquals(1, u4Plans.size());
		assertEquals(true, u4Plans.contains(p1));
	}

	private static void testChangePriceForAllUserAllPlan() {
		// Following two price change will end up adding all users for all plans
		// in notification system
		marketPlaceInstance.changePlanPrice(plan1, Money.dollars(new BigDecimal(25)));
		marketPlaceInstance.changePlanPrice(plan2, Money.dollars(new BigDecimal(35)));
		InternetPlan p1 = marketPlaceInstance.getInternetPlan(plan1);
		InternetPlan p2 = marketPlaceInstance.getInternetPlan(plan2);
		assertEquals(4, notificationSystemInstance.getAllNotificationInfo().size());
		HashSet<InternetPlan> u1Plans = notificationSystemInstance.getCurrentPlansForNotification(email1);
		assertEquals(2, u1Plans.size());
		assertEquals(true, u1Plans.contains(p1));
		assertEquals(true, u1Plans.contains(p2));

		HashSet<InternetPlan> u2Plans = notificationSystemInstance.getCurrentPlansForNotification(email2);
		assertEquals(2, u2Plans.size());
		assertEquals(true, u2Plans.contains(p1));
		assertEquals(true, u2Plans.contains(p2));

		HashSet<InternetPlan> u3Plans = notificationSystemInstance.getCurrentPlansForNotification(email3);
		assertEquals(1, u3Plans.size());
		assertEquals(true, u3Plans.contains(p1));

		HashSet<InternetPlan> u4Plans = notificationSystemInstance.getCurrentPlansForNotification(email4);
		assertEquals(1, u4Plans.size());
		assertEquals(true, u4Plans.contains(p1));
	}

	/*
	 * More like integration test, removal of notification when user UnSignUp
	 * for a plan
	 */
	@Test
	public void testUserNotificationRemoveSuccessNoMock() {
		initializedUserPlanForMarketPlace();
		addMoreUsersToMarketPlace();
		initializeSignUpUsersForPlans();
		testChangePriceForAllUserAllPlan();

		InternetPlan p1 = marketPlaceInstance.getInternetPlan(plan1);
		InternetPlan p2 = marketPlaceInstance.getInternetPlan(plan2);
		// this should remove all the notification for user1 and plan1
		marketPlaceInstance.removeUserNotificationSignUp(email1, plan1);
		assertEquals(4, notificationSystemInstance.getAllNotificationInfo().size());
		HashSet<InternetPlan> u1Plans = notificationSystemInstance.getCurrentPlansForNotification(email1);
		assertEquals(1, u1Plans.size());
		assertEquals(false, u1Plans.contains(p1));
		assertEquals(true, u1Plans.contains(p2));

		// this should remove all the notification for user1 and plan2
		// because there is no plan for user1 exists not, user1 should
		// completely omitted from notification system
		marketPlaceInstance.removeUserNotificationSignUp(email1, plan2);
		assertEquals(3, notificationSystemInstance.getAllNotificationInfo().size());
		u1Plans = notificationSystemInstance.getCurrentPlansForNotification(email1);
		assertNull(u1Plans);
	}

	/*
	 * More like integration test, generating notification from the system
	 * should completely flush the notification system
	 */
	@Test
	public void testGenerateNotifications() {
		initializedUserPlanForMarketPlace();
		addMoreUsersToMarketPlace();
		initializeSignUpUsersForPlans();
		testChangePriceForAllUserAllPlan();
		assertEquals(4, notificationSystemInstance.getAllNotificationInfo().size());
		ArrayList<String> notifications = notificationSystemInstance.notifyUser();
		assertEquals(0, notificationSystemInstance.getAllNotificationInfo().size());
		assertEquals(4, notifications.size());
	}

}
