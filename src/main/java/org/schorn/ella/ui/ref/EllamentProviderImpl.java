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

import org.schorn.ella.ui.html.CSS;
import org.schorn.ella.ui.html.HTML;
import org.schorn.ella.ui.layout.Frame;
import org.schorn.ella.ui.layout.Identifier;
import org.schorn.ella.ui.layout.Style;
import org.schorn.ella.ui.widget.ControlWidgets;
import org.schorn.ella.ui.widget.InputWidgets;
import org.schorn.ella.ui.widget.OutputWidgets;
import org.schorn.ella.ui.layout.Window;
import org.schorn.ella.ui.layout.Pane;
import org.schorn.ella.ui.EllamentProvider;
import org.schorn.ella.ui.layout.Page;

/**
 *
 * @author bschorn
 */
public class EllamentProviderImpl implements EllamentProvider {

    @Override
    public Page createPage() {
        return new PageImpl();
    }

    @Override
    public Frame createFrame(Identifier name) {
        return new FrameImpl(name.toString());
    }
    @Override
    public Frame createFrame(Identifier name, Frame.Intent intent) {
        return new FrameImpl(name.toString(), intent);
    }

    @Override
    public Window createWindow(Identifier name) {
        return new WindowImpl(name.toString(), null);
    }

    @Override
    public Window createWindow(Identifier name, String label) {
        return new WindowImpl(name.toString(), label);
    }

    @Override
    public Pane createPane(Identifier name) {
        return new PaneImpl(name.toString(), null);
    }

    @Override
    public Pane createPane(Identifier name, String label) {
        return new PaneImpl(name.toString(), label);
    }

    @Override
    public InputWidgets.InputFactory getInputFactory() {
        return InputFactoryImpl.getFactory();
    }

    @Override
    public OutputWidgets.OutputFactory getOutputFactory() {
        return OutputFactoryImpl.getFactory();
    }

    @Override
    public ControlWidgets.ControlFactory getControlFactory() {
        return ControlFactoryImpl.getFactory();
    }

    @Override
    public HTML.HtmlFactory getHTMLFactory() {
        return HtmlFactoryImpl.getFactory();
    }

    @Override
    public CSS.CssFactory getCSSFactory() {
        return CssFactoryImpl.getFactory();
    }

    @Override
    public Style.Reset getStyleSheetReset() {
        return StyleSheetResetImpl.getReset();
    }
}
