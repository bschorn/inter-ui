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
package org.schorn.ella.ui.ref;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.schorn.ella.ui.panel.Panel;

/**
 *
 * @author bschorn
 */
public class PanelImpl implements Panel {

    static public Panel create(String name) {
        return new PanelImpl(name);
    }

    protected final int idx;
    protected final String name;
    protected final PanelImpl parent;
    protected int weight;
    protected final List<Panel> children = new ArrayList<>();

    private PanelImpl(String name) {
        this.name = name;
        this.idx = 0;
        this.parent = null;
        this.weight = 1000;
    }

    private PanelImpl(Panel parent, int idx, int weight) {
        this.parent = (PanelImpl) parent;
        this.idx = idx;
        this.weight = weight;
        this.name = String.format("%s.%d", this.parent.name, this.idx);
    }

    @Override
    public List<Panel> divide() {
        int[] weights = new int[2];
        weights[0] = 50;
        weights[1] = 50;
        List<Panel> panels = new ArrayList<>(weights.length);
        for (int i = 0; i < weights.length; i++) {
            panels.add(create(weights[i]));
        }
        return panels;
    }

    @Override
    public List<Panel> divide(int... weights) {
        List<Panel> panels = new ArrayList<>(weights.length);
        double totWeight = 0;
        for (int i = 0; i < weights.length; i++) {
            totWeight += weights[i];
        }
        for (int i = 0; i < weights.length; i++) {
            panels.add(create(Double.valueOf(weights[i] / totWeight)));
        }
        return panels;
    }

    @Override
    public Panel parent() {
        return this.parent;
    }

    @Override
    public List<Panel> children() {
        return Collections.unmodifiableList(this.children);
    }

    @Override
    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public int getWeight() {
        return this.weight;
    }

    @Override
    public String toString() {
        return String.format("%s - %d",
                this.name,
                this.weight);
    }

    protected PanelImpl create(double percent) {
        PanelImpl panel = new PanelImpl(this, this.children.size(), Double.valueOf(percent * this.weight).intValue());
        this.children.add(panel);
        return panel;
    }

    static public void main(String[] args) {
        try {
            Panel panel0 = PanelImpl.create("top");
            List<Panel> panels = panel0.divide();
            for (int i = 0; i < 10; i++) {
                panels.addAll(panels.get(i).divide());
            }
            for (Panel panel : panels) {
                System.out.println(panel.toString());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
