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

import tapestry.liferay.portlets.data.kawwa2.Product;



public interface ProductCatalog
{
    /**
     * Gets a product by its unique id.
     *
     * @param id of produtc to retrieve
     *
     * @return the Product
     *
     * @throws IllegalArgumentException if no such track exists
     */
    Product getById(long id);

    /**
     * Provides a list of all produtc in an indeterminate order.
     */
    List<Product> getProducts();

}
