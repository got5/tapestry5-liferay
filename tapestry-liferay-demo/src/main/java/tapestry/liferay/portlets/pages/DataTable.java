package tapestry.liferay.portlets.pages;

import java.util.List;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.grid.GridDataSource;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.BeanModelSource;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

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
	
	@Property
	private Celebrity celebrity;
	
	private CelebritySource celebritySource;
	
	@Inject
	private ComponentResources resources;

	@Inject
	private BeanModelSource beanModelSource;
	
	@Property
	private Celebrity current;
	
	@SuppressWarnings("unchecked")
	private BeanModel model;
	
	@Inject
	private JavaScriptSupport js;
	
	@Inject
	private AssetSource as;
	
	public GridDataSource getCelebritySource() {
		if(celebritySource==null)
			celebritySource = new CelebritySource(dataSource);
		return celebritySource;
	}

	public List<Celebrity> getAllCelebrities() {
		System.out.println("Getting all celebrities...");
		return dataSource.getAllCelebrities();
	}

	@SetupRender
	void SetupRender(){
		/*if(model==null){
			this.model = beanModelSource.createDisplayModel(Celebrity.class,resources.getMessages());
			this.model.get("firstName").sortable(false);
		}*/
	
	}
	
	@SuppressWarnings("unchecked")
	public BeanModel getModel() {
		return model;
	}
	
	public JSONObject getOptions(){
		
		JSONObject json = new JSONObject("bJQueryUI", "true", "sDom", "TC<\"clear\">Rlfrtip");

		/*
		 * If you want the Export mechanism, please add these lines
		 * JSONObject dataTable = new JSONObject();
		 * dataTable.put("sSwfPath", 
		 *   as.getContextAsset("dataTables/TableTools/swf/copy_cvs_xls_pdf.swf", null).toClientURL());
		 * json.put("oTableTools", dataTable);
		 */
		
		return json;
	}
	
	@AfterRender
	public void addJsFile(){
		
		/*
		 * The dataTable js file is just here for the demo page. You do not 
		 * have to include it in your page. The DataTable will do it for you 
		 */
		js.importJavaScriptLibrary(
			as.getExpandedAsset("${assets.path}/components/datatables/jquery.dataTables.min.js"));
		
		
		js.importJavaScriptLibrary(
				as.getContextAsset("dataTables/ColVis/media/js/ColVis.js", null));
		js.importJavaScriptLibrary(
				as.getContextAsset("dataTables/ColReorder/media/js/ColReorder.js", null));
		
		/*
		 * If you want the Export mechanism, please add these two lines
		 * js.importJavaScriptLibrary(
			  as.getContextAsset("dataTables/TableTools/js/ZeroClipboard.js", null));
		   js.importJavaScriptLibrary(
		      as.getContextAsset("dataTables/TableTools/js/TableTools.js", null));	
		 */
		  
		
	}
	
	

}
