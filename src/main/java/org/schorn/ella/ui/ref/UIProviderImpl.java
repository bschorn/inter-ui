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

import org.schorn.ella.ui.UIProvider;
import org.schorn.ella.ui.html.CSS;
import org.schorn.ella.ui.html.HTML;
import org.schorn.ella.ui.layout.Editor;
import org.schorn.ella.ui.layout.Frame;
import org.schorn.ella.ui.layout.Page;
import org.schorn.ella.ui.layout.Panel;
import org.schorn.ella.ui.layout.Viewer;
import org.schorn.ella.ui.widget.InputWidgets;
import org.schorn.ella.ui.widget.OutputWidgets;

/**
 *
 * @author bschorn
 */
public class UIProviderImpl implements UIProvider {

    @Override
    public Page createPage() {
        return new PageImpl();
    }

    @Override
    public Frame createFrame(String id, String name) {
        return new FrameImpl(id, name);
    }

    @Override
    public Panel createPanel(String id, String name) {
        return new PanelImpl(id, name);
    }

    @Override
    public Viewer createViewer(String id, String name) {
        return new ViewerImpl(id, name);
    }

    @Override
    public Editor createEditor(String id, String name) {
        return new EditorImpl(id, name);
    }

    @Override
    public InputWidgets.InputFactory getInputFactory() {
        return WriteFactoryImpl.getFactory();
    }

    @Override
    public OutputWidgets.ReadFactory getOutputFactory() {
        return ReadFactoryImpl.getFactory();
    }

    @Override
    public HTML.HtmlFactory getHTMLFactory() {
        return HtmlFactoryImpl.getFactory();
    }

    @Override
    public CSS.CssFactory getCSSFactory() {
        return CssFactoryImpl.getFactory();
    }
}
