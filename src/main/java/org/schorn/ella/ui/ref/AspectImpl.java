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
import org.schorn.ella.ui.layout.Aspect;
import org.schorn.ella.ui.widget.Widget;

/**
 *
 * @author bschorn
 */
class AspectImpl implements Aspect {

    private final String containerId;
    private final String formId;
    private final String name;
    private String label;
    private String actionURL = null;
    private HTML.Enctype enctype = null;
    private HTML.Method method = null;
    private HTML.Target target = null;

    private final List<Widget> widgets = new ArrayList<>();
    private Exception exception = null;

    AspectImpl(String name, String label) {
        this.containerId = String.format("aspect-%s-ID", name);
        this.formId = String.format("form-%s-ID", name);
        this.name = name;
        this.label = label;
    }

    @Override
    public String id() {
        return this.containerId;
    }

    @Override
    public String formId() {
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
    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public void accept(Widget widget) {
        this.widgets.add(widget);
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

    protected List<Widget> widgets() {
        return this.widgets;
    }

    protected HTML.Element build0() throws Exception {
        HTML.Div containerElement = HTML.Div.create();
        containerElement.setId(this.id());
        containerElement.addClass(this.name());
        containerElement.addClass(this.type().className());
        HTML.Form formElement = HTML.Form.create();
        containerElement.append(formElement);
        formElement.setId(this.formId);
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

        this.widgets().stream()
                .map(i -> i.build())
                .filter(o -> o.isPresent())
                .map(o -> o.get())
                .forEachOrdered(e -> formElement.append(e));
        return containerElement;
    }

}
