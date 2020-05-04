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

import java.util.Optional;
import org.schorn.ella.ui.html.CSS;
import org.schorn.ella.ui.html.HTML;
import org.schorn.ella.ui.layout.Frame;
import org.schorn.ella.ui.layout.Page;
import org.schorn.ella.ui.layout.Style;

/**
 *
 * @author bschorn
 */
class PageImpl extends ItemContainerImpl<Frame> implements Page {

    private String title = "stage";
    private boolean isViewport = false;
    private String viewportWidth = "";
    private String viewportScale = "";

    public PageImpl() {
        super("", "");
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setViewport(String width, String scale) {
        this.isViewport = true;
        this.viewportWidth = width;
        this.viewportScale = scale;
    }

    @Override
    public String produce(Style styleSheet) throws Exception {
        Optional<HTML.Element> optPageElement = this.build();
        this.throwException();
        if (optPageElement.isPresent()) {
            HTML.Page pageElement = (HTML.Page) optPageElement.get();
            HTML.Style style = HTML.Style.create();
            pageElement.append(style);
            for (CSS.Style cssStyle : styleSheet.styles()) {
                if (cssStyle instanceof CSS.Block) {
                    CSS.Block cssBlock = (CSS.Block) cssStyle;
                    style.append(cssBlock);
                }
            }
            /*
            styleSheet.styles().stream()
                    .filter(s -> s instanceof CSS.Block)
                    .map(s -> (CSS.Block) s)
                    .forEachOrdered(b -> style.append(b));
             */
            return pageElement.render();
        }
        return "<html><body><p>ERROR<p></body></html>";
    }

    @Override
    protected HTML.Element build0() throws Exception {
        HTML.Page pageElement = HTML.Page.create();
        if (this.isViewport) {
            HTML.Meta metaViewport = HTML.Meta.createViewport(this.viewportWidth, this.viewportScale);
            pageElement.append(metaViewport);
        }
        HTML.Title titleElement = HTML.Title.create();
        titleElement.setTextContent(this.title);
        pageElement.append(titleElement);

        for (Frame item : this.items()) {
            Optional<HTML.Element> optElement = item.build();
            item.throwException();
            if (optElement.isPresent()) {
                pageElement.append(optElement.get());
            }
        }
        return pageElement;
    }

    @Override
    public boolean visible() {
        return true;
    }

}

