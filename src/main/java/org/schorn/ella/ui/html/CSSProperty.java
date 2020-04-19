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

/**
 *
 * @author bschorn
 */
public class CSSProperty {

    private final CSS.Property property;
    private final String value;

    private CSSProperty(CSS.Property property, String value) {
        this.property = property;
        this.value = value;
    }

    public CSS.Property property() {
        return this.property;
    }

    public String value() {
        return this.value;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", this.property.toString(), this.value);
    }

    public interface display {

        /**
         * Turns off the display of an element so that it has no effect on
         * layout (the document is rendered as though the element did not
         * exist). All descendant elements also have their display turned off.
         * To have an element take up the space that it would normally take, but
         * without actually rendering anything, use the *visibility* property
         * instead. |display-box|
         */
        static CSSProperty none = new CSSProperty(CSS.Property.display, "none");
        /**
         * The element generates a block element box, generating line breaks
         * both before and after the element when in the normal flow.
         * |display-outside|
         */
        static CSSProperty block = new CSSProperty(CSS.Property.display, "block");
        /**
         * The element generates one or more inline element boxes that do not
         * generate line breaks before or after themselves. In normal flow, the
         * next element will be on the same line if there is space.
         * |display-outside|
         */
        static CSSProperty inline = new CSSProperty(CSS.Property.display, "inline");
        /**
         * The element behaves like a block element and lays out its content
         * according to the flexbox model. |display-inside|
         */
        static CSSProperty flex = new CSSProperty(CSS.Property.display, "flex");
        /**
         * The element behaves like a block element and lays out its content
         * according to the grid model. |display-inside|
         */
        static CSSProperty grid = new CSSProperty(CSS.Property.display, "grid");
        /**
         * These elements behave like HTML <table> elements. It defines a
         * block-level box. |display-inside|
         */
        static CSSProperty table = new CSSProperty(CSS.Property.display, "table");
    }

    /**
     * Flex Container Property
     */
    public interface flex_direction {

        /**
         * [->][->][->][->]
         */
        static CSSProperty row = new CSSProperty(CSS.Property.flex_direction, "row");
        /**
         * [<-][<-][<-][<-]
         */
        static CSSProperty row_reverse = new CSSProperty(CSS.Property.flex_direction, "row-reverse");
        /*
         * [->]
         * [->]
         * [->]
         * [->]
         */
        static CSSProperty column = new CSSProperty(CSS.Property.flex_direction, "column");
        /*
         * [<-]
         * [<-]
         * [<-]
         * [<-]
         */
        static CSSProperty column_reverse = new CSSProperty(CSS.Property.flex_direction, "column-reverse");
    }

    /**
     * Flex Container Property
     */
    public interface flex_wrap {

        /*
         * [->][->]...|
         */
        static CSSProperty nowrap = new CSSProperty(CSS.Property.flex_wrap, "nowrap");
        /*
         * [->][->]|
         * [->][->]|
         */
        static CSSProperty wrap = new CSSProperty(CSS.Property.flex_wrap, "wrap");
        /*
         * [<-][<-]|
         * [<-][<-]|
         */
        static CSSProperty wrap_reverse = new CSSProperty(CSS.Property.flex_wrap, "wrap-reverse");
    }

    /**
     * Flex Container Property (flex_direction + flex_flow)
     */
    public interface flex_flow {
        /**
         * flex-flow: row; flex-wrap: nowrap;
         */
        static CSSProperty row_nowrap = new CSSProperty(CSS.Property.flex_flow, "row nowrap");
        /**
         * flex-flow: row; flex-wrap: wrap;
         */
        static CSSProperty row_wrap = new CSSProperty(CSS.Property.flex_flow, "row wrap");
        /**
         * flex-flow: row; flex-wrap: wrap-reverse;
         */
        static CSSProperty row_wrap_reverse = new CSSProperty(CSS.Property.flex_flow, "row wrap-reverse");
        /**
         * flex-flow: column; flex-wrap: nowrap;
         */
        static CSSProperty column_nowrap = new CSSProperty(CSS.Property.flex_flow, "column nowrap");
        /**
         * flex-flow: column; flex-wrap: wrap;
         */
        static CSSProperty column_wrap = new CSSProperty(CSS.Property.flex_flow, "column wrap");
        /**
         * flex-flow: column; flex-wrap: wrap-reverse;
         */
        static CSSProperty column_wrap_reverse = new CSSProperty(CSS.Property.flex_flow, "column wrap-reverse");
    }

    /**
     * Flex Container Property
     */
    public interface justify_content {

        /**
         * {[1 ][2 ][3 ] }
         */
        static CSSProperty flex_start = new CSSProperty(CSS.Property.justify_content, "flex-start");
        /**
         * { [1 ][2 ][3 ]}
         */
        static CSSProperty flex_end = new CSSProperty(CSS.Property.justify_content, "flex-end");
        /**
         * { [1 ][2 ][3 ] }
         */
        static CSSProperty center = new CSSProperty(CSS.Property.justify_content, "center");
        /**
         * {[1 ] [2 ] [3 ]}
         */
        static CSSProperty space_between = new CSSProperty(CSS.Property.justify_content, "space-between");
        /**
         * { [1 ] [2 ] [3 ] }
         */
        static CSSProperty space_around = new CSSProperty(CSS.Property.justify_content, "space-around");
        /**
         * { [1 ] [2 ] [3 ] }
         */
        static CSSProperty space_evenly = new CSSProperty(CSS.Property.justify_content, "space-evenly");
    }

    /**
     * Flex Container Property
     */
    public interface align_items {
        static CSSProperty flex_start = new CSSProperty(CSS.Property.align_items, "flex-start");
        static CSSProperty flex_end = new CSSProperty(CSS.Property.align_items, "flex-end");
        static CSSProperty center = new CSSProperty(CSS.Property.align_items, "center");
        static CSSProperty stretch = new CSSProperty(CSS.Property.align_items, "stretch");
        static CSSProperty baseline = new CSSProperty(CSS.Property.align_items, "baseline");
    }

    /**
     * Flex Container Property
     */
    public interface align_content {
        static CSSProperty flex_start = new CSSProperty(CSS.Property.align_content, "flex-start");
        static CSSProperty flex_end = new CSSProperty(CSS.Property.align_content, "flex-end");
        static CSSProperty center = new CSSProperty(CSS.Property.align_content, "center");
        static CSSProperty stretch = new CSSProperty(CSS.Property.align_content, "stretch");
        static CSSProperty space_between = new CSSProperty(CSS.Property.align_content, "space-between");
        static CSSProperty space_around = new CSSProperty(CSS.Property.align_content, "space-around");
    }

    /**
     * Flex Item Property
     */
    public interface align_self {

        static CSSProperty auto = new CSSProperty(CSS.Property.align_self, "auto");
        static CSSProperty flex_start = new CSSProperty(CSS.Property.align_self, "flex-start");
        static CSSProperty flex_end = new CSSProperty(CSS.Property.align_self, "flex-end");
        static CSSProperty center = new CSSProperty(CSS.Property.align_self, "center");
        static CSSProperty stretch = new CSSProperty(CSS.Property.align_self, "stretch");
        static CSSProperty baseline = new CSSProperty(CSS.Property.align_self, "baseline");
    }

    /**
     * Flex Item Property
     */
    public interface order {

        static CSSProperty one = new CSSProperty(CSS.Property.order, "1");
        static CSSProperty two = new CSSProperty(CSS.Property.order, "2");
        static CSSProperty three = new CSSProperty(CSS.Property.order, "3");
        static CSSProperty four = new CSSProperty(CSS.Property.order, "4");
        static CSSProperty five = new CSSProperty(CSS.Property.order, "5");
        static CSSProperty six = new CSSProperty(CSS.Property.order, "6");
        static CSSProperty seven = new CSSProperty(CSS.Property.order, "7");

        /**
         *
         * @param unit
         * @return
         */
        static CSSProperty create(int order) {
            return new CSSProperty(CSS.Property.order, String.valueOf(order));
        }
    }

    /**
     * Flex Item Property
     */
    public interface flex_grow {

        /**
         * proportion of available space: 1 - 9
         *
         * @param unit
         * @return
         */
        static CSSProperty unit(int unit) {
            return new CSSProperty(CSS.Property.flex_grow, String.valueOf(unit));
        }
    }

    /**
     * Flex Item Property
     */
    public interface flex_shrink {

        static CSSProperty unit(int unit) {
            return new CSSProperty(CSS.Property.flex_shrink, String.valueOf(unit));
        }
    }

    public interface flex_basis {
        static CSSProperty auto = new CSSProperty(CSS.Property.flex_basis, "auto");
    }

    public interface flex {

        static CSSProperty create(CSSProperty.flex_grow flex_grow, CSSProperty.flex_shrink flex_shrink, CSSProperty.flex_basis flex_basis) {
            return new CSSProperty(CSS.Property.flex, String.format("%s %s %s", flex_grow.toString(), flex_shrink.toString(), flex_basis.toString()));
        }
        static CSSProperty create(CSSProperty.flex_grow flex_grow, CSSProperty.flex_shrink flex_shrink) {
            return new CSSProperty(CSS.Property.flex, String.format("%s %s auto", flex_grow.toString(), flex_shrink.toString()));
        }
    }
}
