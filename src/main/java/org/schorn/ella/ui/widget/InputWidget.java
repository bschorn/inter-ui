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

import java.util.ArrayList;
import java.util.List;
import org.schorn.ella.ui.html.HTML;
import org.schorn.ella.ui.html.HTML.Element;
import org.schorn.ella.ui.util.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author bschorn
 */
public class InputWidget extends BaseWidget {

    static final Logger LGR = LoggerFactory.getLogger(InputWidget.class);

    private final String customTag = "form-input";
    private final HTML.Div divElement;
    private final HTML.Label labelElement;
    private final HTML.Input inputElement;
    private HTML.Datalist datalistElement;

    /*
<div class="preference">
    <label>Do you like cheese?
        <input type="checkbox" name="cheese" id="cheese">
    </label>
</div>
     */
    public InputWidget(HTML.Input.Type inputType) {
        HTML.Div divElement0 = null;
        HTML.Label labelElement0 = null;
        HTML.Input inputElement0 = null;
        try {
            divElement0 = HTML.Div.create();
            labelElement0 = HTML.Label.create();
            inputElement0 = HTML.Input.create();
            inputElement0.addAttribute(inputType.asAttribute());
        } catch (Exception ex) {
            LGR.error("{}.ctor() - Caught Exception: {}",
                    this.getClass().getSimpleName(),
                    ToString.stackTrace(ex));
        }
        this.divElement = divElement0;
        this.labelElement = labelElement0;
        this.inputElement = inputElement0;
        try {
            this.divElement.append(this.labelElement);
            this.labelElement.append(this.inputElement);
        } catch (HTML.InvalidContentException ex) {
            LGR.error("{}.ctor() - Caught InvalidContentException: {}",
                    this.getClass().getSimpleName(),
                    ToString.stackTrace(ex));
        }

    }

    @Override
    public String customTag() {
        return this.customTag;
    }

    @Override
    public Element owner() {
        return this.divElement;
    }

    public Element addLabel(String label) {
        return this.labelElement.setTextContent(label);
    }

    @Override
    public Element setTextContent(String content) {
        this.labelElement.setTextContent(content);
        return this;
    }

    @Override
    public Element addAttribute(HTML.Attribute attribute) throws HTML.InvalidAttributeException {
        this.inputElement.addAttribute(attribute);
        return this;
    }

    @Override
    public Element setId(String value) throws Exception {
        this.inputElement.setId(value);
        return this;
    }

    @Override
    public String getId() {
        return this.inputElement.getId();
    }

    @Override
    public List<Element> children() {
        return new ArrayList<>(0);
    }

    public Element setName(String name) {
        try {
            HTML.Attribute attribute = HTML.Attribute.create(HTML.Input.InputAttributes.NAME, name);
            this.inputElement.addAttribute(attribute);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return this;
    }

    @Override
    public List<HTML.Attribute> attributes() {
        return this.inputElement.attributes();
    }

    @Override
    public Element setAutoCapitalize(HTML.AutoCapitalize autoCapitalize) {
        this.inputElement.setAutoCapitalize(autoCapitalize);
        return this;
    }

    @Override
    public Element setContentEditable(boolean flag) {
        this.inputElement.setContentEditable(flag);
        return this;
    }

    @Override
    public Element setDraggable(boolean flag) {
        this.inputElement.setDraggable(flag);
        return this;
    }

    @Override
    public Element setInputMode(HTML.InputMode inputMode) {
        this.inputElement.setInputMode(inputMode);
        return this;
    }

    public void addDatalist(String id, String[] datalist) {
        try {
            this.datalistElement = HTML.Datalist.create();
            for (String optionStr : datalist) {
                HTML.Option option = HTML.Option.create();
                option.addAttribute(HTML.Attribute.create("value", optionStr));
                this.datalistElement.append(option);
            }
            this.inputElement.addAttribute(HTML.Attribute.create("list", id));
            this.datalistElement.setId(id);
            this.owner().append(this.datalistElement);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
