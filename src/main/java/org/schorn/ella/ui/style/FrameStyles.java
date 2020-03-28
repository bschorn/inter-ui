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

/**
 *
 * @author bschorn
 */
public enum FrameStyles implements StyleFactory.FactorySupplier {
    DEBUG;

    @Override
    public CSS.Style get() {
        return StyleFactory.get(this);
    }

    static public void init() {
        StyleFactory.set(DEBUG, CSS.Block.create().append(CSS.Selector.createClass("frame"))
                .append(CSS.Rule.create(CSS.Property.display, "inline-block"))
                .append(CSS.Rule.create(CSS.Property.min_width, "500px"))
                .append(CSS.Rule.create(CSS.Property.min_height, "100px"))
                .append(CSS.Rule.create(CSS.Property.border, "dashed lightgray 2px")));
    }

}
