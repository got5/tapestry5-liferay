
package tapestry.liferay.portlets.pages;


import java.util.Date;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;


public class Draggable
{

	@Inject
	private Messages messages;
	
	@InjectComponent
	@Property
	private org.apache.tapestry5.corelib.components.Zone clickZone2;
	
	@OnEvent(value="click")
	Object onClick() {
		return clickZone2.getBody();
	}
	
	@Persist
	@Property
	private String data;
	
	public Date getNow()
	 {
		 return new Date();
	 }
	
	
	
}
