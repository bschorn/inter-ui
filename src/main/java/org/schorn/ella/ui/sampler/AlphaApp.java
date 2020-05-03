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
import org.schorn.ella.ui.layout.Frame;
import org.schorn.ella.ui.layout.Identifier;
import org.schorn.ella.ui.util.ToString;
import org.schorn.ella.ui.widget.InputWidgets;
import org.schorn.ella.ui.widget.OutputWidgets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.schorn.ella.ui.layout.Window;
import org.schorn.ella.ui.layout.Pane;
import org.schorn.ella.ui.EllamentProvider;
import org.schorn.ella.ui.layout.Page;

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

            Identifier menuName = Identifier.create("menu");
            Frame menuFrame = EllamentProvider.provider().createFrame(menuName);
            Window menuPanel = EllamentProvider.provider().createWindow(menuName, "Menu");
            menuFrame.accept(menuPanel);
            page.accept(menuFrame);

            Identifier navName = Identifier.create("nav");
            Frame navFrame = EllamentProvider.provider().createFrame(navName);
            Window navPanel = EllamentProvider.provider().createWindow(navName, "Navigation");
            navFrame.accept(navPanel);
            page.accept(navFrame);

            Identifier viewerName = Identifier.create("viewer");
            Frame viewerFrame = EllamentProvider.provider().createFrame(viewerName);
            Window viewerPanel = EllamentProvider.provider().createWindow(viewerName, "Viewer");
            viewerFrame.accept(viewerPanel);
            page.accept(viewerFrame);

            Identifier editorName = Identifier.create("editor");
            Frame editorFrame = EllamentProvider.provider().createFrame(editorName);
            Window editorPanel = EllamentProvider.provider().createWindow(editorName, "Editor");
            editorFrame.accept(editorPanel);
            page.accept(editorFrame);

            Pane formAddress = EllamentProvider.provider().createPane(Identifier.create("addr"), "Address");
            formAddress.accept(OutputWidgets.Title.create("customerAddress", "Customer Address"));
            formAddress.accept(InputWidgets.TextBox.create(Identifier.create("streetAddress"), "Street Address", Pattern.compile("^.*$")));
            formAddress.accept(InputWidgets.ComboBox.create(Identifier.create("city"), "City", new String[]{"Chicago", "Houston", "New York", "San Francisco"}));
            formAddress.accept(InputWidgets.ComboBox.create(Identifier.create("state"), "State", new String[]{"Illinois", "Texas", "New York", "California"}));
            formAddress.accept(InputWidgets.TextBox.create(Identifier.create("postal"), "Postal", Pattern.compile("^\\d\\d\\d\\d\\d$")));
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
