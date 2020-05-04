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

import java.util.List;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import org.schorn.ella.ui.layout.Frame;
import org.schorn.ella.ui.layout.Item;
import org.schorn.ella.ui.layout.Page;
import org.schorn.ella.ui.layout.Style;
import org.schorn.ella.ui.layout.Window;

/**
 *
 * @author bschorn
 */
public interface App<T> extends Callable<T> {

    public interface Bootstrap {

        public Properties getProperties();

        public App.AppConfig getConfig(App app) throws Exception;

        public App.AppController getController(App app) throws Exception;

        public App.AppViewer getViewer(App app) throws Exception;

        public App.AppStyler getStyler(App app) throws Exception;
    }

    public interface Monitor {

        public void thumper(Consumer<String> listener) throws Exception;
    }

    public interface AppConfig {

        public App getApp();

        public <T> T getProperty(Class<T> classT, String name);

        public <T> T getItemPropertyValue(Class<T> classT, Class<?> classFor, Item.Property property);

    }

    public interface AppController {

        public interface Subscription {

        }

        public interface Publication {

        }

        public App getApp();
    }

    public interface AppViewer {

        public interface Component extends Item {
            public Frame frame();
        }

        public interface Stage extends Component {
            public List<Scene> scenes();

            public Scene scene(String id);
        }

        public interface Scene extends Item {
            public List<Window> windows();
        }

        public App getApp();

        public <T extends Item> T component(Class<T> classT, String id);

        public List<Item> items();

        public Page page();

        public Stage stage();
    }

    public interface AppStyler {

        public App getApp();

        public Style getPageStyle();
    }

    public String name();

    public Bootstrap bootstrap();

    public AppConfig config();

    public AppController controller();

    public AppViewer viewer();

    public AppStyler styler();

}
