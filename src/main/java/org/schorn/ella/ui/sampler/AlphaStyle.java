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
package org.schorn.ella.ui.sampler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.schorn.ella.ui.html.CSS;
import org.schorn.ella.ui.style.FontStyles;
import org.schorn.ella.ui.style.FrameStyles;
import org.schorn.ella.ui.style.GenericStyles;
import org.schorn.ella.ui.style.PanelStyles;
import org.schorn.ella.ui.style.StyleSheet;
import org.schorn.ella.ui.style.WidgetStyles;

/**
 *
 * @author bschorn
 */
public final class AlphaStyle implements StyleSheet {

    private final List<CSS.Style> styles = new ArrayList<>();

    public AlphaStyle() {
        this.reset();
        this.add(FontStyles.ARIAL85);
        this.add(GenericStyles.GLOBAL_BOX_SIZING_BORDER_BOX);
        this.add(FrameStyles.DEBUG);
        this.add(PanelStyles.DEBUG);
        this.add(WidgetStyles.LABEL_OVER_VALUE);
    }

    @Override
    public List<CSS.Style> styles() {
        return this.styles;
    }

    @Override
    public String toString() {
        return this.styles.stream().map(s -> s.render()).collect(Collectors.joining("\n"));
    }
}
