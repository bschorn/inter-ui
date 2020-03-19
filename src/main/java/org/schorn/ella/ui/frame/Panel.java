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
package org.schorn.ella.ui.frame;

import java.util.List;

/**
 *
 * @author bschorn
 */
public interface Panel extends Frame<Facet>, Comment, Style, Build {

    public enum Orientation {
        ROOT, VERTICAL, HORIZONTAL;
    }

    /*
    static Panel create(String name) {
        return UIProvider.provider().createPanel(name);
    }
     */

    @Override
    public void addContent(Facet facet) throws Exception;

    /**
     * Vertically Splits
     *
     * @param widths
     * @return
     */
    public List<Panel> vsplit(int... widths);

    public List<Panel> hsplit(int... heights);

    //public HTML.Element build() throws Exception;

    public Orientation orientation();

    public int width();

    public int height();

}
