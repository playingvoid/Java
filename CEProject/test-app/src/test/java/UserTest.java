import java.util.Arrays;
import java.util.Collection;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(org.junit.experimental.runners.Enclosed.class)
public class UserTest {
	@RunWith(Parameterized.class)
	public static class UserParameterisedInvalidEmailTest {
		private String email;

		@SuppressWarnings("rawtypes")
		@Parameters
		public static Collection inputParameters() {
			return Arrays.asList(new Object[][] { { "" }, { null }, { "invalidemail" } });
		}

		public UserParameterisedInvalidEmailTest(String email) {
			this.email = email;
		}

		@Rule
		public ExpectedException invalidEmailRule = ExpectedException.none();

		/*
		 * Test user instantiation with invalid email address
		 */
		@Test
		public void testInvalidEmail() {
			invalidEmailRule.expect(RuntimeException.class);
			invalidEmailRule.expectMessage(startsWith("Not able to create user - Invalid Email Address:"));
			new User(email, "fistname", "lastname");			
		}
	}

	public static class userSingleTest {
		/*
		 * Test user instantiation correctness
		 */
		@Test
		public void testUserInstantiation() {
			User user = new User("email@email.com", "firstname", "lastname");
			assertEquals("firstname", user.getFirstName());
			assertEquals("lastname", user.getLastName());
			assertEquals("email@email.com", user.getEmailAddress());
			assertEquals("email@email.com [firstname, lastname]", user.toString());
		}

		/*
		 * Test user equality, email address should uniquely identify a user
		 * in system
		 */
		@Test
		public void testUserEquality() {
			User user1 = new User("email@email.com", "firstname", "lastname");
			User user2 = new User("email@email.com", "firstname1", "lastname2");
			assertEquals(true, user1.equals(user2));
			assertEquals("email@email.com".hashCode(), user1.hashCode());
			assertEquals("email@email.com".hashCode(), user2.hashCode());
		}

		/*
		 * Test user inequality - again in terms of email address
		 */
		@Test
		public void testUserInEquality() {
			User user1 = new User("email1@email.com", "firstname", "lastname");
			User user2 = new User("email2@email.com", "firstname", "lastname");
			assertEquals(false, user1.equals(user2));
			assertEquals("email1@email.com".hashCode(), user1.hashCode());
			assertEquals("email2@email.com".hashCode(), user2.hashCode());
			assertNotSame(user2.hashCode(), user1.hashCode());
		}
	}

}
