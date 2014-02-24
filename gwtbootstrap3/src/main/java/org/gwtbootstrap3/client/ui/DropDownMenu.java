package org.gwtbootstrap3.client.ui;

/*
 * #%L
 * GWT Widgets
 * %%
 * Copyright (C) 2014 GWT Widgets
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

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import org.gwtbootstrap3.client.ui.base.mixin.PullMixin;
import org.gwtbootstrap3.client.ui.constants.Attributes;
import org.gwtbootstrap3.client.ui.constants.Pull;
import org.gwtbootstrap3.client.ui.constants.Styles;

/**
 * Container for drop down menu items.
 * <p/>
 * <strong>Must</strong> be encapsulated in a {@link ButtonGroup} to build button dropdowns.
 *
 * @author Sven Jacobs
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 * @see ButtonGroup
 */
public class DropDownMenu extends UnorderedList implements HasPull {
    private static final String MENU = "menu";

    private final PullMixin<DropDownMenu> pullMixin = new PullMixin<DropDownMenu>(this);

    public DropDownMenu() {
        setStyleName(Styles.DROPDOWN_MENU);
        getElement().setAttribute(Attributes.ROLE, MENU);
    }

    @Override
    public void setPull(final Pull pull) {
        pullMixin.setPull(pull);
    }

    @Override
    public Pull getPull() {
        return pullMixin.getPull();
    }

    public void onBrowserEvent(Event event) {
        super.onBrowserEvent(event);

        switch (DOM.eventGetType(event)) {
            case Event.ONCLICK:
                if (getParent() instanceof ListDropDown) {
                    ((ListDropDown) getParent()).hide();
                }
                break;
        }
    }

    protected void onAttach() {
        super.onAttach();

        sinkEvents(Event.ONCLICK);
    }

}
