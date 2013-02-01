package tapestry.liferay.portlets.components;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;

/**
 * Layout component for pages of application preferences pages
 */

public class LayoutForPreferences
{
	 @SuppressWarnings("unused")
	 @Inject @Symbol(value=SymbolConstants.APPLICATION_VERSION)
	 @Property
	 private String applicationVersion; 
}
