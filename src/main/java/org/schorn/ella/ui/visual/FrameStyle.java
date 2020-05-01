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
import org.schorn.ella.ui.html.CSSProperty;
import org.schorn.ella.ui.layout.Frame;
import org.schorn.ella.ui.layout.Style;

/**
 *
 * @author bschorn
 */
public enum FrameStyle implements Style.Supplier {
    DEFAULT_CONTAINER,
    BLUE_STEEL;

    @Override
    public CSS.Style style() {
        return Style.Repo.get(this);
    }

    static public void init() {
        Style.Repo.set(DEFAULT_CONTAINER, CSS.Block.create().append(Frame.Selector.CONTAINER.selector())
                .append(CSS.Rule.create(CSS.Property.margin, "0px"))
                .append(CSS.Rule.create(CSS.Property.background_color, "white"))
        //.append(CSS.Rule.create(CSS.Property.flex_direction, "row"))
        //.append(CSS.Rule.create(CSS.Property.flex_wrap, "wrap"))
                //.append(CSS.Rule.create(CSS.Property.justify_content, "space-evenly"))
                //.append(CSS.Rule.create(CSS.Property.align_content, "space-evenly"))
        //.append(CSS.Rule.create(CSS.Property.display, "flex")));
        );
        Style.Repo.set(BLUE_STEEL, CSS.Block.create()
                .append(Frame.Selector.CONTENT.selector())
                //.append(CSS.Rule.create(CSSProperty.display.flex))
                .append(CSS.Rule.create(CSSProperty.flex_flow.row_nowrap))
                .append(CSS.Rule.create(CSSProperty.flex_grow.unit(1)))
        );
    }
    /*
.header,
.footer {
  height: 30px;
  border: 1px solid rgba(0, 0, 0, 0.5);
  background-image: linear-gradient(180deg, #fff, #ddd 40%, #ccc);
  padding: 5px;
}

.main {
  flex-grow: 1;
  background-color: tan;
  padding: 5px;
}

ul.menu {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
}
.menu-item {
  border: 1px solid white;
  padding: 5px;
}
.fit-picture {
    width: 16px;
}
     */

}
