package tapestry.liferay.portlets.pages;

import java.util.Date;
import java.util.List;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.RequestParameter;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.grid.GridDataSource;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.portlet.services.PortletRequestGlobals;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.BeanModelSource;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.got5.tapestry5.jquery.internal.TableInformation;
import org.slf4j.Logger;

import tapestry.liferay.portlets.data.Celebrity.Celebrity;
import tapestry.liferay.portlets.data.Celebrity.CelebritySource;
import tapestry.liferay.portlets.data.Celebrity.IDataSource;

@Import(stylesheet ={ "context:dataTables/css/demo_table_jui.css",
		  "context:dataTables/css/demo_page.css",	
		  "context:dataTables/css/demo_table.css",
		  "context:dataTables/ColVis/media/css/ColVis.css", 
		  "context:dataTables/ColReorder/media/css/ColReorder.css", 
		  "context:dataTables/TableTools/css/TableTools.css"})
public class DataTable
{
@SessionState
private IDataSource dataSource;
private Celebrity celebrity;
private CelebritySource celebritySource;

/**
* For ajax mode, annotate your current item with @Environmental
* The same for any object used in propertyOverride block for ajax mode (index, ...)
* */
@Environmental
@Property
private Celebrity current;

@Environmental
@Property
private int index;

@Inject
private Logger logger;


public GridDataSource getCelebritySource() {
	if(celebritySource==null)
	celebritySource = new CelebritySource(dataSource);
	return celebritySource;
}

public List<Celebrity> getAllCelebrities() {
	logger.debug("Getting all celebrities...");
	return dataSource.getAllCelebrities();
}

public Celebrity getCelebrity() {
	return celebrity;
}

public void setCelebrity(Celebrity celebrity) {
	this.celebrity = celebrity;
}

@Inject
private ComponentResources resources;


@Inject
private BeanModelSource beanModelSource;

@SuppressWarnings({ "unchecked", "rawtypes" })
private BeanModel model;

@SuppressWarnings("unchecked")
public BeanModel getModel() {
	this.model = beanModelSource.createDisplayModel(Celebrity.class,resources.getMessages());
	this.model.get("firstName").sortable(false);
	return model;
}

public TableInformation getInformation(){
	return new TableInformation() {
	
		public String getTableSummary() {
			// TODO Auto-generated method stub
			return "A summary description of table data";
		}
		
		public String getTableCaption() {
			// TODO Auto-generated method stub
			return "The table title";
		}
		
		public String getTableCSS() {
			// TODO Auto-generated method stub
			return "k-data-table";
			}
	};
}

public JSONObject getOptions(){

	JSONObject json = new JSONObject("bJQueryUI", "true", "bStateSave", "true", "sDom", "TC<\"clear\">Rlfrtip");
	
	JSONObject dataTable = new JSONObject();
	dataTable.put("sSwfPath", as.getContextAsset("dataTables/TableTools/swf/copy_cvs_xls_pdf.swf", null).toClientURL());
	
	json.put("oTableTools", dataTable);
	
	//These parameters are not a DataTable. They are used to get more information about a row
	json.put("ajaxUrl", resources.createEventLink("extraInfo", null).toURI());
	json.put("openImg", as.getContextAsset("img/details_open.png", null).toClientURL());
	json.put("closeImg", as.getContextAsset("img/details_close.png", null).toClientURL());
	
	return json;
}

@Inject
private JavaScriptSupport js;

@Inject
private AssetSource as;

@AfterRender
public void addJsFile(){
	js.importJavaScriptLibrary(as.getContextAsset("dataTables/ColVis/media/js/ColVis.js", null));
	js.importJavaScriptLibrary(as.getContextAsset("dataTables/ColReorder/media/js/ColReorder.js", null));
	js.importJavaScriptLibrary(as.getContextAsset("dataTables/TableTools/js/ZeroClipboard.js", null));
	js.importJavaScriptLibrary(as.getContextAsset("dataTables/TableTools/js/TableTools.js", null));
	js.importJavaScriptLibrary(as.getContextAsset("js/demo.js", null));
}

@OnEvent(value="extrainfo")
public JSONObject sendResponse(@RequestParameter(value = "name") String name){
	return new JSONObject("name", name);
}

@Property
@Persist
private Date lastClick;

@Inject
private PortletRequestGlobals globals;


public void onActionFromDateRefresh()
{
    lastClick =new Date();
}

@BeginRender
public boolean checkCache(){
	    
		 lastClick =new Date();  
		 logger.debug("lastClick--------"+ lastClick.toString());
		 String ETag =  globals.getRenderRequest().getETag(); 
		 
		 //String ETag =  globals.getRenderResponse().getCacheControl().getETag(); 
		 if( ETag!=null && ETag.equals(lastClick.toString()))
		 {
			 logger.debug("get --------------ETag"+ ETag);
			 return false;
		 }
		 else
		 {
			 logger.debug("Set --------------ETag"+ lastClick.toString());
			 globals.getRenderResponse().getCacheControl().setUseCachedContent(true);
			 globals.getRenderResponse().getCacheControl().setExpirationTime(100);
			 globals.getRenderResponse().getCacheControl().setETag(lastClick.toString());
			 return true;
		 }
		 
}

}
