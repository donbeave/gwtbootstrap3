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
package com.dianaui.universal.core.client.ui.base.button;

import com.dianaui.universal.core.client.ui.ButtonGroup;
import com.dianaui.universal.core.client.ui.DropDownButton;
import com.dianaui.universal.core.client.ui.ListDropDown;
import com.dianaui.universal.core.client.ui.base.HasToggle;
import com.dianaui.universal.core.client.ui.base.mixin.ToggleMixin;
import com.dianaui.universal.core.client.ui.constants.ButtonType;
import com.dianaui.universal.core.client.ui.constants.Styles;
import com.dianaui.universal.core.client.ui.constants.Toggle;
import com.dianaui.universal.core.client.ui.html.Text;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;

/**
 * Base class for buttons that can be toggle buttons
 *
 * @author Sven Jacobs
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 * @see AbstractButton
 * @see Toggle
 */
public abstract class AbstractToggleButton extends AbstractIconButton implements HasToggle {

    private Text separator;
    private Caret caret;

    protected AbstractToggleButton() {
        this(ButtonType.DEFAULT);
    }

    protected AbstractToggleButton(final ButtonType type) {
        setType(type);
    }

    /**
     * Toggles the display of the caret for the button
     *
     * @param toggleCaret show/hide the caret for the button
     */
    public void setToggleCaret(final boolean toggleCaret) {
        caret.setVisible(toggleCaret);
    }

    @Override
    public Toggle getToggle() {
        return ToggleMixin.getToggle(this);
    }

    /**
     * Specifies that this button acts as a toggle, for instance for a parent {@link com.dianaui.universal.core.client.ui.DropDown}
     * or {@link com.dianaui.universal.core.client.ui.ButtonGroup}
     * Adds a {@link Caret} as a child widget.
     *
     * @param toggle Kind of toggle
     */
    @Override
    public void setToggle(final Toggle toggle) {
        ToggleMixin.setToggle(this, toggle);

        if (separator != null)
            separator.removeFromParent();

        if (caret != null)
            caret.removeFromParent();

        separator = new Text(" ");
        caret = new Caret();

        if (toggle == Toggle.DROPDOWN) {
            addStyleName(Styles.DROPDOWN_TOGGLE);

            add(separator, (Element) getElement());
            add(caret, (Element) getElement());
        }
    }

    public void onBrowserEvent(Event event) {
        super.onBrowserEvent(event);

        switch (DOM.eventGetType(event)) {
            case Event.ONCLICK:
                if (getToggle() == Toggle.BUTTON) {
                    toggle();
                } else if (getToggle() == Toggle.DROPDOWN && getParent() instanceof ListDropDown) {
                    ((ListDropDown) getParent()).toggle();
                } else if (getToggle() == Toggle.DROPDOWN && getParent() instanceof DropDownButton) {
                    ((DropDownButton) getParent()).toggle();
                } else if (getToggle() == Toggle.DROPDOWN && getParent() instanceof ButtonGroup) {
                    ((ButtonGroup) getParent()).toggle();
                }
                break;
        }
    }

    @Override
    protected void onChanged() {
        // fix caret position
        setToggle(getToggle());
    }

}
