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

import java.util.List;
import org.schorn.ella.ui.html.CSS;
import org.schorn.ella.ui.html.HTML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.schorn.ella.ui.EllamentProvider;


/**
 *
 * @author bschorn
 */
public interface Page extends Container<Item>, Item {

    static final Logger LGR = LoggerFactory.getLogger(Page.class);

    static Page create() {
        return EllamentProvider.provider().createPage();
    }

    public void setTitle(String title);

    @Override
    public void accept(Item item);

    @Override
    public List<Item> items();

    public void setViewport(String width, String initialScale);

    @Override
    default Type type() {
        return Type.PAGE;
    }

    public enum Selector implements Style.Selectors {
        GLOBAL(CSS.Selector.createGlobal()),
        CONTAINER(CSS.Selector.createType(HTML.BODY));

        private final CSS.Selector selector;

        Selector(CSS.Selector selector) {
            this.selector = selector;
        }

        @Override
        public CSS.Selector selector() {
            return this.selector;
        }
    }

    public String produce(Style styleSheet) throws Exception;

    default Window newWindowFrame(Identifier name, String label) {
        Frame frame = EllamentProvider.provider().createFrame(name);
        Window window = EllamentProvider.provider().createWindow(name, label);
        frame.accept(window);
        this.accept(frame);
        return window;
    }
}
