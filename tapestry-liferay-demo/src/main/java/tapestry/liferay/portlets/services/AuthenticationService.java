package tapestry.liferay.portlets.services;

public interface AuthenticationService {

	/**
	 * authenticate a user from its login and password.true if user exists.
	 * 
	 * @param login
	 * @param password
	 * @return
	 */
	String authenticate(String login, String password);

}
