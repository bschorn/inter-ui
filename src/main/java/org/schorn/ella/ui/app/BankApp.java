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
    private final AppConfig config;
    private final AppController controller;
    private final AppViewer viewer;
    private final AppStyler styler;

    public BankApp(App.Bootstrap bootstrap) throws Exception {
        this.bootstrap = bootstrap;
        this.config = this.bootstrap.getConfig(this);
        this.controller = this.bootstrap.getController(this);
        this.viewer = this.bootstrap.getViewer(this);
        this.styler = this.bootstrap.getStyler(this);
    }

    @Override
    final public Bootstrap bootstrap() {
        return this.bootstrap;
    }

    @Override
    final public AppConfig config() {
        return this.config;
    }

    @Override
    final public AppController controller() {
        return this.controller;
    }

    @Override
    final public AppViewer viewer() {
        return this.viewer;
    }

    @Override
    final public AppStyler styler() {
        return this.styler;
    }

}
