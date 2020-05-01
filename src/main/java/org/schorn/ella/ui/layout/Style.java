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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.schorn.ella.ui.html.CSS;
import org.schorn.ella.ui.EllamentProvider;

/**
 *
 * @author bschorn
 */
public interface Style {

    public interface Reset extends Style {
        static Reset get() {
            return EllamentProvider.provider().getStyleSheetReset();
        }
    }
    public interface Supplier {

        CSS.Style style();
    }

    public interface Factory extends Supplier {

        default CSS.Style add(CSS.Selector selector) {
            CSS.Style style = this.style();
            CSS.Block newBlock = CSS.Block.create().append(selector);
            return compose(newBlock, style);
        }

        static CSS.Style compose(CSS.Block newBlock, CSS.Style style) {
            switch (style.role()) {
                case BLOCK:
                    CSS.Block oldBlock = (CSS.Block) style;
                    oldBlock.rules().stream().forEach(newBlock);
                    break;
                case RULE:
                    CSS.Rule rule = (CSS.Rule) style;
                    newBlock.accept(rule);
                    break;
            }
            return newBlock;
        }

    }

    public interface Selectors {

        public CSS.Selector selector();
    }

    public void add(Supplier supplier);

    public void add(Factory factory, Selectors selector);

    public void add(Factory factory, CSS.Selector selector);

    public List<CSS.Style> styles();

    public class Repo {

        private static final Map<Supplier, CSS.Style> STYLES = new HashMap<>();

        static public void set(Supplier id, CSS.Style style) {
            STYLES.put(id, style);
        }

        static public CSS.Style get(Supplier id) {
            return STYLES.get(id);
        }
    }

    public class Sheet implements Style {

        private final List<CSS.Style> styles;
        private final Map<String, CSS.Block> blocksBySelector = new HashMap<>();
        private final List<String> blocksRenderOrder = new ArrayList<>();

        public Sheet() {
            this.styles = new ArrayList<>();
            this.styles.addAll(Reset.get().styles());
        }

        final void map(CSS.Style style) {
            switch (style.role()) {
                case BLOCK:
                    CSS.Block iBlock = (CSS.Block) style;
                    String selectorKey = iBlock.selectorKey();
                    CSS.Block block1 = this.blocksBySelector.get(selectorKey);
                    if (block1 == null) {
                        this.blocksBySelector.put(selectorKey, iBlock);
                        this.blocksRenderOrder.add(selectorKey);
                    } else {
                        this.blocksRenderOrder.remove(selectorKey);
                        iBlock.rules().stream().forEachOrdered(r -> block1.append(r));
                        String postKey = iBlock.selectorKey();
                        this.blocksBySelector.remove(selectorKey);
                        this.blocksBySelector.put(postKey, block1);
                        this.blocksRenderOrder.add(selectorKey);
                    }
                    break;
                case RULE:
                    break;
            }
        }

        @Override
        public List<CSS.Style> styles() {
            List<CSS.Style> temp = new ArrayList<>();
            temp.addAll(this.styles);
            temp.addAll(this.blocksRenderOrder.stream()
                    .map(selectorKey -> (CSS.Style) blocksBySelector.get(selectorKey))
                    .collect(Collectors.toList()));
            return temp;
        }
        @Override
        public String toString() {
            return this.styles().stream()
                    .map(s -> s.render())
                    .collect(Collectors.joining("\n"));
        }

        @Override
        final public void add(Supplier supplier) {
            this.map(supplier.style());
        }

        @Override
        final public void add(Factory factory, Selectors selector) {
            this.map(factory.add(selector.selector()));
        }

        @Override
        public void add(Factory factory, CSS.Selector selector) {
            this.map(factory.add(selector));
        }
    }
}
