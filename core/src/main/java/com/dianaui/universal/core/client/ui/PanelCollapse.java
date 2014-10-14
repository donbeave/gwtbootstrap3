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

import com.dianaui.universal.core.client.ui.base.helper.StyleHelper;
import com.dianaui.universal.core.client.ui.constants.Styles;
import com.dianaui.universal.core.client.ui.html.Div;

/**
 * @author Grant Slender
 */
public class PanelCollapse extends Div {

    public PanelCollapse() {
        setStyleName(Styles.PANEL_COLLAPSE);
        addStyleName(Styles.COLLAPSE);
    }

    public boolean isIn() {
        return StyleHelper.containsStyle(getStyleName(), Styles.IN);
    }

    public void setIn(final boolean in) {
        if (in) {
            addStyleName(Styles.IN);
        } else {
            removeStyleName(Styles.IN);
        }
    }

}
