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


import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.InjectService;
import org.apache.tapestry5.ioc.annotations.Local;
import org.apache.tapestry5.ioc.annotations.SubModule;
import org.apache.tapestry5.ioc.services.ServiceOverride;
import org.apache.tapestry5.portlet.internal.services.PortalUtilities;
import org.apache.tapestry5.portlet.services.PortletModule;


public final class LiferayModule
{

    public static void bind(ServiceBinder binder)
    {
    	 binder.bind(PortalUtilities.class, LiferayPortalUtilImpl.class).withId("LiferayPortalUtilitiesOverride");
    }

      
    @Contribute(ServiceOverride.class)
    public void contributeServiceOverride(
			MappedConfiguration<Class, Object> configuration,
			@InjectService(value = "LiferayPortalUtilitiesOverride")
			PortalUtilities portalUtilitiesOverride)

	{
		configuration.add(PortalUtilities.class, portalUtilitiesOverride);
	}
  

}
