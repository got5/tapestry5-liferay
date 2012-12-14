//
// Copyright 2010 GOT5 (GO Tapestry 5)
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

package tapestry.liferay.portlets.pages;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;

import com.liferay.portal.kernel.servlet.PortletServlet;
import com.liferay.portal.util.PortalUtil;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.portlet.services.PortletRequestGlobals;
import org.got5.tapestry5.jquery.utils.JQueryTabData;


public class Liferay
{
	
	@Inject
	private PortletRequestGlobals globals;
	
	public String getHttpServletRequestFromRenderRequest()
    {
    	RenderRequest renderRequest = globals.getRenderRequest();
    	HttpServletRequest httpRequest = (HttpServletRequest)  renderRequest.getAttribute(PortletServlet.PORTLET_SERVLET_REQUEST);		
    	String myContainer1 =  httpRequest.getParameter("test");
    	String myContainer = PortalUtil.getOriginalServletRequest(httpRequest).getParameter("test");
    	return myContainer;
    	
    }
	
	
	@OnEvent(value = EventConstants.ACTIVATE)
	void onActivate() {
	                    
	       HttpServletRequest request = PortalUtil.getHttpServletRequest(globals.getRenderRequest());
	       String myContainer = PortalUtil.getOriginalServletRequest(request).getParameter("test");
	             
	}

	
}
