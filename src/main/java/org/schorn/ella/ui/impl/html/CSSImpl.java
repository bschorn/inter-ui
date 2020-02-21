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
package org.schorn.ella.ui.impl.html;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import org.schorn.ella.ui.html.CSS;

/**
 *
 * @author bschorn
 */
public class CSSImpl {
    static public void register() {
        CSS.BLOCK.setImpl(BlockImpl.class);
        CSS.SELECTOR.setImpl(SelectorImpl.class);
        CSS.RULE.setImpl(RuleImpl.class);
        CSS.PROPERTY.setImpl(PropertyImpl.class);
    }

    static abstract class ElementImpl implements CSS.Element {

        @Override
        public CSS.Element append(CSS.Element element) {
            return this;
        }
    }

    static class BlockImpl extends ElementImpl implements CSS.Block {

        private final List<CSS.Selector> selectors = new ArrayList<>();
        private final List<CSS.Rule> rules = new ArrayList<>();

        @Override
        public CSS.Element append(CSS.Element element) {
            if (element instanceof CSS.Selector) {
                this.append((CSS.Selector) element);
            } else if (element instanceof CSS.Rule) {
                this.append((CSS.Rule) element);
            }
            return this;
        }

        public CSS.Element append(CSS.Selector selector) {
            this.selectors.add(selector);
            return this;
        }

        public CSS.Element append(CSS.Rule rule) {
            this.rules.add(rule);
            return this;
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
    }

    static class SelectorImpl extends ElementImpl implements CSS.Selector {

        private final String selector;

        SelectorImpl(String selector) {
            this.selector = selector;
        }

        @Override
        public String render() {
            return this.selector;
        }
    }

    static class RuleImpl extends ElementImpl implements CSS.Rule {

        private final CSS.Property property;
        private final Object value;

        RuleImpl(CSS.Property property, Object value) {
            this.property = property;
            this.value = value;
        }

        @Override
        public String render() {
            return String.format("%s: %s;", this.property.render(), value.toString());
        }
    }

    static class PropertyImpl extends ElementImpl implements CSS.Property {

        private final String name;
        private final Object[] params;

        PropertyImpl(String name, Object[] params) {
            this.name = name;
            this.params = params;
        }

        @Override
        public String render() {
            return this.name;
        }
    }
}
