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
package org.schorn.ella.ui.layout;

import java.util.HashMap;
import java.util.Map;
import org.schorn.ella.ui.widget.Input;
import org.schorn.ella.ui.widget.Output;
import org.schorn.ella.ui.widget.Widget;

/**
 *
 * @author bschorn
 */
public interface Type {

    static Type get(Class<?> classFor) {
        return Helper.MAP.get(classFor);
    }

    /**
     * Add Custom
     *
     * @param itemType
     */
    static void add(Type itemType) {
        Helper.MAP.put(itemType.classFor(), itemType);
    }

    /**
     * These are the default style components
     */
    enum Types implements Type {
        PAGE(Page.class),
        PANEL(Panel.class),
        FRAME(Frame.class),
        ASPECT(Aspect.class),
        WIDGET(Widget.class),
        INPUT(Input.class),
        OUTPUT(Output.class);

        private final Class<?> classFor;
        private final String className;

        Types(Class<?> classFor) {
            this.classFor = classFor;
            this.className = classFor.getSimpleName().toLowerCase();
            Type.add(this);
        }

        @Override
        public Class<?> classFor() {
            return this.classFor;
        }

        @Override
        public String className() {
            return this.className;
        }
    }

    /**
     * Who is this style for?
     *
     * @return
     */
    Class<?> classFor();

    String className();

    /**
     * Support Class (so the MAP is private)
     */
    static class Helper {

        static private Map<Class<?>, Type> MAP = new HashMap<>();

        static {
            Types.values();
        }
    }

}
