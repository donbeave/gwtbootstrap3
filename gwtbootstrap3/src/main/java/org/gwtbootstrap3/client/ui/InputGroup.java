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

import org.gwtbootstrap3.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap3.client.ui.constants.Styles;

/**
 * Input group parent element of {@link InputGroupAddon}.
 *
 * @author Sven Jacobs
 * @author Joshua Godi
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 * @see InputGroupAddon
 */
public class InputGroup extends Div implements HasResponsiveness {

    public InputGroup() {
        setStyleName(Styles.INPUT_GROUP);
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
