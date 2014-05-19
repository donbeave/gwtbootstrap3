package org.gwtbootstrap3.client.ui.base.button;

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

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.ui.RadioButton;
import org.gwtbootstrap3.client.ui.base.AbstractButtonGroup;
import org.gwtbootstrap3.client.ui.base.ComplexWidget;
import org.gwtbootstrap3.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap3.client.ui.base.mixin.ActiveMixin;
import org.gwtbootstrap3.client.ui.base.mixin.FocusableMixin;
import org.gwtbootstrap3.client.ui.constants.*;

/**
 * Abstract base class for different kinds of buttons.
 *
 * @author Sven Jacobs
 * @author Joshua Godi
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 * @see org.gwtbootstrap3.client.ui.constants.HasEnabled
 */
public abstract class AbstractButton extends ComplexWidget implements HasEnabled, HasActive, HasType<ButtonType>,
        HasSize<ButtonSize>, HasClickHandlers, HasTargetHistoryToken, HasHref, Focusable, HasAllMouseHandlers {

    private final ActiveMixin<AbstractButton> activeMixin = new ActiveMixin<AbstractButton>(this);
    private FocusableMixin<AbstractButton> focusableMixin;
    private String targetHistoryToken;

    /**
     * Creates button with DEFAULT type.
     */
    protected AbstractButton() {
        this(ButtonType.DEFAULT);
    }

    protected AbstractButton(final ButtonType type) {
        setElement(createElement());
        setStyleName(Styles.BTN);
        setType(type);
    }

    /**
     * Makes button a block level element that spawns full width of parent.
     *
     * @param block True for block level element
     */
    public void setBlock(final boolean block) {
        if (block) {
            addStyleName(Styles.BTN_BLOCK);
        } else {
            removeStyleName(Styles.BTN_BLOCK);
        }
    }

    public void toggle() {
        if (isActive()) {
            setActive(false);
        } else {
            setActive(true);
        }
    }

    @Override
    public boolean isActive() {
        return activeMixin.isActive();
    }

    @Override
    public void setActive(final boolean active) {
        activeMixin.setActive(active);
    }

    @Override
    public boolean isEnabled() {
        return !getElement().getPropertyBoolean(DISABLED_PROPERTY);
    }

    @Override
    public void setEnabled(final boolean enabled) {
        getElement().setPropertyBoolean(DISABLED_PROPERTY, !enabled);
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

    @Override
    public ButtonType getType() {
        return ButtonType.fromStyleName(getStyleName());
    }

    /**
     * Sets type of button.
     *
     * @param type Type of button
     */
    @Override
    public void setType(final ButtonType type) {
        StyleHelper.addUniqueEnumStyleName(this, ButtonType.class, type);
    }

    @Override
    public ButtonSize getSize() {
        return ButtonSize.fromStyleName(getStyleName());
    }

    /**
     * Sets size of button.
     *
     * @param size Size of button
     */
    @Override
    public void setSize(final ButtonSize size) {
        StyleHelper.addUniqueEnumStyleName(this, ButtonSize.class, size);
    }

    @Override
    public String getTargetHistoryToken() {
        return targetHistoryToken;
    }

    @Override
    public void setTargetHistoryToken(final String targetHistoryToken) {
        this.targetHistoryToken = targetHistoryToken;
        final String hash = History.encodeHistoryToken(targetHistoryToken);
        setHref("#" + hash);
    }

    @Override
    public String getHref() {
        return getElement().getAttribute(HREF);
    }

    @Override
    public void setHref(final String href) {
        getElement().setAttribute(HREF, href);
    }


    @Override
    public int getTabIndex() {
        return getFocusableMixin().getTabIndex();
    }

    @Override
    public void setTabIndex(final int index) {
        getFocusableMixin().setTabIndex(index);
    }

    @Override
    public void setAccessKey(final char key) {
        getFocusableMixin().setAccessKey(key);
    }

    @Override
    public void setFocus(final boolean focused) {
        getFocusableMixin().setFocus(focused);
    }

    @Override
    public void onBrowserEvent(Event event) {
        switch (DOM.eventGetType(event)) {
            case Event.ONCLICK:
                if (getParent() instanceof AbstractButtonGroup) {
                    Toggle toggle = ((AbstractButtonGroup) getParent()).getToggle();

                    if (toggle != null && toggle == Toggle.BUTTONS) {
                        for (int i = 0; i < ((AbstractButtonGroup) getParent()).getWidgetCount(); i++) {
                            Widget widget = ((AbstractButtonGroup) getParent()).getWidget(i);

                            if (widget instanceof RadioButton) {
                                ((RadioButton) widget).setValue(false);
                            }
                        }

                        if (this instanceof RadioButton) {
                            ((RadioButton) this).setValue(true);
                        }
                    }
                }
            default:
                super.onBrowserEvent(event);
                break;
        }
    }

    protected abstract Element createElement();

    @Override
    protected void onAttach() {
        super.onAttach();

        sinkEvents(Event.ONCLICK);
    }

    private FocusableMixin getFocusableMixin() {
        if (focusableMixin == null) {
            focusableMixin = new FocusableMixin<AbstractButton>(this);
        }
        return focusableMixin;
    }

}
