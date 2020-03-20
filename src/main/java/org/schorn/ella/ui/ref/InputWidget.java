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
import org.schorn.ella.ui.html.CSS;
import org.schorn.ella.ui.html.HTML;
import org.schorn.ella.ui.util.ToString;
import org.schorn.ella.ui.widget.Widget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author bschorn
 */
abstract class InputWidget implements Widget {

    static final Logger LGR = LoggerFactory.getLogger(InputWidget.class);

    protected final String customTag;
    protected final List<CSS.Rule> cssRules = new ArrayList<>();
    protected final List<CSS.Block> cssBlocks = new ArrayList<>();
    protected final HTML.Div divElement;
    protected final HTML.Label labelElement;
    protected final HTML.Input inputElement;
    protected HTML.Datalist datalistElement;

    public InputWidget(String customTag, HTML.Input.Type inputType) {
        this.customTag = customTag;
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

    public void addLabel(String label) {
        this.labelElement.setTextContent(label);
    }

    public void setName(String name) {
        try {
            HTML.Attribute attribute = HTML.Attribute.create(HTML.Input.InputAttributes.NAME, name);
            this.inputElement.addAttribute(attribute);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
            //this.owner().append(this.datalistElement);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void addStyle(CSS.Style style) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CSS.Style> getStyles() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HTML.Element build() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String customTag() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
