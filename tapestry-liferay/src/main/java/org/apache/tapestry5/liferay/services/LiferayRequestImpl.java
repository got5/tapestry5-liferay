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

import java.util.Enumeration;
import java.util.List;

import javax.portlet.PortletRequest;

import org.apache.tapestry5.portlet.internal.services.PortletRequestImpl;
import org.apache.tapestry5.services.SessionPersistedObjectAnalyzer;

import com.liferay.portal.kernel.portlet.LiferayPortletRequest;


public class LiferayRequestImpl
		extends PortletRequestImpl {
	private LiferayPortletRequest portletRequest;

	public LiferayRequestImpl(PortletRequest request, String pageName, SessionPersistedObjectAnalyzer analyzer) {
		super(request, pageName, analyzer);
		portletRequest = (LiferayPortletRequest) request;
	}

	@Override
	public boolean isXHR() {
		return XML_HTTP_REQUEST.equals(getHeader(REQUESTED_WITH_HEADER));
	}

	@Override
	public String getHeader(String name) {
		String headerValue = super.getHeader(name);
		return headerValue == null ? portletRequest.getHttpServletRequest().getHeader(name) : headerValue;
	}

	@Override
	public List<String> getHeaderNames() {
		List<String> result = super.getHeaderNames();
		for (Enumeration<String> e = portletRequest.getHttpServletRequest().getHeaderNames(); e.hasMoreElements();) {
			result.add(e.nextElement());
		}
		return result;
	}

}
