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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import org.schorn.ella.ui.html.CSS;
import org.schorn.ella.ui.html.HTML;
import org.schorn.ella.ui.visual.Aspect;
import org.schorn.ella.ui.visual.Container;
import org.schorn.ella.ui.visual.Panel;
import org.schorn.ella.ui.visual.Style;
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
    protected final List<CSS.Rule> cssRules = new ArrayList<>();
    protected final List<CSS.Block> cssBlocks = new ArrayList<>();
    protected int idx;
    protected PanelImpl parent;
    protected final Orientation orientation;
    protected final List<Aspect> aspects = new ArrayList<>();
    protected int width;
    protected int height;

    PanelImpl(String id, String name) {
        this.orientation = Orientation.PAGE;
        this.id = id;
        this.name = name;
        this.idx = 0;
        this.parent = null;
        this.width = 100;
        this.height = 100;
        /*
        HTML.Div htmlElement0 = null;
        try {
            htmlElement0 = HTML.Div.create();
            htmlElement0.setId(this.id);
            htmlElement0.addClass(Panel.class.getSimpleName().toLowerCase());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        this.htmlElement = htmlElement0;
         */
    }

    private PanelImpl(Panel parent, Orientation orientation, int idx, int width, int height) {
        this.parent = (PanelImpl) parent;
        this.orientation = orientation;
        this.idx = idx;
        this.width = width;
        this.height = height;
        this.id = String.format("%s_%d", this.parent.id, this.idx);
        this.name = String.format("%s_%d", this.parent.name, this.idx);
        /*
        HTML.Div htmlElement0 = null;
        try {
            htmlElement0 = HTML.Div.create();
            htmlElement0.setId(this.id);
            htmlElement0.addClass(Panel.class.getSimpleName().toLowerCase());
            htmlElement0.addClass(this.parent.name);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        this.htmlElement = htmlElement0;
         */
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
        final CSS.Block block = CSS.Block.create();
        if (!this.cssRules.isEmpty()) {
            block.append(CSS.Selector.createID(this.id));
            this.cssRules.stream().forEach(r -> block.append(r));
            styles.add(block);
        }
        final Map<PanelImpl, Integer> panelPct = new HashMap<>();
        this.aspects.stream()
                .filter(a -> a.panel() != null)
                .map(a -> (PanelImpl) a.panel())
                .forEachOrdered(p -> {
                    Double pct = p.orientation().equals(Orientation.HORIZONTAL)
                            ? (p.height() / Integer.valueOf(this.height).doubleValue() * 100)
                            : (p.width() / Integer.valueOf(this.height).doubleValue() * 100);
                    panelPct.put(p, pct.intValue());
                });
        if (!panelPct.isEmpty()) {
            StringJoiner fitContent = new StringJoiner(" ", "", "");
            for (PanelImpl panel : panelPct.keySet()) {
                fitContent.add(String.format("fit-content(%d%%)", panelPct.get(panel)));
            }
            if (this.aspects.stream()
                    .filter(f -> f instanceof Panel)
                    .map(f -> (Panel) f)
                    .map(p -> p.orientation())
                    .filter(o -> o.equals(Orientation.HORIZONTAL))
                    .findAny()
                    .isPresent()) {
                try {
                    block.append(CSS.Rule.create(CSS.Property.grid_template_columns, fitContent.toString()));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        this.aspects.stream()
                .filter(f -> f instanceof Style)
                .map(f -> (Style) f)
                .map(s -> s.styles())
                .filter(ls -> ls != null)
                .forEachOrdered(ls -> styles.addAll(ls));

        /*
        if (!this.itemRules.isEmpty()) {
            final CSS.Block block = CSS.Block.create();
            block.append(CSS.Selector.createClass(this.name));
        }
         */
        return styles;
    }

    @Override
    public void addContent(Aspect aspect) {
        this.aspects.add(aspect);
    }

    @Override
    public List<Aspect> contents() {
        return Collections.unmodifiableList(this.aspects);
    }

    @Override
    public Orientation orientation() {
        return this.orientation;
    }

    @Override
    public List<Panel> split(Orientation orientation, int... units) {
        switch (orientation) {
            case HORIZONTAL:
                return this.hsplit(units);
            case VERTICAL:
                return this.vsplit(units);
            default:
                LGR.error("{}.split() - unable to split with this {} orientation",
                        this.getClass().getSimpleName(),
                        orientation.name());
        }
        return new ArrayList<>();
    }

    @Override
    public HTML.Element build() throws Exception {
        HTML.Div htmlElement = HTML.Div.create();
        htmlElement.setId(this.id);
        switch (this.orientation) {
            case PAGE:
                htmlElement.addClass("panel");
                htmlElement.addClass("page");
                break;
            case VERTICAL:
                htmlElement.addClass("panel");
                htmlElement.addClass("vertical");
                break;
            case HORIZONTAL:
                htmlElement.addClass("panel");
                htmlElement.addClass("horizontal");
                break;
        }
        for (Container frame : this.aspects) {
            if (frame instanceof Panel) {
                htmlElement.append(((Panel) frame).build());
            } else if (frame instanceof Aspect) {
                htmlElement.append(((Aspect) frame).build());
            }
        }
        return htmlElement;
    }

    @Override
    public String toString() {
        return String.format("%s - w(%d) x h(%d)",
                this.id,
                this.width,
                this.height);
    }

    protected List<Panel> vsplit(int... widths) {
        if (widths.length == 0) {
            widths = new int[2];
            widths[0] = 50;
            widths[1] = 50;
        }
        List<Panel> panels = new ArrayList<>(widths.length);
        double totWidth = 0;
        for (int i = 0; i < widths.length; i++) {
            totWidth += widths[i];
        }
        for (int i = 0; i < widths.length; i++) {
            panels.add(createPanel(Orientation.VERTICAL, i, widths[i] / totWidth));
        }
        return panels;
    }

    protected List<Panel> hsplit(int... heights) {
        if (heights.length == 0) {
            heights = new int[2];
            heights[0] = 50;
            heights[1] = 50;
        }
        List<Panel> panels = new ArrayList<>(heights.length);
        double totHeight = 0;
        for (int i = 0; i < heights.length; i++) {
            totHeight += heights[i];
        }
        for (int i = 0; i < heights.length; i++) {
            panels.add(createPanel(Orientation.HORIZONTAL, i, heights[i] / totHeight));
        }
        return panels;
    }

    protected Panel createPanel(Orientation orientation, int idx, double percent) {
        Panel panel = null;
        switch (orientation) {
            case VERTICAL:
                panel = new PanelImpl(this, orientation, idx,
                        Double.valueOf(percent * this.width).intValue(), this.height);
                break;
            case HORIZONTAL:
                panel = new PanelImpl(this, orientation, idx, this.width,
                        Double.valueOf(percent * this.height).intValue());
                break;
        }
        return panel;
    }

    @Override
    public int width() {
        return this.width;
    }

    @Override
    public int height() {
        return this.height;
    }

}
