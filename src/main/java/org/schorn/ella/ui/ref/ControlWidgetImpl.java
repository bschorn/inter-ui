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
import java.util.UUID;
import org.schorn.ella.ui.html.HTML;
import org.schorn.ella.ui.widget.Control;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author bschorn
 */
abstract class ControlWidgetImpl implements Control {

    static private final Logger LGR = LoggerFactory.getLogger(ControlWidgetImpl.class);

    protected final String customTag;
    protected final String widgetId = UUID.randomUUID().toString();
    protected final String id = UUID.randomUUID().toString();
    protected final String name;
    protected final List<String> datalist = new ArrayList<>();
    protected String label = null;
    protected Exception exception = null;

    ControlWidgetImpl(String customTag, String name, String label) {
        this.customTag = customTag;
        this.name = name;
        this.label = label;
    }

    @Override
    public String customTag() {
        return this.customTag;
    }

    @Override
    public String widgetId() {
        return this.widgetId;
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
    public void setLabel(String label) {
        this.label = label;
    }

    public void addLabel(String label) {
        this.label = label;
    }

    @Override
    public Optional<HTML.Element> build() {
        HTML.Div divElement = null;
        try {
            divElement = HTML.Div.create();
            divElement.setId(this.id());
            divElement.addClass(this.name());
            divElement.addClass(this.widgetName());
            divElement.addClass(this.customTag());
            divElement.addClass("control");
            divElement.addClass(this.type().className());
            HTML.Element element = this.build0();
            element.setId(this.widgetId());
            divElement.append(element);
        } catch (Exception ex) {
            this.exception = ex;
        }
        return Optional.ofNullable(divElement);
    }

    abstract protected HTML.Element build0() throws Exception;

    @Override
    public void throwException() throws Exception {
        if (this.exception != null) {
            throw this.exception;
        }
    }

}
