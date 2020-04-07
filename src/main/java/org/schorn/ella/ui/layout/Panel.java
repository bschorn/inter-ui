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

import org.schorn.ella.ui.UIProvider;
import org.schorn.ella.ui.html.CSS;
import org.schorn.ella.ui.util.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author bschorn
 */
public interface Panel extends Container<Item> {

    static final Logger LGR = LoggerFactory.getLogger(Panel.class);

    static Panel create(Item.Name name) {
        return UIProvider.provider().createPanel(name);
    }
    static Panel create(Item.Name name, String label) {
        return UIProvider.provider().createPanel(name, label);
    }

    @Override
    public void accept(Item item);

    @Override
    default Type type() {
        return Type.PANEL;
    }

    public enum Selector implements Style.Selectors {
        CONTAINER(CSS.Selector.createClass("panel", "container")),
        LABEL(CSS.Selector.create(".panel.label > *"));

        private final CSS.Selector selector;

        Selector(CSS.Selector selector) {
            this.selector = selector;
        }

        @Override
        public CSS.Selector selector() {
            return this.selector;
        }
    }

    default Aspect newAspect() {
        try {
            Aspect aspect = UIProvider.provider().createAspect(Item.Name.create(this.name()), this.label());
            this.accept(aspect);
            return aspect;
        } catch (Exception ex) {
            LGR.error("{}.newAspect() - Caught Exception: {}",
                    Panel.class.getSimpleName(),
                    ToString.stackTrace(ex));
        }
        return null;
    }

    default Aspect newAspect(Item.Name name, String label) {
        Aspect aspect = UIProvider.provider().createAspect(name, label);
        this.accept(aspect);
        return aspect;
    }

}
