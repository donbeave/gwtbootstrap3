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
package com.dianaui.universal.core.client.ui.base.mixin;

import com.dianaui.universal.core.client.ui.constants.Attributes;
import com.dianaui.universal.core.client.ui.constants.Toggle;
import com.google.gwt.user.client.ui.UIObject;

/**
 * @author Sven Jacobs
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class ToggleMixin {

    public static Toggle getToggle(final UIObject uiObject) {
        final String toggle = uiObject.getElement().getAttribute(Attributes.DATA_TOGGLE);
        return toggle != null && !toggle.equals("") ? Toggle.valueOf(toggle.toUpperCase()) : null;
    }

    public static void setToggle(final UIObject uiObject, final Toggle toggle) {
        if (toggle != null) {
            uiObject.getElement().setAttribute(Attributes.DATA_TOGGLE, toggle.getToggle());
        } else {
            uiObject.getElement().removeAttribute(Attributes.DATA_TOGGLE);
        }
    }

}
