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
package com.dianaui.universal.core.client.ui;

import com.dianaui.universal.core.client.ui.constants.ButtonGroupSize;
import com.dianaui.universal.core.client.ui.constants.Styles;
import com.dianaui.universal.core.client.ui.constants.Toggle;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class ButtonGroupGwtTest extends TestCore {

    // TODO toggle attr helper
    String DATA_TOGGLE_BUTTONS = "data-toggle=\"" + Toggle.BUTTONS.getToggle() + "\"";

    static String getHtml(String content, String styles, String attributes) {
        return "<div class=\"" + Styles.BTN_GROUP + (styles != null ? " " + styles : "") + "\"" +
                (attributes != null ? " " + attributes : "") + ">" + (content != null ? content : "") + "</div>";
    }

    public void testDefaults() {
        ButtonGroup group = new ButtonGroup();
        hasStyle(Styles.BTN_GROUP, group);
        assertFalse(group.isOpen());
        assertFalse(group.isJustified());
        assertEquals(getHtml(null, null, null), group.getElement().toString());

        group.setJustified(true);
        assertEquals(getHtml(null, Styles.BTN_GROUP_JUSTIFIED, null), group.getElement().toString());

        group.setJustified(false);
        group.setToggle(Toggle.BUTTONS);
        assertEquals(getHtml(null, null, DATA_TOGGLE_BUTTONS), group.getElement().toString());

        group.setToggle(null);
        group.setSize(ButtonGroupSize.EXTRA_SMALL);
        assertEquals(getHtml(null, ButtonGroupSize.EXTRA_SMALL.getCssName(), null), group.getElement().toString());

        group.setSize(ButtonGroupSize.DEFAULT);
        group.setDropUp(true);
        assertEquals(getHtml(null, Styles.DROP_UP, null), group.getElement().toString());

        group.setDropUp(false);
        group.show();
        assertEquals(getHtml(null, Styles.OPEN, null), group.getElement().toString());
        assertTrue(group.isOpen());

        group.hide();
        assertEquals(getHtml(null, null, null), group.getElement().toString());
    }

    public void testClickOnCheckBox() {
        ButtonGroup group = new ButtonGroup();
        CheckBoxButton button1 = new CheckBoxButton("left");
        CheckBoxButton button2 = new CheckBoxButton("right");

        group.add(button1);
        group.add(button2);

        RootPanel.get().add(group);

        try {
            assertFalse(button1.getValue());
            assertFalse(button2.getValue());
            doesNotHaveStyle(Styles.ACTIVE, button1);
            doesNotHaveStyle(Styles.ACTIVE, button2);

            click(button2);

            assertFalse(button1.getValue());
            assertTrue(button2.getValue());
            doesNotHaveStyle(Styles.ACTIVE, button1);
            hasStyle(Styles.ACTIVE, button2);

            click(button1);
            click(button2);

            assertTrue(button1.getValue());
            assertFalse(button2.getValue());
            hasStyle(Styles.ACTIVE, button1);
            doesNotHaveStyle(Styles.ACTIVE, button2);
        } finally {
            group.removeFromParent();
        }
    }

    public void testClickOnRadio() {
        ButtonGroup group = new ButtonGroup();
        RadioButton button1 = new RadioButton("test");
        RadioButton button2 = new RadioButton("test");

        group.add(button1);
        group.add(button2);

        RootPanel.get().add(group);

        try {
            assertFalse(button1.getValue());
            assertFalse(button2.getValue());
            doesNotHaveStyle(Styles.ACTIVE, button1);
            doesNotHaveStyle(Styles.ACTIVE, button2);

            click(button2);

            assertFalse(button1.getValue());
            assertTrue(button2.getValue());
            doesNotHaveStyle(Styles.ACTIVE, button1);
            hasStyle(Styles.ACTIVE, button2);

            // emulate click event on radio button2
            button1.onBrowserEvent((Event) Document.get().createClickEvent(0, 0, 0, 0, 0, false, false, false, false));
            button2.setValue(false, false);

            assertTrue(button1.getValue());
            assertFalse(button2.getValue());
            hasStyle(Styles.ACTIVE, button1);
            doesNotHaveStyle(Styles.ACTIVE, button2);
        } finally {
            group.removeFromParent();
        }
    }

}
