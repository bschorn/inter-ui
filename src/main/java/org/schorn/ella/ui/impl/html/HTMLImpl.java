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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.schorn.ella.ui.html.DOM;
import org.schorn.ella.ui.html.HTML;
import org.schorn.ella.ui.html.HTML.Attribute;
import org.schorn.ella.ui.html.HTML.Element;
import org.schorn.ella.ui.html.HTML.SingleElement;
import org.schorn.ella.ui.util.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author bschorn
 */
public class HTMLImpl {

    static public void register() {
        for (HTML html : HTML.values()) {
            html.setImpl(HtmlElementImpl.class);
        }
        HTML.ELEMENT.setImpl(HtmlElementImpl.class);
        HTML.ATTRIBUTE.setImpl(HtmlAttributeImpl.class);
        HTML.TABLE.setImpl(HtmlTableImpl.class);
        HTML.SINGLE.setImpl(HtmlSingleElementImpl.class);

    }

    static class ElementImpl implements Element {

        static private final AtomicInteger ID = new AtomicInteger(100);
        static public final String[] INDENT = new String[]{"  ", "    ", "      ", "        ", "          ", "            ", "              "};
        static public final String LINEFEED = "\n";
        private static final Logger LGR = LoggerFactory.getLogger(ElementImpl.class);

        protected Element parent;
        protected Integer level = 0;
        protected String id;
        protected String tag;
        protected String textContent = "";
        protected List<Element> children = new ArrayList<>();
        protected List<Attribute> attributes = new ArrayList<>();

        ElementImpl(String tag) {
            if (tag == null) {
                this.tag = "root";
            } else {
                this.tag = tag;
            }
            this.parent = this;
        }

        public void setLevel(Integer level) {
            this.level = level;
            this.children.forEach(e -> ((ElementImpl) e).setLevel(level + 1));
        }

        @Override
        public Element setTextContent(String content) {
            this.textContent = content;
            return this;
        }

        @Override
        public Element append(Element element) throws HTML.InvalidContentException {
            //validateContent(this, element);
            ElementImpl elementImpl = (ElementImpl) element;
            if (elementImpl.parent != element) {
                ((ElementImpl) elementImpl.parent).children.remove(element);
            }
            elementImpl.parent = this;
            this.children.add(element);
            return this;
        }

        @Override
        public Element addClass(String className) {
            List<Attribute> attrs = this.attributes.stream().filter(a -> a.name().equals("class")).collect(Collectors.toList());
            if (attrs.isEmpty()) {
                try {
                    this.addAttribute0(Attribute.create("class", className));
                } catch (Exception ex) {
                    LGR.error(ToString.stackTrace(ex));
                }
            } else {
                attrs.stream().forEach(a -> a.addValue(className));
            }
            return this;
        }

        @Override
        public Element addAttribute(HTML.Attribute attribute) throws HTML.InvalidAttributeException {
            this.addAttribute0(attribute);
            return this;
        }

        @Override
        public Element setId(String value) throws Exception {
            this.id = value;
            if (value != null) {
                this.addAttribute0(Attribute.create("id", value));
            }
            return this;
        }

        @Override
        public String getId() {
            Optional<Attribute> id = this.attributes.stream().filter(a -> a.name().equalsIgnoreCase("id")).findFirst();
            if (id.isPresent()) {
                return id.get().value().toString();
            }
            return "";
        }

        @Override
        public String render() {
            StringBuilder builder = new StringBuilder();
            builder.append(renderIndent());
            builder.append(renderStartTag());
            builder.append(renderContent());
            if (!this.children.isEmpty()) {
                builder.append(renderLinefeed());
                builder.append(renderChildren());
                builder.append(renderIndent());
            }
            builder.append(renderEndTag());
            builder.append(renderLinefeed());
            return builder.toString();
        }

        @Override
        public String toString() {
            try {
                return render();
            } catch (Exception ex) {
                LGR.error(ex.getMessage());
            }
            return "";
        }

        protected final void addAttribute0(Attribute attribute) throws HTML.InvalidAttributeException {
            if (attribute.render() != null) {
                List<Attribute> existing
                        = this.attributes.stream()
                                .filter(a -> a.name().equals(attribute.name()))
                                .collect(Collectors.toList());
                if (existing.isEmpty()) {
                    this.attributes.add((HtmlAttributeImpl) attribute);
                } else {
                    existing.forEach(a -> a.setValue(attribute.value()));
                }
            }
        }

        protected String renderIndent() {
            return INDENT[this.level];
        }

        protected String renderLinefeed() {
            return LINEFEED;
        }

        protected String renderAttributes() {
            StringJoiner joiner = new StringJoiner(" ", "", "");
            attributes.forEach((attribute) -> {
                joiner.add(attribute.toString());
            });
            return joiner.toString();
        }

        protected String renderStartTag() {
            if (this.attributes.isEmpty()) {
                return String.format("<%s>", this.tag);
            } else {
                return String.format("<%s %s>", this.tag, this.renderAttributes());
            }
        }

        protected String renderContent() {
            return this.textContent != null ? this.textContent : "";
        }

        protected String renderChildren() {
            StringBuilder builder = new StringBuilder();
            if (this.children != null && !this.children.isEmpty()) {
                children.stream()
                        .filter(e -> ((HtmlElementImpl) e).parent == this)
                        .forEach(e -> {
                            try {
                                builder.append(e.render());
                            } catch (Exception ex) {

                            }
                        });
            }
            return builder.toString();
        }

        protected String renderEndTag() {
            return String.format("</%s>", this.tag);
        }

        @Override
        public DOM dom() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String tag() {
            return this.tag;
        }
    }

    static class HtmlElementImpl extends ElementImpl {

        public HtmlElementImpl(String tag) {
            super(tag);
        }
    }

    static class HtmlSingleElementImpl extends ElementImpl implements SingleElement {

        public HtmlSingleElementImpl(String tag) {
            super(tag);
        }

        @Override
        public Element setTextContent(String content) {
            return this;
        }

        @Override
        public String render() {
            StringBuilder builder = new StringBuilder();
            builder.append(renderIndent());
            builder.append(renderStartTag());
            builder.append(renderLinefeed());
            return builder.toString();
        }

        @Override
        protected String renderStartTag() {
            return String.format("<%s %s />", this.tag, this.renderAttributes());
        }
    }

    static class HtmlAttributeImpl implements Attribute {

        protected final String name;
        protected Object value;
        String rendered = null;

        public HtmlAttributeImpl(String name, Object value) {
            this.name = name;
            this.value = value;
            this.setValue0(this.value);
        }

        @Override
        public String name() {
            return this.name;
        }

        @Override
        public Object value() {
            return this.value;
        }

        @Override
        public void setValue(Object value) {
            this.value = value;
            this.setValue0(this.value);
        }

        @Override
        public void addValue(Object value) {
            if (value == null) {
                return;
            }
            if (this.value == null) {
                this.setValue0(value);
                return;
            }
            if (value instanceof String && this.value instanceof String) {
                this.value = String.format("%s %s", this.value, value);
            } else if (value instanceof Number && this.value instanceof Number) {
                if (value instanceof BigDecimal && this.value instanceof BigDecimal) {
                    this.value = BigDecimal.valueOf(((Number) value).doubleValue()).add(BigDecimal.valueOf(((Number) this.value).doubleValue()));
                } else if (value instanceof Double && this.value instanceof Double) {
                    this.value = ((Double) value) + ((Double) this.value);
                } else if (value instanceof Float && this.value instanceof Float) {
                    this.value = ((Float) value) + ((Float) this.value);
                } else if (value instanceof Integer && this.value instanceof Integer) {
                    this.value = ((Integer) value) + ((Integer) this.value);
                }
            }
            this.setValue0(this.value);
        }

        @Override
        public String render() {
            return this.rendered;
        }

        @Override
        public String toString() {
            return this.rendered;
        }

        /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
         *
         * 										PRIVATE
         *
         * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
        /**
         *
         * @param value
         */
        private void setValue0(Object value) {
            if (value != null) {
                if (value instanceof Boolean && (Boolean) value) {
                    this.rendered = String.format("%s", name);
                } else if (value instanceof String) {
                    this.rendered = String.format("%s='%s'", name, value);
                } else if (value instanceof Number) {
                    if (value instanceof Double || value instanceof Float || value instanceof BigDecimal) {
                        this.rendered = String.format("%s='%f'", name, value);
                    } else {
                        this.rendered = String.format("%s='%d'", name, value);
                    }
                } else if (value instanceof Temporal) {
                    if (value instanceof LocalDate) {
                        this.rendered = String.format("%s='%s'", ((LocalDate) value).format(DateTimeFormatter.ISO_LOCAL_DATE));
                    } else if (value instanceof LocalTime) {
                        this.rendered = String.format("%s='%s'", ((LocalTime) value).format(DateTimeFormatter.ISO_LOCAL_TIME));
                    } else if (value instanceof LocalDateTime) {
                        this.rendered = String.format("%s='%s'", ((LocalDateTime) value).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                    }
                }
            }
        }
    }

    static class HtmlTableImpl extends ElementImpl implements HTML.Table {
        public HtmlTableImpl(String tag) {
            super(tag);
        }
    }
}
