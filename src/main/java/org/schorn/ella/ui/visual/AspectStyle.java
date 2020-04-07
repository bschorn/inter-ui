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
import org.schorn.ella.ui.layout.Aspect;
import org.schorn.ella.ui.layout.Style;

/**
 *
 * @author bschorn
 */
public enum AspectStyle implements Style.Supplier {
    DEFAULT_CONTAINER,
    DEFAULT_LABEL;

    @Override
    public CSS.Style style() {
        return Style.Repo.get(this);
    }

    static public void init() {
        Style.Repo.set(DEFAULT_CONTAINER, CSS.Block.create()
                .append(Aspect.Selector.CONTAINER.selector())
                .append(CSS.Rule.create(CSS.Property.display, "grid"))
                .append(CSS.Rule.create(CSS.Property.padding, "2px"))
                .append(CSS.Rule.create(CSS.Property.margin, "1px"))
                .append(CSS.Rule.create(CSS.Property.border_style, "solid"))
                .append(CSS.Rule.create(CSS.Property.border_width, "1px"))
                .append(CSS.Rule.create(CSS.Property.border_color, "black"))
                .append(CSS.Rule.create(CSS.Property.border_radius, "4px"))
                .append(CSS.Rule.create(CSS.Property.background_color, "rgba(225, 225, 225, 0.5)")));
        Style.Repo.set(DEFAULT_LABEL, CSS.Block.create()
                .append(Aspect.Selector.LABEL.selector())
                .append(CSS.Rule.create(CSS.Property.text_align, "center"))
                .append(CSS.Rule.create(CSS.Property.padding, "2px")));
    }

}
