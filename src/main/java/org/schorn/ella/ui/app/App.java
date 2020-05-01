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
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import org.schorn.ella.ui.layout.Item;

/**
 *
 * @author bschorn
 */
public interface App<T> extends Callable<T> {

    public interface Bootstrap {

        public <T> T get(Class<T> classT, Object... options);
    }

    public interface Monitor {

        public void thumper(Consumer<String> listener) throws Exception;
    }

    public interface Config {

        public <T> T getProperty(Class<T> classT, String name);
        public <T> T getItemPropertyValue(Class<T> classT, Class<?> classFor, Item.Property property);

    }

    public interface Data {
        public interface Subscription {

        }

        public interface Publication {

        }
    }

    public interface Screen extends Consumer<ScreenComponent> {

        public interface Component extends Item {

        }

        public Component component(String id);

        public <T extends Component> T component(Class<T> classT, String id);

        public List<Component> components();
    }

    public Config config();

    public Data data();

    public Screen screen();

}
