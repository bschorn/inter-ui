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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author bschorn
 */
public class Identifier {
    static final Pattern REGEX = Pattern.compile("[a-z][a-zA-Z0-9_]{1,30}");
    static public final Identifier NONE = new Identifier("none");

    static public Identifier create(String name) throws Exception {
        Matcher matcher = REGEX.matcher(name);
        if (matcher.matches()) {
            return new Identifier(name);
        }
        throw new Exception(String.format("'%s' is not a valid name for an Item object.",
                name));
    }
    private final String name;

    private Identifier(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
