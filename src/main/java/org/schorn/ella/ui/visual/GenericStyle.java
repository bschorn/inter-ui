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
import org.schorn.ella.ui.layout.Style;

/**
 *
 * @author bschorn
 */
public enum GenericStyle implements Style.Factory {
    BOX_SIZING_BORDER_BOX,
    FLEX_HALF_DASHED_LIGHTGRAY,
    GRID_FULL_SOLID_WHITE_ON_GREEN,
    GRID_FULL_SOLID_BLACK_ON_CYAN,
    TEXT_ALIGN_CENTER_PADDING_2PX,
    ORANGE_BORDER,
    DASHED_BORDER,
    ROUNDED_BORDER,
    GRID_WITH_ONE_COLUMN,
    GRID_WITH_TWO_COLUMNS,
    GRID_WITH_THREE_COLUMNS,
    GRID_WITH_FOUR_COLUMNS,;

    @Override
    public CSS.Style style() {
        return Style.Repo.get(this);
    }

    static public void init() {
        Style.Repo.set(BOX_SIZING_BORDER_BOX, CSS.Block.create()
                .append(CSS.Rule.create(CSS.Property.box_sizing, "border-box")));
        Style.Repo.set(FLEX_HALF_DASHED_LIGHTGRAY, CSS.Block.create()
                .append(CSS.Rule.create(CSS.Property.display, "block"))
                .append(CSS.Rule.create(CSS.Property.width, "50vw"))
                //.append(CSS.Rule.create(CSS.Property.min_height, "100px"))
                .append(CSS.Rule.create(CSS.Property.border, "dashed lightgray 1px")));
        Style.Repo.set(GRID_FULL_SOLID_WHITE_ON_GREEN, CSS.Block.create()
                .append(CSS.Rule.create(CSS.Property.display, "flex"))
                .append(CSS.Rule.create(CSS.Property.padding, "5px"))
                .append(CSS.Rule.create(CSS.Property.border, "solid green 1px"))
        //.append(CSS.Rule.create(CSS.Property.border_radius, "5px"))
        //.append(CSS.Rule.create(CSS.Property.background, "tan"))
        );
        Style.Repo.set(GRID_FULL_SOLID_BLACK_ON_CYAN, CSS.Block.create()
                .append(CSS.Rule.create(CSS.Property.display, "grid"))
                .append(CSS.Rule.create(CSS.Property.padding, "2px"))
                .append(CSS.Rule.create(CSS.Property.margin, "2px"))
                .append(CSS.Rule.create(CSS.Property.border, "solid black 1px"))
                .append(CSS.Rule.create(CSS.Property.border_radius, "3px"))
                .append(CSS.Rule.create(CSS.Property.background, "cyan")));
        Style.Repo.set(TEXT_ALIGN_CENTER_PADDING_2PX, CSS.Block.create().append(CSS.Selector.createClass("panel", "label"))
                .append(CSS.Rule.create(CSS.Property.text_align, "center"))
                .append(CSS.Rule.create(CSS.Property.padding, "2px")));
        Style.Repo.set(ORANGE_BORDER, CSS.Block.create()
                .append(CSS.Rule.create(CSS.Property.border_color, "orange"))
                .append(CSS.Rule.create(CSS.Property.border_width, "2px")));
        Style.Repo.set(DASHED_BORDER, CSS.Block.create()
                .append(CSS.Rule.create(CSS.Property.border_style, "dashed"))
                .append(CSS.Rule.create(CSS.Property.border_width, "2px")));
        Style.Repo.set(ROUNDED_BORDER, CSS.Block.create()
                .append(CSS.Rule.create(CSS.Property.border_radius, "5px"))
                .append(CSS.Rule.create(CSS.Property.border_width, "2px")));
        Style.Repo.set(GRID_WITH_ONE_COLUMN, CSS.Block.create()
                .append(CSS.Rule.create(CSS.Property.display, "grid"))
                .append(CSS.Rule.create(CSS.Property.grid_template_columns, "1fr")));
        Style.Repo.set(GRID_WITH_TWO_COLUMNS, CSS.Block.create()
                .append(CSS.Rule.create(CSS.Property.display, "grid"))
                .append(CSS.Rule.create(CSS.Property.grid_template_columns, "1fr 1fr")));
        Style.Repo.set(GRID_WITH_THREE_COLUMNS, CSS.Block.create()
                .append(CSS.Rule.create(CSS.Property.display, "grid"))
                .append(CSS.Rule.create(CSS.Property.grid_template_columns, "1fr 1fr 1fr")));
        Style.Repo.set(GRID_WITH_FOUR_COLUMNS, CSS.Block.create()
                .append(CSS.Rule.create(CSS.Property.display, "grid"))
                .append(CSS.Rule.create(CSS.Property.grid_template_columns, "1fr 1fr 1fr 1fr")));
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
