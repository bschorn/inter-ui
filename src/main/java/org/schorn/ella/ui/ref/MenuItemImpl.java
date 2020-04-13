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
import org.schorn.ella.ui.html.HTML.Attribute;
import org.schorn.ella.ui.widget.ControlWidgets;

/**
 *
 * @author bschorn
 */
class MenuItemImpl extends ControlWidgetImpl implements ControlWidgets.MenuItem {

    private URL menuURL;
    private URL imageURL;

    public MenuItemImpl(String name) {
        super("menu-item", name, null);
    }

    @Override
    public void setAnchor(URL url) {
        this.menuURL = url;
    }

    @Override
    public void setImage(URL url) {
        this.imageURL = url;
    }

    @Override
    protected HTML.Element build0() throws Exception {
        HTML.Li liElement = HTML.Li.create();
        liElement.addClass(this.name());
        liElement.addClass(this.customTag());
        liElement.addClass(this.widgetName());
        HTML.A aElement = HTML.A.create();
        aElement.addAttribute(Attribute.create("href", this.menuURL.toString()));
        liElement.append(aElement);
        HTML.Img imgElement = HTML.Img.create();
        imgElement.addAttribute(Attribute.create("src", this.imageURL.toString()));
        liElement.append(imgElement);
        return liElement;
    }

}
