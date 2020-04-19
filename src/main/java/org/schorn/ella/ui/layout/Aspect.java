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
import org.schorn.ella.ui.html.HTML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author bschorn
 */
public interface Aspect extends Container<Widget> {

    static final Logger LGR = LoggerFactory.getLogger(Aspect.class);

    static Aspect create(Item.Name name) {
        return UIProvider.provider().createAspect(name);
    }

    static Aspect create(Item.Name name, String label) {
        return UIProvider.provider().createAspect(name, label);
    }

    public String aspectId();

    @Override
    public void accept(Widget widget);

    @Override
    default Type type() {
        return Type.ASPECT;
    }

    public enum Selector implements Style.Selectors {
        CONTAINER(CSS.Selector.create("div.aspect.container")),
        LABEL(CSS.Selector.createClass("aspect", "label")),
        ENTITY(CSS.Selector.createClass("aspect", "entity"));

        private final CSS.Selector selector;

        Selector(CSS.Selector selector) {
            this.selector = selector;
        }

        @Override
        public CSS.Selector selector() {
            return this.selector;
        }

        public CSS.Selector selector(Aspect aspect) {
            return CSS.Selector.createClass(this.selector.render().substring(1), aspect.name());
        }
    }


    public void setAction(String actionURL);

    public void setMethod(HTML.Method method);

    public void setEnctype(HTML.Enctype enctype);

    public void setTarget(HTML.Target target);
}
