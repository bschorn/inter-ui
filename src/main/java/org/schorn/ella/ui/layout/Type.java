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

/**
 *
 * @author bschorn
 */
public enum Type {
    PAGE(Page.class),
    FRAME(Frame.class),
    WINDOW(Window.class),
    PANE(Pane.class),
    WIDGET(Widget.class),
    ITEM(Item.class);

    private final Class<? extends Item> itemClass;

    Type(Class<? extends Item> itemClass) {
        this.itemClass = itemClass;
    }

    static Type get(Class<? extends Item> classFor) {
        for (Type type : Type.values()) {
            if (type.itemClass.equals(classFor)) {
                return type;
            }
        }
        return null;
    }

    public Class<?> classFor() {
        return this.itemClass;
    }

    public String className() {
        return this.itemClass.getSimpleName().toLowerCase();
    }
}
