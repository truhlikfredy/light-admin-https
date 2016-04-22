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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;

import static org.springframework.beans.PropertyAccessorFactory.forDirectFieldAccess;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentContextPath;

/**
 * Overides http into https
 * @Author Yoann
 */
/*
 * Modified https handling
 *
 * @Author Anton
 */
public class LightAdminUrlTag extends org.springframework.web.servlet.tags.UrlTag {

    //these URLs will be excluded from https and will use http
    public  static        String Exclude1          = "localhost";
    public  static        String Exclude2          = "example.com";
    private static final  String URL_TYPE_ABSOLUTE = "://";
    private static final  Logger log               = LoggerFactory.getLogger(LightAdminUrlTag.class);


    private String replaceHttp(String url) {
        if ( !url.contains(Exclude1) && !url.contains(Exclude2) && !url.contains("https")) {
            return url.replace("http", "https");
        }
        return url;
    }


    @Override
    public int doEndTag() throws JspException {
        if (true) {
            if (isRelative(getValue())) {
                setValue(absoluteUrlOf(applicationUrl(getValue())));
            }
            return super.doEndTag();
        }

        log.debug(getValue() + " --- " +absoluteUrlOf(applicationUrl(getValue())));

        if (isRelative(getValue())) {
            //relative paths convert into absolute first and then convert http into https
            setValue(replaceHttp(absoluteUrlOf(applicationUrl(getValue()))));
        } else {
            //absolute paths just replace http with https
            setValue(replaceHttp(getValue()));
        }

        log.debug("Converted: "+getValue());
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