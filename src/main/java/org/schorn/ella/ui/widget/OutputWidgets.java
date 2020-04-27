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
import org.schorn.ella.ui.layout.Widget;
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

    public interface OutputFactory {

        public void register();

        public <T> T createInstance(OutputWidgets output, Object... params) throws Exception;
    }

    private <T> T create(Object... params) throws Exception {
        return (T) FACTORY.createInstance(this, params);
    }

    static final Logger LGR = LoggerFactory.getLogger(OutputWidgets.class);

    static final OutputFactory FACTORY;

    static {
        FACTORY = UIProvider.provider().getOutputFactory();
    }

    public interface Chart extends Widget.Output {
        @Override
        default String widgetName() {
            return Chart.class.getSimpleName().toLowerCase();
        }

        static public Chart create(String name, String url) throws Exception {
            return OutputWidgets.CHART.create(name, url);
        }
    }

    public interface Image extends Widget.Output {
        @Override
        default String widgetName() {
            return Image.class.getSimpleName().toLowerCase();
        }

        static public Image create(String name, String url) throws Exception {
            return OutputWidgets.IMAGE.create(name, url);
        }
    }

    public interface Progress extends Widget.Output {
        @Override
        default String widgetName() {
            return Progress.class.getSimpleName().toLowerCase();
        }

        static public Progress create(String name) throws Exception {
            return OutputWidgets.PROGRESS.create(name);
        }
    }

    public interface Scroll extends Widget.Output {
        @Override
        default String widgetName() {
            return Scroll.class.getSimpleName().toLowerCase();
        }

        static public Scroll create(String name) throws Exception {
            return OutputWidgets.SCROLL.create(name);
        }
    }

    public interface Text extends Widget.Output {
        @Override
        default String widgetName() {
            return Text.class.getSimpleName().toLowerCase();
        }

        static public Text create(String name, String text) throws Exception {
            return OutputWidgets.TEXT.create(name, text);
        }
    }

    public interface Title extends Widget.Output {
        @Override
        default String widgetName() {
            return Title.class.getSimpleName().toLowerCase();
        }

        void setTitle(String title);

        static public Title create(String name, String title) throws Exception {
            return OutputWidgets.TITLE.create(name, title);
        }
    }

}
