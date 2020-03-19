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
public class StyleFactory {

    /*
    input, label {
        display:block;
    }
     */
    static public final CSS.Style LABEL_STYLE_01;

    static {
        CSS.Style labelStyle01 = null;
        try {
            labelStyle01 = CSS.Block.create()
                    .append(CSS.Selector.createType(HTML.INPUT))
                    .append(CSS.Selector.createType(HTML.LABEL))
                    .append(CSS.Rule.create(CSS.Property.display, "block"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        LABEL_STYLE_01 = labelStyle01;
    }
}
