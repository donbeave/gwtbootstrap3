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
import com.google.gwt.dom.client.Element;
import com.google.gwt.text.shared.Parser;
import com.google.gwt.text.shared.Renderer;

/**
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 * @see com.google.gwt.user.client.ui.HasEnabled
 */
public class ValueBoxBase<T> extends com.google.gwt.user.client.ui.ValueBoxBase<T> implements HasId, HasResponsiveness,
        HasPlaceholder, HasAutoComplete {

    private static final String MAX_LENGTH = "maxlength";

    /**
     * Creates a value box that wraps the given browser element handle. This is
     * only used by subclasses.
     *
     * @param elem     the browser element to wrap
     * @param renderer renderer object
     * @param parser   parser object
     */
    protected ValueBoxBase(final Element elem, final Renderer<T> renderer, final Parser<T> parser) {
        super(elem, renderer, parser);
    }

    public void setMaxLength(int maxLength) {
        getElement().setAttribute(MAX_LENGTH, Integer.toString(maxLength));
    }

    @Override
    public String getPlaceholder() {
        return getElement().getAttribute(PLACEHOLDER);
    }

    @Override
    public void setPlaceholder(final String placeHolder) {
        getElement().setAttribute(PLACEHOLDER, placeHolder != null ? placeHolder : "");
    }

    @Override
    public String getAutoComplete() {
        return getElement().getAttribute(AUTOCOMPLETE);
    }

    @Override
    public void setAutoComplete(final boolean autoComplete) {
        getElement().setAttribute(AUTOCOMPLETE, autoComplete ? ON : OFF);
    }

    @Override
    public String getId() {
        return IdMixin.getId(this);
    }

    @Override
    public void setId(final String id) {
        IdMixin.setId(this, id);
    }

    @Override
    public void setVisibleOn(final DeviceSize deviceSize) {
        StyleHelper.setVisibleOn(this, deviceSize);
    }

    @Override
    public void setHiddenOn(final DeviceSize deviceSize) {
        StyleHelper.setHiddenOn(this, deviceSize);
    }

}
