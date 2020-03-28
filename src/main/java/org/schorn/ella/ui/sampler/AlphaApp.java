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
import org.schorn.ella.ui.layout.Editor;
import org.schorn.ella.ui.layout.Frame;
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

            Frame menuFrame = UIProvider.provider().createFrame("menu-frame", "Menu Frame");
            Panel menuPanel = UIProvider.provider().createPanel("menu-panel", "Menu Panel");
            menuFrame.accept(menuPanel);
            page.accept(menuFrame);

            Frame navFrame = UIProvider.provider().createFrame("nav-frame", "Navigation Frame");
            Panel navPanel = UIProvider.provider().createPanel("nav-panel", "Navigation Panel");
            navFrame.accept(navPanel);
            page.accept(navFrame);

            Frame viewerFrame = UIProvider.provider().createFrame("viewer-frame", "Viewer Frame");
            Panel viewerPanel = UIProvider.provider().createPanel("viewer-panel", "Viewer Panel");
            viewerFrame.accept(viewerPanel);
            page.accept(viewerFrame);

            Frame editorFrame = UIProvider.provider().createFrame("editor-frame", "Editor Frame");
            Panel editorPanel = UIProvider.provider().createPanel("editor-panel", "Editor Panel");
            editorFrame.accept(editorPanel);
            page.accept(editorFrame);

            String formAddressId = "Form.Address.00";
            String formAddressName = "Address";
            Editor formAddress = UIProvider.provider().createEditor(formAddressId, formAddressName);
            formAddress.accept(OutputWidgets.Title.create("customerAddress", "Customer Address"));
            formAddress.accept(InputWidgets.TextBox.create("streetAddress", "Street Address"));
            formAddress.accept(InputWidgets.ComboBox.create("city", "City", new String[]{"Chicago", "Houston", "New York", "San Francisco"}));
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
