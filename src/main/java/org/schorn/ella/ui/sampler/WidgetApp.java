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
import java.util.regex.Pattern;
import org.schorn.ella.ui.layout.Aspect;
import org.schorn.ella.ui.layout.Frame;
import org.schorn.ella.ui.layout.Item;
import org.schorn.ella.ui.layout.Page;
import org.schorn.ella.ui.layout.Panel;
import org.schorn.ella.ui.layout.Style;
import org.schorn.ella.ui.util.ToString;
import org.schorn.ella.ui.visual.AspectStyle;
import org.schorn.ella.ui.visual.FontStyle;
import org.schorn.ella.ui.visual.FrameStyle;
import org.schorn.ella.ui.visual.GenericStyle;
import org.schorn.ella.ui.visual.PageStyle;
import org.schorn.ella.ui.visual.PanelStyle;
import org.schorn.ella.ui.visual.Visual;
import org.schorn.ella.ui.visual.WidgetStyle;
import org.schorn.ella.ui.widget.InputWidgets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author bschorn
 */
public class WidgetApp {

    static private final Logger LGR = LoggerFactory.getLogger(WidgetApp.class);

    private static final String OUTFILE = String.format("D:/Users/bschorn/output/%s.html", WidgetApp.class.getSimpleName());

    public WidgetApp(Style styleSheet) {
        try {
            Page page = Page.create();
            page.setTitle(this.getClass().getSimpleName());
            page.setViewport("device-width", "1");

            Frame frame1 = Frame.create(Item.Name.create("frame1"));
            page.accept(frame1);
            Panel panel1 = Panel.create(Item.Name.create("panel1"), "Panel One");
            frame1.accept(panel1);
            Panel panel2 = Panel.create(Item.Name.create("panel2"), "Panel Two");
            frame1.accept(panel2);
            Aspect aspect1 = Aspect.create(Item.Name.create("library1"), "Address");
            panel1.accept(aspect1);
            Aspect aspect2 = Aspect.create(Item.Name.create("library2"), "Address");
            panel2.accept(aspect2);

            //Aspect aspect1 = page.newFramePanel(Item.Name.create("library"), "Widget List").newAspect();

            //aspect1.accept(OutputWidgets.Title.create("addressType", "Home Address"));
            aspect1.accept(InputWidgets.ComboBox.create(Item.Name.create("addressType"), "Type", new String[]{"Primary", "Secondary", "Work", "Other"}));
            aspect1.accept(InputWidgets.TextBox.create(Item.Name.create("streetAddress"), "Street", Pattern.compile("^.*$")));
            aspect1.accept(InputWidgets.ComboBox.create(Item.Name.create("city"), "City", new String[]{"Chicago", "Houston", "New York", "San Francisco"}));
            aspect1.accept(InputWidgets.ComboBox.create(Item.Name.create("state"), "State", new String[]{"Illinois", "Texas", "New York", "California"}));
            aspect1.accept(InputWidgets.TextBox.create(Item.Name.create("postal"), "Postal", Pattern.compile("^\\d\\d\\d\\d\\d$")));

            //aspect2.accept(OutputWidgets.Title.create("addressType", "Work Address"));
            aspect2.accept(InputWidgets.ComboBox.create(Item.Name.create("addressType"), "Type", new String[]{"Primary", "Secondary", "Work", "Other"}));
            aspect2.accept(InputWidgets.TextBox.create(Item.Name.create("streetAddress"), "Street", Pattern.compile("^.*$")));
            aspect2.accept(InputWidgets.ComboBox.create(Item.Name.create("city"), "City", new String[]{"Chicago", "Houston", "New York", "San Francisco"}));
            aspect2.accept(InputWidgets.ComboBox.create(Item.Name.create("state"), "State", new String[]{"Illinois", "Texas", "New York", "California"}));
            aspect2.accept(InputWidgets.TextBox.create(Item.Name.create("postal"), "Postal", Pattern.compile("^\\d\\d\\d\\d\\d$")));

            styleSheet.add(GenericStyle.GRID_WITH_TWO_COLUMNS, Aspect.Selector.ENTITY.selector(aspect2));

            Files.write(Paths.get(OUTFILE), page.produce(styleSheet).getBytes());
            page.throwException();

        } catch (Exception ex) {
            LGR.error("{}.ctor() - Caught Exception: {}",
                    this.getClass().getSimpleName(),
                    ToString.stackTrace(ex));
        }
    }

    static class AppStyle extends Style.Sheet {

        public AppStyle() {
            Visual.init();
            this.reset();
            this.add(FontStyle.ARIAL85, Page.Selector.CONTAINER);
            this.add(GenericStyle.BOX_SIZING_BORDER_BOX, Page.Selector.GLOBAL);
            this.add(PageStyle.DEFAULT_CONTAINER);
            this.add(FrameStyle.DEFAULT_CONTAINER);
            this.add(PanelStyle.DEFAULT_CONTAINER);
            this.add(PanelStyle.DEFAULT_LABEL);
            this.add(AspectStyle.DEFAULT_CONTAINER);
            this.add(AspectStyle.DEFAULT_LABEL);
            this.add(GenericStyle.GRID_FULL_SOLID_BLACK_ON_TGREY, Aspect.Selector.CONTAINER);
            this.add(GenericStyle.TEXT_ALIGN_CENTER_PADDING_2PX, Aspect.Selector.LABEL);
            this.add(GenericStyle.BORDER_2PX, Aspect.Selector.CONTAINER);
            this.add(WidgetStyle.DEFAULT_CONTAINER);
            this.add(WidgetStyle.DEFAULT_LABEL);
            this.add(WidgetStyle.DEFAULT_INPUT);
            this.add(WidgetStyle.LABEL_OVER_INPUT);
            //this.add(WidgetStyle.LABEL_LEFT_INPUT);
        }

    }

    static public void main(String[] args) {
        WidgetApp widgetApp = new WidgetApp(new AppStyle());
    }
}
