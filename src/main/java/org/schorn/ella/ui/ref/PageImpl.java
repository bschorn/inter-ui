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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.schorn.ella.ui.html.CSS;
import org.schorn.ella.ui.html.HTML;
import org.schorn.ella.ui.layout.Frame;
import org.schorn.ella.ui.visual.Page;
import org.schorn.ella.ui.visual.Panel;

/**
 *
 * @author bschorn
 */
class PageImpl implements Page {

    protected final String title;
    protected final List<CSS.Style> styles = new ArrayList<>();
    protected final List<Frame> frames = new ArrayList<>();
    protected final Map<Frame, Panel> panels = new HashMap<>();
    protected final Map<Frame, Frame.Styler> frameStylers = new HashMap<>();


    PageImpl(String title) {
        this.title = title;
    }

    @Override
    public HTML.Element build() throws Exception {
        List<CSS.Style> cssStyles = new ArrayList<>();
        cssStyles.addAll(this.styles.stream()
                .filter(css -> css instanceof CSS.Block)
                .collect(Collectors.toList()));

        final CSS.Block cssBlock = CSS.Block.create();
        cssBlock.append(CSS.Selector.createType(HTML.BODY));
        this.styles.stream()
                .filter(css -> css instanceof CSS.Rule)
                .map(css -> (CSS.Rule) css)
                .forEachOrdered(cssr -> cssBlock.append(cssr));
        cssStyles.add(cssBlock);

        HTML.Page page = HTML.Page.create();
        for (Frame frame : this.frames) {
            HTML.Div div = HTML.Div.create();
            page.append(div);
            div.setId(frame.id());
            div.addClass("frame");
            div.addClass(frame.name());
            Frame.Styler frameStyler = this.frameStylers.get(frame);
            if (frameStyler != null) {
                cssStyles.add(frameStyler.apply(frame));
            }
            Panel panel = this.panels.get(frame);
            if (panel != null) {
                HTML.Element panelElement = panel.build();
                if (panelElement != null) {
                    div.append(panelElement);
                    cssStyles.addAll(panel.styles());
                }
            }
        }

        HTML.Style style = HTML.Style.create();
        cssStyles.stream()
                .filter(s -> s instanceof CSS.Block)
                .map(s -> (CSS.Block) s)
                .forEachOrdered(b -> style.append(b));

        page.htmlHead().append(style);

        return page;
    }

    @Override
    public void accept(Frame frame) {
        this.frames.add(frame);
    }

    @Override
    public void assign(Panel panel, String frameName) {
        Optional<Frame> optFrame = this.frames.stream()
                .filter(frame -> frame.name().equals(frameName))
                .findAny();
        if (optFrame.isPresent()) {
            this.panels.put(optFrame.get(), panel);
        }
    }

    @Override
    public void addStyle(CSS.Style style) {
        this.styles.add(style);
    }

    @Override
    public void assign(Frame.Styler frameStyler, String frameName) {
        Optional<Frame> optFrame = this.frames.stream()
                .filter(frame -> frame.name().equals(frameName))
                .findAny();
        if (optFrame.isPresent()) {
            this.frameStylers.put(optFrame.get(), frameStyler);
        }
    }

}
