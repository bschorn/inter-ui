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

import java.util.ArrayList;
import java.util.List;
import org.schorn.ella.ui.html.HTML;
import org.schorn.ella.ui.widget.ControlWidgets;
import org.schorn.ella.ui.widget.ControlWidgets.MenuItem;


/**
 *
 * @author bschorn
 */
class MenuImpl extends ControlWidgetImpl implements ControlWidgets.Menu {

    private final List<MenuItem> items = new ArrayList<>();

    public MenuImpl(String name) {
        super("menu", name, null);
    }

    @Override
    protected HTML.Element build0() throws Exception {
        HTML.Ul ulElement = HTML.Ul.create();
        ulElement.addClass(this.name());
        ulElement.addClass(this.customTag());
        ulElement.addClass(this.widgetName());
        this.items.stream()
                .map(mi -> mi.build())
                .filter(opt -> opt.isPresent())
                .map(opt -> opt.get())
                .forEachOrdered(el -> ulElement.append(el));
        return ulElement;
    }

    @Override
    public ControlWidgets.Menu addItem(MenuItem menuItem) {
        //MenuItemImpl menuItem = new MenuItemImpl(name, label);
        this.items.add(menuItem);
        return this;
    }

}
