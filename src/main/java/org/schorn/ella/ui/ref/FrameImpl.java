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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import org.schorn.ella.ui.layout.Frame;
import org.schorn.ella.ui.visual.Rect;

/**
 *
 * @author bschorn
 */
class FrameImpl implements Frame {

    static private final Map<FrameImpl, Set<FrameImpl>> FRAMES = new HashMap<>();

    /**
     *
     * @return
     */
    static public FrameImpl create() {
        return new FrameImpl();
    }

    enum Orientation {
        ROOT, VERTICAL, HORIZONTAL;
    }

    private final FrameImpl parent;
    private final int idx;
    private final Rect rect;
    private final String id;
    private String name;

    FrameImpl() {
        this.parent = this;
        this.idx = 0;
        this.rect = new Rect(0, 0, 100, 100);
        this.id = String.format("%d", this.idx);
        this.name = this.id;
        FRAMES.put(this, new TreeSet<>());
    }

    private FrameImpl(FrameImpl parent, int idx, Rect rect) {
        this.parent = parent;
        this.idx = idx;
        this.rect = rect;
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
        if (object instanceof FrameImpl) {
            return this.compareTo((FrameImpl) object) == 0;
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

    @Override
    public void setName(String id, String name) {
        FrameImpl frame = findFrame(id);
        if (frame != null) {
            frame.name = name;
        }
    }

    @Override
    public List<Frame> frames() {
        Set<FrameImpl> set = FRAMES.get(this.findRoot());
        if (set != null) {
            return set.stream().map(f -> (Frame) f).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public String id() {
        return this.id;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public Rect rect() {
        return this.rect;
    }

    @Override
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
            int xy = this.rect().top();
            for (int i = 0; i < widths.length; i++) {
                Frame frame = createFrame(Orientation.VERTICAL, i, xy, widths[i] / totWidth);
                xy += frame.rect().width();
                frames.add(frame);
            }
            return frames;
        }
        return new ArrayList<>();
    }

    @Override
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
            int xy = this.rect().left();
            for (int i = 0; i < heights.length; i++) {
                Frame frame = createFrame(Orientation.HORIZONTAL, i, xy, heights[i] / totHeight);
                xy += frame.rect().height();
                frames.add(frame);
            }
            return frames;
        }
        return new ArrayList<>(0);
    }

    protected Frame createFrame(Orientation orientation, int idx, int xy, double percent) {
        FrameImpl frame = null;
        switch (orientation) {
            case VERTICAL:
                frame = new FrameImpl(this, idx,
                        new Rect(
                                xy,
                                this.rect().left(),
                                Double.valueOf(percent * this.rect().width()).intValue(),
                                this.rect().height())
                );
                break;
            case HORIZONTAL:
                frame = new FrameImpl(this, idx,
                        new Rect(
                                this.rect().top(),
                                xy,
                                this.rect().width(),
                                Double.valueOf(percent * this.rect().width()).intValue())
                );
                break;
        }
        return frame;
    }

    private FrameImpl findRoot() {
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
        Set<FrameImpl> set = FRAMES.get(this.findRoot());
        if (!set.isEmpty()) {
            if (set.contains(this)) {
                set.remove(this);
                return true;
            }
        }
        return false;
    }

    private FrameImpl findFrame(String id) {
        return this.frames().stream().map(f -> (FrameImpl) f).filter(f -> f.id().equals(id)).findAny().get();
    }

    static public void main(String[] args) {
        try {
            FrameImpl root = FrameImpl.create();
            root.hsplit(20, 80).get(1).vsplit(40, 60).get(1).hsplit(50, 50);

            root.setName("0.0", "menu");
            root.setName("0.1.0", "nav");
            root.setName("0.1.1.0", "viewer");
            root.setName("0.1.1.1", "editor");

        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}
