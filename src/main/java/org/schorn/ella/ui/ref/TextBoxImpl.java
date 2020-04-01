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

import java.util.regex.Pattern;
import org.schorn.ella.ui.html.HTML;
import org.schorn.ella.ui.widget.InputWidgets;

/**
 *
 * @author bschorn
 */
final class TextBoxImpl extends InputWidgetImpl implements InputWidgets.TextBox {

    private Pattern pattern;
    private Integer minLength = null;
    private Integer maxLength = null;
    private Integer size = null;
    private Boolean spellcheck = null;

    public TextBoxImpl(String name, String label, Pattern pattern) {
        super("inter-textbox", HTML.Input.InputType.TEXT, name, label);
        this.setPattern(pattern);
    }

    @Override
    public Integer minLength() {
        return this.minLength;
    }

    @Override
    public Integer maxLength() {
        return this.maxLength;
    }

    @Override
    public Pattern pattern() {
        return this.pattern;
    }

    @Override
    public Integer size() {
        return this.size;
    }

    @Override
    public Boolean isSpellcheck() {
        return this.spellcheck;
    }

    @Override
    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }
    @Override
    public void setLength(int minLength, int maxLength) {
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public void setSpellcheck(boolean spellcheck) {
        this.spellcheck = spellcheck;
    }

    @Override
    protected HTML.Element postBuild(HTML.Element element) {
        if (element instanceof HTML.Input) {
            HTML.Input inputElement = (HTML.Input) element;
            if (this.pattern() != null) {
                inputElement.setPattern(this.pattern());
            }
            if (this.minLength() != null) {
                inputElement.setMinLength(this.minLength());
            }
            if (this.maxLength() != null) {
                inputElement.setMaxLength(this.maxLength());
            }
            if (this.size() != null) {
                inputElement.setSize(this.size());
            }
            if (this.isSpellcheck() != null) {
                inputElement.setSpellcheck(this.isSpellcheck());
            }
        }
        return element;
    }

}
