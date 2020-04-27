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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.schorn.ella.ui.html.HTML;
import org.schorn.ella.ui.layout.Item;
import org.schorn.ella.ui.layout.Widget;
import org.schorn.ella.ui.util.ToString;
import org.schorn.ella.ui.layout.Pane;

/**
 *
 * @author bschorn
 */
class PaneImpl implements Pane {

    private final String id;
    private final String name;
    private String label = null;
    private Map<Item.Property, Object> properties = new HashMap<>();

    private final String formId;
    private String actionURL = null;
    private HTML.Enctype enctype = null;
    private HTML.Method method = null;
    private HTML.Target target = null;

    private final List<Widget> widgets = new ArrayList<>();
    private Exception exception = null;
    private boolean visible = true;

    PaneImpl(String name, String label) {
        this.id = UUID.randomUUID().toString();
        this.formId = UUID.randomUUID().toString();
        this.name = name;
        this.label = label;
    }

    @Override
    public String id() {
        return this.id;
    }

    @Override
    public String paneId() {
        return this.formId;
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
    public void accept(Widget widget) {
        this.widgets.add(widget);
    }

    @Override
    public List<Widget> items() {
        return Collections.unmodifiableList(this.widgets);
    }

    @Override
    public void setAction(String actionURL) {
        this.actionURL = actionURL;
    }

    @Override
    public void setMethod(HTML.Method method) {
        this.method = method;
    }

    @Override
    public void setEnctype(HTML.Enctype enctype) {
        this.enctype = enctype;
    }

    @Override
    public void setTarget(HTML.Target target) {
        this.target = target;
    }

    @Override
    public void throwException() throws Exception {
        if (this.exception != null) {
            throw this.exception;
        }
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

    protected HTML.Element build0() throws Exception {
        HTML.Div containerElement = HTML.Div.create();
        containerElement.setId(this.id());
        containerElement.addClass(this.name());
        containerElement.addClass(this.type().className());
        containerElement.addClass("container");
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

        HTML.Form formElement = HTML.Form.create();
        containerElement.append(formElement);
        formElement.setId(this.formId);
        formElement.addClass(this.name());
        formElement.addClass(this.type().className());
        formElement.addClass("entity");
        if (this.actionURL != null) {
            formElement.setAction(this.actionURL);
        }
        if (this.enctype != null) {
            formElement.setEnctype(this.enctype);
        }
        if (this.method != null) {
            formElement.setMethod(this.method);
        }
        if (this.target != null) {
            formElement.setTarget(this.target);
        }

        for (Widget widget : this.widgets) {
            Optional<HTML.Element> optElement = widget.build();
            if (optElement.isPresent()) {
                HTML.Element element = optElement.get();
                formElement.append(element);
            } else {
                try {
                    widget.throwException();
                } catch (Exception ex) {
                    HTML.Div exElement = HTML.Div.create();
                    exElement.addClass("widget");
                    exElement.addClass("exception");
                    exElement.setTextContent(widget.label());
                    HTML.P pElement = HTML.P.create();
                    exElement.append(pElement);
                    pElement.setTextContent(ToString.stackTrace(ex));
                    formElement.append(exElement);
                }
            }
        }
        return containerElement;
    }

    protected List<Widget> widgets() {
        return this.widgets;
    }

}
