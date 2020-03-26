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
import java.util.List;
import java.util.Optional;
import org.schorn.ella.ui.html.HTML;
import org.schorn.ella.ui.style.StyleComponent;
import org.schorn.ella.ui.widget.Widget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author bschorn
 */
class OutputWidgetImpl implements Widget {

    static final Logger LGR = LoggerFactory.getLogger(OutputWidgetImpl.class);

    protected final String customTag;
    protected final String id;
    protected final String name;
    protected final List<String> datalist = new ArrayList<>();
    protected String label = null;
    protected Exception exception = null;

    OutputWidgetImpl(String customTag, String id, String name) {
        this.customTag = customTag;
        this.id = id;
        this.name = name;
    }

    public void addLabel(String label) {
        this.label = label;
    }

    public void addDatalist(String[] datalist) {
        this.datalist.addAll(Arrays.asList(datalist));
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

        HTML.Div divElement = HTML.Div.create();
        HTML.Input inputElement = HTML.Input.create();
        //inputElement.addAttribute(this.inputType.asAttribute());
        HTML.Attribute attribute = HTML.Attribute.create(HTML.Input.InputAttributes.NAME, this.name);
        inputElement.addAttribute(attribute);
        if (!this.datalist.isEmpty()) {
            String datalistId = String.format("%s-list", this.id);
            HTML.Datalist datalistElement = HTML.Datalist.create();
            datalistElement.setId(datalistId);
            for (String optionStr : this.datalist) {
                HTML.Option option = HTML.Option.create();
                option.addAttribute(HTML.Attribute.create("value", optionStr));
                datalistElement.append(option);
            }
            inputElement.addAttribute(HTML.Attribute.create("list", datalistId));
            inputElement.append(datalistElement);
        }
        if (this.label != null) {
            HTML.Label labelElement = HTML.Label.create();
            labelElement.append(inputElement);
            labelElement.setTextContent(this.label);
            labelElement.append(inputElement);
            divElement.append(labelElement);
        } else {
            divElement.append(inputElement);
        }

        return divElement;
    }

    @Override
    public String customTag() {
        return this.customTag;
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
    public void throwException() throws Exception {
        if (this.exception != null) {
            throw this.exception;
        }
    }

    @Override
    public StyleComponent styleComponent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /*
    @Override
    public void addStyle(CSS.Style style) {
        this.styles.add(style);
    }

    @Override
    public List<CSS.Style> styles() {
        List<CSS.Style> cssStyles = new ArrayList<>();
        cssStyles.addAll(this.styles.stream()
                .filter(css -> css instanceof CSS.Block)
                .collect(Collectors.toList()));

        final CSS.Block cssBlock = CSS.Block.create();
        cssBlock.append(CSS.Selector.createID(this.id));
        this.styles.stream()
                .filter(css -> css instanceof CSS.Rule)
                .map(css -> (CSS.Rule) css)
                .forEachOrdered(cssr -> cssBlock.append(cssr));
        cssStyles.add(cssBlock);

        return cssStyles;
    }
     */
}
