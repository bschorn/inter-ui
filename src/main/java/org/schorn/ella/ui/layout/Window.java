/*
 * The MIT License
 *
 * Copyright 2020 bschorn.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.schorn.ella.ui.layout;

import org.schorn.ella.ui.html.CSS;
import org.schorn.ella.ui.util.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.schorn.ella.ui.EllamentProvider;

/**
 *
 * @author bschorn
 */
public interface Window extends Container<Item> {

    static final Logger LGR = LoggerFactory.getLogger(Window.class);

    static Window create(Identifier name) {
        return EllamentProvider.provider().createWindow(name);
    }
    static Window create(Identifier name, String label) {
        return EllamentProvider.provider().createWindow(name, label);
    }

    @Override
    public void accept(Item item);

    @Override
    default Role type() {
        return Role.WINDOW;
    }

    public enum Selector implements Style.Selectors {
        CONTAINER(CSS.Selector.create("div.window.container")),
        LABEL(CSS.Selector.create("div.window.label > *")),
        FORM(CSS.Selector.create("div.window form"));

        private final CSS.Selector selector;

        Selector(CSS.Selector selector) {
            this.selector = selector;
        }

        @Override
        public CSS.Selector selector() {
            return this.selector;
        }
    }

    default Pane newPane() {
        try {
            Pane pane = EllamentProvider.provider().createPane(Identifier.create(this.name()), this.label());
            this.accept(pane);
            return pane;
        } catch (Exception ex) {
            LGR.error("{}.newPane() - Caught Exception: {}",
                    Window.class.getSimpleName(),
                    ToString.stackTrace(ex));
        }
        return null;
    }

    default Pane newPane(Identifier name, String label) {
        Pane pane = EllamentProvider.provider().createPane(name, label);
        this.accept(pane);
        return pane;
    }

}
