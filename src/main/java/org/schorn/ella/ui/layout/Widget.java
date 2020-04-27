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

/**
 *
 * @author bschorn
 */
public interface Widget extends Item {

    public String widgetId();

    public String customTag();

    @Override
    default Type type() {
        return Type.WIDGET;
    }

    public enum Selector implements Style.Selectors {
        CONTAINER(CSS.Selector.createClass("widget")),
        INPUT(CSS.Selector.create(".widget input")),
        LABEL(CSS.Selector.create(".widget > label"));

        private final CSS.Selector selector;

        Selector(CSS.Selector selector) {
            this.selector = selector;
        }

        @Override
        public CSS.Selector selector() {
            return this.selector;
        }

        public CSS.Selector selector(Widget widget) {
            return CSS.Selector.createClass(this.selector.render().substring(1), widget.name());
        }
    }

    public String widgetName();

    public interface Input extends Widget {
        public void setDatalist(String[] datalist);

        public void setPlaceholder(String placeholder);

        public void setReadonly(boolean readonly);

        public String placeholder();

        public boolean isReadonly();

    }

    public interface Output extends Widget {

    }

    public interface Control extends Widget {

    }

}
