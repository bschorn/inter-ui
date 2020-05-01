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
package org.schorn.ella.ui.app;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.schorn.ella.ui.html.HTML;
import org.schorn.ella.ui.layout.Item;
import org.schorn.ella.ui.layout.Type;
import org.schorn.ella.ui.util.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author bschorn
 */
public abstract class ScreenComponent implements App.Screen.Component {

    static final Logger LGR = LoggerFactory.getLogger(ScreenComponent.class);

    private final String id;
    private final String name;
    private String label;
    private boolean visible;

    protected final Map<Item.Property, Object> properties = new HashMap<>();

    private Exception exception = null;

    public ScreenComponent(String id, String name, String label, boolean visible) {
        this.id = id;
        this.name = name;
        this.label = label;
        this.visible = visible;
    }

    @Override
    public Type type() {
        return Type.ITEM;
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
    public boolean visible() {
        return this.visible;
    }

    @Override
    public String label() {
        return this.label;
    }

    @Override
    public void property(Property property, Object value) {
        if (property != null) {
            if (property instanceof Properties) {
                switch ((Properties) property) {
                    case LABEL:
                        this.label = (String) value;
                        break;
                    case VISIBILE:
                        this.visible = (Boolean) value;
                }
            } else {
                this.properties.put(property, value);
            }
        }
    }

    @Override
    public <T> T property(Property property, Class<T> classForT) {
        if (property != null) {
            if (property instanceof Properties) {
                switch ((Properties) property) {
                    case LABEL:
                        return (T) classForT.cast(this.label);
                    case VISIBILE:
                        return (T) classForT.cast(this.visible);
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
        }
        return null;
    }

    @Override
    public void throwException() throws Exception {
        if (this.exception != null) {
            throw this.exception;
        }
    }

    @Override
    public Optional<HTML.Element> build() {
        HTML.Div div = null;
        try {
            div = HTML.Div.create();
            div.setId(this.id());
            div.addClass(this.name());
            div.addClass("app-screen-component");
            this.build0(div);

        } catch (Exception ex) {
            LGR.error("{}.build() - Caught Exception: {}",
                    this.getClass().getSimpleName(),
                    ToString.stackTrace(ex));
        }
        return Optional.of(div);
    }

    abstract protected void build0(HTML.Element element) throws Exception;
}
