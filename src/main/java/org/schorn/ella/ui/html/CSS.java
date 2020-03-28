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
package org.schorn.ella.ui.html;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Pattern;
import org.schorn.ella.ui.UIProvider;
import org.schorn.ella.ui.util.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author bschorn
 */
public enum CSS {
    BLOCK(Block.class),
    SELECTOR(Selector.class),
    RULE(Rule.class),
    PROPERTY(Property.class);

    private final Class<?> interfaceOf;
    private Class<?> implOf = null;

    CSS(Class<?> interfaceOf) {
        this.interfaceOf = interfaceOf;
    }

    public Class<?> interfaceOf() {
        return this.interfaceOf;
    }

    public void setImpl(Class<?> implOf) {
        this.implOf = implOf;
    }

    public Class<?> getImpl() {
        return this.implOf;
    }

    public Block createBlock(Object... params) throws Exception {
        return createInstance(this, params);
    }

    public <T> T create(Class<T> classOfT, Object... params) throws Exception {
        return (T) createInstance(this, params);
    }

    static final Logger LGR = LoggerFactory.getLogger(CSS.class);

    public interface CssFactory {

        public void register();

        public <T> T createInstance(CSS css, Object... params) throws Exception;
    }

    static final CssFactory FACTORY;

    static {
        FACTORY = UIProvider.provider().getCSSFactory();
    }

    static private <T> T createInstance(CSS css, Object... params) throws Exception {
        Class<?> classFor = css.implOf;
        if (classFor == null) {
            /*
            LGR.error("{}.newInstance() - there is no implementation specified for interface {}",
            CSS.class.getSimpleName(), css.interfaceOf.getSimpleName());
             */
            return null;
        }
        Constructor<?> constructor = null;
        T newInstance = null;
        for (Constructor<?> ctr : classFor.getDeclaredConstructors()) {
            if (params.length == ctr.getParameterCount()) {
                constructor = ctr;
                for (int i = 0; i < params.length; i++) {
                    Class<?> paramClass = ctr.getParameterTypes()[i];
                    Object paramObj = params[i];
                    if (paramObj == null || paramClass.isInstance(paramObj)) {
                        continue;
                    }
                    constructor = null;
                    break;
                }
            }
            if (constructor != null) {
                try {
                    newInstance = (T) constructor.newInstance(params);
                } catch (InvocationTargetException ite) {
                    // LGR.error(ToString.stackTrace(ite);
                }
                break;
            }
        }
        if (newInstance == null) {
            StringJoiner joiner = new StringJoiner(", ", "[", "]");
            for (Object o : params) {
                joiner.add(String.format("(%s) %s",
                        o.getClass().getSimpleName(),
                        o.toString()));
            }
            /*
            LGR.error("{}.newInstance() - there is no constructor available to match the parameters {} specified for interface {}",
                CSS.class.getSimpleName(),
                joiner.toString(),
                css.interfaceOf.getSimpleName());
             */
        }
        return newInstance;
    }

    public enum Role {
        BLOCK,
        SELECTOR,
        RULE,
        PROPERTY;
    }

    public interface Style {

        String render();

        Role role();
    }

    public interface Block extends Style {

        static final Logger LGR = LoggerFactory.getLogger(Block.class);

        Block append(Selector selector);

        Block append(Rule rule);

        List<Selector> selectors();

        List<Rule> rules();

        @Override
        default Role role() {
            return Role.BLOCK;
        }

        static public Block create() {
            try {
                return CSS.BLOCK.create(Block.class);
            } catch (Exception ex) {
                LGR.error("{}.create() - Caught Exception: {}",
                        Selector.class.getSimpleName(),
                        ToString.stackTrace(ex));
            }
            return null;
        }
    }

    public interface Selector extends Style {
        static final Logger LGR = LoggerFactory.getLogger(Selector.class);

        @Override
        default Role role() {
            return Role.SELECTOR;
        }

        static public Selector create(String selector) {
            try {
                return CSS.SELECTOR.create(Selector.class, selector);
            } catch (Exception ex) {
                LGR.error("{}.create({}) - Caught Exception: {}",
                        Selector.class.getSimpleName(),
                        selector,
                        ToString.stackTrace(ex));
            }
            return null;
        }

        static public Selector createGlobal() {
            try {
                return CSS.SELECTOR.create(Selector.class, "*");
            } catch (Exception ex) {
                LGR.error("{}.create({}) - Caught Exception: {}",
                        Selector.class.getSimpleName(),
                        "*",
                        ToString.stackTrace(ex));
            }
            return null;
        }

        static public Selector createType(HTML type) {
            try {
                return CSS.SELECTOR.create(Selector.class, type.tag());
            } catch (Exception ex) {
                LGR.error("{}.createType({}) - Caught Exception: {}",
                        Selector.class.getSimpleName(),
                        type.tag(),
                        ToString.stackTrace(ex));
            }
            return null;
        }

        static public Selector createClass(String className) {
            try {
                return CSS.SELECTOR.create(Selector.class, String.format(".%s", className));
            } catch (Exception ex) {
                LGR.error("{}.createClass({}) - Caught Exception: {}",
                        Selector.class.getSimpleName(),
                        className,
                        ToString.stackTrace(ex));
            }
            return null;
        }

        static public Selector createID(String id) {
            try {
                return CSS.SELECTOR.create(Selector.class, String.format("#%s", id));
            } catch (Exception ex) {
                LGR.error("{}.createID({}) - Caught Exception: {}",
                        Selector.class.getSimpleName(),
                        id,
                        ToString.stackTrace(ex));
            }
            return null;
        }

        static public Selector createDescendant(HTML type, HTML descendantType) throws Exception {
            try {
                return CSS.SELECTOR.create(Selector.class,
                        String.format("%s %s", type.tag(), descendantType.tag()));
            } catch (Exception ex) {
                LGR.error("{}.createDescendant({}) - Caught Exception: {}",
                        Selector.class.getSimpleName(),
                        type.tag(), descendantType.tag(),
                        ToString.stackTrace(ex));
            }
            return null;
        }

        static public Selector createChild(HTML type, HTML childType) throws Exception {
            try {
                return CSS.SELECTOR.create(Selector.class,
                        String.format("%s > %s", type.tag(), childType.tag()));
            } catch (Exception ex) {
                LGR.error("{}.createChild({}) - Caught Exception: {}",
                        Selector.class.getSimpleName(),
                        type.tag(), childType.tag(),
                        ToString.stackTrace(ex));
            }
            return null;
        }

        static public Selector createSibling(HTML type, HTML siblingType) throws Exception {
            try {
                return CSS.SELECTOR.create(Selector.class,
                        String.format("%s ~ %s", type.tag(), siblingType.tag()));
            } catch (Exception ex) {
                LGR.error("{}.createSibling({}) - Caught Exception: {}",
                        Selector.class.getSimpleName(),
                        type.tag(), siblingType.tag(),
                        ToString.stackTrace(ex));
            }
            return null;
        }

        static public Selector createAdjacentSibling(HTML type, HTML siblingType) throws Exception {
            try {
                return CSS.SELECTOR.create(Selector.class,
                        String.format("%s + %s", type.tag(), siblingType.tag()));
            } catch (Exception ex) {
                LGR.error("{}.createAdjacentSibling({}) - Caught Exception: {}",
                        Selector.class.getSimpleName(),
                        type.tag(), siblingType.tag(),
                        ToString.stackTrace(ex));
            }
            return null;
        }
    }

    public interface Rule extends Style {

        Property property();

        String value();

        @Override
        default Role role() {
            return Role.RULE;
        }

        static public Rule create(Property property, String value) {
            try {
                return CSS.RULE.create(Rule.class, property, value);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }
    }

    public enum DataTypeFlavor {
        LIST,
        PATTERN,
        RANGE,
        HYBRID;
    }

    public enum DataType {
        angle(new String[]{"0turn", "0.125turn", "0.25turn", "0.375turn", "0.5turn", "0.625turn", "0.75turn", "0.875turn", "1turn"}),
        angle_percentage,
        angular_color_hint,
        angular_color_stop,
        attr_fallback,
        attr_name,
        basic_shape,
        blend_mode,
        calc_product,
        calc_sum,
        calc_value,
        color,
        color_stop,
        color_stop_angle,
        counter_style,
        custom_ident,
        dimension,
        filter_function,
        flex,
        frequency,
        frequency_percentage,
        gradient,
        ident,
        image,
        integer(Pattern.compile("\\d")),
        length,
        length_percentage,
        number(Pattern.compile("\\f")),
        number_percentage,
        percentage,
        position,
        quote,
        ratio,
        resolution,
        shape_box,
        shape_radius,
        string,
        time,
        time_percentage,
        timing_function,
        toggle_value,
        transform_function,
        type_or_unit,
        url,
        url_modifier,
        zero;

        private final DataTypeFlavor flavor;
        private final List<String> list;
        private final Pattern pattern;
        private final Double min;
        private final Double max;

        DataType() {
            this.flavor = DataTypeFlavor.PATTERN;
            this.list = null;
            this.pattern = Pattern.compile("^.*$");
            this.min = null;
            this.max = null;
        }
        DataType(String[] list) {
            this.flavor = DataTypeFlavor.LIST;
            this.list = Arrays.asList(list);
            this.pattern = null;
            this.min = null;
            this.max = null;
        }

        DataType(Pattern pattern) {
            this.flavor = DataTypeFlavor.PATTERN;
            this.pattern = pattern;
            this.list = null;
            this.min = null;
            this.max = null;
        }

        DataType(Integer min, Integer max) {
            this.flavor = DataTypeFlavor.RANGE;
            this.list = null;
            this.pattern = null;
            this.min = min.doubleValue();
            this.max = max.doubleValue();
        }

        DataType(Double min, Double max) {
            this.flavor = DataTypeFlavor.RANGE;
            this.list = null;
            this.pattern = null;
            this.min = min;
            this.max = max;
        }

        public DataTypeFlavor flavor() {
            return this.flavor;
        }

        public Pattern pattern() {
            return this.pattern;
        }

        public List<String> list() {
            return this.list;
        }

        public Double min() {
            return this.min;
        }

        public Double max() {
            return this.max;
        }
    }

    public enum Property implements Style {
        additive_symbols("@counter-style"),
        align_content,
        align_items,
        align_self,
        all,
        animation,
        animation_delay,
        animation_direction,
        animation_duration,
        animation_fill_mode,
        animation_iteration_count,
        animation_name,
        animation_play_state,
        animation_timing_function,
        backdrop_filter,
        backface_visibility,
        background,
        background_attachment,
        background_blend_mode,
        background_clip,
        background_color,
        background_image,
        background_origin,
        background_position,
        background_repeat,
        background_size,
        bleed("@page"),
        block_size,
        border,
        border_block,
        border_block_color,
        border_block_end,
        border_block_end_color,
        border_block_end_style,
        border_block_end_width,
        border_block_start,
        border_block_start_color,
        border_block_start_style,
        border_block_start_width,
        border_block_style,
        border_block_width,
        border_bottom,
        border_bottom_color,
        border_bottom_left_radius,
        border_bottom_right_radius,
        border_bottom_style,
        border_bottom_width,
        border_collapse,
        border_color,
        border_end_end_radius,
        border_end_start_radius,
        border_image,
        border_image_outset,
        border_image_repeat,
        border_image_slice,
        border_image_source,
        border_image_width,
        border_inline,
        border_inline_color,
        border_inline_end,
        border_inline_end_color,
        border_inline_end_style,
        border_inline_end_width,
        border_inline_start,
        border_inline_start_color,
        border_inline_start_style,
        border_inline_start_width,
        border_inline_style,
        border_inline_width,
        border_left,
        border_left_color,
        border_left_style,
        border_left_width,
        border_radius,
        border_right,
        border_right_color,
        border_right_style,
        border_right_width,
        border_spacing,
        border_start_end_radius,
        border_start_start_radius,
        border_style,
        border_top,
        border_top_color,
        border_top_left_radius,
        border_top_right_radius,
        border_top_style,
        border_top_width,
        border_width,
        bottom,
        box_decoration_break,
        box_shadow,
        box_sizing,
        break_after,
        break_before,
        break_inside,
        caption_side,
        caret_color,
        ch,
        clear,
        clip,
        clip_path,
        cm,
        color,
        color_adjust,
        column_count,
        column_fill,
        column_gap,
        column_rule,
        column_rule_color,
        column_rule_style,
        column_rule_width,
        column_span,
        column_width,
        columns,
        contain,
        content,
        counter_increment,
        counter_reset,
        counter_set,
        cursor,
        deg,
        direction,
        display,
        dpcm,
        dpi,
        dppx,
        em,
        empty_cells,
        ex,
        fallback("@counter-style"),
        filter,
        flex,
        flex_basis,
        flex_direction,
        flex_flow,
        flex_grow,
        flex_shrink,
        flex_wrap,
        float__,
        font,
        //font_family,
        font_family("@font-face"),
        //font_feature_settings,
        font_feature_settings("@font-face"),
        font_kerning,
        font_language_override,
        font_optical_sizing,
        font_size,
        font_size_adjust,
        //font_stretch,
        font_stretch("@font-face"),
        //font_style,
        font_style("@font-face"),
        font_synthesis,
        //font_variant,
        font_variant("@font-face"),
        font_variant_alternates,
        font_variant_caps,
        font_variant_east_asian,
        font_variant_ligatures,
        font_variant_numeric,
        font_variant_position,
        //font_variation_settings,
        font_variation_settings("@font-face"),
        //font_weight,
        font_weight("@font-face"),
        fr,
        gap,
        grad,
        grid,
        grid_area,
        grid_auto_columns,
        grid_auto_flow,
        grid_auto_rows,
        grid_column,
        grid_column_end,
        grid_column_start,
        grid_row,
        grid_row_end,
        grid_row_start,
        grid_template,
        grid_template_areas,
        grid_template_columns,
        grid_template_rows,
        Hz,
        hanging_punctuation,
        //height,
        height("@viewport"),
        hyphens,
        image_orientation,
        image_rendering,
        in,
        inherit,
        initial,
        inline_size,
        inset,
        inset_block,
        inset_block_end,
        inset_block_start,
        inset_inline,
        inset_inline_end,
        inset_inline_start,
        isolation,
        justify_content,
        justify_items,
        justify_self,
        kHz,
        left,
        letter_spacing,
        line_break,
        line_height,
        list_style,
        list_style_image,
        list_style_position,
        list_style_type,
        margin,
        margin_block,
        margin_block_end,
        margin_block_start,
        margin_bottom,
        margin_inline,
        margin_inline_end,
        margin_inline_start,
        margin_left,
        margin_right,
        margin_top,
        marks("@page"),
        mask,
        mask_border,
        mask_border_mode,
        mask_border_outset,
        mask_border_repeat,
        mask_border_slice,
        mask_border_source,
        mask_border_width,
        mask_clip,
        mask_composite,
        mask_image,
        mask_mode,
        mask_origin,
        mask_position,
        mask_repeat,
        mask_size,
        mask_type,
        max_block_size,
        //max_height,
        max_height("@viewport"),
        max_inline_size,
        //max_width,
        max_width("@viewport"),
        max_zoom("@viewport"),
        min_block_size,
        //min_height,
        min_height("@viewport"),
        min_inline_size,
        //min_width,
        min_width("@viewport"),
        min_zoom("@viewport"),
        mix_blend_mode,
        mm,
        ms,
        negative("@counter-style"),
        object_fit,
        object_position,
        offset,
        offset_anchor,
        offset_distance,
        offset_path,
        offset_rotate,
        opacity,
        order,
        orientation("@viewport"),
        orphans,
        outline,
        outline_color,
        outline_offset,
        outline_style,
        outline_width,
        overflow,
        overflow_anchor,
        overflow_block,
        overflow_inline,
        overflow_wrap,
        overflow_x,
        overflow_y,
        overscroll_behavior,
        overscroll_behavior_block,
        overscroll_behavior_inline,
        overscroll_behavior_x,
        overscroll_behavior_y,
        pad("@counter-style"),
        padding,
        padding_block,
        padding_block_end,
        padding_block_start,
        padding_bottom,
        padding_inline,
        padding_inline_end,
        padding_inline_start,
        padding_left,
        padding_right,
        padding_top,
        page_break_after,
        page_break_before,
        page_break_inside,
        paint_order,
        pc,
        perspective,
        perspective_origin,
        place_content,
        place_items,
        place_self,
        pointer_events,
        position,
        prefix("@counter-style"),
        pt,
        px,
        quotes,
        rad,
        range("@counter-style"),
        rem,
        resize,
        revert,
        right,
        rotate,
        row_gap,
        s,
        scale,
        scroll_behavior,
        scroll_margin,
        scroll_margin_block,
        scroll_margin_block_end,
        scroll_margin_block_start,
        scroll_margin_bottom,
        scroll_margin_inline,
        scroll_margin_inline_end,
        scroll_margin_inline_start,
        scroll_margin_left,
        scroll_margin_right,
        scroll_margin_top,
        scroll_padding,
        scroll_padding_block,
        scroll_padding_block_end,
        scroll_padding_block_start,
        scroll_padding_bottom,
        scroll_padding_inline,
        scroll_padding_inline_end,
        scroll_padding_inline_start,
        scroll_padding_left,
        scroll_padding_right,
        scroll_padding_top,
        scroll_snap_align,
        scroll_snap_stop,
        scroll_snap_type,
        scrollbar_color,
        scrollbar_width,
        shape_image_threshold,
        shape_margin,
        shape_outside,
        size("@page"),
        speak_as("@counter-style"),
        src("@font-face"),
        suffix("@counter-style"),
        symbols("@counter-style"),
        system("@counter-style"),
        tab_size,
        table_layout,
        text_align,
        text_align_last,
        text_combine_upright,
        text_decoration,
        text_decoration_color,
        text_decoration_line,
        text_decoration_skip_ink,
        text_decoration_style,
        text_decoration_thickness,
        text_emphasis,
        text_emphasis_color,
        text_emphasis_position,
        text_emphasis_style,
        text_indent,
        text_justify,
        text_orientation,
        text_overflow,
        text_rendering,
        text_shadow,
        text_transform,
        text_underline_offset,
        text_underline_position,
        top,
        touch_action,
        transform,
        transform_box,
        transform_origin,
        transform_style,
        transition,
        transition_delay,
        transition_duration,
        transition_property,
        transition_timing_function,
        translate,
        turn,
        unicode_bidi,
        unicode_range("@font-face"),
        unset,
        user_zoom("@viewport"),
        vertical_align,
        vh,
        viewport_fit("@viewport"),
        visibility,
        vmax,
        vmin,
        vw,
        white_space,
        widows,
        //width,
        width("@viewport"),
        will_change,
        word_break,
        word_spacing,
        word_wrap,
        writing_mode,
        x,
        z_index,
        zoom("@viewport");

        private final String propertyName;
        private final String atRule;
        Property(String atRule) {
            this.propertyName = this.name().replace("__", "").replace("_", "-");
            this.atRule = atRule;
        }

        Property() {
            this.propertyName = this.name().replace("__", "").replace("_", "-");
            this.atRule = null;
        }

        public String atRule() {
            return this.atRule;
        }

        @Override
        public String toString() {
            return this.propertyName;
        }

        @Override
        public String render() {
            return this.toString();
        }

        @Override
        public CSS.Role role() {
            return Role.PROPERTY;
        }

    }
}
