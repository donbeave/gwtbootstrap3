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
package com.dianaui.universal.core.client.ui.base;

import com.dianaui.universal.core.client.ui.base.helper.StyleHelper;
import com.dianaui.universal.core.client.ui.base.mixin.IdMixin;
import com.dianaui.universal.core.client.ui.constants.DeviceSize;
import com.dianaui.universal.core.client.ui.constants.InputSize;
import com.google.gwt.dom.client.Element;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.editor.client.HasEditorErrors;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.text.shared.Parser;
import com.google.gwt.text.shared.Renderer;

import java.util.List;

public class ValueBoxBase<T> extends com.google.gwt.user.client.ui.ValueBoxBase<T> implements HasId, HasResponsiveness,
        HasPlaceholder, HasAutoComplete, HasSize<InputSize>, HasEditorErrors<T> {

    /**
     * Add support for HasEditorErrors implementation.
     */
    public interface EditorErrorSupport extends AttachEvent.Handler {

        void showErrors(List<EditorError> errors);

    }

    private static final String MAX_LENGTH = "maxlength";

    private EditorErrorSupport errorSupport = new ValueBoxErrorSupport(this);

    /**
     * Creates a value box that wraps the given browser element handle. This is only used by subclasses.
     *
     * @param elem the browser element to wrap
     */
    protected ValueBoxBase(final Element elem, final Renderer<T> renderer, final Parser<T> parser) {
        super(elem, renderer, parser);
    }

    public void setMaxLength(final int maxLength) {
        getElement().setAttribute(MAX_LENGTH, Integer.toString(maxLength));
    }

    @Override
    public void setPlaceholder(final String placeHolder) {
        getElement().setAttribute(PLACEHOLDER, placeHolder != null ? placeHolder : "");
    }

    @Override
    public String getPlaceholder() {
        return getElement().getAttribute(PLACEHOLDER);
    }

    @Override
    public void setAutoComplete(final boolean autoComplete) {
        getElement().setAttribute(AUTO_COMPLETE, autoComplete ? ON : OFF);
    }

    @Override
    public String getAutoComplete() {
        return getElement().getAttribute(AUTO_COMPLETE);
    }

    @Override
    public void setId(final String id) {
        IdMixin.setId(this, id);
    }

    @Override
    public String getId() {
        return IdMixin.getId(this);
    }

    @Override
    public void setVisibleOn(final DeviceSize deviceSize) {
        StyleHelper.setVisibleOn(this, deviceSize);
    }

    @Override
    public void setHiddenOn(final DeviceSize deviceSize) {
        StyleHelper.setHiddenOn(this, deviceSize);
    }

    @Override
    public void setSize(InputSize size) {
        StyleHelper.addUniqueEnumStyleName(this, InputSize.class, size);
    }

    @Override
    public InputSize getSize() {
        return InputSize.fromStyleName(getStyleName());
    }

    public void setAddErrorSupport(boolean addErrorSupport) {
        if (!addErrorSupport) {
            errorSupport = null;
        }
    }

    public boolean getAddErrorSupport() {
        return errorSupport != null;
    }

    public void setErrorSupport(EditorErrorSupport errorSupport) {
        this.errorSupport = errorSupport;
        addAttachHandler(errorSupport);
    }

    @Override
    public void showErrors(List<EditorError> errors) {
        if (errorSupport != null) {
            errorSupport.showErrors(errors);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValue(T value, boolean fireEvents) {
        showErrors(null);
        super.setValue(value, fireEvents);
    }

}
