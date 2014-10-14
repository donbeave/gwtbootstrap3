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
package com.dianaui.universal.core.client.ui.gwt;

import com.dianaui.universal.core.client.ui.base.HasId;
import com.dianaui.universal.core.client.ui.base.HasPull;
import com.dianaui.universal.core.client.ui.base.HasResponsiveness;
import com.dianaui.universal.core.client.ui.base.helper.StyleHelper;
import com.dianaui.universal.core.client.ui.base.mixin.IdMixin;
import com.dianaui.universal.core.client.ui.base.mixin.PullMixin;
import com.dianaui.universal.core.client.ui.constants.DeviceSize;
import com.dianaui.universal.core.client.ui.constants.Pull;

/**
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class Widget extends com.google.gwt.user.client.ui.Widget implements HasResponsiveness, HasId, HasPull {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getId() {
        return IdMixin.getId(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(final String id) {
        IdMixin.setId(this, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVisibleOn(final DeviceSize deviceSize) {
        StyleHelper.setVisibleOn(this, deviceSize);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHiddenOn(final DeviceSize deviceSize) {
        StyleHelper.setHiddenOn(this, deviceSize);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pull getPull() {
        return PullMixin.getPull(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPull(final Pull pull) {
        PullMixin.setPull(this, pull);
    }

}
