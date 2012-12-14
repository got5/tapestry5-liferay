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

package org.apache.tapestry5.liferay.annotations;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.apache.tapestry5.ioc.annotations.AnnotationUseContext.COMPONENT;
import static org.apache.tapestry5.ioc.annotations.AnnotationUseContext.MIXIN;
import static org.apache.tapestry5.ioc.annotations.AnnotationUseContext.PAGE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.apache.tapestry5.internal.transform.OnEventWorker;
import org.apache.tapestry5.ioc.annotations.UseWith;
import org.apache.tapestry5.ioc.services.TypeCoercer;
import org.apache.tapestry5.services.Request;

/**
 * Identifies a field as  that may be placed on parameters of event handler methods.
 * Annotated field will be {@linkplain Original Portal HttpRequest#getParameter(String) extracted from the request} if defined,
 * then {@linkplain TypeCoercer coerced} to the type of the parameter. Such parameters are separate
 * from ordinary context parameters (extracted from the Request path). 
 * 
 * @since 5.2.2
 * @see PortalParameter
 */
@Target(
{ PARAMETER })
@Retention(RUNTIME)
@Documented
@UseWith(
{ COMPONENT, MIXIN, PAGE })
public @interface PortalParameter
{
    /** The name of the query parameter to extract from the request. */
    String value();

    /**
     * If false (the default), then an exception is thrown when the query parameter is read, if it is blank (null or an
     * empty string). If true, then blank values are allowed.
     */
    boolean allowBlank() default false;
}
