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

import java.util.List;
import org.schorn.ella.ui.html.HTML;
import org.schorn.ella.ui.html.HTML.Element;
import org.schorn.ella.ui.html.HTML.HtmlElement;

/**
 *
 * @author bschorn
 */
public abstract class BaseWidget implements HTML.CustomElement {

    @Override
    public Element setTextContent(String content) {
        this.owner().setTextContent(content);
        return this;
    }

    @Override
    public Element append(Element element) throws HTML.InvalidContentException {
        this.owner().append(element);
        return this;
    }

    @Override
    public Element insert(Element element) throws HTML.InvalidContentException {
        this.owner().insert(element);
        return this;
    }

    @Override
    public Element addAttribute(HTML.Attribute attribute) throws HTML.InvalidAttributeException {
        this.owner().addAttribute(attribute);
        return this;
    }

    @Override
    public List<HTML.Attribute> attributes() {
        return this.owner().attributes();
    }

    @Override
    public Element addClass(String className) {
        this.owner().addClass(className);
        return this;
    }

    @Override
    public HtmlElement setAutoCapitalize(HTML.AutoCapitalize autoCapitalize) {
        this.owner().setAutoCapitalize(autoCapitalize);
        return this;
    }

    @Override
    public HtmlElement setContentEditable(boolean flag) {
        this.owner().setContentEditable(flag);
        return this;
    }

    @Override
    public HtmlElement setDraggable(boolean flag) {
        this.owner().setDraggable(flag);
        return this;
    }

    @Override
    public HtmlElement setHidden(boolean flag) {
        this.owner().setHidden(flag);
        return this;
    }

    @Override
    public HtmlElement setInputMode(HTML.InputMode inputMode) {
        this.owner().setInputMode(inputMode);
        return this;
    }

    @Override
    public HTML.HtmlElement setStyle(HTML.Style style) {
        this.owner().setStyle(style);
        return this;
    }

    @Override
    public Element setId(String id) throws Exception {
        this.owner().setId(id);
        return this;
    }

    @Override
    public String getId() {
        return this.owner().getId();
    }

    @Override
    public String tag() {
        return this.customTag();
    }

    @Override
    public Element parent() {
        return this.owner().parent();
    }

    @Override
    public List<Element> children() {
        return this.owner().children();
    }

    @Override
    public String render() {
        return this.owner().render();
    }

}
