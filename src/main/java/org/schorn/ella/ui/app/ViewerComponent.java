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

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.schorn.ella.ui.html.HTML;
import org.schorn.ella.ui.layout.Item;
import org.schorn.ella.ui.layout.Role;
import org.schorn.ella.ui.support.ItemSupport;
import org.schorn.ella.ui.util.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author bschorn
 */
public abstract class ViewerComponent implements App.AppViewer.Component {

    static private final Logger LGR = LoggerFactory.getLogger(ViewerComponent.class);
    static private final String CLASS = "app-viewer-component";

    protected final ItemSupport support = new ItemSupport(LGR);

    private Exception exception = null;

    public ViewerComponent(String id, String name, String label, boolean visible) {
        support.properties().put(Item.Properties.ID, UUID.randomUUID().toString());
        support.properties().put(Item.Properties.NAME, name);
        support.properties().put(Item.Properties.LABEL, label);
        support.properties().put(Item.Properties.VISIBLE, Boolean.TRUE);
    }

    @Override
    public Role type() {
        return Role.ITEM;
    }

    @Override
    public void property(Property property, Object value) {
        support.property(property, value);
    }

    @Override
    public <T> T property(Property property, Class<T> classForT) {
        return support.property(property, classForT);
    }

    @Override
    public Object property(Property property) {
        return support.property(property);
    }


    @Override
    public void throwException() throws Exception {
        if (this.exception != null) {
            throw this.exception;
        }
    }

    @Override
    public Optional<HTML.Element> build() {
        HTML.Div div = null;
        try {
            div = HTML.Div.create();
            div.setId(this.id());
            div.addClass(this.name());
            div.addClass(CLASS);
            this.build0(div);

        } catch (Exception ex) {
            LGR.error("{}.build() - Caught Exception: {}",
                    this.getClass().getSimpleName(),
                    ToString.stackTrace(ex));
        }
        return Optional.of(div);
    }

    abstract protected void build0(HTML.Element element) throws Exception;

    final protected String dumpProperties() {
        return Arrays.asList(new Item.Properties[]{Item.Properties.ID, Item.Properties.NAME, Item.Properties.LABEL, Item.Properties.VISIBLE}).stream()
                .map(p -> String.format("%s.%s=%s",
                this.getClass().getSimpleName(),
                p.getName(),
                this.property(p).toString()))
                .collect(Collectors.joining("\n"));
    }

}
