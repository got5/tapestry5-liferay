// Copyright 2012 The Apache Software Foundation
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

package org.apache.tapestry5.liferay.services;


import javax.portlet.PortletRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.tapestry5.portlet.PortletRenderable;
import org.apache.tapestry5.portlet.internal.services.PortalUtilities;
import org.apache.tapestry5.portlet.internal.services.PortletResourceResponseImpl;
import org.apache.tapestry5.portlet.services.PortletRequestGlobals;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.Response;
import org.apache.tapestry5.services.SessionPersistedObjectAnalyzer;

import com.liferay.portal.kernel.servlet.PortletServlet;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.ResourceResponseImpl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LiferayPortalUtilImpl implements PortalUtilities
{

    private final Logger logger = LoggerFactory.getLogger(LiferayPortalUtilImpl.class);
    private PortletRequestGlobals globals;

    public LiferayPortalUtilImpl(PortletRequestGlobals globals)
    {
    	this.globals = globals;
    }

    @Override
	public Request buildPortletRequest(PortletRequest request, String pageName, SessionPersistedObjectAnalyzer analyzer)
	{
		return  new LiferayRequestImpl(request,pageName, analyzer);
	}

    @Override
    public Response buildResourceReponse(ResourceResponse response) {
    	return new LiferayResourceResponseImpl(response);
	}
    
	public HttpServletRequest getOriginalServletRequest()
	{
		//depend on com.liferay.portal.util.PortalUtil
		PortletRequest portletRequest = globals.getPortletRequest();
    	HttpServletRequest httpRequest = (HttpServletRequest)  portletRequest.getAttribute(PortletServlet.PORTLET_SERVLET_REQUEST);		
     	return PortalUtil.getOriginalServletRequest(httpRequest);
	}

	public Object onActionFromRefreshAction()
	{
		
		PortletRenderable renderable = new PortletRenderable("Index");
	    renderable.addRenderParameter("rp1", "Render Parameter 1");
	    renderable.addRenderParameter("rp2", "Render Parameter 2");
	    return renderable;
	   
	}
	
	

}
