
package tapestry.liferay.portlets.pages;


import java.util.Date;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.slf4j.Logger;


public class Droppable
{

	@Persist
	@Property
	private String data;
	
	@Component
	private Zone dropzone; 
	
	
	 public Object onDrop(String contexte)
	 {
		 data = contexte;
		 return dropzone.getBody();		 
	 }
	 public Date getNow()
	 {
		 return new Date();
	 }
	 
	 
	 @InjectComponent
	 @Property
	 private org.apache.tapestry5.corelib.components.Zone clickZone;
		
	 @Inject
	 private Logger logger;
		
	 @OnEvent(value="click")
	    Object onClick() {
			return clickZone.getBody();
		}
	 
}
