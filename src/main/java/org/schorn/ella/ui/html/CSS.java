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
import java.util.List;
import java.util.StringJoiner;
import org.schorn.ella.ui.UIProvider;
import org.schorn.ella.ui.util.ToString;
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

    public Class<?> getImpl() {
        return this.implOf;
    }

    public Block createBlock(Object... params) throws Exception {
        return createInstance(this, params);
    }

    public <T> T create(Class<T> classOfT, Object... params) throws Exception {
        return (T) createInstance(this, params);
    }

    static final Logger LGR = LoggerFactory.getLogger(CSS.class);

    public interface CssFactory {

        public void register();

        public <T> T createInstance(CSS css, Object... params) throws Exception;
    }

    static final CssFactory FACTORY;

    static {
        FACTORY = UIProvider.provider().getCSSFactory();
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

    public enum Role {
        BLOCK,
        SELECTOR,
        RULE,
        PROPERTY;
    }

    public interface Style {

        String render();

        Role role();
    }

    public interface Block extends Style {

        static final Logger LGR = LoggerFactory.getLogger(Block.class);

        Block append(Selector selector);

        Block append(Rule rule);

        List<Selector> selectors();

        List<Rule> rules();

        @Override
        default Role role() {
            return Role.BLOCK;
        }

        static public Block create() {
            try {
                return CSS.BLOCK.create(Block.class);
            } catch (Exception ex) {
                LGR.error("{}.create() - Caught Exception: {}",
                        Selector.class.getSimpleName(),
                        ToString.stackTrace(ex));
            }
            return null;
        }
    }

    public interface Selector extends Style {
        static final Logger LGR = LoggerFactory.getLogger(Selector.class);

        @Override
        default Role role() {
            return Role.SELECTOR;
        }

        static public Selector create(String selector) throws Exception {
            try {
                return CSS.SELECTOR.create(Selector.class, selector);
            } catch (Exception ex) {
                LGR.error("{}.create({}) - Caught Exception: {}",
                        Selector.class.getSimpleName(),
                        selector,
                        ToString.stackTrace(ex));
            }
            return null;
        }

        static public Selector createType(HTML type) throws Exception {
            try {
                return CSS.SELECTOR.create(Selector.class, type.tag());
            } catch (Exception ex) {
                LGR.error("{}.createType({}) - Caught Exception: {}",
                        Selector.class.getSimpleName(),
                        type.tag(),
                        ToString.stackTrace(ex));
            }
            return null;
        }

        static public Selector createClass(String className) throws Exception {
            try {
                return CSS.SELECTOR.create(Selector.class, String.format(".%s", className));
            } catch (Exception ex) {
                LGR.error("{}.createClass({}) - Caught Exception: {}",
                        Selector.class.getSimpleName(),
                        className,
                        ToString.stackTrace(ex));
            }
            return null;
        }

        static public Selector createID(String id) {
            try {
                return CSS.SELECTOR.create(Selector.class, String.format("#%s", id));
            } catch (Exception ex) {
                LGR.error("{}.createID({}) - Caught Exception: {}",
                        Selector.class.getSimpleName(),
                        id,
                        ToString.stackTrace(ex));
            }
            return null;
        }

        static public Selector createDescendant(HTML type, HTML descendantType) throws Exception {
            try {
                return CSS.SELECTOR.create(Selector.class,
                        String.format("%s %s", type.tag(), descendantType.tag()));
            } catch (Exception ex) {
                LGR.error("{}.createDescendant({}) - Caught Exception: {}",
                        Selector.class.getSimpleName(),
                        type.tag(), descendantType.tag(),
                        ToString.stackTrace(ex));
            }
            return null;
        }

        static public Selector createChild(HTML type, HTML childType) throws Exception {
            try {
                return CSS.SELECTOR.create(Selector.class,
                        String.format("%s > %s", type.tag(), childType.tag()));
            } catch (Exception ex) {
                LGR.error("{}.createChild({}) - Caught Exception: {}",
                        Selector.class.getSimpleName(),
                        type.tag(), childType.tag(),
                        ToString.stackTrace(ex));
            }
            return null;
        }

        static public Selector createSibling(HTML type, HTML siblingType) throws Exception {
            try {
                return CSS.SELECTOR.create(Selector.class,
                        String.format("%s ~ %s", type.tag(), siblingType.tag()));
            } catch (Exception ex) {
                LGR.error("{}.createSibling({}) - Caught Exception: {}",
                        Selector.class.getSimpleName(),
                        type.tag(), siblingType.tag(),
                        ToString.stackTrace(ex));
            }
            return null;
        }

        static public Selector createAdjacentSibling(HTML type, HTML siblingType) throws Exception {
            try {
                return CSS.SELECTOR.create(Selector.class,
                        String.format("%s + %s", type.tag(), siblingType.tag()));
            } catch (Exception ex) {
                LGR.error("{}.createAdjacentSibling({}) - Caught Exception: {}",
                        Selector.class.getSimpleName(),
                        type.tag(), siblingType.tag(),
                        ToString.stackTrace(ex));
            }
            return null;
        }
    }

    public interface Rule extends Style {

        Property property();

        String value();

        default Role role() {
            return Role.RULE;
        }

        static public Rule create(Property property, String value) throws Exception {
            return CSS.RULE.create(Rule.class, property, value);
        }
    }

    public enum Property implements Style {
        align_items,
        background_color,
        border,
        border_bottom,
        border_bottom_color,
        border_bottom_style,
        border_bottom_width,
        border_radius,
        box_sizing,
        color,
        display,
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
        public String toString() {
            return this.name().replace("_", "-");
        }

        @Override
        public String render() {
            return this.toString();
        }

        @Override
        public CSS.Role role() {
            return Role.PROPERTY;
        }

    }
}
