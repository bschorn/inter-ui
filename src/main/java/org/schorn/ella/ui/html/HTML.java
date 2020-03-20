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
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.schorn.ella.ui.UIProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author bschorn
 */
public enum HTML {
    //ELEMENT(Element.class, DOM.HTMLElement),
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
    COMMENT(Comment.class, DOM.NA),
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
    //private Element instance = null;

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

    public Class<?> getImpl() {
        return this.implOf;
    }

    public Element createElement(Object... params) throws Exception {
        return FACTORY.createInstance(this, params);
    }

    private <T> T create(Object... params) throws Exception {
        return (T) FACTORY.createInstance(this, params);
    }

    static final Logger LGR = LoggerFactory.getLogger(HTML.class);

    public interface HtmlFactory {
        public void register();
        public <T> T createInstance(HTML html, Object... params) throws Exception;
    }

    static final HtmlFactory FACTORY;

    static {
        FACTORY = UIProvider.provider().getHTMLFactory();
    }

    public interface AttributeType {

        public enum ValueType {
            AVOID,
            FLAG,
            BOOLEAN,
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
        CONTENTEDITABLE(AttributeType.ValueType.BOOLEAN, new String[]{"true", "false"}),
        DATA(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
        DIR(AttributeType.ValueType.LIST, new String[]{"ltr", "rtl", "auto"}),
        DRAGGABLE(AttributeType.ValueType.BOOLEAN, new String[]{"true", "false"}),
        HIDDEN(AttributeType.ValueType.FLAG, new String[]{}),
        ID(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
        INPUTMODE(AttributeType.ValueType.LIST, InputMode.valuesAsString()),
        IS(AttributeType.ValueType.PATTERN, Pattern.compile("^.*[-].*$")),
        ITEMID(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
        ITEMPROP(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
        ITEMREF(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
        ITEMSCOPE(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
        ITEMTYPE(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
        LANG(AttributeType.ValueType.PATTERN, Pattern.compile("^[a-z]+{2,3}.*$")),
        PART(AttributeType.ValueType.AVOID, Pattern.compile("[A-Za-z]")),
        SLOT(AttributeType.ValueType.FLAG, new String[]{}),
        SPELLCHECK(AttributeType.ValueType.BOOLEAN, new String[]{"true", "false"}),
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

    public enum AutoCapitalize {
        OFF, NONE, ON, SENTENCES, WORDS, CHARACTERS;

        private final Attribute attribute;

        AutoCapitalize() {
            Attribute attribute0 = null;
            try {
                attribute0 = Attribute.create("contenteditable", this.value());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            this.attribute = attribute0;
        }

        public String value() {
            return this.name().toLowerCase().replace("_", "-");
        }

        public Attribute asAttribute() {
            return this.attribute;
        }

        static public String[] valuesAsString() {
            return Arrays.asList(AutoCapitalize.values()).stream()
                    .map(t -> t.value()).collect(Collectors.toList())
                    .toArray(new String[AutoCapitalize.values().length]);
        }
    }

    public enum InputMode {
        NONE,
        TEXT,
        DECIMAL,
        NUMERIC,
        TEL,
        SEARCH,
        EMAIL,
        URL;

        private final Attribute attribute;

        InputMode() {
            Attribute attribute0 = null;
            try {
                attribute0 = Attribute.create("inputmode", this.value());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            this.attribute = attribute0;
        }

        public String value() {
            return this.name().toLowerCase().replace("_", "-");
        }

        public Attribute asAttribute() {
            return this.attribute;
        }

        static public String[] valuesAsString() {
            return Arrays.asList(InputMode.values()).stream()
                    .map(t -> t.value()).collect(Collectors.toList())
                    .toArray(new String[InputMode.values().length]);
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
            return (Attribute) new Impl(attributeType.tag(), value);
        }

        static public Attribute create(String name, String value) throws Exception {
            return (Attribute) new Impl(name, value);
        }

        public String name();

        public String value();

        public void setValue(String value);

        public void addValue(Number value);

        public void addValue(String value);

        static class Impl implements Attribute {

            private final String name;
            private java.lang.Object value;
            private String rendered = null;

            public Impl(String name, String value) {
                this.name = name;
                this.value = value;
                this.setValue0(this.value);
            }

            @Override
            public String name() {
                return this.name;
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
                        this.rendered = String.format("%s", this.name);
                    } else if (value instanceof String) {
                        this.rendered = String.format("%s='%s'", this.name, value);
                    } else if (value instanceof Number) {
                        if (value instanceof Double || value instanceof Float || value instanceof BigDecimal) {
                            this.rendered = String.format("%s='%f'", this.name, value);
                        } else {
                            this.rendered = String.format("%s='%d'", this.name, value);
                        }
                    } else if (value instanceof Temporal) {
                        if (value instanceof LocalDate) {
                            this.rendered = String.format("%s='%s'",
                                    this.name,
                                    ((LocalDate) value).format(DateTimeFormatter.ISO_LOCAL_DATE));
                        } else if (value instanceof LocalTime) {
                            this.rendered = String.format("%s='%s'",
                                    this.name,
                                    ((LocalTime) value).format(DateTimeFormatter.ISO_LOCAL_TIME));
                        } else if (value instanceof LocalDateTime) {
                            this.rendered = String.format("%s='%s'",
                                    this.name,
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

        Element setId(String value) throws Exception;

        String getId();

        String tag();

        Element setTextContent(String content);

        Element addAttribute(Attribute attribute) throws InvalidAttributeException;

        List<Attribute> attributes();

        Element addClass(String className);

        Element append(Element element) throws InvalidContentException;

        Element insert(Element element) throws InvalidContentException;

        Element parent();

        List<Element> children();

        default TagOmission tagOmission() {
            return TagOmission.None;
        }

    }

    public interface HtmlElement extends Element {

        HtmlElement setAutoCapitalize(AutoCapitalize autoCapitalize);

        HtmlElement setContentEditable(boolean flag);

        HtmlElement setDraggable(boolean flag);

        HtmlElement setHidden(boolean flag);

        HtmlElement setInputMode(InputMode inputMode);

        HtmlElement setStyle(Style style);

        default List<ContentCategory> contentCategories() {
            return ContentCategory.parse(HTML.valueOf(this.tag()));
        }

        default DOM domInterface() {
            return HTML.valueOf(this.tag()).domInterface;
        }

        default boolean isPermittedContent(HtmlElement element) {
            return true;
        }
    }

    public interface CustomElement extends HtmlElement {

        HtmlElement owner();

        String customTag();
    }

    public interface Head extends HtmlElement {

        static public Head create(Object... params) throws Exception {
            return HTML.HEAD.create(params);
        }

    }

    public interface Meta extends HtmlElement {

        static public Meta create(Object... params) throws Exception {
            return HTML.META.create(params);
        }

        Meta setCharset(String charset) throws Exception;
    }

    public interface Style extends HtmlElement {

        static public Style create(Object... params) throws Exception {
            return HTML.STYLE.create(params);
        }

        void append(CSS.Block cssElement);
    }

    public interface Script extends HtmlElement {

        static public Script create(Object... params) throws Exception {
            return HTML.SCRIPT.create(params);
        }

        void append(String code);
    }

    public interface Body extends HtmlElement {

        static public Body create(Object... params) throws Exception {
            return HTML.BODY.create(params);
        }
    }

    public interface Page extends HtmlElement {

        static public Page create(Object... params) throws Exception {
            return HTML.HTML.create(params);
        }

        Head htmlHead();

        Body htmlBody();
    }

    public interface A extends HtmlElement {
        static public A create(Object... params) throws Exception {
            return HTML.A.create(params);
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
    public interface Abbr extends HtmlElement {

        static public Abbr create(Object... params) throws Exception {
            return HTML.ABBR.create(params);
        }
    }

    public interface Address extends HtmlElement {

        static public Address create(Object... params) throws Exception {
            return HTML.ADDRESS.create(params);
        }
    }

    public interface Br extends HtmlElement {

        static public Br create(Object... params) throws Exception {
            return HTML.BR.create(params);
        }

        @Override
        default boolean isPermittedContent(HtmlElement element) {
            return false;
        }
    }

    public interface Div extends HtmlElement {

        static public Div create(Object... params) throws Exception {
            return HTML.DIV.create(params);
        }
    }

    public interface Table extends HtmlElement {

        static public Table create(Object... params) throws Exception {
            return HTML.TABLE.create(params);
        }
    }

    public interface Tr extends HtmlElement {

        static public Tr create(Object... params) throws Exception {
            return HTML.TR.create(params);
        }
    }

    public interface Th extends HtmlElement {
        static public Th create(Object... params) throws Exception {
            return HTML.TH.create(params);
        }
    }

    public interface Td extends HtmlElement {
        static public Td create(Object... params) throws Exception {
            return HTML.TD.create(params);
        }
    }

    public interface Article extends HtmlElement {
        static public Article create(Object... params) throws Exception {
            return HTML.ARTICLE.create(params);
        }
    }

    public interface Section extends HtmlElement {
        static public Section create(Object... params) throws Exception {
            return HTML.SECTION.create(params);
        }
    }

    public interface Option extends HtmlElement {
        static public Option create(Object... params) throws Exception {
            return HTML.OPTION.create(params);
        }
    }

    public interface Fieldset extends HtmlElement {
        static public Fieldset create(Object... params) throws Exception {
            return HTML.FIELDSET.create(params);
        }
    }

    public interface Form extends HtmlElement {

        static public Form create(Object... params) throws Exception {
            return HTML.FORM.create(params);
        }
    }

    public interface Input extends HtmlElement {

        static public Input create(Object... params) throws Exception {
            return HTML.INPUT.create(params);
        }

        public Element setName(String name);

        public enum InputAttributes implements AttributeType {
            ACCEPT(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
            ALT(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
            AUTOCOMPLETE(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
            AUTOFOCUS(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
            CAPTURE(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
            CHECKED(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
            DIRNAME(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
            DISABLED(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
            FORM(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
            FORMACTION(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
            FORMENCTYPE(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
            FORMMETHOD(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
            FORMNOVALIDATE(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
            FORMTARGET(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
            HEIGHT(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
            LIST(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
            MAX(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
            MAXLENGTH(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
            MIN(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
            MINLENGTH(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
            MULTIPLE(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
            NAME(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
            PATTERN(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
            PLACEHOLDER(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
            READONLY(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
            REQUIRED(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
            SIZE(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
            SRC(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
            STEP(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
            TYPE(AttributeType.ValueType.LIST, Input.Type.valuesAsString()),
            VALUE(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$")),
            WIDTH(AttributeType.ValueType.PATTERN, Pattern.compile("^.*$"));

            private final String attributeTag;
            private final AttributeType.ValueType attributesType;
            private final List<String> allowables;
            private final Pattern pattern;

            InputAttributes(AttributeType.ValueType attributesType, String[] allowables) {
                this.attributeTag = this.name().toLowerCase();
                this.attributesType = attributesType;
                this.allowables = Arrays.asList(allowables);
                this.pattern = Pattern.compile("^.*$");
            }

            InputAttributes(AttributeType.ValueType attributesType, Pattern pattern) {
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
                return Arrays.asList(InputAttributes.values());
            }
        }

        public enum Type {
            BUTTON,
            CHECKBOX,
            COLOR,
            DATE,
            DATETIME_LOCAL,
            EMAIL,
            FILE,
            HIDDEN,
            IMAGE,
            MONTH,
            NUMBER,
            PASSWORD,
            RADIO,
            RANGE,
            RESET,
            SEARCH,
            SUBMIT,
            TEL,
            TEXT,
            TIME,
            URL,
            WEEK;

            private final Attribute attribute;

            Type() {
                Attribute attribute0 = null;
                try {
                    attribute0 = Attribute.create("type", this.value());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                this.attribute = attribute0;
            }

            public String value() {
                return this.name().toLowerCase().replace("_", "-");
            }

            public Attribute asAttribute() {
                return this.attribute;
            }

            static public String[] valuesAsString() {
                return Arrays.asList(Type.values()).stream()
                        .map(t -> t.value()).collect(Collectors.toList())
                        .toArray(new String[Type.values().length]);
            }
        }

    }

    public interface Label extends HtmlElement {

        static public Label create(Object... params) throws Exception {
            return HTML.LABEL.create(params);
        }
    }

    public interface Html extends HtmlElement {

        static public Html create(Object... params) throws Exception {
            return HTML.HTML.create(params);
        }
    }

    public interface Acronym extends HtmlElement {

        static public Acronym create(Object... params) throws Exception {
            return HTML.ACRONYM.create(params);
        }
    }

    public interface Applet extends HtmlElement {

        static public Applet create(Object... params) throws Exception {
            return HTML.APPLET.create(params);
        }
    }

    public interface Area extends HtmlElement {

        static public Area create(Object... params) throws Exception {
            return HTML.AREA.create(params);
        }
    }

    public interface Aside extends HtmlElement {

        static public Aside create(Object... params) throws Exception {
            return HTML.ASIDE.create(params);
        }
    }

    public interface Audio extends HtmlElement {

        static public Audio create(Object... params) throws Exception {
            return HTML.AUDIO.create(params);
        }
    }

    public interface B extends HtmlElement {

        static public B create(Object... params) throws Exception {
            return HTML.B.create(params);
        }
    }

    public interface Base extends HtmlElement {

        static public Base create(Object... params) throws Exception {
            return HTML.BASE.create(params);
        }
    }

    public interface Basefont extends HtmlElement {

        static public Basefont create(Object... params) throws Exception {
            return HTML.BASEFONT.create(params);
        }
    }

    public interface Bb extends HtmlElement {

        static public Bb create(Object... params) throws Exception {
            return HTML.BB.create(params);
        }
    }

    public interface Bdo extends HtmlElement {

        static public Bdo create(Object... params) throws Exception {
            return HTML.BDO.create(params);
        }
    }

    public interface Big extends HtmlElement {

        static public Big create(Object... params) throws Exception {
            return HTML.BIG.create(params);
        }
    }

    public interface Blockquote extends HtmlElement {

        static public Blockquote create(Object... params) throws Exception {
            return HTML.BLOCKQUOTE.create(params);
        }
    }

    public interface Button extends HtmlElement {

        static public Button create(Object... params) throws Exception {
            return HTML.BUTTON.create(params);
        }
    }

    public interface Canvas extends HtmlElement {

        static public Canvas create(Object... params) throws Exception {
            return HTML.CANVAS.create(params);
        }
    }

    public interface Caption extends HtmlElement {

        static public Caption create(Object... params) throws Exception {
            return HTML.CAPTION.create(params);
        }
    }

    public interface Center extends HtmlElement {

        static public Center create(Object... params) throws Exception {
            return HTML.CENTER.create(params);
        }
    }

    public interface Cite extends HtmlElement {

        static public Cite create(Object... params) throws Exception {
            return HTML.CITE.create(params);
        }
    }

    public interface Code extends HtmlElement {

        static public Code create(Object... params) throws Exception {
            return HTML.CODE.create(params);
        }
    }

    public interface Col extends HtmlElement {

        static public Col create(Object... params) throws Exception {
            return HTML.COL.create(params);
        }
    }

    public interface Colgroup extends HtmlElement {

        static public Colgroup create(Object... params) throws Exception {
            return HTML.COLGROUP.create(params);
        }
    }

    public interface Command extends HtmlElement {

        static public Command create(Object... params) throws Exception {
            return HTML.COMMAND.create(params);
        }
    }

    public interface Comment extends HtmlElement {

        static public Comment create(Object... params) throws Exception {
            return HTML.COMMENT.create(params);
        }
    }

    public interface Datagrid extends HtmlElement {

        static public Datagrid create(Object... params) throws Exception {
            return HTML.DATAGRID.create(params);
        }
    }

    public interface Datalist extends HtmlElement {

        static public Datalist create(Object... params) throws Exception {
            return HTML.DATALIST.create(params);
        }

    }

    public interface Dd extends HtmlElement {

        static public Dd create(Object... params) throws Exception {
            return HTML.DD.create(params);
        }
    }

    public interface Del extends HtmlElement {

        static public Del create(Object... params) throws Exception {
            return HTML.DEL.create(params);
        }
    }

    public interface Details extends HtmlElement {

        static public Details create(Object... params) throws Exception {
            return HTML.DETAILS.create(params);
        }
    }

    public interface Dialog extends HtmlElement {

        static public Dialog create(Object... params) throws Exception {
            return HTML.DIALOG.create(params);
        }
    }

    public interface Dir extends HtmlElement {

        static public Dir create(Object... params) throws Exception {
            return HTML.DIR.create(params);
        }
    }

    public interface Dfn extends HtmlElement {

        static public Dfn create(Object... params) throws Exception {
            return HTML.DFN.create(params);
        }
    }

    public interface Dl extends HtmlElement {

        static public Dl create(Object... params) throws Exception {
            return HTML.DL.create(params);
        }
    }

    public interface Dt extends HtmlElement {

        static public Dt create(Object... params) throws Exception {
            return HTML.DT.create(params);
        }
    }

    public interface Em extends HtmlElement {

        static public Em create(Object... params) throws Exception {
            return HTML.EM.create(params);
        }
    }

    public interface Embed extends HtmlElement {

        static public Embed create(Object... params) throws Exception {
            return HTML.EMBED.create(params);
        }
    }

    public interface Figure extends HtmlElement {

        static public Figure create(Object... params) throws Exception {
            return HTML.FIGURE.create(params);
        }
    }

    public interface Font extends HtmlElement {

        static public Font create(Object... params) throws Exception {
            return HTML.FONT.create(params);
        }
    }

    public interface Footer extends HtmlElement {

        static public Footer create(Object... params) throws Exception {
            return HTML.FOOTER.create(params);
        }
    }

    public interface Frame extends HtmlElement {

        static public Frame create(Object... params) throws Exception {
            return HTML.FRAME.create(params);
        }
    }

    public interface Frameset extends HtmlElement {

        static public Frameset create(Object... params) throws Exception {
            return HTML.FRAMESET.create(params);
        }
    }

    public interface H1 extends HtmlElement {

        static public H1 create(Object... params) throws Exception {
            return HTML.H1.create(params);
        }
    }

    public interface H2 extends HtmlElement {

        static public H2 create(Object... params) throws Exception {
            return HTML.H2.create(params);
        }
    }

    public interface H3 extends HtmlElement {

        static public H3 create(Object... params) throws Exception {
            return HTML.H3.create(params);
        }
    }

    public interface H4 extends HtmlElement {

        static public H4 create(Object... params) throws Exception {
            return HTML.H4.create(params);
        }
    }

    public interface H5 extends HtmlElement {

        static public H5 create(Object... params) throws Exception {
            return HTML.H5.create(params);
        }
    }

    public interface H6 extends HtmlElement {

        static public H6 create(Object... params) throws Exception {
            return HTML.H6.create(params);
        }
    }

    public interface Header extends HtmlElement {

        static public Header create(Object... params) throws Exception {
            return HTML.HEADER.create(params);
        }
    }

    public interface Hgroup extends HtmlElement {

        static public Hgroup create(Object... params) throws Exception {
            return HTML.HGROUP.create(params);
        }
    }

    public interface Hr extends HtmlElement {

        static public Hr create(Object... params) throws Exception {
            return HTML.HR.create(params);
        }
    }

    public interface I extends HtmlElement {

        static public I create(Object... params) throws Exception {
            return HTML.I.create(params);
        }
    }

    public interface Iframe extends HtmlElement {

        static public Iframe create(Object... params) throws Exception {
            return HTML.IFRAME.create(params);
        }
    }

    public interface Img extends HtmlElement {

        static public Img create(Object... params) throws Exception {
            return HTML.IMG.create(params);
        }
    }

    public interface Ins extends HtmlElement {

        static public Ins create(Object... params) throws Exception {
            return HTML.INS.create(params);
        }
    }

    public interface Isindex extends HtmlElement {

        static public Isindex create(Object... params) throws Exception {
            return HTML.ISINDEX.create(params);
        }
    }

    public interface Kdb extends HtmlElement {

        static public Kdb create(Object... params) throws Exception {
            return HTML.KDB.create(params);
        }
    }

    public interface Legend extends HtmlElement {

        static public Legend create(Object... params) throws Exception {
            return HTML.LEGEND.create(params);
        }
    }

    public interface Li extends HtmlElement {

        static public Li create(Object... params) throws Exception {
            return HTML.LI.create(params);
        }
    }

    public interface Link extends HtmlElement {

        static public Link create(Object... params) throws Exception {
            return HTML.LINK.create(params);
        }
    }

    public interface Mark extends HtmlElement {

        static public Mark create(Object... params) throws Exception {
            return HTML.MARK.create(params);
        }
    }

    public interface Map extends HtmlElement {

        static public Map create(Object... params) throws Exception {
            return HTML.MAP.create(params);
        }
    }

    public interface Menu extends HtmlElement {

        static public Menu create(Object... params) throws Exception {
            return HTML.MENU.create(params);
        }
    }

    public interface Meter extends HtmlElement {

        static public Meter create(Object... params) throws Exception {
            return HTML.METER.create(params);
        }
    }

    public interface Nav extends HtmlElement {

        static public Nav create(Object... params) throws Exception {
            return HTML.NAV.create(params);
        }
    }

    public interface Noframes extends HtmlElement {

        static public Noframes create(Object... params) throws Exception {
            return HTML.NOFRAMES.create(params);
        }
    }

    public interface Noscript extends HtmlElement {

        static public Noscript create(Object... params) throws Exception {
            return HTML.NOSCRIPT.create(params);
        }
    }

    public interface Object extends HtmlElement {

        static public Object create(Object... params) throws Exception {
            return HTML.OBJECT.create(params);
        }
    }

    public interface Ol extends HtmlElement {

        static public Ol create(Object... params) throws Exception {
            return HTML.OL.create(params);
        }
    }

    public interface Optgroup extends HtmlElement {

        static public Optgroup create(Object... params) throws Exception {
            return HTML.OPTGROUP.create(params);
        }
    }

    public interface Output extends HtmlElement {

        static public Output create(Object... params) throws Exception {
            return HTML.OUTPUT.create(params);
        }
    }

    public interface P extends HtmlElement {

        static public P create(Object... params) throws Exception {
            return HTML.P.create(params);
        }
    }

    public interface Param extends HtmlElement {

        static public Param create(Object... params) throws Exception {
            return HTML.PARAM.create(params);
        }
    }

    public interface Pre extends HtmlElement {

        static public Pre create(Object... params) throws Exception {
            return HTML.PRE.create(params);
        }
    }

    public interface Progress extends HtmlElement {

        static public Progress create(Object... params) throws Exception {
            return HTML.PROGRESS.create(params);
        }
    }

    public interface Q extends HtmlElement {

        static public Q create(Object... params) throws Exception {
            return HTML.Q.create(params);
        }
    }

    public interface Ruby extends HtmlElement {

        static public Ruby create(Object... params) throws Exception {
            return HTML.RUBY.create(params);
        }
    }

    public interface Rp extends HtmlElement {

        static public Rp create(Object... params) throws Exception {
            return HTML.RP.create(params);
        }
    }

    public interface Rt extends HtmlElement {

        static public Rt create(Object... params) throws Exception {
            return HTML.RT.create(params);
        }
    }

    public interface S extends HtmlElement {

        static public S create(Object... params) throws Exception {
            return HTML.S.create(params);
        }
    }

    public interface Samp extends HtmlElement {

        static public Samp create(Object... params) throws Exception {
            return HTML.SAMP.create(params);
        }
    }

    public interface Select extends HtmlElement {

        static public Select create(Object... params) throws Exception {
            return HTML.SELECT.create(params);
        }
    }

    public interface Small extends HtmlElement {

        static public Small create(Object... params) throws Exception {
            return HTML.SMALL.create(params);
        }
    }

    public interface Source extends HtmlElement {

        static public Source create(Object... params) throws Exception {
            return HTML.SOURCE.create(params);
        }
    }

    public interface Span extends HtmlElement {

        static public Span create(Object... params) throws Exception {
            return HTML.SPAN.create(params);
        }
    }

    public interface Strike extends HtmlElement {

        static public Strike create(Object... params) throws Exception {
            return HTML.STRIKE.create(params);
        }
    }

    public interface Strong extends HtmlElement {

        static public Strong create(Object... params) throws Exception {
            return HTML.STRONG.create(params);
        }
    }

    public interface Sub extends HtmlElement {

        static public Sub create(Object... params) throws Exception {
            return HTML.SUB.create(params);
        }
    }

    public interface Sup extends HtmlElement {

        static public Sup create(Object... params) throws Exception {
            return HTML.SUP.create(params);
        }
    }

    public interface Tbody extends HtmlElement {

        static public Tbody create(Object... params) throws Exception {
            return HTML.TBODY.create(params);
        }
    }

    public interface Textarea extends HtmlElement {

        static public Textarea create(Object... params) throws Exception {
            return HTML.TEXTAREA.create(params);
        }
    }

    public interface Tfoot extends HtmlElement {

        static public Tfoot create(Object... params) throws Exception {
            return HTML.TFOOT.create(params);
        }
    }

    public interface Thead extends HtmlElement {

        static public Thead create(Object... params) throws Exception {
            return HTML.THEAD.create(params);
        }
    }

    public interface Time extends HtmlElement {

        static public Time create(Object... params) throws Exception {
            return HTML.TIME.create(params);
        }
    }

    public interface Title extends HtmlElement {

        static public Title create(Object... params) throws Exception {
            return HTML.TITLE.create(params);
        }
    }

    public interface Tt extends HtmlElement {

        static public Tt create(Object... params) throws Exception {
            return HTML.TT.create(params);
        }
    }

    public interface U extends HtmlElement {

        static public U create(Object... params) throws Exception {
            return HTML.U.create(params);
        }
    }

    public interface Ul extends HtmlElement {

        static public Ul create(Object... params) throws Exception {
            return HTML.UL.create(params);
        }
    }

    public interface Var extends HtmlElement {

        static public Var create(Object... params) throws Exception {
            return HTML.VAR.create(params);
        }
    }

    public interface Video extends HtmlElement {

        static public Video create(Object... params) throws Exception {
            return HTML.VIDEO.create(params);
        }
    }

    public interface Xmp extends HtmlElement {

        static public Xmp create(Object... params) throws Exception {
            return HTML.XMP.create(params);
        }
    }

}
