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
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import org.schorn.ella.ui.html.CSS;
import org.schorn.ella.ui.visual.Control;
import org.schorn.ella.ui.visual.Rect;

/**
 *
 * @author bschorn
 */
public interface Frame extends Control, Comparable<Frame> {

    /**
     *
     */
    public interface Styler extends Function<Frame, CSS.Block> {

        static final Map<Thread, Exception> EXCEPTIONS = new HashMap<>();

        default void throwException() throws Exception {
            Exception exception = EXCEPTIONS.get(Thread.currentThread());
            if (exception != null) {
                throw EXCEPTIONS.remove(Thread.currentThread());
            }
        }

        default void catchException(Exception exception) {
            EXCEPTIONS.put(Thread.currentThread(), exception);
        }

        @Override
        public CSS.Block apply(Frame frame);
    }


    public void setName(String id, String name);

    public List<Frame> frames();

    public Rect rect();

    public List<Frame> vsplit(int... widths);

    public List<Frame> hsplit(int... heights);

}
