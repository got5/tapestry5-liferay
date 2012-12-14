package tapestry.liferay.portlets.services;

import java.util.List;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.internal.grid.CollectionGridDataSource;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.ScopeConstants;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.internal.util.CollectionFactory;
import org.apache.tapestry5.portlet.DeclaredResourceResponseSender;
import org.apache.tapestry5.portlet.PortletPageResolver;
import org.apache.tapestry5.portlet.internal.services.PortletActionRenderResponseGeneratorImpl;
import org.apache.tapestry5.portlet.internal.services.PortletIdAllocatorFactoryImpl;
import org.apache.tapestry5.portlet.internal.services.PortletLinkSourceImpl;
import org.apache.tapestry5.portlet.internal.services.PortletPageResolverImpl;
import org.apache.tapestry5.portlet.internal.services.PortletRequestGlobalsImpl;
import org.apache.tapestry5.portlet.internal.services.PortletResourceResponseIdentifierImpl;
import org.apache.tapestry5.portlet.services.PortletActionRenderResponseGenerator;
import org.apache.tapestry5.portlet.services.PortletConfigProvider;
import org.apache.tapestry5.portlet.services.PortletConfigProviderImpl;
import org.apache.tapestry5.portlet.services.PortletIdAllocatorFactory;
import org.apache.tapestry5.portlet.services.PortletLinkSource;
import org.apache.tapestry5.portlet.services.PortletRequestGlobals;
import org.apache.tapestry5.portlet.services.PortletResourceResponseIdentifier;
import org.apache.tapestry5.services.ApplicationStateContribution;
import org.apache.tapestry5.services.ApplicationStateCreator;
import org.got5.tapestry5.jquery.JQuerySymbolConstants;
import org.slf4j.Logger;
import org.slf4j.Logger;

import tapestry.liferay.portlets.data.kawwa2.BasketDetails;
import tapestry.liferay.portlets.data.kawwa2.Product;

import tapestry.liferay.portlets.data.kawwa2.BasketDetails;
import tapestry.liferay.portlets.data.kawwa2.Product;

public class AdminPortletsModule {

	public static void contributeApplicationDefaults(MappedConfiguration<String, String> configuration) {
		configuration.add(SymbolConstants.SUPPORTED_LOCALES, "en,fr,en_US");
		configuration.add(SymbolConstants.PRODUCTION_MODE, "false");
		//configuration.add(JQuerySymbolConstants.JQUERY_UI_DEFAULT_THEME, "classpath:tapestry/liferay/portlets/empty.css");
	}
	
	public static void contributePortletResourceResponseIdentifier(
            Configuration<DeclaredResourceResponseSender> configuration)
    {

		//tapestry-jQuery Bind delagate event to the page 
		//so let allow all the event to be t
		configuration.add(new DeclaredResourceResponseSender("tapestry.liferay.portlets.pages.Droppable",false));

    }	
	
	 /*public static void bind(ServiceBinder binder)
	    {
	        binder.bind(ProductCatalog.class, ProductCatalogImpl.class);
	        
	    }*/
    public static ProductCatalog buildProductCatalog(Logger log)
    {

    	
        final List<Product> catalog = CollectionFactory.newList();

        catalog.add(new Product(71,"New IPad",900.0,"kawwa2/img/image_examples/new_ipad.jpg"));
        catalog.add(new Product(72,"IPad 3",700.0,"kawwa2/img/image_examples/ipad3-white.jpg"));
        catalog.add(new Product(73,"Galaxy Tab",259.0,"kawwa2/img/image_examples/product_1235_cat.gif"));
        
        final CollectionGridDataSource cgds = new CollectionGridDataSource(catalog);
        
        
        return new ProductCatalog()
        {
            public Product getById(long id)
            {
            	for(int i=0; i < cgds.getAvailableRows();i++ )
            	{
            		Product result = (Product)cgds.getRowValue(i);
            		if(result.getId()==id) return result;
            	}
            	return null;
             
            }

           			
			public List<Product> getProducts() {
				List<Product> result = CollectionFactory.newList();
				int nb = cgds.getAvailableRows();
                for (int i=0; i<nb;i++)
                {
                	result.add((Product)cgds.getRowValue(i));
                }

                return result;
			}

        };
    }
    
    
    public void contributeApplicationStateManager(MappedConfiguration<Class, ApplicationStateContribution> configuration)
    {
      ApplicationStateCreator<BasketDetails> creator = new ApplicationStateCreator<BasketDetails>()
      {
        public BasketDetails create()
        {
          return new BasketDetails();
        }
      };
    
      configuration.add(BasketDetails.class, new ApplicationStateContribution("session", creator));
    }
}
