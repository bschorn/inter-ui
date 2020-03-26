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

import org.schorn.ella.ui.UIProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author bschorn
 */
public enum OutputWidgets {
    CHART,
    IMAGE,
    PROGRESS,
    SCROLL,
    TEXT,
    TITLE;

    private Class<?> implOf = null;

    public void setImpl(Class<?> implOf) {
        this.implOf = implOf;
    }

    public Class<?> getImpl() {
        return this.implOf;
    }

    public interface ReadFactory {

        public void register();

        public <T> T createInstance(OutputWidgets output, Object... params) throws Exception;
    }

    private <T> T create(Object... params) throws Exception {
        return (T) FACTORY.createInstance(this, params);
    }

    static final Logger LGR = LoggerFactory.getLogger(OutputWidgets.class);

    static final ReadFactory FACTORY;

    static {
        FACTORY = UIProvider.provider().getOutputFactory();
    }

    public interface Output extends Widget {

    }

    public interface Chart extends Output {

        static public Chart create(Object... params) throws Exception {
            return OutputWidgets.CHART.create(params);
        }
    }

    public interface Image extends Output {

        static public Image create(Object... params) throws Exception {
            return OutputWidgets.IMAGE.create(params);
        }
    }

    public interface Progress extends Output {

        static public Progress create(Object... params) throws Exception {
            return OutputWidgets.PROGRESS.create(params);
        }
    }

    public interface Scroll extends Output {

        static public Scroll create(Object... params) throws Exception {
            return OutputWidgets.SCROLL.create(params);
        }
    }

    public interface Text extends Output {

        static public Text create(Object... params) throws Exception {
            return OutputWidgets.TEXT.create(params);
        }
    }

    public interface Title extends Output {

        void setTitle(String title);

        static public Title create(Object... params) throws Exception {
            return OutputWidgets.TITLE.create(params);
        }
    }

}
