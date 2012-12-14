/*
 * Apache License
 * Version 2.0, January 2004
 * http://www.apache.org/licenses/
 *
 * Copyright 2008 by chenillekit.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package tapestry.liferay.portlets.services;

import java.util.List;

import javax.portlet.PortletRequest;

import org.apache.tapestry5.internal.grid.CollectionGridDataSource;
import org.apache.tapestry5.ioc.internal.util.CollectionFactory;
import org.apache.tapestry5.services.SessionPersistedObjectAnalyzer;

import com.liferay.portal.kernel.portlet.LiferayPortletRequest;

import tapestry.liferay.portlets.data.kawwa2.Product;



public class ProductCatalogImpl implements ProductCatalog
{
	private List<Product> catalog;
	private CollectionGridDataSource cgds;
	
	public ProductCatalogImpl() {
		 catalog = CollectionFactory.newList();

        catalog.add(new Product(71,"IPad",300.0,"kawwa2/img/image_examples/product_1235_cat.gif"));
        catalog.add(new Product(72,"IPad 2",400.99,"kawwa2/img/image_examples/product_1235_cat.gif"));
        catalog.add(new Product(73,"IPad 3",500.99,"kawwa2/img/image_examples/product_1235_cat.gif"));
        
        CollectionGridDataSource cgds = new CollectionGridDataSource(catalog);
     
	}
	
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


}
