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

import com.dianaui.universal.core.client.ui.CheckableInputButton;
import com.dianaui.universal.core.client.ui.base.HasActive;
import com.dianaui.universal.core.client.ui.base.HasFormValue;
import com.dianaui.universal.core.client.ui.base.mixin.ActiveMixin;
import com.dianaui.universal.core.client.ui.constants.ButtonType;
import com.dianaui.universal.core.client.ui.constants.Styles;
import com.dianaui.universal.core.client.ui.constants.TypeAttrType;
import com.google.gwt.dom.client.Element;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.editor.client.adapters.TakesValueEditor;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasValue;

/**
 * Base class for {@link com.dianaui.universal.core.client.ui.CheckBoxButton}
 * and {@link com.dianaui.universal.core.client.ui.RadioButton} which are
 * encapsulated {@code <input>} elements within {@code <label>}.
 *
 * @author Sven Jacobs
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public abstract class AbstractLabelButton extends AbstractIconButton implements HasActive, HasName, HasFormValue,
        HasValue<Boolean>, IsEditor<LeafValueEditor<Boolean>> {

    protected final CheckableInputButton input;
    private LeafValueEditor<Boolean> editor;

    protected AbstractLabelButton(final TypeAttrType typeAttr) {
        super(ButtonType.DEFAULT);

        input = new CheckableInputButton(typeAttr);
        input.setStyleName("");

        add(input, (Element) getElement());
    }

    protected AbstractLabelButton(final TypeAttrType typeAttr, final String label) {
        this(typeAttr);
        setText(label);
    }

    @Override
    public boolean isEnabled() {
        return input.isEnabled();
    }

    @Override
    public void setEnabled(final boolean enabled) {
        if (enabled) {
            removeStyleName(Styles.DISABLED);
        } else {
            addStyleName(Styles.DISABLED);
        }

        input.setEnabled(enabled);
    }

    @Override
    public boolean isActive() {
        return ActiveMixin.isActive(this);
    }

    @Override
    public void setActive(final boolean active) {
        setValue(active);
    }

    @Override
    public String getName() {
        return input.getName();
    }

    @Override
    public void setName(final String name) {
        input.setName(name);
    }

    @Override
    public String getFormValue() {
        return input.getFormValue();
    }

    @Override
    public void setFormValue(final String value) {
        input.setFormValue(value);
    }

    @Override
    public void setValue(final Boolean value, final boolean fireEvents) {
        changeValue(value, fireEvents);
    }

    @Override
    public Boolean getValue() {
        return input.getValue();
    }

    @Override
    public void setValue(final Boolean value) {
        setValue(value, false);
    }

    @Override
    public HandlerRegistration addValueChangeHandler(final ValueChangeHandler<Boolean> handler) {
        return input.addValueChangeHandler(handler);
    }

    protected void changeValue(final Boolean value, final boolean fireEvents) {
        ActiveMixin.setActive(this, value);
        input.setValue(value, fireEvents);
    }

    @Override
    protected Element createElement() {
        return DOM.createLabel();
    }

    @Override
    public LeafValueEditor<Boolean> asEditor() {
        if (editor == null) {
            editor = TakesValueEditor.of(this);
        }
        return editor;
    }

}
