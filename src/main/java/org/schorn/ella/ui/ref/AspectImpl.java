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
package org.schorn.ella.ui.ref;

import java.util.ArrayList;
import java.util.List;
import org.schorn.ella.ui.frame.Aspect;
import org.schorn.ella.ui.html.CSS;
import org.schorn.ella.ui.html.HTML;
import org.schorn.ella.ui.widget.Widget;

/**
 *
 * @author bschorn
 */
abstract class AspectImpl implements Aspect {

    private final HTML.Div divElement;
    private final HTML.Form formElement;
    private final String id;
    private final String name;
    private final List<Widget> widgets = new ArrayList<>();
    private final List<CSS.Rule> cssRules = new ArrayList<>();
    private final List<CSS.Block> cssBlocks = new ArrayList<>();

    AspectImpl(String id, String name) {
        this.id = id;
        this.name = name;
        HTML.Div divElement0 = null;
        HTML.Form formElement0 = null;
        try {
            divElement0 = HTML.Div.create();
            formElement0 = HTML.Form.create();
            formElement0.setId(this.id);
            formElement0.addClass(this.name);
            divElement0.append(formElement0);
            /*
             */
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        this.divElement = divElement0;
        this.formElement = formElement0;
    }

    @Override
    public void addContent(Widget widget) throws Exception {
        this.widgets.add(widget);
    }

    @Override
    public void addStyle(CSS.Style cssStyle) {
        switch (cssStyle.role()) {
            case BLOCK:
                CSS.Block cssBlock = (CSS.Block) cssStyle;
                if (cssBlock.selectors().isEmpty()) {
                    cssBlock.rules().stream().forEachOrdered(r -> this.cssRules.add((CSS.Rule) r));
                } else {
                    this.cssBlocks.add((CSS.Block) cssStyle);
                }
                break;
            case RULE:
                this.cssRules.add((CSS.Rule) cssStyle);
                break;
            default:
                //LGR.error("error");
                break;
        }
    }

    @Override
    public List<CSS.Style> getStyles() {
        List<CSS.Style> styles = new ArrayList<>();
        styles.addAll(this.cssBlocks);
        if (!this.cssRules.isEmpty()) {
            final CSS.Block block = CSS.Block.create();
            block.append(CSS.Selector.createID(this.id));
            this.cssRules.stream().forEach(r -> block.append(r));
            styles.add(block);
        }
        return styles;
    }

    @Override
    public HTML.Element build() throws Exception {
        for (Widget widget : this.widgets) {
            this.formElement.append(widget.build());
        }
        return this.divElement;
    }
}
