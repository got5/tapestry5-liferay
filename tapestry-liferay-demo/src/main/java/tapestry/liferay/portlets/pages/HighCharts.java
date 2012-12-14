package tapestry.liferay.portlets.pages;

import java.util.Calendar;
import java.util.Random;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONArray;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.portlet.services.PortletRequestGlobals;
import org.apache.tapestry5.services.javascript.InitializationPriority;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

import tapestry.liferay.portlets.HighChartsConstants;
import tapestry.liferay.portlets.HighChartsConstants.HIGHCHARTS_TYPE_ENUM;

@Import(library={"classpath:tapestry/liferay/portlets/pages/highcharts.js"})
public class HighCharts {
	
	@Inject
	private PortletRequestGlobals request; 
	
	@Inject
	private JavaScriptSupport javascript;
	
	public JSONObject getOptions(){
		PortletPreferences prefs = request.getPortletRequest().getPreferences();
		
		JSONObject opt = new JSONObject();

		String title = prefs.getValue(HighChartsConstants.HIGHCHARTS_TITLE, "Stats");
		
		HIGHCHARTS_TYPE_ENUM type = HIGHCHARTS_TYPE_ENUM.valueOf(prefs.getValue(HighChartsConstants.HIGHCHARTS_TYPE, HIGHCHARTS_TYPE_ENUM.LINE.toString()));
		
		if(type == HIGHCHARTS_TYPE_ENUM.PIE){
			title += (" - " + prefs.getValue(HighChartsConstants.HIGHCHARTS_YEAR, String.valueOf(Calendar.getInstance().get(Calendar.YEAR))));
		}
		
		opt.put("text", title);
		opt.put("x", -20);
		
		JSONObject high = new JSONObject();
		high.put("subtitle", opt);
		high.put("series", getSeries());
		return high;
	}
	
	private JSONArray getSeries() {
		JSONArray series = new JSONArray();
		PortletPreferences prefs = request.getPortletRequest().getPreferences();
		
		if(HIGHCHARTS_TYPE_ENUM.valueOf(prefs.getValue(HighChartsConstants.HIGHCHARTS_TYPE, HIGHCHARTS_TYPE_ENUM.PIE.toString())) == HIGHCHARTS_TYPE_ENUM.PIE){
			JSONObject json = new JSONObject();
			json.put("type", "pie");
			json.put("name", "CA");
			
			JSONArray atosArray = new JSONArray();
			Random randomGenerator = new Random();
			for(String atos : prefs.getValues(HighChartsConstants.HIGHCHARTS_ATOS, new String[]{"Seclin"})){
				atosArray.put(new JSONArray().put(atos).put(randomGenerator.nextInt(100)));	
			}
			json.put("data", atosArray);
			series.put(json);
		}
		else {
			for(String atos : prefs.getValues(HighChartsConstants.HIGHCHARTS_ATOS, new String[]{"Seclin"})){
				series.put(new JSONObject().put("name", atos).put("data", getRandomSeries()));	
			}
		}
		return series;
	}

	private JSONArray getRandomSeries() {
		JSONArray random = new JSONArray();
		Random randomGenerator = new Random();
		for(int i=0; i<12; i++){
			random.put(randomGenerator.nextInt(100));
		}
		return random;
	}

	@AfterRender
	public void afterRender(){
		javascript.addInitializerCall(InitializationPriority.EARLY, "basicComponent", 
				new JSONObject("clientId", getClientId(), 
						"type_graph", request.getPortletRequest().getPreferences().getValue(HighChartsConstants.HIGHCHARTS_TYPE, HIGHCHARTS_TYPE_ENUM.LINE.toString())));
	}
	
	public String getClientId(){
		return request.getPortletRequest().getWindowID();
	}
}
