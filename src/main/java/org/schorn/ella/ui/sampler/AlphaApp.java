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
import org.schorn.ella.ui.UIProvider;
import org.schorn.ella.ui.layout.Aspect;
import org.schorn.ella.ui.layout.Frame;
import org.schorn.ella.ui.layout.Item;
import org.schorn.ella.ui.layout.Page;
import org.schorn.ella.ui.layout.Panel;
import org.schorn.ella.ui.util.ToString;
import org.schorn.ella.ui.widget.InputWidgets;
import org.schorn.ella.ui.widget.OutputWidgets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author bschorn
 */
public class AlphaApp {

    static private final Logger LGR = LoggerFactory.getLogger(AlphaApp.class);

    static private String OUTFILE = String.format("D:/Users/bschorn/output/%s.html", AlphaApp.class.getSimpleName());

    public AlphaApp() {
        try {
            Page page = Page.create();
            page.setTitle("AlphaApp");
            page.setViewport("device-width", "1");

            Item.Name menuName = Item.Name.create("menu");
            Frame menuFrame = UIProvider.provider().createFrame(menuName);
            Panel menuPanel = UIProvider.provider().createPanel(menuName, "Menu");
            menuFrame.accept(menuPanel);
            page.accept(menuFrame);

            Item.Name navName = Item.Name.create("nav");
            Frame navFrame = UIProvider.provider().createFrame(navName);
            Panel navPanel = UIProvider.provider().createPanel(navName, "Navigation");
            navFrame.accept(navPanel);
            page.accept(navFrame);

            Item.Name viewerName = Item.Name.create("viewer");
            Frame viewerFrame = UIProvider.provider().createFrame(viewerName);
            Panel viewerPanel = UIProvider.provider().createPanel(viewerName, "Viewer");
            viewerFrame.accept(viewerPanel);
            page.accept(viewerFrame);

            Item.Name editorName = Item.Name.create("editor");
            Frame editorFrame = UIProvider.provider().createFrame(editorName);
            Panel editorPanel = UIProvider.provider().createPanel(editorName, "Editor");
            editorFrame.accept(editorPanel);
            page.accept(editorFrame);

            Aspect formAddress = UIProvider.provider().createAspect(Item.Name.create("addr"), "Address");
            formAddress.accept(OutputWidgets.Title.create("customerAddress", "Customer Address"));
            formAddress.accept(InputWidgets.TextBox.create(Item.Name.create("streetAddress"), "Street Address", Pattern.compile("^.*$")));
            formAddress.accept(InputWidgets.ComboBox.create(Item.Name.create("city"), "City", new String[]{"Chicago", "Houston", "New York", "San Francisco"}));
            formAddress.accept(InputWidgets.ComboBox.create(Item.Name.create("state"), "State", new String[]{"Illinois", "Texas", "New York", "California"}));
            formAddress.accept(InputWidgets.TextBox.create(Item.Name.create("postal"), "Postal", Pattern.compile("^\\d\\d\\d\\d\\d$")));
            editorPanel.accept(formAddress);

            Files.write(Paths.get(OUTFILE), page.produce(new AlphaStyle()).getBytes());
            page.throwException();

        } catch (Exception ex) {
            LGR.error("{}.ctor() - Caught Exception: {}",
                    this.getClass().getSimpleName(),
                    ToString.stackTrace(ex));
        }
    }

    static public void main(String[] args) {
        new AlphaApp();
    }
}
