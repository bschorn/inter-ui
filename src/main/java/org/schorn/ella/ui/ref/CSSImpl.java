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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;
import org.schorn.ella.ui.html.CSS;

/**
 *
 * @author bschorn
 */
public class CSSImpl implements CSS.CssFactory {
    static private final CSSImpl INSTANCE = new CSSImpl();

    static public CSS.CssFactory getFactory() {
        return INSTANCE;
    }

    private CSSImpl() {
        this.register();
    }

    public void register() {
        CSS.BLOCK.setImpl(BlockImpl.class);
        CSS.SELECTOR.setImpl(SelectorImpl.class);
        CSS.RULE.setImpl(RuleImpl.class);
    }

    @Override
    public <T> T createInstance(CSS css, Object... params) throws Exception {
        Class<?> classFor = css.getImpl();
        if (classFor == null) {
            // ERROR
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
                    // ERROR
                }
                break;
            }
        }
        if (newInstance == null) {
            StringJoiner joiner = new StringJoiner(", ", "[", "] ");
            for (Object o : params) {
                joiner.add(String.format("(%s) %s", o.getClass().getSimpleName(), o.toString()));
            }
            /*
            LGR.error("{}.newInstance() - there is no constructor available to match the parameters {} specificed for interface {}",
                CSS.class.getSimpleName(),
                joiner.toString(),
                css.interfaceOf.getSimpleName());
             */
        }
        return newInstance;
    }

    static public class BlockImpl implements CSS.Block {

        private final List<CSS.Selector> selectors = new ArrayList<>();
        private final List<CSS.Rule> rules = new ArrayList<>();

        @Override
        public CSS.Block append(CSS.Selector selector) {
            this.selectors.add(selector);
            return this;
        }

        @Override
        public CSS.Block append(CSS.Rule rule) {
            this.rules.add(rule);
            return this;
        }

        @Override
        public List<CSS.Selector> selectors() {
            return Collections.unmodifiableList(this.selectors);
        }

        @Override
        public List<CSS.Rule> rules() {
            return Collections.unmodifiableList(this.rules);
        }

        @Override
        public String render() {
            StringJoiner sJoiner = new StringJoiner(", ", "    ", "");
            this.selectors.forEach(s -> sJoiner.add(s.render()));
            StringJoiner rJoiner = new StringJoiner("\n        ", " {\n        ", "\n    }\n");
            this.rules.forEach(r -> rJoiner.add(r.render()));
            StringBuilder builder = new StringBuilder();
            builder.append("\n");
            builder.append(sJoiner.toString());
            builder.append(rJoiner.toString());
            return builder.toString();
        }
        @Override
        public String toString() {
            return this.render();
        }

    }

    static public class SelectorImpl implements CSS.Selector {

        private final String selector;

        public SelectorImpl(String selector) {
            this.selector = selector;
        }

        @Override
        public String render() {
            return this.selector;
        }

        @Override
        public String toString() {
            return this.render();
        }
    }

    static public class RuleImpl implements CSS.Rule {

        private final CSS.Property property;
        private final String value;

        public RuleImpl(CSS.Property property, String value) {
            this.property = property;
            this.value = value;
        }

        @Override
        public String render() {
            return String.format("%s: %s;", this.property.toString(), this.value);
        }

        @Override
        public String toString() {
            return this.render();
        }

        @Override
        public CSS.Property property() {
            return this.property;
        }

        @Override
        public String value() {
            return this.value;
        }
    }

}
