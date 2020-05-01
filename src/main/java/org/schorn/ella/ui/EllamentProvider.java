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
package org.schorn.ella.ui;

import java.util.Properties;
import org.schorn.ella.ui.html.CSS;
import org.schorn.ella.ui.html.HTML;
import org.schorn.ella.ui.layout.Frame;
import org.schorn.ella.ui.layout.Identifier;
import org.schorn.ella.ui.layout.Page;
import org.schorn.ella.ui.layout.Style;
import org.schorn.ella.ui.widget.ControlWidgets;
import org.schorn.ella.ui.widget.InputWidgets;
import org.schorn.ella.ui.widget.OutputWidgets;
import org.schorn.ella.ui.layout.Window;
import org.schorn.ella.ui.layout.Pane;

/**
 *
 * @author bschorn
 */
public interface EllamentProvider {

    public HTML.HtmlFactory getHTMLFactory();

    public CSS.CssFactory getCSSFactory();

    public InputWidgets.InputFactory getInputFactory();

    public OutputWidgets.OutputFactory getOutputFactory();

    public ControlWidgets.ControlFactory getControlFactory();

    public Page createPage();

    public Frame createFrame(Identifier name);

    public Frame createFrame(Identifier name, Frame.Intent intent);

    public Window createWindow(Identifier name);

    public Window createWindow(Identifier name, String label);

    public Pane createPane(Identifier name);

    public Pane createPane(Identifier name, String label);

    public Style.Reset getStyleSheetReset();

    /**
     * Gets a new instance of the UIProvider's implementation as found by the
     * ClassLocator.
     *
     * @return
     */
    static EllamentProvider provider() {
        return Support.INSTANCE;
    }

    /**
     * Privatizes the ClassLocator
     */
    static class Support implements ClassLocator {

        static final Support SUPPORT = new Support();
        static final EllamentProvider INSTANCE = SUPPORT.newInstance(EllamentProvider.class);

        private final ClassLocator classLocator;

        private Support() {
            Properties properties = System.getProperties();
            properties.setProperty("UIProvider", "org.schorn.ella.ui.ref.UIProviderImpl");
            this.classLocator = ClassLocator.create(properties);
        }

        @Override
        public Class<?> getImplClass(String interfaceName) throws Exception {
            return this.classLocator.getImplClass(interfaceName);
        }

        @Override
        public Class<?> getImplClass(Class<?> interfaceClass) throws Exception {
            return this.classLocator.getImplClass(interfaceClass);
        }

        @Override
        public <T> T newInstance(Class<T> interfaceClass) {
            return (T) this.classLocator.newInstance(interfaceClass);
        }

        @Override
        public Object newInstance(String interfaceName) throws Exception {
            return (Object) this.classLocator.newInstance(interfaceName);
        }

    }
}
