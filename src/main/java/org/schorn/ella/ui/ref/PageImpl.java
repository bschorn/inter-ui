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
import org.schorn.ella.ui.html.CSS;
import org.schorn.ella.ui.html.HTML;
import org.schorn.ella.ui.visual.Page;
import org.schorn.ella.ui.visual.Panel;

/**
 *
 * @author bschorn
 */
class PageImpl implements Page {

    private final Panel panel;
    protected final List<CSS.Rule> cssRules = new ArrayList<>();
    protected final List<CSS.Block> cssBlocks = new ArrayList<>();

    public PageImpl(String id, String name) {
        this.panel = new PanelImpl(id, name);
    }

    @Override
    public Panel panel() {
        return this.panel;
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
    public List<CSS.Style> styles() {
        List<CSS.Style> styles = new ArrayList<>();
        styles.addAll(this.cssBlocks);
        if (!this.cssRules.isEmpty()) {
            final CSS.Block block = CSS.Block.create();
            try {
                block.append(CSS.Selector.createType(HTML.BODY));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            this.cssRules.stream().forEach(r -> block.append(r));
            styles.add(block);
        }
        return styles;
    }

    @Override
    public HTML.Element build() throws Exception {
        HTML.Page page = HTML.Page.create();

        List<CSS.Style> cssStyles = new ArrayList<>();
        cssStyles.addAll(this.cssBlocks);
        cssStyles.addAll(this.panel.styles());
        HTML.Style style = HTML.Style.create();
        final CSS.Block cssBlock = CSS.Block.create();
        cssBlock.append(CSS.Selector.createType(HTML.BODY));
        this.cssRules.stream()
                .forEachOrdered(r -> cssBlock.append(r));
        cssStyles.add(cssBlock);
        cssStyles.stream()
                .filter(s -> s instanceof CSS.Block)
                .map(s -> (CSS.Block) s)
                .forEachOrdered(b -> style.append(b));

        page.htmlBody().append(this.panel.build());
        page.htmlHead().append(style);

        return page;
    }

    @Override
    public String id() {
        return this.panel.id();
    }

    @Override
    public String name() {
        return this.panel.name();
    }

}
