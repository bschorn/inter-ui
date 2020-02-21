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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author bschorn
 */
public enum HTML {
    ELEMENT(Element.class),
    SINGLE(SingleElement.class),
    HTML(Page.class),
    ATTRIBUTE(Attribute.class),
    A(Element.class),
    ABBR(Element.class),
    ACRONYM(Element.class),
    ADDRESS(Element.class),
    APPLET(Element.class),
    AREA(Element.class),
    ARTICLE(Article.class),
    ASIDE(Element.class),
    AUDIO(Element.class),
    B(Element.class),
    BASE(Element.class),
    BASEFONT(Element.class),
    BB(Element.class),
    BDO(Element.class),
    BIG(Element.class),
    BLOCKQUOTE(Element.class),
    BODY(Body.class),
    BR(Element.class),
    BUTTON(Element.class),
    CANVAS(Element.class),
    CAPTION(Element.class),
    CENTER(Element.class),
    CITE(Element.class),
    CODE(Element.class),
    COL(Element.class),
    COLGROUP(Element.class),
    COMMAND(Element.class),
    DATAGRID(Element.class),
    DATALIST(Element.class),
    DD(Element.class),
    DEL(Element.class),
    DETAILS(Element.class),
    DIALOG(Element.class),
    DIR(Element.class),
    DIV(Div.class),
    DFN(Element.class),
    DL(Element.class),
    DT(Element.class),
    EM(Element.class),
    EMBED(Element.class),
    FIELDSET(Fieldset.class),
    FIGURE(Element.class),
    FONT(Element.class),
    FOOTER(Element.class),
    FORM(Form.class),
    FRAME(Element.class),
    FRAMESET(Element.class),
    H1(Element.class),
    H2(Element.class),
    H3(Element.class),
    H4(Element.class),
    H5(Element.class),
    H6(Element.class),
    HEAD(Head.class),
    HEADER(Element.class),
    HGROUP(Element.class),
    HR(Element.class),
    I(Element.class),
    IFRAME(Element.class),
    IMG(Element.class),
    INPUT(Input.class),
    INS(Element.class),
    ISINDEX(Element.class),
    KDB(Element.class),
    LABEL(Label.class),
    LEGEND(Element.class),
    LI(Element.class),
    LINK(Element.class),
    MARK(Element.class),
    MAP(Element.class),
    MENU(Element.class),
    META(Meta.class),
    METER(Element.class),
    NAV(Element.class),
    NOFRAMES(Element.class),
    NOSCRIPT(Element.class),
    OBJECT(Element.class),
    OL(Element.class),
    OPTGROUP(Element.class),
    OPTION(Option.class),
    OUTPUT(Element.class),
    P(Element.class),
    PARAM(Element.class),
    PRE(Element.class),
    PROGRESS(Element.class),
    Q(Element.class),
    RUBY(Element.class),
    RP(Element.class),
    RT(Element.class),
    S(Element.class),
    SAMP(Element.class),
    SCRIPT(Script.class),
    SECTION(Section.class),
    SELECT(Element.class),
    SMALL(Element.class),
    SOURCE(Element.class),
    SPAN(Element.class),
    STRIKE(Element.class),
    STRONG(Element.class),
    STYLE(Style.class),
    SUB(Element.class),
    SUP(Element.class),
    TABLE(Table.class),
    TBODY(Element.class),
    TD(Td.class),
    TEXTAREA(Element.class),
    TFOOT(Element.class),
    TH(Th.class),
    THEAD(Element.class),
    TIME(Element.class),
    TITLE(Element.class),
    TR(Tr.class),
    TT(Element.class),
    U(Element.class),
    UL(Element.class),
    VAR(Element.class),
    VIDEO(Element.class),
    XMP(Element.class);

    private final Class<?> interfaceOf;
    private Class<?> implOf = null;

    HTML(Class<?> interfaceOf) {
        this.interfaceOf = interfaceOf;
    }

    public String tag() {
        return this.name().toLowerCase();
    }

    public Class<?> interfaceOf() {
        return this.interfaceOf;
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

        DOM dom();

        String tag();

        List<Element> children();

        default List<ContentCategory> contentCategories() {
            return ContentCategory.parse(HTML.valueOf(this.tag()));
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
