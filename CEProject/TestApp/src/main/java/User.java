import javax.mail.internet.InternetAddress;

/*
 * Encapsulates the user information in the system. 
 * It is assumed that a user is identified by its email address
 */
public class User {
	private String firstName;
	private String lastName;
	private String emailAddress;

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public User(String emailAddress, String firstName, String lastName) {
		if (isValidEmailAddress(emailAddress)) {
			this.emailAddress = emailAddress;
			this.firstName = firstName;
			this.lastName = lastName;
		} else {
			throw new RuntimeException("Not able to create user - Invalid Email Address: " + emailAddress);
		}
	}

	private static boolean isValidEmailAddress(String email) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (Exception ex) {
			result = false;
		}
		return result;
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if (!(other instanceof User)) {
			return false;
		}
		User oUser = (User) other;
		return this.emailAddress.equals(oUser.emailAddress);
	}

	@Override
	public int hashCode() {
		return this.emailAddress.hashCode();
	}

	@Override
	public String toString() {
		return emailAddress + " [" + firstName + ", " + lastName + "]";
	}
}
