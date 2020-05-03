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

import java.util.ArrayList;
import java.util.List;
import org.schorn.ella.ui.html.CSS;

/**
 *
 * @author bschorn
 */
public interface Item extends Build {

    public interface Property {
        public Class<?> classType();

        public boolean isMutable();

        public String getName();
    }

    public enum Properties implements Property {
        ID(String.class, false),
        NAME(String.class, false),
        LABEL(String.class, true),
        VISIBLE(Boolean.class, true);

        private final Class<?> classType;
        private final boolean mutable;

        Properties(Class<?> classType, boolean mutable) {
            this.classType = classType;
            this.mutable = mutable;
        }

        @Override
        public Class<?> classType() {
            return this.classType;
        }

        @Override
        public boolean isMutable() {
            return this.mutable;
        }

        @Override
        public String getName() {
            return this.name().toLowerCase();
        }

    }

    default String id() {
        return (String) this.property(Item.Properties.ID, String.class);
    }

    default String name() {
        return (String) this.property(Item.Properties.NAME, String.class);
    }

    default String label() {
        return (String) this.property(Item.Properties.LABEL, String.class);
    }

    default boolean visible() {
        return this.property(Item.Properties.VISIBLE, Boolean.class);
    }

    public void property(Property property, Object value);

    public <T> T property(Property property, Class<T> classType);

    public Object property(Property property);

    public Role type();

    default List<CSS.Style> styles() {
        return new ArrayList<>(0);
    }
}
