package tapestry.liferay.portlets.services;

import java.util.List;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.internal.grid.CollectionGridDataSource;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.internal.util.CollectionFactory;
import org.apache.tapestry5.portlet.DeclaredResourceResponseSender;

import org.apache.tapestry5.services.ApplicationStateContribution;
import org.apache.tapestry5.services.ApplicationStateCreator;
import org.slf4j.Logger;

import tapestry.liferay.portlets.data.kawwa2.BasketDetails;
import tapestry.liferay.portlets.data.kawwa2.Product;

import tapestry.liferay.portlets.pages.jquery.BindExample;

public class AdminPortletsModule {

	public static void contributeApplicationDefaults(MappedConfiguration<String, String> configuration) {
		configuration.add(SymbolConstants.SUPPORTED_LOCALES, "en,fr,en_US");
		configuration.add(SymbolConstants.PRODUCTION_MODE, "false");
		configuration.add(SymbolConstants.APPLICATION_VERSION, "5.3.3-SNASPHOT");
		//configuration.add(JQuerySymbolConstants.JQUERY_UI_DEFAULT_THEME, "classpath:tapestry/liferay/portlets/empty.css");
	}
	
	public static void contributePortletResourceResponseIdentifier(
            Configuration<DeclaredResourceResponseSender> configuration)
    {

		//tapestry-jQuery Bind delagate event to the page 
		//so let allow all the event to be t
		configuration.add(new DeclaredResourceResponseSender("tapestry.liferay.portlets.pages.Droppable",false));
		DeclaredResourceResponseSender bindExample = new DeclaredResourceResponseSender(BindExample.class.getName());
			bindExample.addEvent("bindEvent");
		configuration.add(bindExample);

		

    }	
	
		
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
