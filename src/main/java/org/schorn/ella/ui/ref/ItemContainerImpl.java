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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.schorn.ella.ui.html.HTML;
import org.schorn.ella.ui.layout.Container;
import org.schorn.ella.ui.layout.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author bschorn
 */
abstract class ItemContainerImpl implements Container<Item>, Item {

    static private final Logger LGR = LoggerFactory.getLogger(ItemContainerImpl.class);

    private final String id;
    private final String name;
    private String label;
    protected final Map<Item.Property, Object> properties = new HashMap<>();

    private final List<Item> items = new ArrayList<>();
    private Exception exception = null;
    private boolean visible = true;

    ItemContainerImpl(String name, String label) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.label = label;
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
    public String label() {
        return this.label;
    }

    @Override
    public void property(Property property, Object value) {
        if (property instanceof Properties) {
            switch ((Properties) property) {
                case LABEL:
                    this.label = (String) value;
                    break;
            }
        } else {
            this.properties.put(property, value);
        }
    }

    @Override
    public <T> T property(Property property, Class<T> classForT) {
        if (property instanceof Properties) {
            switch ((Properties) property) {
                case LABEL:
                    return (T) classForT.cast(this.label);
            }
        } else {
            Object value = this.properties.get(property);
            if (value != null) {
                if (property.classType().equals(classForT)
                        && classForT.isInstance(value)) {
                    return (T) value;
                }
            }
        }
        return null;
    }

    @Override
    public void accept(Item item) {
        LGR.debug("{}.accept() - Item: {} -> {}",
                this.getClass().getSimpleName(),
                item.getClass().getSimpleName(),
                item.toString());
        this.items.add(item);
    }

    @Override
    public List<Item> items() {
        return Collections.unmodifiableList(this.items);
    }

    @Override
    public boolean visible() {
        return this.visible;
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
        if (this.label != null) {
            HTML.Div labelElement = HTML.Div.create();
            HTML.Span span = HTML.Span.create();
            labelElement.append(span);
            span.setId(String.format("%s-label", this.id()));
            labelElement.addClass(this.name());
            labelElement.addClass(this.type().className());
            labelElement.addClass("label");
            span.setTextContent(this.label());
            containerElement.append(labelElement);
        }
        for (Item item : this.items()) {
            Optional<HTML.Element> optElement = item.build();
            item.throwException();
            if (optElement.isPresent()) {
                containerElement.append(optElement.get());
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
}
