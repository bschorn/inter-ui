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

import org.schorn.ella.ui.EllamentProvider;
import org.schorn.ella.ui.html.CSS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author bschorn
 */
public interface Frame extends Container<Item> {

    static final Logger LGR = LoggerFactory.getLogger(Frame.class);

    public enum Intent {
        HEADER,
        FOOTER,
        CONTENT,
        CONTAINER;

        public String className() {
            return this.name().toLowerCase();
        }
    }

    static Frame create(Identifier name) {
        return EllamentProvider.provider().createFrame(name);
    }
    static Frame create(Identifier name, Intent intent) {
        return EllamentProvider.provider().createFrame(name, intent);
    }

    @Override
    public void accept(Item item);

    @Override
    default Role type() {
        return Role.FRAME;
    }
    public enum Selector implements Style.Selectors {
        CONTAINER(CSS.Selector.create("div.frame." + Intent.CONTAINER.className())),
        CONTENT(CSS.Selector.create("div.frame." + Intent.CONTENT.className())),
        HEADER(CSS.Selector.create("div.frame." + Intent.HEADER.className())),
        FOOTER(CSS.Selector.create("div.frame." + Intent.FOOTER.className()));


        private final CSS.Selector selector;

        Selector(CSS.Selector selector) {
            this.selector = selector;
        }

        @Override
        public CSS.Selector selector() {
            return this.selector;
        }
    }

}
