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
import org.schorn.ella.ui.layout.Style;

/**
 *
 * @author bschorn
 */
public enum FontStyle implements Style.Factory {
    ARIAL,
    CALIBRI,
    VERDANA;

    @Override
    public CSS.Style style() {
        return Style.Repo.get(this);
    }

    static public void init() {
        Style.Repo.set(ARIAL, CSS.Block.create()
                .append(CSS.Rule.create(CSS.Property.font_family, "Arial, sans-serif"))
        );
        Style.Repo.set(CALIBRI, CSS.Block.create()
                .append(CSS.Rule.create(CSS.Property.font_family, "Calibri, sans-serif"))
        );
        Style.Repo.set(VERDANA, CSS.Block.create()
                .append(CSS.Rule.create(CSS.Property.font_family, "Verdana, sans-serif"))
        );
    }
    static private CSS.Style compose(CSS.Block newBlock, CSS.Style style) {
        switch (style.role()) {
            case BLOCK:
                CSS.Block oldBlock = (CSS.Block) style;
                oldBlock.rules().stream().forEach(newBlock);
                break;
            case RULE:
                CSS.Rule rule = (CSS.Rule) style;
                newBlock.accept(rule);
                break;
        }
        return newBlock;
    }

    @Override
    public CSS.Style add(CSS.Selector selector) {
        CSS.Style style = this.style();
        CSS.Block newBlock = CSS.Block.create().append(selector);
        return compose(newBlock, style);
    }

}
