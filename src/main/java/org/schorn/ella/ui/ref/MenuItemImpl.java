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

import java.net.URL;
import org.schorn.ella.ui.html.HTML;
import org.schorn.ella.ui.widget.ControlWidgets.MenuItem;

/**
 *
 * @author bschorn
 */
class MenuItemImpl extends ControlWidgetImpl implements MenuItem {

    private URL menuURL = null;
    private URL imageURL = null;

    public MenuItemImpl(String name, String label) {
        super("menu-item", name, label);
    }

    @Override
    public MenuItem setAnchor(URL url) {
        this.menuURL = url;
        return this;
    }

    URL getAnchor() {
        return this.menuURL;
    }

    @Override
    public MenuItem setImage(URL url) {
        this.imageURL = url;
        return this;
    }

    URL getImage() {
        return this.imageURL;
    }

    @Override
    protected HTML.Element build0() throws Exception {
        HTML.Li liElement = HTML.Li.create();
        URL aurl = this.getAnchor();
        URL imgurl = this.getImage();
        //HTML.A aElement = HTML.A.create();
        //liElement.append(aElement);
        if (aurl != null) {
            //aElement.addAttribute(Attribute.create("href", aurl.toString()));
            liElement.addAttribute(HTML.Attribute.create("data-href", aurl.toString()));
        }
        if (imgurl != null) {
            HTML.Img imgElement = HTML.Img.create();
            imgElement.addAttribute(HTML.Attribute.create("src", imgurl.toString()));
            liElement.append(imgElement);
        }
        if (this.label() != null) {
            HTML.Span spanElement = HTML.Span.create();
            spanElement.setTextContent(this.label());
            liElement.append(spanElement);
        }
        return liElement;
    }

}
