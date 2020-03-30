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
import org.schorn.ella.ui.html.CSS;
import org.schorn.ella.ui.html.HTML;
import org.schorn.ella.ui.widget.Input;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author bschorn
 */
abstract class InputWidgetImpl implements Input {

    static final Logger LGR = LoggerFactory.getLogger(InputWidgetImpl.class);

    protected final String customTag;
    protected final HTML.Input.InputType inputType;
    protected final String containerId;
    protected final String inputId;
    protected final String name;
    protected final List<String> datalist = new ArrayList<>();
    protected String label = null;
    protected Exception exception = null;

    InputWidgetImpl(String customTag, HTML.Input.InputType inputType, String name, String label) {
        this.customTag = customTag;
        this.inputType = inputType;
        this.containerId = String.format("widget-%s-ID", name);
        this.inputId = String.format("input-%s-ID", name);
        this.name = name;
        this.label = label;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    final public void setDatalist(String[] datalist) {
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
        HTML.Div containerElement = HTML.Div.create();
        containerElement.setId(this.id());
        containerElement.addClass(this.name());
        containerElement.addClass(this.type().className());
        HTML.Input inputElement = HTML.Input.create();
        this.preBuild(inputElement);
        inputElement.addAttribute(this.inputType.asAttribute());
        HTML.Attribute attribute = HTML.Attribute.create(HTML.Input.InputAttributes.NAME, this.name);
        inputElement.addAttribute(attribute);
        if (!this.datalist.isEmpty()) {
            String datalistId = String.format("%s-list", this.containerId);
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
            containerElement.append(labelElement);
        } else {
            containerElement.append(inputElement);
        }

        this.postBuild(inputElement);
        return containerElement;
    }

    protected HTML.Element preBuild(HTML.Element element) {
        return element;
    }

    protected HTML.Element postBuild(HTML.Element element) {
        return element;
    }

    @Override
    public String customTag() {
        return this.customTag;
    }

    @Override
    public String id() {
        return this.containerId;
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
    public void throwException() throws Exception {
        if (this.exception != null) {
            throw this.exception;
        }
    }

    @Override
    public List<CSS.Style> styles() {
        return new ArrayList<>();
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
