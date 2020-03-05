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
    HTML(Page.class, DOM.HTMLElement),
    ATTRIBUTE(Attribute.class, DOM.HTMLElement),
    A(A.class, DOM.HTMLAnchorElement),
    ABBR(Abbr.class, DOM.HTMLElement),
    ACRONYM(Element.class, DOM.HTMLElement),
    ADDRESS(Element.class, DOM.HTMLElement),
    APPLET(Element.class, DOM.HTMLElement),
    AREA(Element.class, DOM.HTMLElement),
    ARTICLE(Article.class, DOM.HTMLElement),
    ASIDE(Element.class, DOM.HTMLElement),
    AUDIO(Element.class, DOM.HTMLElement),
    B(Element.class, DOM.HTMLElement),
    BASE(Element.class, DOM.HTMLElement),
    BASEFONT(Element.class, DOM.HTMLElement),
    BB(Element.class, DOM.HTMLElement),
    BDO(Element.class, DOM.HTMLElement),
    BIG(Element.class, DOM.HTMLElement),
    BLOCKQUOTE(Element.class, DOM.HTMLElement),
    BODY(Body.class, DOM.HTMLElement),
    BR(Element.class, DOM.HTMLBRElement),
    BUTTON(Element.class, DOM.HTMLElement),
    CANVAS(Element.class, DOM.HTMLElement),
    CAPTION(Element.class, DOM.HTMLElement),
    CENTER(Element.class, DOM.HTMLElement),
    CITE(Element.class, DOM.HTMLElement),
    CODE(Element.class, DOM.HTMLElement),
    COL(Element.class, DOM.HTMLElement),
    COLGROUP(Element.class, DOM.HTMLElement),
    COMMAND(Element.class, DOM.HTMLElement),
    DATAGRID(Element.class, DOM.HTMLElement),
    DATALIST(Element.class, DOM.HTMLElement),
    DD(Element.class, DOM.HTMLElement),
    DEL(Element.class, DOM.HTMLElement),
    DETAILS(Element.class, DOM.HTMLElement),
    DIALOG(Element.class, DOM.HTMLElement),
    DIR(Element.class, DOM.HTMLElement),
    DIV(Div.class, DOM.HTMLDivElement),
    DFN(Element.class, DOM.HTMLElement),
    DL(Element.class, DOM.HTMLElement),
    DT(Element.class, DOM.HTMLElement),
    EM(Element.class, DOM.HTMLElement),
    EMBED(Element.class, DOM.HTMLElement),
    FIELDSET(Fieldset.class, DOM.HTMLElement),
    FIGURE(Element.class, DOM.HTMLElement),
    FONT(Element.class, DOM.HTMLElement),
    FOOTER(Element.class, DOM.HTMLElement),
    FORM(Form.class, DOM.HTMLElement),
    FRAME(Element.class, DOM.HTMLElement),
    FRAMESET(Element.class, DOM.HTMLElement),
    H1(Element.class, DOM.HTMLElement),
    H2(Element.class, DOM.HTMLElement),
    H3(Element.class, DOM.HTMLElement),
    H4(Element.class, DOM.HTMLElement),
    H5(Element.class, DOM.HTMLElement),
    H6(Element.class, DOM.HTMLElement),
    HEAD(Head.class, DOM.HTMLElement),
    HEADER(Element.class, DOM.HTMLElement),
    HGROUP(Element.class, DOM.HTMLElement),
    HR(Element.class, DOM.HTMLElement),
    I(Element.class, DOM.HTMLElement),
    IFRAME(Element.class, DOM.HTMLElement),
    IMG(Element.class, DOM.HTMLElement),
    INPUT(Input.class, DOM.HTMLElement),
    INS(Element.class, DOM.HTMLElement),
    ISINDEX(Element.class, DOM.HTMLElement),
    KDB(Element.class, DOM.HTMLElement),
    LABEL(Label.class, DOM.HTMLElement),
    LEGEND(Element.class, DOM.HTMLElement),
    LI(Element.class, DOM.HTMLElement),
    LINK(Element.class, DOM.HTMLElement),
    MARK(Element.class, DOM.HTMLElement),
    MAP(Element.class, DOM.HTMLElement),
    MENU(Element.class, DOM.HTMLElement),
    META(Meta.class, DOM.HTMLElement),
    METER(Element.class, DOM.HTMLElement),
    NAV(Element.class, DOM.HTMLElement),
    NOFRAMES(Element.class, DOM.HTMLElement),
    NOSCRIPT(Element.class, DOM.HTMLElement),
    OBJECT(Element.class, DOM.HTMLElement),
    OL(Element.class, DOM.HTMLElement),
    OPTGROUP(Element.class, DOM.HTMLElement),
    OPTION(Option.class, DOM.HTMLElement),
    OUTPUT(Element.class, DOM.HTMLElement),
    P(Element.class, DOM.HTMLElement),
    PARAM(Element.class, DOM.HTMLElement),
    PRE(Element.class, DOM.HTMLElement),
    PROGRESS(Element.class, DOM.HTMLElement),
    Q(Element.class, DOM.HTMLElement),
    RUBY(Element.class, DOM.HTMLElement),
    RP(Element.class, DOM.HTMLElement),
    RT(Element.class, DOM.HTMLElement),
    S(Element.class, DOM.HTMLElement),
    SAMP(Element.class, DOM.HTMLElement),
    SCRIPT(Script.class, DOM.HTMLElement),
    SECTION(Section.class, DOM.HTMLElement),
    SELECT(Element.class, DOM.HTMLElement),
    SMALL(Element.class, DOM.HTMLElement),
    SOURCE(Element.class, DOM.HTMLElement),
    SPAN(Element.class, DOM.HTMLElement),
    STRIKE(Element.class, DOM.HTMLElement),
    STRONG(Element.class, DOM.HTMLElement),
    STYLE(Style.class, DOM.HTMLElement),
    SUB(Element.class, DOM.HTMLElement),
    SUP(Element.class, DOM.HTMLElement),
    TABLE(Table.class, DOM.HTMLElement),
    TBODY(Element.class, DOM.HTMLElement),
    TD(Td.class, DOM.HTMLElement),
    TEXTAREA(Element.class, DOM.HTMLElement),
    TFOOT(Element.class, DOM.HTMLElement),
    TH(Th.class, DOM.HTMLElement),
    THEAD(Element.class, DOM.HTMLElement),
    TIME(Element.class, DOM.HTMLElement),
    TITLE(Element.class, DOM.HTMLElement),
    TR(Tr.class, DOM.HTMLElement),
    TT(Element.class, DOM.HTMLElement),
    U(Element.class, DOM.HTMLElement),
    UL(Element.class, DOM.HTMLElement),
    VAR(Element.class, DOM.HTMLElement),
    VIDEO(Element.class, DOM.HTMLElement),
    XMP(Element.class, DOM.HTMLElement);

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

    interface Attributes {
        public enum Type {
            AVOID,
            FLAG,
            LIST,
            PATTERN;
        }
        String tag();

        Type type();
    }

    static public enum GlobalAttributes implements Attributes {
        ACCESSKEY(Attributes.Type.AVOID, Pattern.compile("[A-Za-z]")),
        AUTOCAPITALIZE(Attributes.Type.LIST, new String[]{"off", "none", "on", "sentences", "words", "characters"}),
        CLASS(Attributes.Type.PATTERN, Pattern.compile("^.*$")),
        CONTENTEDITABLE(Attributes.Type.LIST, new String[]{"true", "false"}),
        DATA(Attributes.Type.PATTERN, Pattern.compile("^.*$")),
        DIR(Attributes.Type.LIST, new String[]{"ltr", "rtl", "auto"}),
        DRAGGABLE(Attributes.Type.LIST, new String[]{"true", "false"}),
        HIDDEN(Attributes.Type.FLAG, new String[]{}),
        ID(Attributes.Type.PATTERN, Pattern.compile("^.*$")),
        INPUTMODE(Attributes.Type.LIST, new String[]{"none", "text", "decimal", "numeric", "tel", "search", "email", "url"}),
        IS(Attributes.Type.PATTERN, Pattern.compile("^.*[-].*$")),
        ITEMID(Attributes.Type.PATTERN, Pattern.compile("^.*$")),
        ITEMPROP(Attributes.Type.PATTERN, Pattern.compile("^.*$")),
        ITEMREF(Attributes.Type.PATTERN, Pattern.compile("^.*$")),
        ITEMSCOPE(Attributes.Type.PATTERN, Pattern.compile("^.*$")),
        ITEMTYPE(Attributes.Type.PATTERN, Pattern.compile("^.*$")),
        LANG(Attributes.Type.PATTERN, Pattern.compile("^[a-z]+{2,3}.*$")),
        PART(Attributes.Type.AVOID, Pattern.compile("[A-Za-z]")),
        SLOT(Attributes.Type.FLAG, new String[]{}),
        SPELLCHECK(Attributes.Type.LIST, new String[]{"true", "false"}),
        STYLE(Attributes.Type.PATTERN, Pattern.compile("^.*$")),
        TABINDEX(Attributes.Type.PATTERN, Pattern.compile("^(\\d|-1)$")),
        TITLE(Attributes.Type.PATTERN, Pattern.compile("^.*$"));

        private final String attributeTag;
        private final Attributes.Type attributesType;
        private final List<String> allowables;
        private final Pattern pattern;

        GlobalAttributes(Attributes.Type attributesType, String[] allowables) {
            this.attributeTag = this.name().toLowerCase();
            this.attributesType = attributesType;
            this.allowables = Arrays.asList(allowables);
            this.pattern = Pattern.compile("^.*$");
        }
        GlobalAttributes(Attributes.Type attributesType, Pattern pattern) {
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
        public Attributes.Type type() {
            return this.attributesType;
        }

        public List<String> allowables() {
            return this.allowables;
        }

        public Pattern pattern() {
            return this.pattern;
        }

        static public List<Attributes> attributes() {
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

        static public Attribute create(String name, Object value) throws Exception {
            return HTML.ATTRIBUTE.create(Attribute.class, name, value);
        }

        String name();

        Object value();

        void setValue(Object value);

        void addValue(Object value);
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
}
