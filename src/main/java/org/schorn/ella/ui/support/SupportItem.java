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
package org.schorn.ella.ui.support;

import java.util.HashMap;
import java.util.Map;
import org.schorn.ella.ui.layout.Item;
import org.schorn.ella.ui.layout.Role;
import org.slf4j.Logger;

/**
 *
 * @author bschorn
 */
public final class SupportItem implements Item {

    protected final Map<Item.Property, Object> properties = new HashMap<>();

    private final Logger logger;

    public SupportItem(Logger logger) {
        this.logger = logger;
    }

    public Map<Item.Property, Object> properties() {
        return this.properties;
    }

    @Override
    public void property(Item.Property property, Object value) {
        if (property != null) {
            if (property.isMutable()) {
                this.properties.put(property, value);
            } else {
                logger.error("{}.property() - can not set property: {} because it is immutable.",
                        this.getClass().getSimpleName(),
                        property.getName());
            }
        }
    }

    @Override
    public <T> T property(Item.Property property, Class<T> classForT) {
        if (property != null) {
            Object value = this.properties.get(property);
            if (value != null) {
                if (property.classType().equals(classForT)
                        && classForT.isInstance(value)) {
                    return (T) value;
                }
            }
        }
        return null;
    }

    @Override
    public Object property(Item.Property property) {
        if (property != null) {
            return this.properties.get(property);
        }
        return null;
    }

    @Override
    public Role type() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String tag() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
