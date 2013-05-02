package tapestry.liferay.portlets.pages.kawwa2;

import java.util.Set;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.AssetSource;


import tapestry.liferay.portlets.data.kawwa2.BasketDetails;
import tapestry.liferay.portlets.data.kawwa2.Product;
import tapestry.liferay.portlets.services.ProductCatalog;

public class Basket
{
    @Inject
	private AssetSource as;
    
    @SessionState
    private BasketDetails shoppingBasket;

    /**
     * Handler for portletEvent addTobasket.
     */
    public Object onAddToBasket(Long productId)
    {
       shoppingBasket.addToBasket(productId);
       return this;
    }

     @Component
	 private Zone basketZone; 
     //handler for mixin zoneDroppable	
	 public Object onDrop(Long productId)
	 {
		 shoppingBasket.addToBasket(productId);
		 return basketZone.getBody();		 
	 }
     
    @Property
    private Long CurrentKey;
    
    public Long getNbItems(){
    	return shoppingBasket.getNbItems();
    }
    
    
    public Set<Long> getKeys(){
		return shoppingBasket.getProductIDs();
	}
    
    @Inject
	private ProductCatalog catalog;
    
    public Product getProduct(){
    	return catalog.getById(CurrentKey);    
    }   
    
    public String getLogoUrl(){
    	return as.getContextAsset(catalog.getById(CurrentKey).getAssetLocation(), null).toClientURL();
    }
    
    public Long getNb(){
    	return shoppingBasket.getNb(CurrentKey);
    }
    
    
}
