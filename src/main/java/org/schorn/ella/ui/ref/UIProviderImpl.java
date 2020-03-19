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
import org.schorn.ella.ui.frame.Facet;
import org.schorn.ella.ui.frame.Page;
import org.schorn.ella.ui.frame.Panel;
import org.schorn.ella.ui.html.CSS;
import org.schorn.ella.ui.html.HTML;

/**
 *
 * @author bschorn
 */
public class UIProviderImpl implements UIProvider {

    @Override
    public HTML.HtmlFactory getHTMLFactory() {
        return HTMLImpl.getFactory();
    }

    @Override
    public CSS.CssFactory getCSSFactory() {
        return CSSImpl.getFactory();
    }

    @Override
    public Page createPage(String panelId, String panelName) {
        return new PageImpl(panelId, panelName);
    }

    @Override
    public Panel createPanel(String id, String name) {
        return new PanelImpl(id, name);
    }

    @Override
    public Facet createFacet(String id, String name) {
        return new FacetImpl(id, name);
    }

}
