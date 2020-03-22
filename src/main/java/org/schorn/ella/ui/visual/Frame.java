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
package org.schorn.ella.ui.visual;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author bschorn
 */
public class Frame implements Comparable<Frame> {

    static private final Map<Frame, Set<Frame>> FRAMES = new HashMap<>();

    static public Frame create() {
        return new Frame();
    }

    public enum Orientation {
        ROOT, VERTICAL, HORIZONTAL;
    }

    private final Frame parent;
    private final int idx;
    private final Orientation orientation;
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final String id;
    private String name;

    private Frame() {
        this.parent = this;
        this.idx = 0;
        this.orientation = Orientation.ROOT;
        this.x = 0;
        this.y = 0;
        this.width = 100;
        this.height = 100;
        this.id = String.format("%d", this.idx);
        this.name = this.id;
        FRAMES.put(this, new TreeSet<>());
    }

    private Frame(Frame parent, int idx, Orientation orientation, int x, int y, int width, int height) {
        this.parent = parent;
        this.idx = idx;
        this.orientation = orientation;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = String.format("%s.%d", this.parent.id, this.idx);
        this.name = this.id;
        FRAMES.get(this.findRoot()).add(this);
    }

    @Override
    public int compareTo(Frame that) {
        return this.name().compareTo(that.name());
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Frame) {
            return this.compareTo((Frame) object) == 0;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public String toString() {
        return String.format("%s: %s",
                this.id, this.rect().toString());
    }

    public void setName(String id, String name) {
        Frame frame = this.frames().get(id);
        if (frame != null) {
            frame.name = name;
        }
    }

    public Map<String, Frame> frames() {
        final Map<String, Frame> frames = new TreeMap<>();
        FRAMES.get(this.findRoot()).stream()
                .forEach(f -> frames.put(f.name(), f));
        return frames;
    }

    public String name() {
        return this.id;
    }

    public Rect rect() {
        return new Rect(this.x, this.y, this.width, this.height);
    }

    public List<Frame> vsplit(int... widths) {
        if (this.unlink()) {
            if (widths.length == 0) {
                widths = new int[2];
                widths[0] = 50;
                widths[1] = 50;
            }
            List<Frame> frames = new ArrayList<>(widths.length);
            double totWidth = 0;
            for (int i = 0; i < widths.length; i++) {
                totWidth += widths[i];
            }
            int xy = this.x;
            for (int i = 0; i < widths.length; i++) {
                Frame frame = createFrame(Orientation.VERTICAL, i, xy, widths[i] / totWidth);
                xy += frame.width;
                frames.add(frame);
            }
            return frames;
        }
        return new ArrayList<>();
    }

    public List<Frame> hsplit(int... heights) {
        if (this.unlink()) {
            if (heights.length == 0) {
                heights = new int[2];
                heights[0] = 50;
                heights[1] = 50;
            }
            List<Frame> frames = new ArrayList<>(heights.length);
            double totHeight = 0;
            for (int i = 0; i < heights.length; i++) {
                totHeight += heights[i];
            }
            int xy = this.y;
            for (int i = 0; i < heights.length; i++) {
                Frame frame = createFrame(Orientation.HORIZONTAL, i, xy, heights[i] / totHeight);
                xy += frame.height;
                frames.add(frame);
            }
            return frames;
        }
        return new ArrayList<>(0);
    }

    protected Frame createFrame(Orientation orientation, int idx, int xy, double percent) {
        Frame frame = null;
        switch (orientation) {
            case VERTICAL:
                frame = new Frame(this, idx, orientation,
                        xy,
                        this.y,
                        Double.valueOf(percent * this.width).intValue(),
                        this.height);
                break;
            case HORIZONTAL:
                frame = new Frame(this, idx, orientation,
                        this.x,
                        xy,
                        this.width,
                        Double.valueOf(percent * this.height).intValue());
                break;
        }
        return frame;
    }

    private Frame findRoot() {
        if (this == this.parent) {
            return this;
        } else {
            return this.parent.findRoot();
        }
    }

    private boolean unlink() {
        if (this == this.parent) {
            return true;
        }
        Set<Frame> set = FRAMES.get(this.findRoot());
        if (!set.isEmpty()) {
            if (set.contains(this)) {
                set.remove(this);
                return true;
            }
        }
        return false;
    }


    static public void main(String[] args) {
        try {
            Frame root = Frame.create();
            root.hsplit(20, 80).get(1).vsplit(40, 60).get(1).hsplit(50, 50);

            root.setName("0.0", "menu");
            root.setName("0.1.0", "nav");
            root.setName("0.1.1.0", "viewer");
            root.setName("0.1.1.1", "editor");

            root.frames().values().stream()
                    .forEachOrdered(frame -> {
                System.out.println(frame.toString());
            });
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}
