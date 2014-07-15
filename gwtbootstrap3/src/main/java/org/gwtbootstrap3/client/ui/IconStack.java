package org.gwtbootstrap3.client.ui;

/*
 * #%L
 * GwtBootstrap3
 * %%
 * Copyright (C) 2013 GwtBootstrap3
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

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap3.client.ui.constants.Styles;

/**
 * @author Joshua Godi
 */
public class IconStack extends ComplexPanel implements HasResponsiveness {

    public IconStack() {
        setElement(Document.get().createSpanElement());
        getElement().addClassName(Styles.ICON_STACK);
    }

    /**
     * Adds an icon onto the icon stack
     *
     * @param fontAwesomeIcon Icon
     * @param base            Bottom icon or not
     */
    public void add(final FontAwesomeIcon fontAwesomeIcon, final boolean base) {
        fontAwesomeIcon.setStackBase(base);
        add(fontAwesomeIcon);
    }

    @Override
    public void add(final Widget child) {
        if (!(child instanceof FontAwesomeIcon)) {
            throw new IllegalArgumentException("An IconStack can only have children that are of type Icon.");
        }

        add(child, (Element) getElement());
    }

    @Override
    public void setVisibleOn(final String deviceSizeString) {
        StyleHelper.setVisibleOn(this, deviceSizeString);
    }

    @Override
    public void setHiddenOn(final String deviceSizeString) {
        StyleHelper.setHiddenOn(this, deviceSizeString);
    }
}
