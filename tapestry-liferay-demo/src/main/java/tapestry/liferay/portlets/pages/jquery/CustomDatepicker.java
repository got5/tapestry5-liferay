package tapestry.liferay.portlets.pages.jquery;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.json.JSONObject;

public class CustomDatepicker {
	
	@Property
	private String value;
	
	public JSONObject getParams(){
		return new JSONObject("prevText", "Previous Month ",
							  "nextText", "Next Month");
	}
}
