package tapestry.liferay.portlets.pages.preferences;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;

import javax.portlet.PortletMode;
import javax.portlet.PortletModeException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.portlet.services.PortletRequestGlobals;

import tapestry.liferay.portlets.FrontPortletConstants;
import tapestry.liferay.portlets.HighChartsConstants;


public class GridPreferences {
	
	@Property
	private int rowsPerPage;
	
	@Inject
	private PortletRequestGlobals globals;
	
	@Property
	private PortletRequest request;
	
	public void setupRender(){
		request = (PortletRequest)globals.getPortletRequest();
		rowsPerPage = Integer.valueOf(request.getPreferences().getValue(FrontPortletConstants.GRID_PREF_ROWSPERPAGE, "5"));
		
	}
	
	
	@OnEvent(value=EventConstants.SUBMIT)
	public Object savePreferences(){
		PortletPreferences prefs = globals.getPortletRequest().getPreferences();
		
		try {
			prefs.setValue(FrontPortletConstants.GRID_PREF_ROWSPERPAGE, String.valueOf(rowsPerPage));
				
		} catch (ReadOnlyException e) {
			
		}
		try {
			prefs.store();
			globals.getActionResponse().setPortletMode(PortletMode.VIEW);
		} catch (PortletModeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ValidatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
