package tapestry.liferay.portlets.pages.kawwa2;

import java.util.List;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.AssetSource;

import javax.portlet.Event;
import com.liferay.portlet.EventImpl;

import tapestry.liferay.portlets.data.kawwa2.BasketDetails;
import tapestry.liferay.portlets.data.kawwa2.Product;
import tapestry.liferay.portlets.services.ProductCatalog;

public class Catalog {

	@Inject
	private ProductCatalog catalog;

	@Property
	private Product current;

	@Inject
	private AssetSource as;

	/**
	 * The persistence strategy of this object is defined in the module
	 * AdminPortletsModule, in the function contributeApplicationStateManager.
	 */
	@SessionState
	private BasketDetails shoppingBasket;

	public List<Product> getList() {
		return catalog.getProducts();
	}

	public String getLogoUrl() {
		return as.getContextAsset(current.getAssetLocation(), null)
				.toClientURL();
	}

	/**
	 * An event is dispatched by the Catalog portlet, and is listened by the
	 * Basket Portlet. The Basket onAddToBasket function will be called and will
	 * update the user basket with the added product.
	 */
	@OnEvent(value = "publishAddToBasket")
	public Event onPublishAddToBasket(Long productId) {
		return (Event) new EventImpl("addToBasket", null, productId);
	}

	/**
	 * The Session State object shoppingBasket is updated with the new product.
	 * Because of its persistence strategy (APPLICATION_SCOPE, defined in
	 * AdminPortletModule), it will be shared among all the other portlets of
	 * your application, so the portlet Basket will be aware of the
	 * shoppingBasket modifications.
	 */
	@OnEvent(value = EventConstants.ACTION, component = "linkAddBasket")
	public void onActionFromLinkAddBasket(Long productId) {
		shoppingBasket.addToBasket(productId);
	}
	
	public Long getNbProductsInBasket() {
		Long nbProducts = shoppingBasket.getNb(current.getId());
		return nbProducts != null ? nbProducts : 0;
	}

}
