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

import java.util.function.Function;
import org.schorn.ella.ui.html.CSS;
import org.schorn.ella.ui.html.HTML;
import org.schorn.ella.ui.layout.Frame;

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
    static public final CSS.Style BODY_FONT_01;
    static public final CSS.Style GLOBAL_BORDER_BOX;
    static public final CSS.Style FRAME;
    static public final CSS.Style PANEL;
    static public final CSS.Style VALUE;
    static public final PositionedFrameStyler POSITIONED_FRAME_STYLER = new PositionedFrameStyler();

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

        CSS.Rule bodyFont01 = null;
        try {
            bodyFont01 = CSS.Rule.create(CSS.Property.font, ".85em Arial, sans-serif");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        BODY_FONT_01 = bodyFont01;

        CSS.Block globalBorderBox = null;
        try {
            globalBorderBox = CSS.Block.create().append(CSS.Selector.create("*"))
                    .append(CSS.Rule.create(CSS.Property.box_sizing, "border-box"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        GLOBAL_BORDER_BOX = globalBorderBox;

        CSS.Block frame = null;
        try {
            frame = CSS.Block.create().append(CSS.Selector.createClass("frame"))
                    .append(CSS.Rule.create(CSS.Property.display, "inline-block"))
                    .append(CSS.Rule.create(CSS.Property.width, "inline-block"))
                    .append(CSS.Rule.create(CSS.Property.height, "inline-block"))
                    .append(CSS.Rule.create(CSS.Property.border, "dashed lightgray 2px"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        FRAME = frame;

        CSS.Block panel = null;
        try {
            panel = CSS.Block.create().append(CSS.Selector.createClass("panel"))
                    .append(CSS.Rule.create(CSS.Property.display, "inline-grid"))
                    .append(CSS.Rule.create(CSS.Property.padding, "10px"))
                    .append(CSS.Rule.create(CSS.Property.border, "solid white 1px"))
                    .append(CSS.Rule.create(CSS.Property.border_radius, "5px"))
                    .append(CSS.Rule.create(CSS.Property.background, "tan"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        PANEL = panel;

        CSS.Block value = null;
        try {
            value = CSS.Block.create().append(CSS.Selector.createClass("value"))
                    .append(CSS.Rule.create(CSS.Property.border, "solid grey 1px"))
                    .append(CSS.Rule.create(CSS.Property.color, "black"))
                    .append(CSS.Rule.create(CSS.Property.background, "white"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        VALUE = value;
    }

    static class PositionedFrameStyler implements Function<Frame, CSS.Block> {

        public CSS.Block apply(Frame frame) {
            try {
                return CSS.Block.create()
                        .append(CSS.Selector.createID(frame.id()))
                        .append(CSS.Rule.create(CSS.Property.position, "absolute"));

            } catch (Exception ex) {
                return CSS.Block.create();
            }
        }

    }

}
