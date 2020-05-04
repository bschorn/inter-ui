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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.schorn.ella.ui.html.HTML;
import org.schorn.ella.ui.layout.Build;
import org.schorn.ella.ui.layout.Container;
import org.schorn.ella.ui.layout.Item;
import org.schorn.ella.ui.support.SupportItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author bschorn
 */
abstract class ItemContainerImpl<T extends Item> implements Container<T>, Item, Build {

    static private final Logger LGR = LoggerFactory.getLogger(ItemContainerImpl.class);

    protected final SupportItem support = new SupportItem(LGR);

    private final List<T> items = new ArrayList<>();
    private Exception exception = null;

    ItemContainerImpl(String name, String label) {
        support.properties().put(Item.Properties.ID, UUID.randomUUID().toString());
        support.properties().put(Item.Properties.NAME, name);
        support.properties().put(Item.Properties.LABEL, label);
        support.properties().put(Item.Properties.VISIBLE, Boolean.TRUE);
    }

    @Override
    public void property(Property property, Object value) {
        support.property(property, value);
    }

    @Override
    public <T> T property(Property property, Class<T> classForT) {
        return support.property(property, classForT);
    }

    @Override
    public Object property(Property property) {
        return support.property(property);
    }


    @Override
    public void accept(T item) {
        LGR.debug("{}.accept() - Item: {} -> {}",
                this.getClass().getSimpleName(),
                item.getClass().getSimpleName(),
                item.toString());
        this.items.add(item);
    }

    @Override
    public List<T> items() {
        return Collections.unmodifiableList(this.items);
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

    protected List<String> containerClasses() {
        if (this.name() == null || this.name().equals("none")) {
            return Arrays.asList((new String[]{this.type().className(), "container"}));
        } else {
            return Arrays.asList((new String[]{this.name(), this.type().className(), "container"}));
        }
    }

    protected HTML.Element build0() throws Exception {
        HTML.Div containerElement = HTML.Div.create();
        containerElement.setId(this.id());
        containerClasses().stream()
                .filter(s -> s != null)
                .forEachOrdered(className -> containerElement.addClass(className));
        if (this.label() != null) {
            HTML.Div labelElement = HTML.Div.create();
            HTML.Span span = HTML.Span.create();
            labelElement.append(span);
            span.setId(String.format("%s-label", this.id()));
            labelElement.addClass(this.name());
            labelElement.addClass(this.tag());
            labelElement.addClass("label");
            span.setTextContent(this.label());
            containerElement.append(labelElement);
        }
        for (Item item : this.items()) {
            if (item instanceof Build) {
                Build buildItem = (Build) item;
                Optional<HTML.Element> optElement = buildItem.build();
                buildItem.throwException();
                if (optElement.isPresent()) {
                    containerElement.append(optElement.get());
                }
            }
        }
        /*
        this.items().stream()
                .map(i -> i.build())
                .filter(o -> o.isPresent())
                .map(o -> o.get())
                .forEachOrdered(e -> containerElement.append(e));
         */
        return containerElement;
    }

    @Override
    public String tag() {
        return this.type().className().toLowerCase();
    }

}
