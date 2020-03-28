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
import java.util.Optional;
import org.schorn.ella.ui.html.HTML;
import org.schorn.ella.ui.layout.Container;
import org.schorn.ella.ui.layout.Item;

/**
 *
 * @author bschorn
 */
abstract class ItemContainerImpl implements Container<Item>, Item {

    private final String id;
    private final String name;
    private final List<Item> items = new ArrayList<>();
    private Exception exception = null;

    ItemContainerImpl(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String id() {
        return this.id;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public void accept(Item item) {
        this.items.add(item);
    }

    @Override
    public Optional<HTML.Element> build() {
        HTML.Element element = null;
        try {
            element = this.build0();
        } catch (Exception ex) {
            this.exception = ex;
        }
        return Optional.ofNullable(element);
    }

    @Override
    public void throwException() throws Exception {
        if (this.exception != null) {
            throw this.exception;
        }
    }

    protected List<Item> items() {
        return this.items;
    }

    protected HTML.Element build0() throws Exception {
        HTML.Div containerElement = HTML.Div.create();
        containerElement.setId(this.id());
        containerElement.addClass(this.name());
        containerElement.addClass(this.type().className());
        this.items().stream()
                .map(i -> i.build())
                .filter(o -> o.isPresent())
                .map(o -> o.get())
                .forEachOrdered(e -> containerElement.append(e));
        return containerElement;
    }
}
