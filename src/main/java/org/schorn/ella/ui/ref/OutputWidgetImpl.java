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
import org.schorn.ella.ui.widget.Output;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author bschorn
 */
abstract class OutputWidgetImpl implements Output {

    static private final Logger LGR = LoggerFactory.getLogger(OutputWidgetImpl.class);

    protected final String customTag;
    protected final String id;
    protected final String name;
    protected final List<String> datalist = new ArrayList<>();
    protected String label = null;
    protected Exception exception = null;

    OutputWidgetImpl(String customTag, String name, String label) {
        this.customTag = customTag;
        this.id = String.format("%s_ID", name);
        this.name = name;
        this.label = label;
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

    abstract HTML.Element build0() throws Exception;

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
    public String label() {
        return this.label;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public void throwException() throws Exception {
        if (this.exception != null) {
            throw this.exception;
        }
    }

}
