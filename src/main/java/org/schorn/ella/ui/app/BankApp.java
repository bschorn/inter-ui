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
package org.schorn.ella.ui.app;

/**
 *
 * @author bschorn
 * @param <T>
 */
public abstract class BankApp<T> implements App<T> {

    private final Bootstrap bootstrap;
    private final Config config;
    private final Controller data;
    private final View screen;

    public BankApp(App.Bootstrap bootstrap) throws Exception {
        this.bootstrap = bootstrap;
        this.config = this.bootstrap.getConfig(this);
        this.data = this.bootstrap.getController(this);
        this.screen = this.bootstrap.getView(this);
    }

    @Override
    final public Bootstrap bootstrap() {
        return this.bootstrap;
    }

    @Override
    final public Config config() {
        return this.config;
    }

    @Override
    final public Controller data() {
        return this.data;
    }

    @Override
    final public View screen() {
        return this.screen;
    }

}
