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
import java.util.ArrayList;
import java.util.List;
import org.schorn.ella.ui.html.HTML;
import org.schorn.ella.ui.html.HTML.Attribute;
import org.schorn.ella.ui.widget.ControlWidgets;


/**
 *
 * @author bschorn
 */
class MenuImpl extends ControlWidgetImpl implements ControlWidgets.Menu {

    private final List<MenuItemImpl> items = new ArrayList<>();

    public MenuImpl(String name) {
        super("menu", name, null);
    }

    @Override
    protected HTML.Element build0() throws Exception {
        HTML.Ul ulElement = HTML.Ul.create();
        ulElement.addClass(this.name());
        ulElement.addClass(this.customTag());
        ulElement.addClass(this.widgetName());
        for (MenuItemImpl menuItem : this.items) {
            HTML.Li liElement = HTML.Li.create();
            URL aurl = menuItem.getAnchor();
            URL imgurl = menuItem.getImage();
            //HTML.A aElement = HTML.A.create();
            //liElement.append(aElement);
            if (aurl != null) {
                //aElement.addAttribute(Attribute.create("href", aurl.toString()));
                liElement.addAttribute(Attribute.create("data-href", aurl.toString()));
            }
            if (imgurl != null) {
                HTML.Img imgElement = HTML.Img.create();
                imgElement.addAttribute(Attribute.create("src", imgurl.toString()));
                liElement.append(imgElement);
            }
            if (menuItem.label() != null) {
                HTML.Span spanElement = HTML.Span.create();
                spanElement.setTextContent(menuItem.label());
                liElement.append(spanElement);
            }
            ulElement.append(liElement);
        }
        return ulElement;
    }

    @Override
    public MenuItem addItem(String name, String label) {
        MenuItemImpl menuItem = new MenuItemImpl(name, label);
        this.items.add(menuItem);
        return menuItem;
    }

    class MenuItemImpl implements ControlWidgets.Menu.MenuItem {

        private final String name;
        private final String label;
        private URL menuURL = null;
        private URL imageURL = null;

        public MenuItemImpl(String name, String label) {
            this.name = name;
            this.label = label;
        }

        public String name() {
            return this.name;
        }

        public String label() {
            return this.label;
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

    }
}
