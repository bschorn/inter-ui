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
import org.schorn.ella.ui.layout.Item;

/**
 *
 * @author bschorn
 */
public interface App<T> extends Callable<T> {

    public interface Bootstrap {

        public Properties getProperties();

        public App.Config getConfig(App app) throws Exception;

        public App.Controller getController(App app) throws Exception;

        public App.View getView(App app) throws Exception;
    }

    public interface Monitor {

        public void thumper(Consumer<String> listener) throws Exception;
    }

    public interface Config {

        public App getApp();

        public <T> T getProperty(Class<T> classT, String name);

        public <T> T getItemPropertyValue(Class<T> classT, Class<?> classFor, Item.Property property);

    }

    public interface Controller {

        public interface Subscription {

        }

        public interface Publication {

        }

        public App getApp();
    }

    public interface View extends Consumer<ViewComponent> {

        public interface Component extends Item {

        }

        public App getApp();

        public Component component(String id);

        public <T extends Component> T component(Class<T> classT, String id);

        public List<Component> components();
    }

    public Bootstrap bootstrap();

    public Config config();

    public Controller data();

    public View screen();

}
