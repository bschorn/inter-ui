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
package org.schorn.ella.ui.facet;

import java.util.HashMap;
import java.util.Map;
import org.schorn.ella.ui.html.HTML;
import org.schorn.ella.ui.ref.PageImpl;
import org.schorn.ella.ui.widget.InputWidget;
import org.schorn.ella.ui.widget.TitleWidget;

/**
 *
 * @author bschorn
 */
public class AddressPanel extends Panel {

    private final String customTag = "form-address";
    private final HTML.Div divElement;
    private final HTML.Form form;
    private final String id;
    private final String name;
    private final Map<String, String> formIds = new HashMap<>();

    public AddressPanel(String id, String name) throws Exception {
        this.id = id;
        this.name = name;
        this.divElement = HTML.Div.create();
        this.form = HTML.Form.create();
        this.form.setId(id);
        this.divElement.append(this.form);
    }

    public void addTitle(String title) throws HTML.InvalidContentException {
        TitleWidget titleWidget = new TitleWidget("Customer Address");
        this.divElement.insert(titleWidget);
    }

    public void addText(String name, String label) throws Exception {
        InputWidget formInput = new InputWidget(HTML.Input.Type.TEXT);
        formInput.setId(String.format("%s.%s", this.id, name));
        formInput.addLabel(label);
        formInput.setName(name);
        this.form.append(formInput);
    }
    public void addList(String name, String label, String optionId, String[] options) throws Exception {
        InputWidget formInput = new InputWidget(HTML.Input.Type.TEXT);
        formInput.setId(String.format("%s.%s", this.id, name));
        formInput.addLabel(label);
        formInput.setName(name);
        formInput.addDatalist(optionId, options);
        this.form.append(formInput);
    }

    @Override
    public String render() {
        return this.divElement.render();
    }

    static public void main(String[] args) {
        try {
            PageImpl page = new PageImpl();
            AddressPanel formAddress = new AddressPanel("Form.Address.00", "Address");
            page.addContent(formAddress);
            formAddress.addTitle("Customer Address");
            formAddress.addText("streetAddress", "Street Address");
            formAddress.addList("city", "City", "cities", new String[]{"Chicago", "Houston", "New York", "San Francisco"});
            System.out.println(page.render());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
