package tapestry.liferay.portlets.components.customer;


import org.apache.tapestry5.ComponentEventCallback;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.internal.util.Holder;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.portlet.services.PortletRequestGlobals;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import tapestry.liferay.portlets.FrontEventsConstants;

public class LoginForm {

	@Parameter(required = true)
	@Property
	private String login;

	@Parameter(required = true)
	@Property
	private String password;

	@Inject
	private ComponentResources componentResources;

	@InjectComponent
	private Form loginForm;

	@OnEvent(value = EventConstants.VALIDATE, component = "loginForm")
	public Object validateLoginForm() {
		final Holder<Object> holder = new Holder<Object>();
		if (!loginForm.getHasErrors()) {
			componentResources.triggerEvent(FrontEventsConstants.LOGIN_FORM_VALIDATE, null,
					new ComponentEventCallback<Object>() {
						public boolean handleResult(Object result) {
							holder.put(result);
							return false;
						}
					});
		}
		return holder.get();
	}

	@OnEvent(value = EventConstants.FAILURE, component = "loginForm")
	public Object failureLoginForm() {
		final Holder<Object> holder = new Holder<Object>();
		componentResources.triggerEvent(FrontEventsConstants.LOGIN_FORM_FAILURE, null,
				new ComponentEventCallback<Object>() {
					public boolean handleResult(Object result) {
						holder.put(result);
						return false;
					}
				});
		return holder.get();
	}

	@OnEvent(value = EventConstants.SUCCESS, component = "loginForm")
	public Object successLoginForm() {
		final Holder<Object> holder = new Holder<Object>();
		componentResources.triggerEvent(FrontEventsConstants.LOGIN_FORM_SUCCESS, null,
				new ComponentEventCallback<Object>() {
					public boolean handleResult(Object result) {
						holder.put(result);
						return false;
					}
				});
		return holder.get();
	}

	@OnEvent(value = EventConstants.SUBMIT, component = "loginForm")
	public Object submitLoginForm() {
		final Holder<Object> holder = new Holder<Object>();
		componentResources.triggerEvent(FrontEventsConstants.LOGIN_FORM_SUBMIT, null,
				new ComponentEventCallback<Object>() {
					public boolean handleResult(Object result) {
						holder.put(result);
						return false;
					}
				});
		return holder.get();
	}

	@OnEvent(value = EventConstants.ACTION, component = "forgetPassword")
	public Object userForgetPassword() {
		final Holder<Object> holder = new Holder<Object>();
		componentResources.triggerEvent(FrontEventsConstants.LOGIN_FORM_FORGETPASSWORD, null,
				new ComponentEventCallback<Object>() {
					public boolean handleResult(Object result) {
						holder.put(result);
						return false;
					}
				});
		return holder.get();
	}

	@OnEvent(value = EventConstants.ACTION, component = "mainnav")
	public Object registerPage() {
		final Holder<Object> holder = new Holder<Object>();
		componentResources.triggerEvent(FrontEventsConstants.LOGIN_FORM_REGISTER, null,
				new ComponentEventCallback<Object>() {
					public boolean handleResult(Object result) {
						holder.put(result);
						return false;
					}
				});
		return holder.get();
	}
	
	@Inject
	private PortletRequestGlobals globals;
	
	public boolean isSignedIn(){
		ThemeDisplay themeDisplay = (ThemeDisplay)globals.getPortletRequest().getAttribute(WebKeys.THEME_DISPLAY);
		return themeDisplay.isSignedIn();
	}
}
