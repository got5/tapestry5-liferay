package tapestry.liferay.portlets.pages.preferences;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

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


import tapestry.liferay.portlets.HighChartsConstants;
import tapestry.liferay.portlets.HighChartsConstants.HIGHCHARTS_TYPE_ENUM;

public class HighCharts {
	
	@Property 
	private String title;
	
	@Property
	private Integer year;
	
	@Property
	private HIGHCHARTS_TYPE_ENUM type;
	
	@Property
	private List<String> atos;
	
	@Inject
	private PortletRequestGlobals globals;
	
	@Property
	private PortletRequest request;
	
	public void setupRender(){
		request = (PortletRequest)globals.getPortletRequest();
		title = request.getPreferences().getValue(HighChartsConstants.HIGHCHARTS_TITLE, "Stats");
		year = Integer.valueOf(request.getPreferences().getValue(HighChartsConstants.HIGHCHARTS_YEAR, String.valueOf(Calendar.getInstance().get(Calendar.YEAR))));
		type = HIGHCHARTS_TYPE_ENUM.valueOf(request.getPreferences().getValue(HighChartsConstants.HIGHCHARTS_TYPE, HIGHCHARTS_TYPE_ENUM.LINE.toString()));
		atos = Arrays.asList(request.getPreferences().getValues(HighChartsConstants.HIGHCHARTS_ATOS, new String[] {"Seclin"}));
	}
	
	@OnEvent(value=EventConstants.SUBMIT)
	public Object savePreferences(){
		PortletPreferences prefs = globals.getPortletRequest().getPreferences();
		
		try {
			prefs.setValue(HighChartsConstants.HIGHCHARTS_TITLE, title);
			prefs.setValue(HighChartsConstants.HIGHCHARTS_YEAR, String.valueOf(year));
			prefs.setValue(HighChartsConstants.HIGHCHARTS_TYPE, type.toString());
			
			String[] a = new String[] {};
			prefs.setValues(HighChartsConstants.HIGHCHARTS_ATOS, atos.toArray(a));
			
			
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
	
	public List<String> getModel(){
		return Arrays.asList("Seclin", "Bezon", "Lyon", "Blois");
	}
	
	/*public MultipleValueEncoder getEncoder(){
		return new MultipleValueEncoder<String>() {

			@Override
			public String toClient(String arg0) {
				return arg0;
			}

			@Override
			public List<String> toValue(String[] arg0) {
				return Arrays.asList(arg0);
			}
		};
	}*/
}
