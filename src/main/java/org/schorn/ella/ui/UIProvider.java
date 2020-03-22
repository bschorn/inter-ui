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
import org.schorn.ella.ui.visual.Capture;
import org.schorn.ella.ui.visual.Display;
import org.schorn.ella.ui.visual.Page;
import org.schorn.ella.ui.visual.Panel;
import org.schorn.ella.ui.html.CSS;
import org.schorn.ella.ui.html.HTML;
import org.schorn.ella.ui.widget.READ;
import org.schorn.ella.ui.widget.WRITE;

/**
 *
 * @author bschorn
 */
public interface UIProvider {

    public HTML.HtmlFactory getHTMLFactory();

    public CSS.CssFactory getCSSFactory();

    public WRITE.WriteFactory getInputFactory();

    public READ.ReadFactory getOutputFactory();

    public Page createPage(String panelId, String panelName);

    public Panel createPanel(String id, String name);

    public Display createDisplay(String id, String name);

    public Capture createCapture(String id, String name);

    /**
     * Gets a new instance of the UIProvider's implementation as found by the
     * ClassLocator.
     *
     * @return
     */
    static UIProvider provider() {
        return Support.INSTANCE;
    }

    /**
     * Privatizes the ClassLocator
     */
    static class Support implements ClassLocator {

        static final Support SUPPORT = new Support();
        static final UIProvider INSTANCE = SUPPORT.newInstance(UIProvider.class);

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
