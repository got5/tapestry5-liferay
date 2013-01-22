package tapestry.liferay.portlets.pages;


import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequestWrapper;


import org.apache.log4j.Logger;
import org.apache.tapestry5.ValidationTracker;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.portlet.PortalPage;
import org.apache.tapestry5.portlet.services.PortletRequestGlobals;

import tapestry.liferay.portlets.FrontEventsConstants;

import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.PortalClassInvoker;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.CompanyMaxUsersException;
import com.liferay.portal.CookieNotSupportedException;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.PasswordExpiredException;
import com.liferay.portal.UserEmailAddressException;
import com.liferay.portal.UserLockoutException;
import com.liferay.portal.UserPasswordException;
import com.liferay.portal.UserScreenNameException;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.auth.AuthException;
import com.liferay.portal.util.PropsValues;


//Kudos to Neil Griffin
// https://github.com/liferay/liferay-faces/tree/3.1.x/demos/portal/jsf2-login-portlet
// see Also http://www.liferay.com/community/forums/-/message_boards/message/18704606
public class Login {

	@Persist
	@Property
	private String login;

	@Persist
	@Property
	private String password;

	
	@Inject
	private PortletRequestGlobals globals;
	

	
	@Environmental
	private ValidationTracker tracker;
	
	// Private Constants
	private static final String LOGIN_UTIL_FQCN = "com.liferay.portlet.login.util.LoginUtil";
	private static final String LOGIN_METHOD = "login";
	
	private static final String NAMESPACE_SERVLET_REQUEST_FQCN = "com.liferay.portal.servlet.NamespaceServletRequest";

	private static Logger logger = Logger.getLogger(Login.class);
	
	
	@OnEvent(value = FrontEventsConstants.LOGIN_FORM_VALIDATE)
	public Object validateLogin() {
		ThemeDisplay themeDisplay = (ThemeDisplay)globals.getPortletRequest().getAttribute(WebKeys.THEME_DISPLAY);
		ActionRequest actionRequest = globals.getActionRequest();
		ActionResponse actionResponse = globals.getActionResponse();
		HttpServletRequest httpServletRequest = PortalUtil.getHttpServletRequest(actionRequest);

		// If the request object is a wrapper that handles special namespacing considerations for portlet session
		// attributes, then get the inner-most wrapped request. This will ensure that the following call to
		// LoginUtil.login(...) will be able to work with a session that has an attribute map shared by the portal.
		if (httpServletRequest.getClass().getName().equals(NAMESPACE_SERVLET_REQUEST_FQCN)) {

			while (httpServletRequest instanceof HttpServletRequestWrapper) {
				HttpServletRequestWrapper httpServletRequestWrapper = (HttpServletRequestWrapper) httpServletRequest;
				httpServletRequest = (HttpServletRequest) httpServletRequestWrapper.getRequest();
			}
		}
		
		HttpServletResponse httpServletResponse = PortalUtil.getHttpServletResponse(actionResponse);

		
		boolean authenticated = false;
		String feedbackMessageId = null;
		
		String sessionIdbefore = httpServletRequest.getSession().getId();
		logger.debug("httpServletRequest sessionId before login is " + sessionIdbefore);
		
		PortletRequest portletRequest = globals.getPortletRequest();
		String portletSessionIdbefore = portletRequest.getPortletSession().getId();
		logger.debug("portletRequest sessionId before login is " + portletSessionIdbefore);

		try {
			MethodKey key = new MethodKey(LOGIN_UTIL_FQCN, LOGIN_METHOD, HttpServletRequest.class, HttpServletResponse.class, 
					String.class, String.class, boolean.class, String.class);
			PortalClassInvoker.invoke(false, key, new Object[] {httpServletRequest, httpServletResponse, login, password, false,
					CompanyConstants.AUTH_TYPE_EA });
			authenticated = true;
		}
		catch (AuthException e) {
			feedbackMessageId = "authentication-failed";
		}
		catch (CompanyMaxUsersException e) {
			feedbackMessageId = "unable-to-login-because-the-maximum-number-of-users-has-been-reached";
		}
		catch (CookieNotSupportedException e) {
			feedbackMessageId = "authentication-failed-please-enable-browser-cookies";
		}
		catch (NoSuchUserException e) {
			feedbackMessageId = "authentication-failed";
		}
		catch (PasswordExpiredException e) {
			feedbackMessageId = "your-password-has-expired";
		}
		catch (UserEmailAddressException e) {
			feedbackMessageId = "authentication-failed";
		}
		catch (UserLockoutException e) {
			feedbackMessageId = "this-account-has-been-locked";
		}
		catch (UserPasswordException e) {
			feedbackMessageId = "authentication-failed";
		}
		catch (UserScreenNameException e) {
			feedbackMessageId = "authentication-failed";
		}
		catch (Exception e) {
			logger.error(e);
		}
		
		//to see the session invalidation effect
		HttpSession session = httpServletRequest.getSession();
		logger.debug("httpServletRequest sessionId after login is " + session.getId());
		
		String portletSessionIdAfter = globals.getPortletRequest().getPortletSession().getId();
		logger.debug("portletRequest sessionId after login is " + portletSessionIdAfter);
		
		if (authenticated) {

			try {
					if(PropsValues.PORTAL_JAAS_ENABLE){
						return new PortalPage(themeDisplay.getPathMain() + "/portal/protected");
					}
					else
					{
						String redirect = ParamUtil.getString(actionRequest, "redirect");
						if (Validator.isNotNull(redirect)) {
							redirect = PortalUtil.escapeRedirect(redirect);
	
							if (!redirect.startsWith(Http.HTTP)) {
								redirect = getCompleteRedirectURL(httpServletRequest, redirect);
							}
	
							return new PortalPage(redirect);
							
						}
						else {
								redirect = getCompleteRedirectURL(httpServletRequest, themeDisplay.getPathMain());
								return new PortalPage(redirect);
						}
					}
				
			}
			catch (Exception e) {
				logger.error(e);
				return this;
			
			}
		}
		else{
			logger.error(feedbackMessageId);
			tracker.recordError(feedbackMessageId);
			return this;
		}
			
		}
		

	
	protected String getCompleteRedirectURL(HttpServletRequest request, String redirect) {

		HttpSession session = request.getSession();

		Boolean httpsInitial = (Boolean) session.getAttribute("HTTPS_INITIAL"); //WebKeys.HTTPS_INITIAL 
		String portalURL = null;

		if ((httpsInitial != null) && !httpsInitial.booleanValue()) {

			portalURL = PortalUtil.getPortalURL(request, false);
		}
		else {
			portalURL = PortalUtil.getPortalURL(request);
		}

		return portalURL.concat(redirect);
	}
}
