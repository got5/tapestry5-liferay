package tapestry.liferay.portlets.pages.jquery;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import org.apache.tapestry5.services.ajax.JavaScriptCallback;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

public class BindExample {

	@Property
	private JSONObject jqueryParams;

	@InjectComponent
	private Zone zonePopupMessages;

	@Inject
	protected JavaScriptSupport javascriptSupport;

	@Inject
	protected AjaxResponseRenderer ajaxResponseRenderer;

	@Persist(PersistenceConstants.FLASH)
	private String infoMessage;

	@InjectComponent
	private Zone messageZone;

	@OnEvent(value = EventConstants.ACTION)
	public Object displayPopup() {

		return this.zonePopupMessages.getBody();
	}

	@SetupRender
	public void init() {

		jqueryParams = new JSONObject();
		jqueryParams.put("modal", true);
		jqueryParams.put("draggable", true);
		jqueryParams.put("resizable", true);
	}

	@OnEvent(value = "bindEvent")
	public void bindEventPopup() {

		ajaxResponseRenderer.addCallback(new JavaScriptCallback() {

			public void run(JavaScriptSupport javascriptSupport) {
				javascriptSupport.addScript("$('#dialogPopupMessages').dialog('close');");

			}
		});

		this.infoMessage = "Hello from bindEvent handler!";
		ajaxResponseRenderer.addRender(messageZone);

	}

	public boolean getHasInfoMessage() {
		return (this.infoMessage != null && !("".equals(this.infoMessage)));
	}

	public String getInfoMessage() {
		return this.infoMessage;
	}

	public void setInfoMessage(String infoMessage) {
		this.infoMessage = infoMessage;
	}
}
