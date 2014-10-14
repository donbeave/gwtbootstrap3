/*
 * #%L
 * Diana UI Core
 * %%
 * Copyright (C) 2014 Diana UI
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.dianaui.universal.core.client.ui.base.helper;


import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;

/**
 * @author Sven Jacobs
 */
public final class SourceCodeHelper {

    private SourceCodeHelper() {
    }

    /**
     * If source code contains "\n" it will be replaced by a {@code <br>} element and "\s" will be replaced by a whitespace.
     *
     * @param code Unformatted source code
     * @return Formatted source code in HTML for use in {@link com.dianaui.universal.core.client.ui.Pre} or {@link com.dianaui.universal.core.client.ui.Code}
     */
    public static SafeHtml parseCode(final String code) {
        final SafeHtmlBuilder builder = new SafeHtmlBuilder();
        final String[] splitted = code.replaceAll("\\\\s", " ").split("\\\\n\\s?");

        for (final String s : splitted) {
            builder.append(SafeHtmlUtils.fromTrustedString(SafeHtmlUtils.htmlEscapeAllowEntities(s)));
            builder.appendHtmlConstant("<br>");
        }

        return builder.toSafeHtml();
    }

}
