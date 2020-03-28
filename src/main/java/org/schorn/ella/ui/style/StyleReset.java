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

import java.util.ArrayList;
import java.util.List;
import org.schorn.ella.ui.html.CSS;
import org.schorn.ella.ui.html.HTML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author bschorn
 */
final public class StyleReset implements StyleSheet {

    static private final Logger LGR = LoggerFactory.getLogger(StyleReset.class);

    private final List<CSS.Style> styles = new ArrayList<>();

    StyleReset() {
        try {
            this.styles.add(CSS.Block.create()
                    .append(CSS.Selector.createType(HTML.HTML))
                    .append(CSS.Selector.createType(HTML.BODY))
                    .append(CSS.Selector.createType(HTML.DIV))
                    .append(CSS.Selector.createType(HTML.SPAN))
                    .append(CSS.Selector.createType(HTML.APPLET))
                    .append(CSS.Selector.createType(HTML.OBJECT))
                    .append(CSS.Selector.createType(HTML.IFRAME))
                    .append(CSS.Selector.createType(HTML.H1))
                    .append(CSS.Selector.createType(HTML.H2))
                    .append(CSS.Selector.createType(HTML.H3))
                    .append(CSS.Selector.createType(HTML.H4))
                    .append(CSS.Selector.createType(HTML.H5))
                    .append(CSS.Selector.createType(HTML.H6))
                    .append(CSS.Selector.createType(HTML.P))
                    .append(CSS.Selector.createType(HTML.BLOCKQUOTE))
                    .append(CSS.Selector.createType(HTML.PRE))
                    .append(CSS.Selector.createType(HTML.A))
                    .append(CSS.Selector.createType(HTML.ABBR))
                    .append(CSS.Selector.createType(HTML.ACRONYM))
                    .append(CSS.Selector.createType(HTML.ADDRESS))
                    .append(CSS.Selector.createType(HTML.BIG))
                    .append(CSS.Selector.createType(HTML.CITE))
                    .append(CSS.Selector.createType(HTML.CODE))
                    .append(CSS.Selector.createType(HTML.BIG))
                    .append(CSS.Selector.createType(HTML.DEL))
                    .append(CSS.Selector.createType(HTML.DFN))
                    .append(CSS.Selector.createType(HTML.EM))
                    .append(CSS.Selector.createType(HTML.IMG))
                    .append(CSS.Selector.createType(HTML.INS))
                    .append(CSS.Selector.createType(HTML.KBD))
                    .append(CSS.Selector.createType(HTML.Q))
                    .append(CSS.Selector.createType(HTML.S))
                    .append(CSS.Selector.createType(HTML.SAMP))
                    .append(CSS.Selector.createType(HTML.SMALL))
                    .append(CSS.Selector.createType(HTML.STRIKE))
                    .append(CSS.Selector.createType(HTML.STRONG))
                    .append(CSS.Selector.createType(HTML.SUB))
                    .append(CSS.Selector.createType(HTML.SUP))
                    .append(CSS.Selector.createType(HTML.TT))
                    .append(CSS.Selector.createType(HTML.VAR))
                    .append(CSS.Selector.createType(HTML.B))
                    .append(CSS.Selector.createType(HTML.U))
                    .append(CSS.Selector.createType(HTML.I))
                    .append(CSS.Selector.createType(HTML.CENTER))
                    .append(CSS.Selector.createType(HTML.DL))
                    .append(CSS.Selector.createType(HTML.DT))
                    .append(CSS.Selector.createType(HTML.DD))
                    .append(CSS.Selector.createType(HTML.OL))
                    .append(CSS.Selector.createType(HTML.UL))
                    .append(CSS.Selector.createType(HTML.LI))
                    .append(CSS.Selector.createType(HTML.FIELDSET))
                    .append(CSS.Selector.createType(HTML.FORM))
                    .append(CSS.Selector.createType(HTML.LABEL))
                    .append(CSS.Selector.createType(HTML.LEGEND))
                    .append(CSS.Selector.createType(HTML.TABLE))
                    .append(CSS.Selector.createType(HTML.CAPTION))
                    .append(CSS.Selector.createType(HTML.TBODY))
                    .append(CSS.Selector.createType(HTML.TFOOT))
                    .append(CSS.Selector.createType(HTML.THEAD))
                    .append(CSS.Selector.createType(HTML.TR))
                    .append(CSS.Selector.createType(HTML.TH))
                    .append(CSS.Selector.createType(HTML.TD))
                    .append(CSS.Selector.createType(HTML.TH))
                    .append(CSS.Selector.createType(HTML.ARTICLE))
                    .append(CSS.Selector.createType(HTML.ASIDE))
                    .append(CSS.Selector.createType(HTML.CANVAS))
                    .append(CSS.Selector.createType(HTML.DETAILS))
                    .append(CSS.Selector.createType(HTML.EMBED))
                    .append(CSS.Selector.createType(HTML.FIGURE))
                    .append(CSS.Selector.createType(HTML.FOOTER))
                    .append(CSS.Selector.createType(HTML.HEADER))
                    .append(CSS.Selector.createType(HTML.HGROUP))
                    .append(CSS.Selector.createType(HTML.MENU))
                    .append(CSS.Selector.createType(HTML.NAV))
                    .append(CSS.Selector.createType(HTML.OUTPUT))
                    .append(CSS.Selector.createType(HTML.RUBY))
                    .append(CSS.Selector.createType(HTML.SECTION))
                    .append(CSS.Selector.createType(HTML.TIME))
                    .append(CSS.Selector.createType(HTML.MARK))
                    .append(CSS.Selector.createType(HTML.AUDIO))
                    .append(CSS.Selector.createType(HTML.VIDEO))
                    .append(CSS.Rule.create(CSS.Property.margin, "0"))
                    .append(CSS.Rule.create(CSS.Property.padding, "0"))
                    .append(CSS.Rule.create(CSS.Property.border, "0"))
                    .append(CSS.Rule.create(CSS.Property.font_size, "100%"))
                    .append(CSS.Rule.create(CSS.Property.font, "inherit"))
                    .append(CSS.Rule.create(CSS.Property.vertical_align, "baseline"))
            );

            this.styles.add(CSS.Block.create()
                    .append(CSS.Selector.createType(HTML.ARTICLE))
                    .append(CSS.Selector.createType(HTML.ASIDE))
                    .append(CSS.Selector.createType(HTML.DETAILS))
                    .append(CSS.Selector.createType(HTML.FIGURE))
                    .append(CSS.Selector.createType(HTML.FOOTER))
                    .append(CSS.Selector.createType(HTML.HEADER))
                    .append(CSS.Selector.createType(HTML.HGROUP))
                    .append(CSS.Selector.createType(HTML.MENU))
                    .append(CSS.Selector.createType(HTML.NAV))
                    .append(CSS.Selector.createType(HTML.SECTION))
                    .append(CSS.Rule.create(CSS.Property.display, "block"))
            );

            this.styles.add(CSS.Block.create()
                    .append(CSS.Selector.createType(HTML.BODY))
                    .append(CSS.Rule.create(CSS.Property.line_height, "1"))
            );

            this.styles.add(CSS.Block.create()
                    .append(CSS.Selector.createType(HTML.UL))
                    .append(CSS.Selector.createType(HTML.OL))
                    .append(CSS.Rule.create(CSS.Property.list_style, "none"))
            );

            this.styles.add(CSS.Block.create()
                    .append(CSS.Selector.createType(HTML.BLOCKQUOTE))
                    .append(CSS.Selector.createType(HTML.Q))
                    .append(CSS.Rule.create(CSS.Property.quotes, "none"))
            );

            this.styles.add(CSS.Block.create()
                    .append(CSS.Selector.create("blockquote:before"))
                    .append(CSS.Selector.create("blockquote:after"))
                    .append(CSS.Selector.create("q:before"))
                    .append(CSS.Selector.create("q:after"))
                    .append(CSS.Rule.create(CSS.Property.content, "none"))
            );

            this.styles.add(CSS.Block.create()
                    .append(CSS.Selector.createType(HTML.TABLE))
                    .append(CSS.Rule.create(CSS.Property.border_collapse, "collapse"))
                    .append(CSS.Rule.create(CSS.Property.border_spacing, "0"))
            );
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<CSS.Style> styles() {
        return this.styles;
    }

}
