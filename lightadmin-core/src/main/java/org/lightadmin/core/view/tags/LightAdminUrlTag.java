/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.lightadmin.core.view.tags;

import org.lightadmin.core.config.LightAdminConfiguration;

import javax.servlet.jsp.JspException;

import static org.springframework.beans.PropertyAccessorFactory.forDirectFieldAccess;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentContextPath;

/**
 * Overides http into https
 * @Author Yoann
 */
/*
 * Added static excludes
 *
 * @Author Anton
 */
public class LightAdminUrlTag extends org.springframework.web.servlet.tags.UrlTag {

    //these URLs will be excluded from https and will use http
    private static final String EXCLUDE_1         = "localhost";
    private static final String EXCLUDE_2         = "run-angel.net";

    private static final String URL_TYPE_ABSOLUTE = "://";

    @Override
    public int doEndTag() throws JspException {
        if (isRelative(getValue())) {
            String absUrl = absoluteUrlOf(applicationUrl(getValue()));
            if (!absUrl.contains(EXCLUDE_1) && !absUrl.contains(EXCLUDE_2) && !absUrl.startsWith("https")) {
                absUrl = absUrl.replace("http", "https");
            }
            setValue(absUrl);
        }
        return super.doEndTag();
    }

    private String absoluteUrlOf(String applicationBaseUrl) {
        return fromCurrentContextPath().path(applicationBaseUrl).build().toUriString();
    }

    private boolean isRelative(String value) {
        return !value.contains(URL_TYPE_ABSOLUTE);
    }

    private String getValue() {
        return (String) forDirectFieldAccess(this).getPropertyValue("value");
    }

    private String applicationUrl(String value) {
        return lightAdminConfiguration().getApplicationUrl(value);
    }

    private LightAdminConfiguration lightAdminConfiguration() {
        return getRequestContext().getWebApplicationContext().getBean(LightAdminConfiguration.class);
    }
}