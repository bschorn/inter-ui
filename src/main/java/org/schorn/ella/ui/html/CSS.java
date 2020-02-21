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
package org.schorn.ella.ui.html;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.StringJoiner;
import org.schorn.ella.ui.impl.html.CSSImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author bschorn
 */
public enum CSS {
    BLOCK(Block.class),
    SELECTOR(Selector.class),
    RULE(Rule.class),
    PROPERTY(Property.class);

    private final Class<?> interfaceOf;
    private Class<?> implOf = null;

    CSS(Class<?> interfaceOf) {
        this.interfaceOf = interfaceOf;
    }

    public Class<?> interfaceOf() {
        return this.interfaceOf;
    }

    public void setImpl(Class<?> implOf) {
        this.implOf = implOf;
    }

    public Element createElement(Object... params) throws Exception {
        return createInstance(this, params);
    }

    public <T> T create(Class<T> classOfT, Object... params) throws Exception {
        return (T) createInstance(this, params);
    }

    static final Logger LGR = LoggerFactory.getLogger(CSS.class);
    static {
        CSSImpl.register();
    }

    static private <T> T createInstance(CSS css, Object... params) throws Exception {
        Class<?> classFor = css.implOf;
        if (classFor == null) {
            /*
            LGR.error("{}.newInstance() - there is no implementation specified for interface {}",
            CSS.class.getSimpleName(), css.interfaceOf.getSimpleName());
             */
            return null;
        }
        Constructor<?> constructor = null;
        T newInstance = null;
        for (Constructor<?> ctr : classFor.getDeclaredConstructors()) {
            if (params.length == ctr.getParameterCount()) {
                constructor = ctr;
                for (int i = 0; i < params.length; i++) {
                    Class<?> paramClass = ctr.getParameterTypes()[i];
                    Object paramObj = params[i];
                    if (paramObj == null || paramClass.isInstance(paramObj)) {
                        continue;
                    }
                    constructor = null;
                    break;
                }
            }
            if (constructor != null) {
                try {
                    newInstance = (T) constructor.newInstance(params);
                } catch (InvocationTargetException ite) {
                    // LGR.error(ToString.stackTrace(ite);
                }
                break;
            }
        }
        if (newInstance == null) {
            StringJoiner joiner = new StringJoiner(", ", "[", "]");
            for (Object o : params) {
                joiner.add(String.format("(%s) %s",
                        o.getClass().getSimpleName(),
                        o.toString()));
            }
            /*
            LGR.error("{}.newInstance() - there is no constructor available to match the parameters {} specified for interface {}",
                CSS.class.getSimpleName(),
                joiner.toString(),
                css.interfaceOf.getSimpleName());
             */
        }
        return newInstance;
    }

    public interface Render {

        String render();
    }

    public interface Element extends Render {

        Element append(Element element);
    }

    public interface Block extends Element {

        static public Block create() throws Exception {
            return CSS.BLOCK.create(Block.class);
        }
    }

    public interface Selector extends Element {

        static public Selector create(String selector) throws Exception {
            return CSS.SELECTOR.create(Selector.class, selector);
        }
    }

    public interface Property extends Element {

        static public Property create(String name, Object params) throws Exception {
            return CSS.PROPERTY.create(Property.class, name, params);
        }
    }

    public interface Rule extends Element {

        static public Rule create(Property property, Object value) throws Exception {
            return CSS.RULE.create(Rule.class, property, value);
        }
    }

    public enum Properties implements Property {
        background_color,
        border,
        border_bottom,
        border_bottom_color,
        border_bottom_style,
        border_bottom_width,
        border_radius,
        color,
        dipslay,
        font,
        font_family,
        font_size,
        font_style,
        font_weight,
        grid_template_columns,
        grid_column_gap,
        margin,
        margin_top,
        margin_bottom,
        margin_left,
        margin_right,
        margin_block,
        margin_block_start,
        margin_block_end,
        max_width,
        min_width,
        overflow_wrap,
        padding,
        table_layout,
        vertical_align,
        width,;

        @Override
        public String render() {
            return this.name().replace("_", "-");
        }

        @Override
        public Element append(Element element) {
            return this;
        }

    }
}
