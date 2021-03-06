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

import com.dianaui.universal.core.client.ui.base.AbstractTextWidget;
import com.dianaui.universal.core.client.ui.base.HasType;
import com.dianaui.universal.core.client.ui.base.helper.StyleHelper;
import com.dianaui.universal.core.client.ui.constants.LabelType;
import com.dianaui.universal.core.client.ui.constants.Styles;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Event;

/**
 * Bootstrap's label, see <a href="http://getbootstrap.com/components/#labels">documentation</a>.
 * Not to be confused with {@code <label>} (see {@link FormLabel}) or GWT's {@link com.google.gwt.user.client.ui.Label}
 *
 * @author Sven Jacobs
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 * @see FormLabel
 */
public class Label extends AbstractTextWidget implements HasType<LabelType>, HasClickHandlers, HasAllMouseHandlers {

    public Label() {
        super(Document.get().createSpanElement());
        setStyleName(Styles.LABEL);
        setType(LabelType.DEFAULT);

        sinkEvents(Event.ONCLICK);
        sinkEvents(Event.MOUSEEVENTS);
    }

    public Label(final LabelType type) {
        this();
        setType(type);
    }

    public Label(final String text) {
        this(LabelType.DEFAULT, text);
    }

    public Label(final LabelType type, final String text) {
        this(type);
        setText(text);
    }

    @Override
    public LabelType getType() {
        return LabelType.fromStyleName(getStyleName());
    }

    /**
     * Sets type of label.
     *
     * @param type Type of label
     */
    @Override
    public void setType(final LabelType type) {
        StyleHelper.addUniqueEnumStyleName(this, LabelType.class, type);
    }

    @Override
    public HandlerRegistration addClickHandler(final ClickHandler handler) {
        return addDomHandler(handler, ClickEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseDownHandler(final MouseDownHandler handler) {
        return addDomHandler(handler, MouseDownEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseMoveHandler(final MouseMoveHandler handler) {
        return addDomHandler(handler, MouseMoveEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseOutHandler(final MouseOutHandler handler) {
        return addDomHandler(handler, MouseOutEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseOverHandler(final MouseOverHandler handler) {
        return addDomHandler(handler, MouseOverEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseUpHandler(final MouseUpHandler handler) {
        return addDomHandler(handler, MouseUpEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseWheelHandler(final MouseWheelHandler handler) {
        return addDomHandler(handler, MouseWheelEvent.getType());
    }

}
