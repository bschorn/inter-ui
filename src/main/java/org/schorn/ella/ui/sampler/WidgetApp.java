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
package org.schorn.ella.ui.sampler;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.schorn.ella.ui.html.CSS;
import org.schorn.ella.ui.layout.Aspect;
import org.schorn.ella.ui.layout.Item;
import org.schorn.ella.ui.layout.Page;
import org.schorn.ella.ui.style.FontStyles;
import org.schorn.ella.ui.style.FrameStyles;
import org.schorn.ella.ui.style.GenericStyles;
import org.schorn.ella.ui.style.PanelStyles;
import org.schorn.ella.ui.style.StyleSheet;
import org.schorn.ella.ui.style.WidgetStyles;
import org.schorn.ella.ui.util.ToString;
import org.schorn.ella.ui.widget.InputWidgets;
import org.schorn.ella.ui.widget.OutputWidgets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author bschorn
 */
public class WidgetApp {

    static private final Logger LGR = LoggerFactory.getLogger(WidgetApp.class);

    private static final String OUTFILE = String.format("D:/Users/bschorn/output/%s.html", WidgetApp.class.getSimpleName());

    public WidgetApp(StyleSheet styleSheet) {
        try {
            Page page = Page.create();
            page.setTitle(this.getClass().getSimpleName());
            page.setViewport("device-width", "1");

            Aspect widgetsAspect = page.newFramePanel(Item.Name.create("library"), "Widget List").newAspect();

            widgetsAspect.accept(OutputWidgets.Title.create("customerAddress", "Customer Address"));
            widgetsAspect.accept(InputWidgets.TextBox.create(Item.Name.create("streetAddress"), "Street Address", Pattern.compile("^.*$")));
            widgetsAspect.accept(InputWidgets.ComboBox.create(Item.Name.create("city"), "City", new String[]{"Chicago", "Houston", "New York", "San Francisco"}));
            widgetsAspect.accept(InputWidgets.ComboBox.create(Item.Name.create("state"), "State", new String[]{"Illinois", "Texas", "New York", "California"}));
            widgetsAspect.accept(InputWidgets.TextBox.create(Item.Name.create("postal"), "Postal", Pattern.compile("^\\d\\d\\d\\d\\d$")));

            Files.write(Paths.get(OUTFILE), page.produce(styleSheet).getBytes());
            page.throwException();

        } catch (Exception ex) {
            LGR.error("{}.ctor() - Caught Exception: {}",
                    this.getClass().getSimpleName(),
                    ToString.stackTrace(ex));
        }
    }

    static class AppStyle implements StyleSheet {

        private final List<CSS.Style> styles = new ArrayList<>();

        public AppStyle() {
            this.reset();
            this.add(FontStyles.ARIAL85);
            this.add(GenericStyles.GLOBAL_BOX_SIZING_BORDER_BOX);
            this.add(FrameStyles.DEBUG);
            this.add(PanelStyles.DEBUG);
            this.add(WidgetStyles.LABEL_OVER_VALUE);
            this.add(WidgetStyles.SIERRA);
        }

        @Override
        public List<CSS.Style> styles() {
            return this.styles;
        }

        @Override
        public String toString() {
            return this.styles.stream().map(s -> s.render()).collect(Collectors.joining("\n"));
        }
    }

    static public void main(String[] args) {
        new WidgetApp(new AppStyle());
    }
}
