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
package org.schorn.ella.ui.style;

import org.schorn.ella.ui.html.CSS;
import org.schorn.ella.ui.html.HTML;

/**
 *
 * @author bschorn
 */
public enum WidgetStyles implements StyleFactory.FactorySupplier {
    LABEL_OVER_VALUE,
    OUTPUT_VALUE,
    HIGHLIGHT_ON_HOVER;

    @Override
    public CSS.Style get() {
        return StyleFactory.get(this);
    }

    static public void init() {
        StyleFactory.set(LABEL_OVER_VALUE, CSS.Block.create()
                .append(CSS.Selector.createType(HTML.INPUT))
                .append(CSS.Selector.createType(HTML.LABEL))
                .append(CSS.Rule.create(CSS.Property.display, "block")));

        StyleFactory.set(OUTPUT_VALUE, CSS.Block.create()
                .append(CSS.Selector.createClass("value"))
                .append(CSS.Rule.create(CSS.Property.border, "solid grey 1px"))
                .append(CSS.Rule.create(CSS.Property.color, "black"))
                .append(CSS.Rule.create(CSS.Property.background, "white")));

        StyleFactory.set(HIGHLIGHT_ON_HOVER, CSS.Block.create()
                .append(CSS.Selector.createClass("value"))
                .append(CSS.Rule.create(CSS.Property.border, "solid grey 1px"))
                .append(CSS.Rule.create(CSS.Property.color, "black"))
                .append(CSS.Rule.create(CSS.Property.background, "white")));
    }

}
