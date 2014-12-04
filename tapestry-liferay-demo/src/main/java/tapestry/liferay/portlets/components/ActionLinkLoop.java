package tapestry.liferay.portlets.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.CleanupRender;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

//import uk.co.ioko.tapestry.caching.services.TimerService;

public class ActionLinkLoop {
	
	@Parameter(required=true, defaultPrefix=BindingConstants.LITERAL)
	@Property
	private int nbElements;
	
	@Property
	private int currentActionLinkIndex;
	
	//@Inject
	//private TimerService timerService;
	
	@Inject
	private JavaScriptSupport js;
	
	@Persist
	private String nonCacheRenderDuration;
	
	@SetupRender
	public void setUpRender() {
		//timerService.start("Begin ActionLinkLoop render");
	}
	
	@CleanupRender
	public void cleanupRender() {
		//timerService.saveState("End ActionLinkLoop render");
		
		String result = "";
		if (nonCacheRenderDuration == null) {
			result = "Refresh current page to get cached render duration.";
			//nonCacheRenderDuration = String.valueOf(timerService.getCurrentTime());
		}
		else
		{
			result = "Non-cached render duration was " + nonCacheRenderDuration + " ms.";
		}
		//js.addScript("document.getElementById('divResult').innerHTML = \"Links have been rendered in <b>" + timerService.getCurrentTime() + "</b> ms. " + result + "\";");
		
		//timerService.stop();
	}
}
