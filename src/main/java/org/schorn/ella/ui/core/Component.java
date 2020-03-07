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
package org.schorn.ella.ui.core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 *
 * @author bschorn
 */
public class Component implements Consumer<Event>, Predicate<Event> {

    private Component parent = null;
    private final List<Component> children = new ArrayList<>();

    public Component setParent(Component parent) {
        this.parent = parent;
        return this;
    }

    public Component append(Component child) {
        this.children.add(child.setParent(this));
        return this;
    }

    @Override
    public boolean test(Event event) {
        return true;
    }

    @Override
    public void accept(Event event) {
        switch (event.eventFlow()) {
            case SUB:
                this.children.stream()
                        .filter(c -> c.test(event))
                        .forEach(c -> c.accept(event));
                break;
            case PUB:
                this.parent.accept(event);
                break;
        }
    }
}
