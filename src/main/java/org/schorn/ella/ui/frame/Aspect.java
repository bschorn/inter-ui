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
package org.schorn.ella.ui.frame;

import org.schorn.ella.ui.widget.Widget;

/**
 *
 * @author bschorn
 */
public interface Aspect extends Frame<Widget>, Style, Build {

    /*
    static public TitleWidget createTitle(String parentId, String title) throws HTML.InvalidContentException {
        TitleWidget titleWidget = new TitleWidget(title);
        //titleWidget.setId(parentId);
        return titleWidget;
    }

    static public InputWidget createTextBox(String parentId, String name, String label) throws Exception {
        InputWidget formInput = new InputWidget(HTML.Input.Type.TEXT);
        formInput.setId(String.format("%s.%s", parentId, name));
        formInput.addLabel(label);
        formInput.setName(name);
        return formInput;
    }

    static public InputWidget createComboBox(String parentId, String name, String label, String optionId, String[] options) throws Exception {
        InputWidget formInput = new InputWidget(HTML.Input.Type.TEXT);
        formInput.setId(String.format("%s.%s", parentId, name));
        formInput.addLabel(label);
        formInput.setName(name);
        formInput.addDatalist(optionId, options);
        return formInput;
    }
     */
}
