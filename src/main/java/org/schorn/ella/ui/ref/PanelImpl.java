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
import java.util.stream.Collectors;
import org.schorn.ella.ui.html.CSS;
import org.schorn.ella.ui.html.HTML;
import org.schorn.ella.ui.visual.Aspect;
import org.schorn.ella.ui.visual.Panel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author bschorn
 */
class PanelImpl implements Panel {

    static final Logger LGR = LoggerFactory.getLogger(PanelImpl.class);

    protected String id;
    protected String name;
    protected final List<CSS.Style> styles = new ArrayList<>();
    protected final List<Aspect> aspects = new ArrayList<>();

    PanelImpl(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public void addStyle(CSS.Style cssStyle) {
        this.styles.add(cssStyle);
    }

    @Override
    public List<CSS.Style> styles() {
        List<CSS.Style> cssStyles = new ArrayList<>();
        cssStyles.addAll(this.styles.stream()
                .filter(css -> css instanceof CSS.Block)
                .collect(Collectors.toList()));

        final CSS.Block cssBlock = CSS.Block.create();
        cssBlock.append(CSS.Selector.createID(this.id));
        this.styles.stream()
                .filter(css -> css instanceof CSS.Rule)
                .map(css -> (CSS.Rule) css)
                .forEachOrdered(cssr -> cssBlock.append(cssr));
        cssStyles.add(cssBlock);

        for (Aspect aspect : this.aspects) {
            cssStyles.addAll(aspect.styles());
        }
        return cssStyles;
    }

    @Override
    public void accept(Aspect aspect) {
        this.aspects.add(aspect);
    }

    @Override
    public HTML.Element build() throws Exception {
        HTML.Div htmlElement = HTML.Div.create();
        htmlElement.setId(this.id);
        htmlElement.addClass(this.name);
        for (Aspect aspect : this.aspects) {
            HTML.Element aspectElement = aspect.build();
            if (aspectElement != null) {
                htmlElement.append(aspectElement);
            }
        }
        return htmlElement;
    }

    @Override
    public String id() {
        return this.id;
    }

    @Override
    public String name() {
        return this.name;
    }

}
