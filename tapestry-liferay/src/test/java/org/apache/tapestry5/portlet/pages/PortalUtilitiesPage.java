//
// Copyright 2012 GOT5 (GO Tapestry 5)
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//

package org.apache.tapestry5.portlet.pages;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.grid.GridDataSource;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.portlet.internal.services.PortalUtilities;



public class PortalUtilitiesPage
{
	
	@Inject
	private PortalUtilities portalUtil;
	
	public String getHttpRequestFromOriginalServletRequest()
    {
		HttpServletRequest httpRequest = portalUtil.getOriginalServletRequest();
    	if (httpRequest!=null)
    		return httpRequest.getRequestURL().toString();
    	else
    		return "undefined";
    	
    }
	
	
	@OnEvent(value = EventConstants.ACTIVATE)
	void onActivate() {
	                    
	       //HttpServletRequest request = PortalUtil.getHttpServletRequest(globals.getRenderRequest());
	       //String myContainer = PortalUtil.getOriginalServletRequest(request).getParameter("test");
	             
	}

	
}
