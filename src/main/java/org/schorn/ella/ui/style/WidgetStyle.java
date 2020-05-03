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
import org.schorn.ella.ui.layout.Style;
import org.schorn.ella.ui.layout.Widget;

/**
 *
 * @author bschorn
 */
public enum WidgetStyle implements Style.Supplier {
    DEFAULT_CONTAINER,
    DEFAULT_LABEL,
    DEFAULT_INPUT,
    LABEL_OVER_INPUT,
    LABEL_LEFT_INPUT,
    OUTPUT_VALUE,
    HIGHLIGHT_ON_HOVER,
    BLUE_STEEL;


    @Override
    public CSS.Style style() {
        return Style.Repo.get(this);
    }

    static public void init() {
        Style.Repo.set(DEFAULT_CONTAINER, CSS.Block.create()
                .append(Widget.Selector.CONTAINER.selector())
                .append(CSS.Rule.create(CSS.Property.margin, "1px"))
                .append(CSS.Rule.create(CSS.Property.padding, "1px"))
        );
        Style.Repo.set(DEFAULT_LABEL, CSS.Block.create()
                .append(Widget.Selector.LABEL.selector())
                .append(CSS.Rule.create(CSS.Property.margin, "1px"))
                .append(CSS.Rule.create(CSS.Property.padding, "2px"))
        );
        Style.Repo.set(DEFAULT_INPUT, CSS.Block.create()
                .append(Widget.Selector.INPUT.selector())
                .append(CSS.Rule.create(CSS.Property.margin, "2px"))
                .append(CSS.Rule.create(CSS.Property.padding, "2px"))
        );

        /**
         * [label] [input]
         */
        Style.Repo.set(LABEL_OVER_INPUT, CSS.Block.create()
                .append(CSS.Selector.createType(HTML.INPUT))
                .append(CSS.Selector.createType(HTML.LABEL))
                .append(CSS.Rule.create(CSS.Property.display, "block"))
        );

        /**
         * [label][input]
         */
        Style.Repo.set(LABEL_LEFT_INPUT, CSS.Block.create()
                .append(CSS.Selector.createType(HTML.INPUT))
                .append(CSS.Selector.createType(HTML.LABEL))
                .append(CSS.Rule.create(CSS.Property.display, "inline"))
        );

        Style.Repo.set(OUTPUT_VALUE, CSS.Block.create()
                .append(CSS.Selector.createClass("value"))
                .append(CSS.Rule.create(CSS.Property.border, "solid grey 1px"))
                .append(CSS.Rule.create(CSS.Property.color, "black"))
                .append(CSS.Rule.create(CSS.Property.background, "white"))
        );

        Style.Repo.set(HIGHLIGHT_ON_HOVER, CSS.Block.create()
                .append(CSS.Selector.createClass("value"))
                .append(CSS.Rule.create(CSS.Property.border, "solid grey 1px"))
                .append(CSS.Rule.create(CSS.Property.color, "black"))
                .append(CSS.Rule.create(CSS.Property.background, "white"))
        );
        Style.Repo.set(BLUE_STEEL, CSS.Block.create()
                .append(Widget.Selector.CONTAINER.selector())
                .append(CSS.Rule.create(CSS.Property.padding, "5px"))
                .append(CSS.Rule.create(CSS.Property.background_color, "rgba(225,225,255,0.7)"))
        );
    }

}
