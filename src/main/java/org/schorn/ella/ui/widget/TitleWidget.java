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

import org.schorn.ella.ui.html.HTML;

/**
 *
 * @author bschorn
 */
public class TitleWidget extends BaseWidget {

    private final String customTag = "form-title";
    private String title;
    private final HTML.Div divElement;

    public TitleWidget(String title) {
        this.title = title;
        HTML.Div divElement0 = null;
        try {
            divElement0 = HTML.Div.create();
            divElement0.setTextContent(title);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        this.divElement = divElement0;
    }

    public void setTitle(String title) {
        this.title = title;
        this.divElement.setTextContent(title);
    }

    public String getTitle() {
        return this.title;
    }


    @Override
    public String customTag() {
        return this.customTag;
    }

    @Override
    public HTML.HtmlElement owner() {
        return this.divElement;
    }

    @Override
    public HTML.HtmlElement setStyle(HTML.Style style) {
        this.divElement.setStyle(style);
        return this;
    }

}
