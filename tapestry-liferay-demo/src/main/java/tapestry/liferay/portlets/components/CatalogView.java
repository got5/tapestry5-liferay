package tapestry.liferay.portlets.components;

import java.util.List;

import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.AssetSource;

import tapestry.liferay.portlets.data.kawwa2.Product;



public class CatalogView {
	
	
	@Property
	private Product current;
	
	@Inject
	private AssetSource as;
	
	@Parameter
	@Property
	private List<Product> Products;
	
		
	public String getLogoUrl(){
    	return as.getContextAsset("kawwa2/img/image_examples/product_1235_cat.gif", null).toClientURL();
    }

}
