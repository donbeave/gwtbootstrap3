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

import com.dianaui.universal.core.client.ui.base.IsClosable;
import com.dianaui.universal.core.client.ui.base.ModalComponent;
import com.dianaui.universal.core.client.ui.base.helper.StyleHelper;
import com.dianaui.universal.core.client.ui.base.modal.ModalContent;
import com.dianaui.universal.core.client.ui.base.modal.ModalDialog;
import com.dianaui.universal.core.client.ui.base.modal.ModalWithBackdrop;
import com.dianaui.universal.core.client.ui.constants.Attributes;
import com.dianaui.universal.core.client.ui.constants.ModalSize;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;
import java.util.List;

/**
 * Modal dialog.
 * <h3>UiBinder example</h3>
 * <pre>
 * {@code
 *     <b:Modal title="Important information" b:id="modal1">
 *         <b:ModalBody>
 *             <g:HTML>Lorem ipsum...</g:HTML>
 *         </b:ModalBody>
 *         <b:ModalFooter>
 *             <b:Button type="PRIMARY">Do something</b:Button>
 *             <b:Button type="DANGER" dismiss="MODAL">Close</b:Button>
 *         </b:ModalFooter>
 *     </b:Modal>
 *     <b:Button target="#modal1" toggle="MODAL">Show modal</b:Button>
 * }
 * </pre>
 * It's also possible to specify a custom modal header:
 * <pre>
 * {@code
 *     <b:Modal>
 *         <b:ModalHeader>
 *             <g:HTML>
 *                 <h4>Custom header</h4>
 *             </g:HTML>
 *         </b:ModalHeader>
 *         ...
 *     </b:Modal>
 * }
 * </pre>
 *
 * @author Sven Jacobs
 * @author Joshua Godi
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 * @see ModalHeader
 * @see ModalBody
 * @see ModalFooter
 */
public class Modal extends ModalWithBackdrop implements IsClosable {

    private final static List<Modal> ACTIVE = new ArrayList<>();

    private final ModalContent content = new ModalContent();
    private final ModalDialog dialog = new ModalDialog();
    private ModalHeader header = new ModalHeader();
    private HandlerRegistration closeHandler;
    private boolean hideOtherModals = false;

    public Modal() {
        content.add(header);
        dialog.add(content);

        add(dialog);

        setFade(true);
    }

    // TODO
    public void setKeyboard(final boolean keyboard) {
        getElement().setAttribute(Attributes.DATA_KEYBOARD, Boolean.toString(keyboard));

        // tabindex must be set to -1 for ESC key to work
        if (keyboard) {
            getElement().setAttribute(Attributes.TABINDEX, "-1");
        }
    }

    public ModalSize getSize() {
        return ModalSize.fromStyleName(getStyleName());
    }

    public void setSize(ModalSize size) {
        StyleHelper.addUniqueEnumStyleName(dialog, ModalSize.class, size);
    }

    public boolean isHideOtherModals() {
        return hideOtherModals;
    }

    public void setHideOtherModals(boolean hideOtherModals) {
        this.hideOtherModals = hideOtherModals;
    }

    @Override
    public void setWidth(final String width) {
        dialog.setWidth(width);
    }

    @Override
    public boolean isClosable() {
        return header.isClosable();
    }

    @Override
    public void setClosable(final boolean closable) {
        header.setClosable(closable);

        if (closable) {
            closeHandler = header.getCloseButton().addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent clickEvent) {
                    hide();
                }
            });
        } else {
            if (closeHandler != null) {
                closeHandler.removeHandler();
                closeHandler = null;
            }
        }
    }

    @Override
    public void add(final Widget w) {
        // User can supply own ModalHeader
        if (w instanceof ModalHeader) {
            header.removeFromParent();
            header = (ModalHeader) w;
        }

        if (w instanceof ModalComponent) {
            content.add(w);
        } else {
            super.add(w);
        }
    }

    @Override
    public void setTitle(final String title) {
        header.setTitle(title);
    }

    @Override
    public void show() {
        if (hideOtherModals && !ACTIVE.isEmpty()) {
            for (Modal modal : ACTIVE) {
                modal.hide();
            }
        }
        super.show();

        if (isViewing() && !ACTIVE.contains(this))
            ACTIVE.add(this);
    }

    @Override
    public void hide() {
        super.hide();

        if (!isViewing() && ACTIVE.contains(this))
            ACTIVE.remove(this);
    }

}
