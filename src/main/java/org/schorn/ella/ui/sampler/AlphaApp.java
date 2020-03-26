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
import org.schorn.ella.ui.UIProvider;
import org.schorn.ella.ui.html.CSS;
import org.schorn.ella.ui.html.HTML;
import org.schorn.ella.ui.layout.Aspect;
import org.schorn.ella.ui.layout.Frame;
import org.schorn.ella.ui.layout.Page;
import org.schorn.ella.ui.layout.Panel;
import org.schorn.ella.ui.style.StyleFactory;
import org.schorn.ella.ui.widget.OutputWidgets;
import org.schorn.ella.ui.widget.InputWidgets;

/**
 *
 * @author bschorn
 */
public class AlphaApp {

    static private String OUTFILE = String.format("D:/Users/bschorn/output/%s.html", AlphaApp.class.getSimpleName());

    public AlphaApp() {
        try {
            Frame frame = UIProvider.provider().createFrame();
            frame.hsplit(20, 80).get(1).vsplit(40, 60).get(1).hsplit(50, 50);

            frame.setIdName("0_0", "menu", "Menu");
            frame.setIdName("0_1_0", "nav", "Navigation");
            frame.setIdName("0_1_1_0", "viewer", "Viewer");
            frame.setIdName("0_1_1_1", "editor", "Editor");

            Page page = Page.create("AlphaApp");
            page.setViewport("device-width", "1");
            frame.frames().forEach(page);
            page.assign(StyleFactory.POSITIONED_FRAME_STYLER, "Menu");
            page.assign(StyleFactory.POSITIONED_FRAME_STYLER, "Navigation");
            page.assign(StyleFactory.POSITIONED_FRAME_STYLER, "Viewer");
            page.assign(StyleFactory.POSITIONED_FRAME_STYLER, "Editor");
            page.addStyle(CSS.Rule.create(CSS.Property.padding, "10px"));
            page.addStyle(StyleFactory.BODY_FONT_01);
            page.addStyle(StyleFactory.GLOBAL_BORDER_BOX);
            page.addStyle(CSS.Block.create().append(CSS.Selector.create("p")).append(CSS.Rule.create(CSS.Property.margin, "0 0 1em 0")));
            page.addStyle(StyleFactory.LABEL_STYLE_01);
            //page.addComment("Test Comment for Page");

            //Panel panel = page.panel();
            //panel.addStyle(CSS.Rule.create(CSS.Property.width, "500px"));
            //panel.addStyle(CSS.Rule.create(CSS.Property.display, "grid"));
            //panel.addStyle(CSS.Rule.create(CSS.Property.border, "2px solid rgb(111,41,97)"));
            //panel.addStyle(CSS.Rule.create(CSS.Property.border_radius, ".2em"));
            //panel.addStyle(CSS.Rule.create(CSS.Property.padding, "5px"));
            //panel.addComment("Test Comment for Panel");
            //CSS.Block itemStyleBlock = CSS.Block.create()
                    //.append(CSS.Rule.create(CSS.Property.width, "100px"))
                    //.append(CSS.Rule.create(CSS.Property.padding, "5px"))
            //.append(CSS.Rule.create(CSS.Property.background_color, "rgba(111, 41, 97, .3)"))
            //.append(CSS.Rule.create(CSS.Property.border, "2px solid rgba(111, 41, 97, .5)"))
            //.append(CSS.Rule.create(CSS.Property.display, "inline-block"))
            //.append(CSS.Rule.create(CSS.Property.vertical_align, "middle"));

            //panel.addItemStyle(itemStyleBlock);
            /*

             */
            //List<Panel> panels = panel.hsplit(33, 33, 33);
            //panels.stream().forEach(p -> p.addStyle(itemStyleBlock));

            String formAddressId = "Form.Address.00";
            String formAddressName = "Address";
            Edit formAddress = Aspect.createEdit(formAddressId, formAddressName);
            formAddress.accept(OutputWidgets.Title.create("customerAddress", "Customer Address"));
            formAddress.accept(InputWidgets.TextBox.create("streetAddress", "Street Address"));
            formAddress.accept(InputWidgets.ComboBox.create("city", "City", new String[]{"Chicago", "Houston", "New York", "San Francisco"}));

            Panel editor = UIProvider.provider().createPanel("editor-panel", "Editor Panel");
            editor.accept(formAddress);
            page.assign(editor, "Editor");

            HTML.Element pageElement = page.build();
            Files.write(Paths.get(OUTFILE), pageElement.render().getBytes());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    static public void main(String[] args) {
        new AlphaApp();
    }
}
