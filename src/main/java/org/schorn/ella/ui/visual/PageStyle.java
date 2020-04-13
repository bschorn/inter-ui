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
package org.schorn.ella.ui.visual;

import org.schorn.ella.ui.html.CSS;
import org.schorn.ella.ui.layout.Page;
import org.schorn.ella.ui.layout.Style;

/**
 *
 * @author bschorn
 */
public enum PageStyle implements Style.Supplier {
    DEFAULT_CONTAINER,
    FLEX_COLUMN_CONTAINER;

    @Override
    public CSS.Style style() {
        return Style.Repo.get(this);
    }

    static public void init() {
        Style.Repo.set(DEFAULT_CONTAINER, CSS.Block.create()
                .append(Page.Selector.CONTAINER.selector())
                .append(CSS.Rule.create(CSS.Property.padding, "0px"))
                .append(CSS.Rule.create(CSS.Property.display, "grid"))
                .append(CSS.Rule.create(CSS.Property.min_height, "100vh"))
                .append(CSS.Rule.create(CSS.Property.justify_content, "center"))
                .append(CSS.Rule.create(CSS.Property.align_content, "center"))
        );
        Style.Repo.set(FLEX_COLUMN_CONTAINER, CSS.Block.create()
                .append(Page.Selector.CONTAINER.selector())
                .append(CSS.Rule.create(CSS.Property.padding, "0px"))
                .append(CSS.Rule.create(CSS.Property.display, "flex"))
                .append(CSS.Rule.create(CSS.Property.flex_direction, "column"))
                .append(CSS.Rule.create(CSS.Property.min_height, "100vh"))
        );

    }

}
