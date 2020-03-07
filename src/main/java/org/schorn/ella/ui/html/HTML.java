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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author bschorn
 */
public enum HTML {
    ELEMENT(Element.class, DOM.HTMLElement),
    SINGLE(SingleElement.class, DOM.HTMLElement),
    A(A.class, DOM.HTMLAnchorElement),
    ABBR(Abbr.class, DOM.HTMLElement),
    ACRONYM(Acronym.class, DOM.HTMLElement),
    ADDRESS(Address.class, DOM.HTMLElement),
    APPLET(Applet.class, DOM.HTMLElement),
    AREA(Area.class, DOM.HTMLAreaElement),
    ARTICLE(Article.class, DOM.HTMLElement),
    ASIDE(Aside.class, DOM.HTMLElement),
    AUDIO(Audio.class, DOM.HTMLAudioElement),
    B(B.class, DOM.HTMLElement),
    BASE(Base.class, DOM.HTMLBaseElement),
    BASEFONT(Basefont.class, DOM.HTMLBaseFontElement),
    BB(Bb.class, DOM.HTMLElement),
    BDO(Bdo.class, DOM.HTMLElement),
    BIG(Big.class, DOM.HTMLElement),
    BLOCKQUOTE(Blockquote.class, DOM.HTMLElement),
    BODY(Body.class, DOM.HTMLBodyElement),
    BR(Br.class, DOM.HTMLBRElement),
    BUTTON(Button.class, DOM.HTMLButtonElement),
    CANVAS(Canvas.class, DOM.HTMLCanvasElement),
    CAPTION(Caption.class, DOM.HTMLTableCaptionElement),
    CENTER(Center.class, DOM.HTMLElement),
    CITE(Cite.class, DOM.HTMLElement),
    CODE(Code.class, DOM.HTMLElement),
    COL(Col.class, DOM.HTMLElement),
    COLGROUP(Colgroup.class, DOM.HTMLElement),
    COMMAND(Command.class, DOM.HTMLElement),
    DATAGRID(Datagrid.class, DOM.HTMLElement),
    DATALIST(Datalist.class, DOM.HTMLDataListElement),
    DD(Dd.class, DOM.HTMLElement),
    DEL(Del.class, DOM.HTMLElement),
    DETAILS(Details.class, DOM.HTMLElement),
    DIALOG(Dialog.class, DOM.HTMLDialgElement),
    DIR(Dir.class, DOM.HTMLElement),
    DIV(Div.class, DOM.HTMLDivElement),
    DFN(Dfn.class, DOM.HTMLElement),
    DL(Dl.class, DOM.HTMLDListElement),
    DT(Dt.class, DOM.HTMLElement),
    EM(Em.class, DOM.HTMLElement),
    EMBED(Embed.class, DOM.HTMLEmbedElement),
    FIELDSET(Fieldset.class, DOM.HTMLFieldSetElement),
    FIGURE(Figure.class, DOM.HTMLElement),
    FONT(Font.class, DOM.HTMLElement),
    FOOTER(Footer.class, DOM.HTMLElement),
    FORM(Form.class, DOM.HTMLFormElement),
    FRAME(Frame.class, DOM.HTMLElement),
    FRAMESET(Frameset.class, DOM.HTMLFrameSetElement),
    H1(H1.class, DOM.HTMLElement),
    H2(H2.class, DOM.HTMLElement),
    H3(H3.class, DOM.HTMLElement),
    H4(H4.class, DOM.HTMLElement),
    H5(H5.class, DOM.HTMLElement),
    H6(H6.class, DOM.HTMLElement),
    HEAD(Head.class, DOM.HTMLHeadElement),
    HEADER(Header.class, DOM.HTMLElement),
    HGROUP(Hgroup.class, DOM.HTMLElement),
    HR(Hr.class, DOM.HTMLHRElement),
    HTML(Page.class, DOM.HTMLHtmlElement),
    I(I.class, DOM.HTMLElement),
    IFRAME(Iframe.class, DOM.HTMLIFrameElement),
    IMG(Img.class, DOM.HTMLImageElement),
    INPUT(Input.class, DOM.HTMLInputElement),
    INS(Ins.class, DOM.HTMLElement),
    ISINDEX(Isindex.class, DOM.HTMLElement),
    KDB(Kdb.class, DOM.HTMLElement),
    LABEL(Label.class, DOM.HTMLLabelElement),
    LEGEND(Legend.class, DOM.HTMLLegendElement),
    LI(Li.class, DOM.HTMLLIElement),
    LINK(Link.class, DOM.HTMLLinkElement),
    MARK(Mark.class, DOM.HTMLElement),
    MAP(Map.class, DOM.HTMLMapElement),
    MENU(Menu.class, DOM.HTMLElement),
    META(Meta.class, DOM.HTMLMetaElement),
    METER(Meter.class, DOM.HTMLMeterElement),
    NAV(Nav.class, DOM.HTMLElement),
    NOFRAMES(Noframes.class, DOM.HTMLElement),
    NOSCRIPT(Noscript.class, DOM.HTMLElement),
    OBJECT(Object.class, DOM.HTMLObjectElement),
    OL(Ol.class, DOM.HTMLOListElement),
    OPTGROUP(Optgroup.class, DOM.HTMLOptGroupElement),
    OPTION(Option.class, DOM.HTMLOptionElement),
    OUTPUT(Output.class, DOM.HTMLOutputElement),
    P(P.class, DOM.HTMLParagraphElement),
    PARAM(Param.class, DOM.HTMLParamElement),
    PRE(Pre.class, DOM.HTMLPreElement),
    PROGRESS(Progress.class, DOM.HTMLProgressElement),
    Q(Q.class, DOM.HTMLQuoteElement),
    RUBY(Ruby.class, DOM.HTMLElement),
    RP(Rp.class, DOM.HTMLElement),
    RT(Rt.class, DOM.HTMLElement),
    S(S.class, DOM.HTMLElement),
    SAMP(Samp.class, DOM.HTMLElement),
    SCRIPT(Script.class, DOM.HTMLScriptElement),
    SECTION(Section.class, DOM.HTMLElement),
    SELECT(Select.class, DOM.HTMLSelectElement),
    SMALL(Small.class, DOM.HTMLElement),
    SOURCE(Source.class, DOM.HTMLSourceElement),
    SPAN(Span.class, DOM.HTMLSpanElement),
    STRIKE(Strike.class, DOM.HTMLElement),
    STRONG(Strong.class, DOM.HTMLElement),
    STYLE(Style.class, DOM.HTMLStyleElement),
    SUB(Sub.class, DOM.HTMLElement),
    SUP(Sup.class, DOM.HTMLElement),
    TABLE(Table.class, DOM.HTMLTableElement),
    TBODY(Tbody.class, DOM.HTMLElement),
    TD(Td.class, DOM.HTMLTableCellElement),
    TEXTAREA(Textarea.class, DOM.HTMLElement),
    TFOOT(Tfoot.class, DOM.HTMLElement),
    TH(Th.class, DOM.HTMLTableCellElement),
    THEAD(Thead.class, DOM.HTMLElement),
    TIME(Time.class, DOM.HTMLTimeElement),
    TITLE(Title.class, DOM.HTMLTitleElement),
    TR(Tr.class, DOM.HTMLTableRowElement),
    TT(Tt.class, DOM.HTMLElement),
    U(U.class, DOM.HTMLElement),
    UL(Ul.class, DOM.HTMLUListElement),
    VAR(Var.class, DOM.HTMLElement),
    VIDEO(Video.class, DOM.HTMLVideoElement),
    XMP(Xmp.class, DOM.HTMLElement);

    private final Class<?> interfaceOf;
    private final DOM domInterface;
    private Class<?> implOf = null;

    HTML(Class<?> interfaceOf, DOM domInterface) {
        this.interfaceOf = interfaceOf;
        this.domInterface = domInterface;
    }

    public String tag() {
        return this.name().toLowerCase();
    }

    public Class<?> interfaceOf() {
        return this.interfaceOf;
    }

    public DOM domInterface() {
        return this.domInterface;
    }

    public void setImpl(Class<?> implOf) {
        this.implOf = implOf;
    }

    public Element createElement(Object... params) throws Exception {
        return createInstance(this, params);
    }

    private <T> T create(Class<T> classOfT, Object... params) throws Exception {
        return (T) createInstance(this, params);
    }

    static final Logger LGR = LoggerFactory.getLogger(HTML.class);

    static private <T> T createInstance(HTML html, Object... params) throws Exception {
        Class<?> classFor = html.implOf;
        if (classFor == null) {
            // ERROR
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
                    // ERROR
                }
                break;
            }
        }
        if (newInstance == null) {
            StringJoiner joiner = new StringJoiner(", ", "[", "] ");
            for (Object o : params) {
                joiner.add(String.format("(%s) %s", o.getClass().getSimpleName(), o.toString()));
            }
            /*
            LGR.error("{}.newInstance() - there is no constructor available to match the parameters {} specificed for interface {}",
                HTML.class.getSimpleName(),
                joiner.toString(),
                html.interfaceOf.getSimpleName());
             */
        }
        return newInstance;
    }

    public interface AttributeType {

        public enum ValueType {
            AVOID,
            FLAG,
            LIST,
            PATTERN;
        }
        String tag();

        ValueType type();
    }

    static public enum GlobalAttributes implements AttributeType {
        ACCESSKEY(AttributeType.ValueType.AVOID, Pattern.compile("[A-Za-z]")),
        AUTOCAPITALIZE(AttributeType.ValueType.LIST, new String[]{"off", "none", "on", "sentences", "words", "characters"}),
        CLASS(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
        CONTENTEDITABLE(AttributeType.ValueType.LIST, new String[]{"true", "false"}),
        DATA(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
        DIR(AttributeType.ValueType.LIST, new String[]{"ltr", "rtl", "auto"}),
        DRAGGABLE(AttributeType.ValueType.LIST, new String[]{"true", "false"}),
        HIDDEN(AttributeType.ValueType.FLAG, new String[]{}),
        ID(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
        INPUTMODE(AttributeType.ValueType.LIST, new String[]{"none", "text", "decimal", "numeric", "tel", "search", "email", "url"}),
        IS(AttributeType.ValueType.PATTERN, Pattern.compile("^.*[-].*$")),
        ITEMID(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
        ITEMPROP(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
        ITEMREF(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
        ITEMSCOPE(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
        ITEMTYPE(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
        LANG(AttributeType.ValueType.PATTERN, Pattern.compile("^[a-z]+{2,3}.*$")),
        PART(AttributeType.ValueType.AVOID, Pattern.compile("[A-Za-z]")),
        SLOT(AttributeType.ValueType.FLAG, new String[]{}),
        SPELLCHECK(AttributeType.ValueType.LIST, new String[]{"true", "false"}),
        STYLE(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
        TABINDEX(AttributeType.ValueType.PATTERN, Pattern.compile("^(\\d|-1)$")),
        TITLE(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$"));

        private final String attributeTag;
        private final AttributeType.ValueType attributesType;
        private final List<String> allowables;
        private final Pattern pattern;

        GlobalAttributes(AttributeType.ValueType attributesType, String[] allowables) {
            this.attributeTag = this.name().toLowerCase();
            this.attributesType = attributesType;
            this.allowables = Arrays.asList(allowables);
            this.pattern = Pattern.compile("^.*$");
        }
        GlobalAttributes(AttributeType.ValueType attributesType, Pattern pattern) {
            this.attributeTag = this.name().toLowerCase();
            this.attributesType = attributesType;
            this.allowables = new ArrayList<>(0);
            this.pattern = pattern;
        }

        @Override
        public String tag() {
            return this.attributeTag;
        }

        @Override
        public AttributeType.ValueType type() {
            return this.attributesType;
        }

        public List<String> allowables() {
            return this.allowables;
        }

        public Pattern pattern() {
            return this.pattern;
        }

        static public List<AttributeType> attributes() {
            return Arrays.asList(GlobalAttributes.values());
        }
    }

    static public enum TagOmission {
        None,
        EndOptional,
        EndMustBeOmitted;
    }

    static public enum ContentCategory {
        Metadata("base", "link", "meta", "noscript", "script", "style", "template", "title"),
        Flow("a", "abbr", "address", "area", "articlea", "side", "audio", "b", "bdi", "bdo",
                "blockquote", "br", "button", "canvas", "cite", "code", "data", "datalist",
                "del", "details", "dfn", "dialog", "div", "dl", "em", "embed", "fieldset",
                "figure", "footer", "form", "h1", "h2", "h3", "h4", "h5", "h6", "header",
                "hgroup", "hr", "i", "iframe", "img", "input", "ins", "kbd", "label", "link",
                "main", "map", "mark", "math", "menu", "meta", "meter", "nav", "noscript",
                "object", "ol", "output", "p", "picture", "pre", "progress", "q", "ruby",
                "s", "samp", "script", "section", "select", "slot", "small", "span", "strong",
                "sub", "sup", "svg", "table", "template", "textarea", "time", "u", "ul", "var",
                "video", "wbr", "autonomoustext"),
        Sectioning("article", "aside", "nav", "section"),
        Heading("h1", "h2", "h3", "h4", "h5", "h6", "hgroup"),
        Phrasing("a", "abbr", "area", "audio", "b", "bdi", "bdo", "br", "button", "canvas",
                "cite", "code", "data", "datalist", "del", "dfn", "em", "embed", "i", "iframe",
                "img", "input", "ins", "kbd", "label", "link", "map", "mark", "math", "meta",
                "meter", "noscript", "object", "output", "picture", "progress", "q", "ruby",
                "s", "samp", "script", "select", "slot", "small", "span", "strong", "sub",
                "sup", "svg", "template", "textarea", "time", "u", "var", "video", "wbr",
                "text"),
        Embedded("audio", "canvas", "embed", "iframe", "img", "math", "object", "picture",
                "svg", "video"),
        Interactive("a", "audio", "button", "details", "embed", "iframe", "img", "input",
                "label", "object", "select", "textarea", "video"),
        Palpable("a", "abbr", "address", "article", "aside", "audio", "b", "bdi", "bdo",
                "blockquote", "button", "canvas", "cite", "code", "data", "details", "dfn",
                "div", "dl", "em", "embed", "fieldset", "figure", "footer", "form", "h1", "h2",
                "h3", "h4", "h5", "h6", "header", "hgroup", "i", "iframe", "img", "input",
                "ins", "kbd", "label", "main", "map", "mark", "math", "menu", "meter", "nav",
                "object", "ol", "output", "p", "pre", "progress", "q", "ruby", "s", "samp",
                "section", "select", "small", "span", "strong", "sub", "sup", "svg", "table",
                "textarea", "time", "u", "ul", "var", "video", "text"),
        Form("button", "fieldset", "input", "label", "meter", "object", "output", "progress", "select", "textarea");

        private final List<String> members;

        ContentCategory(String... tags) {
            List<String> members0 = new ArrayList<>();
            for (String tag : Arrays.asList(tags)) {
                members0.add(tag);
            }
            this.members = Collections.unmodifiableList(members0);
        }

        public List<String> members() {
            return this.members;
        }


        static public List<ContentCategory> parse(HTML html) {
            List<ContentCategory> list = new ArrayList<>();
            for (ContentCategory contentCategory : ContentCategory.values()) {
                if (contentCategory.members().contains(html)) {
                    list.add(contentCategory);
                }
            }
            return list;
        }
    }

    public interface Render {

        String render();
    }

    public interface Attribute extends Render {

        static public Attribute create(AttributeType attributeType, String value) throws Exception {
            return (Attribute) new Impl(attributeType, value);
        }
        public String name();

        public AttributeType attributeType();

        public String value();

        public void setValue(String value);

        public void addValue(Number value);

        public void addValue(String value);

        static class Impl implements Attribute {

            protected final AttributeType attributeType;
            protected java.lang.Object value;
            String rendered = null;

            public Impl(AttributeType attributeType, String value) {
                this.attributeType = attributeType;
                this.value = value;
                this.setValue0(this.value);
            }

            @Override
            public String name() {
                return this.attributeType.tag();
            }

            @Override
            public AttributeType attributeType() {
                return this.attributeType;
            }

            @Override
            public String value() {
                return this.value.toString();
            }

            @Override
            public void setValue(String value) {
                this.value = value;
                this.setValue0(this.value);
            }

            @Override
            public void addValue(String value) {
                if (value == null) {
                    return;
                }
                if (this.value == null) {
                    this.setValue0(value);
                    return;
                }

            }

            @Override
            public void addValue(Number value) {
                if (value == null) {
                    return;
                }
                if (this.value == null) {
                    this.setValue0(value);
                    return;
                }
                if (value instanceof Number && this.value instanceof Number) {
                    if (value instanceof BigDecimal && this.value instanceof BigDecimal) {
                        this.value = BigDecimal.valueOf(((Number) value).doubleValue()).add(BigDecimal.valueOf(((Number) this.value).doubleValue()));
                    } else if (value instanceof Double && this.value instanceof Double) {
                        this.value = ((Double) value) + ((Double) this.value);
                    } else if (value instanceof Float && this.value instanceof Float) {
                        this.value = ((Float) value) + ((Float) this.value);
                    } else if (value instanceof Integer && this.value instanceof Integer) {
                        this.value = ((Integer) value) + ((Integer) this.value);
                    }
                }
                this.setValue0(this.value);
            }

            @Override
            public String render() {
                return this.rendered;
            }

            @Override
            public String toString() {
                return this.rendered;
            }

            /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
             *
             * 										PRIVATE
             *
             * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
            /**
             *
             * @param value
             */
            private void setValue0(java.lang.Object value) {
                if (value != null) {
                    if (value instanceof Boolean && (Boolean) value) {
                        this.rendered = String.format("%s", this.attributeType.tag());
                    } else if (value instanceof String) {
                        this.rendered = String.format("%s='%s'", this.attributeType.tag(), value);
                    } else if (value instanceof Number) {
                        if (value instanceof Double || value instanceof Float || value instanceof BigDecimal) {
                            this.rendered = String.format("%s='%f'", this.attributeType.tag(), value);
                        } else {
                            this.rendered = String.format("%s='%d'", this.attributeType.tag(), value);
                        }
                    } else if (value instanceof Temporal) {
                        if (value instanceof LocalDate) {
                            this.rendered = String.format("%s='%s'",
                                    this.attributeType.tag(),
                                    ((LocalDate) value).format(DateTimeFormatter.ISO_LOCAL_DATE));
                        } else if (value instanceof LocalTime) {
                            this.rendered = String.format("%s='%s'",
                                    this.attributeType.tag(),
                                    ((LocalTime) value).format(DateTimeFormatter.ISO_LOCAL_TIME));
                        } else if (value instanceof LocalDateTime) {
                            this.rendered = String.format("%s='%s'",
                                    this.attributeType.tag(),
                                    ((LocalDateTime) value).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                        }
                    }
                }
            }
        }
    }

    public class InvalidContentException extends Exception {

        public InvalidContentException(String message) {
            super(message);
        }
    }

    public class InvalidAttributeException extends Exception {

        public InvalidAttributeException(String message) {
            super(message);
        }
    }

    public interface Element extends Render {

        static public Element create(Object... params) throws Exception {
            return HTML.ELEMENT.create(Element.class, params);
        }

        Element setTextContent(String content);

        Element append(Element element) throws InvalidContentException;

        Element addClass(String className);

        Element addAttribute(Attribute attribute) throws InvalidAttributeException;

        Element setId(String value) throws Exception;

        String getId();

        String tag();

        List<Element> children();

        default List<ContentCategory> contentCategories() {
            return ContentCategory.parse(HTML.valueOf(this.tag()));
        }

        default TagOmission tagOmission() {
            return TagOmission.None;
        }

        default DOM domInterface() {
            return HTML.valueOf(this.tag()).domInterface;
        }

        default boolean isPermittedContent(Element element) {
            return true;
        }
    }

    public interface SingleElement extends Element {

        static public SingleElement create(Object... params) throws Exception {
            return HTML.SINGLE.create(SingleElement.class, params);
        }
    }

    public interface Head extends Element {

        static public Head create(Object... params) throws Exception {
            return HTML.HEAD.create(Head.class, params);
        }
    }

    public interface Meta extends Element {

        static public Meta create(Object... params) throws Exception {
            return HTML.META.create(Meta.class, params);
        }

        Meta setCharset(String charset) throws Exception;
    }

    public interface Style extends Element {

        static public Style create(Object... params) throws Exception {
            return HTML.STYLE.create(Style.class, params);
        }

        void append(CSS.Element cssElement);
    }

    public interface Script extends Element {

        static public Script create(Object... params) throws Exception {
            return HTML.SCRIPT.create(Script.class, params);
        }

        void append(String code);
    }

    public interface Body extends Element {

        static public Body create(Object... params) throws Exception {
            return HTML.BODY.create(Body.class, params);
        }
    }

    public interface Page extends Element {

        static public Page create(Object... params) throws Exception {
            return HTML.HTML.create(Page.class, params);
        }

        Head htmlHead();

        Body htmlBody();
    }

    public interface A extends Element {
        static public A create(Object... params) throws Exception {
            return HTML.A.create(A.class, params);
        }

    }

    /**
     * It's certainly not required that all abbreviations be marked up using
     * <abbr>. There are, though, a few cases where it's helpful to do so: When
     * an abbreviation is used and you want to provide an expansion or
     * definition outside the flow of the document's content, use <abbr> with an
     * appropriate title. To define an abbreviation which may be unfamiliar to
     * the reader, present the term using <abbr> and either a title attribute or
     * inline text providing the definition. When an abbreviation's presence in
     * the text needs to be semantically noted, the <abbr> element is useful.
     * This can be used, in turn, for styling or scripting purposes. You can use
     * <abbr> in concert with <dfn> to establish definitions for terms which are
     * abbreviations or acronyms. See the example Defining an abbreviation
     * below.
     */
    public interface Abbr extends Element {

        static public Abbr create(Object... params) throws Exception {
            return HTML.ABBR.create(Abbr.class, params);
        }
    }

    public interface Address extends Element {

        static public Address create(Object... params) throws Exception {
            return HTML.ADDRESS.create(Address.class, params);
        }
    }

    public interface Br extends Element {

        static public Br create(Object... params) throws Exception {
            return HTML.BR.create(Br.class, params);
        }

        @Override
        default boolean isPermittedContent(Element element) {
            return false;
        }
    }

    public interface Div extends Element {

        static public Div create(Object... params) throws Exception {
            return HTML.DIV.create(Div.class, params);
        }
    }

    public interface Table extends Element {

        static public Table create(Object... params) throws Exception {
            return HTML.TABLE.create(Table.class, params);
        }
    }

    public interface Tr extends Element {

        static public Tr create(Object... params) throws Exception {
            return HTML.TR.create(Tr.class, params);
        }
    }

    public interface Th extends Element {
        static public Th create(Object... params) throws Exception {
            return HTML.TH.create(Th.class, params);
        }
    }

    public interface Td extends Element {
        static public Td create(Object... params) throws Exception {
            return HTML.TD.create(Td.class, params);
        }
    }

    public interface Article extends Element {
        static public Article create(Object... params) throws Exception {
            return HTML.ARTICLE.create(Article.class, params);
        }
    }

    public interface Section extends Element {
        static public Section create(Object... params) throws Exception {
            return HTML.SECTION.create(Section.class, params);
        }
    }

    public interface Option extends Element {
        static public Option create(Object... params) throws Exception {
            return HTML.OPTION.create(Option.class, params);
        }
    }

    public interface Fieldset extends Element {
        static public Fieldset create(Object... params) throws Exception {
            return HTML.FIELDSET.create(Fieldset.class, params);
        }
    }

    public interface Form extends Element {

        static public Form create(Object... params) throws Exception {
            return HTML.FORM.create(Form.class, params);
        }
    }

    public interface Input extends SingleElement {

        static public Input create(Object... params) throws Exception {
            return HTML.INPUT.create(Input.class, params);
        }
    }

    public interface Label extends Element {

        static public Label create(Object... params) throws Exception {
            return HTML.LABEL.create(Label.class, params);
        }
    }
    /*
    To be continued...
     */

    public interface Single extends Element {

        static public Single create(Object... params) throws Exception {
            return HTML.SINGLE.create(Single.class, params);
        }
    }

    public interface Html extends Element {

        static public Html create(Object... params) throws Exception {
            return HTML.HTML.create(Html.class, params);
        }
    }

    public interface Acronym extends Element {

        static public Acronym create(Object... params) throws Exception {
            return HTML.ACRONYM.create(Acronym.class, params);
        }
    }

    public interface Applet extends Element {

        static public Applet create(Object... params) throws Exception {
            return HTML.APPLET.create(Applet.class, params);
        }
    }

    public interface Area extends Element {

        static public Area create(Object... params) throws Exception {
            return HTML.AREA.create(Area.class, params);
        }
    }

    public interface Aside extends Element {

        static public Aside create(Object... params) throws Exception {
            return HTML.ASIDE.create(Aside.class, params);
        }
    }

    public interface Audio extends Element {

        static public Audio create(Object... params) throws Exception {
            return HTML.AUDIO.create(Audio.class, params);
        }
    }

    public interface B extends Element {

        static public B create(Object... params) throws Exception {
            return HTML.B.create(B.class, params);
        }
    }

    public interface Base extends Element {

        static public Base create(Object... params) throws Exception {
            return HTML.BASE.create(Base.class, params);
        }
    }

    public interface Basefont extends Element {

        static public Basefont create(Object... params) throws Exception {
            return HTML.BASEFONT.create(Basefont.class, params);
        }
    }

    public interface Bb extends Element {

        static public Bb create(Object... params) throws Exception {
            return HTML.BB.create(Bb.class, params);
        }
    }

    public interface Bdo extends Element {

        static public Bdo create(Object... params) throws Exception {
            return HTML.BDO.create(Bdo.class, params);
        }
    }

    public interface Big extends Element {

        static public Big create(Object... params) throws Exception {
            return HTML.BIG.create(Big.class, params);
        }
    }

    public interface Blockquote extends Element {

        static public Blockquote create(Object... params) throws Exception {
            return HTML.BLOCKQUOTE.create(Blockquote.class, params);
        }
    }

    public interface Button extends Element {

        static public Button create(Object... params) throws Exception {
            return HTML.BUTTON.create(Button.class, params);
        }
    }

    public interface Canvas extends Element {

        static public Canvas create(Object... params) throws Exception {
            return HTML.CANVAS.create(Canvas.class, params);
        }
    }

    public interface Caption extends Element {

        static public Caption create(Object... params) throws Exception {
            return HTML.CAPTION.create(Caption.class, params);
        }
    }

    public interface Center extends Element {

        static public Center create(Object... params) throws Exception {
            return HTML.CENTER.create(Center.class, params);
        }
    }

    public interface Cite extends Element {

        static public Cite create(Object... params) throws Exception {
            return HTML.CITE.create(Cite.class, params);
        }
    }

    public interface Code extends Element {

        static public Code create(Object... params) throws Exception {
            return HTML.CODE.create(Code.class, params);
        }
    }

    public interface Col extends Element {

        static public Col create(Object... params) throws Exception {
            return HTML.COL.create(Col.class, params);
        }
    }

    public interface Colgroup extends Element {

        static public Colgroup create(Object... params) throws Exception {
            return HTML.COLGROUP.create(Colgroup.class, params);
        }
    }

    public interface Command extends Element {

        static public Command create(Object... params) throws Exception {
            return HTML.COMMAND.create(Command.class, params);
        }
    }

    public interface Datagrid extends Element {

        static public Datagrid create(Object... params) throws Exception {
            return HTML.DATAGRID.create(Datagrid.class, params);
        }
    }

    public interface Datalist extends Element {

        static public Datalist create(Object... params) throws Exception {
            return HTML.DATALIST.create(Datalist.class, params);
        }
    }

    public interface Dd extends Element {

        static public Dd create(Object... params) throws Exception {
            return HTML.DD.create(Dd.class, params);
        }
    }

    public interface Del extends Element {

        static public Del create(Object... params) throws Exception {
            return HTML.DEL.create(Del.class, params);
        }
    }

    public interface Details extends Element {

        static public Details create(Object... params) throws Exception {
            return HTML.DETAILS.create(Details.class, params);
        }
    }

    public interface Dialog extends Element {

        static public Dialog create(Object... params) throws Exception {
            return HTML.DIALOG.create(Dialog.class, params);
        }
    }

    public interface Dir extends Element {

        static public Dir create(Object... params) throws Exception {
            return HTML.DIR.create(Dir.class, params);
        }
    }

    public interface Dfn extends Element {

        static public Dfn create(Object... params) throws Exception {
            return HTML.DFN.create(Dfn.class, params);
        }
    }

    public interface Dl extends Element {

        static public Dl create(Object... params) throws Exception {
            return HTML.DL.create(Dl.class, params);
        }
    }

    public interface Dt extends Element {

        static public Dt create(Object... params) throws Exception {
            return HTML.DT.create(Dt.class, params);
        }
    }

    public interface Em extends Element {

        static public Em create(Object... params) throws Exception {
            return HTML.EM.create(Em.class, params);
        }
    }

    public interface Embed extends Element {

        static public Embed create(Object... params) throws Exception {
            return HTML.EMBED.create(Embed.class, params);
        }
    }

    public interface Figure extends Element {

        static public Figure create(Object... params) throws Exception {
            return HTML.FIGURE.create(Figure.class, params);
        }
    }

    public interface Font extends Element {

        static public Font create(Object... params) throws Exception {
            return HTML.FONT.create(Font.class, params);
        }
    }

    public interface Footer extends Element {

        static public Footer create(Object... params) throws Exception {
            return HTML.FOOTER.create(Footer.class, params);
        }
    }

    public interface Frame extends Element {

        static public Frame create(Object... params) throws Exception {
            return HTML.FRAME.create(Frame.class, params);
        }
    }

    public interface Frameset extends Element {

        static public Frameset create(Object... params) throws Exception {
            return HTML.FRAMESET.create(Frameset.class, params);
        }
    }

    public interface H1 extends Element {

        static public H1 create(Object... params) throws Exception {
            return HTML.H1.create(H1.class, params);
        }
    }

    public interface H2 extends Element {

        static public H2 create(Object... params) throws Exception {
            return HTML.H2.create(H2.class, params);
        }
    }

    public interface H3 extends Element {

        static public H3 create(Object... params) throws Exception {
            return HTML.H3.create(H3.class, params);
        }
    }

    public interface H4 extends Element {

        static public H4 create(Object... params) throws Exception {
            return HTML.H4.create(H4.class, params);
        }
    }

    public interface H5 extends Element {

        static public H5 create(Object... params) throws Exception {
            return HTML.H5.create(H5.class, params);
        }
    }

    public interface H6 extends Element {

        static public H6 create(Object... params) throws Exception {
            return HTML.H6.create(H6.class, params);
        }
    }

    public interface Header extends Element {

        static public Header create(Object... params) throws Exception {
            return HTML.HEADER.create(Header.class, params);
        }
    }

    public interface Hgroup extends Element {

        static public Hgroup create(Object... params) throws Exception {
            return HTML.HGROUP.create(Hgroup.class, params);
        }
    }

    public interface Hr extends Element {

        static public Hr create(Object... params) throws Exception {
            return HTML.HR.create(Hr.class, params);
        }
    }

    public interface I extends Element {

        static public I create(Object... params) throws Exception {
            return HTML.I.create(I.class, params);
        }
    }

    public interface Iframe extends Element {

        static public Iframe create(Object... params) throws Exception {
            return HTML.IFRAME.create(Iframe.class, params);
        }
    }

    public interface Img extends Element {

        static public Img create(Object... params) throws Exception {
            return HTML.IMG.create(Img.class, params);
        }
    }

    public interface Ins extends Element {

        static public Ins create(Object... params) throws Exception {
            return HTML.INS.create(Ins.class, params);
        }
    }

    public interface Isindex extends Element {

        static public Isindex create(Object... params) throws Exception {
            return HTML.ISINDEX.create(Isindex.class, params);
        }
    }

    public interface Kdb extends Element {

        static public Kdb create(Object... params) throws Exception {
            return HTML.KDB.create(Kdb.class, params);
        }
    }

    public interface Legend extends Element {

        static public Legend create(Object... params) throws Exception {
            return HTML.LEGEND.create(Legend.class, params);
        }
    }

    public interface Li extends Element {

        static public Li create(Object... params) throws Exception {
            return HTML.LI.create(Li.class, params);
        }
    }

    public interface Link extends Element {

        static public Link create(Object... params) throws Exception {
            return HTML.LINK.create(Link.class, params);
        }
    }

    public interface Mark extends Element {

        static public Mark create(Object... params) throws Exception {
            return HTML.MARK.create(Mark.class, params);
        }
    }

    public interface Map extends Element {

        static public Map create(Object... params) throws Exception {
            return HTML.MAP.create(Map.class, params);
        }
    }

    public interface Menu extends Element {

        static public Menu create(Object... params) throws Exception {
            return HTML.MENU.create(Menu.class, params);
        }
    }

    public interface Meter extends Element {

        static public Meter create(Object... params) throws Exception {
            return HTML.METER.create(Meter.class, params);
        }
    }

    public interface Nav extends Element {

        static public Nav create(Object... params) throws Exception {
            return HTML.NAV.create(Nav.class, params);
        }
    }

    public interface Noframes extends Element {

        static public Noframes create(Object... params) throws Exception {
            return HTML.NOFRAMES.create(Noframes.class, params);
        }
    }

    public interface Noscript extends Element {

        static public Noscript create(Object... params) throws Exception {
            return HTML.NOSCRIPT.create(Noscript.class, params);
        }
    }

    public interface Object extends Element {

        static public Object create(Object... params) throws Exception {
            return HTML.OBJECT.create(Object.class, params);
        }
    }

    public interface Ol extends Element {

        static public Ol create(Object... params) throws Exception {
            return HTML.OL.create(Ol.class, params);
        }
    }

    public interface Optgroup extends Element {

        static public Optgroup create(Object... params) throws Exception {
            return HTML.OPTGROUP.create(Optgroup.class, params);
        }
    }

    public interface Output extends Element {

        static public Output create(Object... params) throws Exception {
            return HTML.OUTPUT.create(Output.class, params);
        }
    }

    public interface P extends Element {

        static public P create(Object... params) throws Exception {
            return HTML.P.create(P.class, params);
        }
    }

    public interface Param extends Element {

        static public Param create(Object... params) throws Exception {
            return HTML.PARAM.create(Param.class, params);
        }
    }

    public interface Pre extends Element {

        static public Pre create(Object... params) throws Exception {
            return HTML.PRE.create(Pre.class, params);
        }
    }

    public interface Progress extends Element {

        static public Progress create(Object... params) throws Exception {
            return HTML.PROGRESS.create(Progress.class, params);
        }
    }

    public interface Q extends Element {

        static public Q create(Object... params) throws Exception {
            return HTML.Q.create(Q.class, params);
        }
    }

    public interface Ruby extends Element {

        static public Ruby create(Object... params) throws Exception {
            return HTML.RUBY.create(Ruby.class, params);
        }
    }

    public interface Rp extends Element {

        static public Rp create(Object... params) throws Exception {
            return HTML.RP.create(Rp.class, params);
        }
    }

    public interface Rt extends Element {

        static public Rt create(Object... params) throws Exception {
            return HTML.RT.create(Rt.class, params);
        }
    }

    public interface S extends Element {

        static public S create(Object... params) throws Exception {
            return HTML.S.create(S.class, params);
        }
    }

    public interface Samp extends Element {

        static public Samp create(Object... params) throws Exception {
            return HTML.SAMP.create(Samp.class, params);
        }
    }

    public interface Select extends Element {

        static public Select create(Object... params) throws Exception {
            return HTML.SELECT.create(Select.class, params);
        }
    }

    public interface Small extends Element {

        static public Small create(Object... params) throws Exception {
            return HTML.SMALL.create(Small.class, params);
        }
    }

    public interface Source extends Element {

        static public Source create(Object... params) throws Exception {
            return HTML.SOURCE.create(Source.class, params);
        }
    }

    public interface Span extends Element {

        static public Span create(Object... params) throws Exception {
            return HTML.SPAN.create(Span.class, params);
        }
    }

    public interface Strike extends Element {

        static public Strike create(Object... params) throws Exception {
            return HTML.STRIKE.create(Strike.class, params);
        }
    }

    public interface Strong extends Element {

        static public Strong create(Object... params) throws Exception {
            return HTML.STRONG.create(Strong.class, params);
        }
    }

    public interface Sub extends Element {

        static public Sub create(Object... params) throws Exception {
            return HTML.SUB.create(Sub.class, params);
        }
    }

    public interface Sup extends Element {

        static public Sup create(Object... params) throws Exception {
            return HTML.SUP.create(Sup.class, params);
        }
    }

    public interface Tbody extends Element {

        static public Tbody create(Object... params) throws Exception {
            return HTML.TBODY.create(Tbody.class, params);
        }
    }

    public interface Textarea extends Element {

        static public Textarea create(Object... params) throws Exception {
            return HTML.TEXTAREA.create(Textarea.class, params);
        }
    }

    public interface Tfoot extends Element {

        static public Tfoot create(Object... params) throws Exception {
            return HTML.TFOOT.create(Tfoot.class, params);
        }
    }

    public interface Thead extends Element {

        static public Thead create(Object... params) throws Exception {
            return HTML.THEAD.create(Thead.class, params);
        }
    }

    public interface Time extends Element {

        static public Time create(Object... params) throws Exception {
            return HTML.TIME.create(Time.class, params);
        }
    }

    public interface Title extends Element {

        static public Title create(Object... params) throws Exception {
            return HTML.TITLE.create(Title.class, params);
        }
    }

    public interface Tt extends Element {

        static public Tt create(Object... params) throws Exception {
            return HTML.TT.create(Tt.class, params);
        }
    }

    public interface U extends Element {

        static public U create(Object... params) throws Exception {
            return HTML.U.create(U.class, params);
        }
    }

    public interface Ul extends Element {

        static public Ul create(Object... params) throws Exception {
            return HTML.UL.create(Ul.class, params);
        }
    }

    public interface Var extends Element {

        static public Var create(Object... params) throws Exception {
            return HTML.VAR.create(Var.class, params);
        }
    }

    public interface Video extends Element {

        static public Video create(Object... params) throws Exception {
            return HTML.VIDEO.create(Video.class, params);
        }
    }

    public interface Xmp extends Element {

        static public Xmp create(Object... params) throws Exception {
            return HTML.XMP.create(Xmp.class, params);
        }
    }

}
