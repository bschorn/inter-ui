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
package org.schorn.ella.ui.widget;

import java.net.URL;
import org.schorn.ella.ui.UIProvider;
import org.schorn.ella.ui.html.CSS;
import org.schorn.ella.ui.layout.Style;
import org.schorn.ella.ui.layout.Widget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author bschorn
 */
public enum ControlWidgets {
    MENU,
    MENU_ITEM;

    private Class<?> implOf = null;

    public void setImpl(Class<?> implOf) {
        this.implOf = implOf;
    }

    public Class<?> getImpl() {
        return this.implOf;
    }

    public interface ControlFactory {

        public void register();

        public <T> T createInstance(ControlWidgets output, Object... params) throws Exception;
    }

    private <T> T create(Object... params) throws Exception {
        return (T) FACTORY.createInstance(this, params);
    }

    static final Logger LGR = LoggerFactory.getLogger(ControlWidgets.class);

    static final ControlFactory FACTORY;

    static {
        FACTORY = UIProvider.provider().getControlFactory();
    }

    public interface Control extends Widget {
    }

    public interface Menu extends Control {
        @Override
        default String widgetName() {
            return Menu.class.getSimpleName().toLowerCase();
        }

        public interface MenuItem {

            public MenuItem setAnchor(URL url);

            public MenuItem setImage(URL url);
        }

        public MenuItem addItem(String name, String label);

        static public Menu create(String name) throws Exception {
            return ControlWidgets.MENU.create(name);
        }
        public enum Selector implements Style.Selectors {
            CONTAINER(CSS.Selector.createClass("menu", "control", "widget")),
            MENU(CSS.Selector.create("ul.menu")),
            MENU_ITEM(CSS.Selector.create("ul.menu li")),
            //ITEM_ANCHOR(CSS.Selector.create("ul.menu li a")),
            ANCHOR_IMAGE(CSS.Selector.create("ul.menu li img"));

            private final CSS.Selector selector;

            Selector(CSS.Selector selector) {
                this.selector = selector;
            }

            @Override
            public CSS.Selector selector() {
                return this.selector;
            }

            public CSS.Selector selector(Menu menu) {
                return CSS.Selector.createClass(this.selector.render().substring(1), menu.name());
            }
        }
    }

}
