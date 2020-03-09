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
package org.schorn.ella.ui.impl.html;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.schorn.ella.ui.html.CSS;
import org.schorn.ella.ui.html.HTML;
import org.schorn.ella.ui.html.HTML.A;
import org.schorn.ella.ui.html.HTML.Attribute;
import org.schorn.ella.ui.html.HTML.CustomElement;
import org.schorn.ella.ui.html.HTML.Element;
import org.schorn.ella.ui.html.HTML.SingleElement;
import org.schorn.ella.ui.util.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author bschorn
 */
public class HTMLImpl implements HTML.HtmlFactory {

    static private final HTMLImpl INSTANCE = new HTMLImpl();

    static public HTML.HtmlFactory getFactory() {
        return INSTANCE;
    }

    private HTMLImpl() {
        this.register();
    }

    @Override
    public void register() {
        for (HTML html : HTML.values()) {
            html.setImpl(HtmlElementImpl.class);
        }
        HTML.ELEMENT.setImpl(HtmlElementImpl.class);
        HTML.SINGLE.setImpl(HtmlSingleElementImpl.class);
        HTML.HTML.setImpl(HtmlPageElementImpl.class);
        HTML.A.setImpl(HtmlAImpl.class);
        HTML.ABBR.setImpl(HtmlAbbrImpl.class);
        //HTML.ACRONYM.setImpl(HtmlAcronymImpl.class);
        HTML.ADDRESS.setImpl(HtmlAddressImpl.class);
        HTML.APPLET.setImpl(HtmlAppletImpl.class);
        HTML.AREA.setImpl(HtmlAreaImpl.class);
        HTML.ARTICLE.setImpl(HtmlArticleImpl.class);
        HTML.ASIDE.setImpl(HtmlAsideImpl.class);
        HTML.AUDIO.setImpl(HtmlAudioImpl.class);
        HTML.B.setImpl(HtmlBImpl.class);
        HTML.BASE.setImpl(HtmlBaseImpl.class);
        HTML.BASEFONT.setImpl(HtmlBasefontImpl.class);
        HTML.BB.setImpl(HtmlBbImpl.class);
        HTML.BDO.setImpl(HtmlBdoImpl.class);
        HTML.BIG.setImpl(HtmlBigImpl.class);
        HTML.BLOCKQUOTE.setImpl(HtmlBlockquoteImpl.class);
        HTML.BODY.setImpl(HtmlBodyImpl.class);
        HTML.BR.setImpl(HtmlBrImpl.class);
        HTML.BUTTON.setImpl(HtmlButtonImpl.class);
        HTML.CANVAS.setImpl(HtmlCanvasImpl.class);
        HTML.CAPTION.setImpl(HtmlCaptionImpl.class);
        HTML.CENTER.setImpl(HtmlCenterImpl.class);
        HTML.CITE.setImpl(HtmlCiteImpl.class);
        HTML.CODE.setImpl(HtmlCodeImpl.class);
        HTML.COL.setImpl(HtmlColImpl.class);
        HTML.COLGROUP.setImpl(HtmlColgroupImpl.class);
        HTML.COMMAND.setImpl(HtmlCommandImpl.class);
        HTML.DATAGRID.setImpl(HtmlDatagridImpl.class);
        HTML.DATALIST.setImpl(HtmlDatalistImpl.class);
        HTML.DD.setImpl(HtmlDdImpl.class);
        HTML.DEL.setImpl(HtmlDelImpl.class);
        HTML.DETAILS.setImpl(HtmlDetailsImpl.class);
        HTML.DIALOG.setImpl(HtmlDialogImpl.class);
        HTML.DIR.setImpl(HtmlDirImpl.class);
        HTML.DIV.setImpl(HtmlDivImpl.class);
        HTML.DFN.setImpl(HtmlDfnImpl.class);
        HTML.DL.setImpl(HtmlDlImpl.class);
        HTML.DT.setImpl(HtmlDtImpl.class);
        HTML.EM.setImpl(HtmlEmImpl.class);
        HTML.EMBED.setImpl(HtmlEmbedImpl.class);
        HTML.FIELDSET.setImpl(HtmlFieldsetImpl.class);
        HTML.FIGURE.setImpl(HtmlFigureImpl.class);
        HTML.FONT.setImpl(HtmlFontImpl.class);
        HTML.FOOTER.setImpl(HtmlFooterImpl.class);
        HTML.FORM.setImpl(HtmlFormImpl.class);
        HTML.FRAME.setImpl(HtmlFrameImpl.class);
        HTML.FRAMESET.setImpl(HtmlFramesetImpl.class);
        HTML.H1.setImpl(HtmlH1Impl.class);
        HTML.H2.setImpl(HtmlH2Impl.class);
        HTML.H3.setImpl(HtmlH3Impl.class);
        HTML.H4.setImpl(HtmlH4Impl.class);
        HTML.H5.setImpl(HtmlH5Impl.class);
        HTML.H6.setImpl(HtmlH6Impl.class);
        HTML.HEAD.setImpl(HtmlHeadImpl.class);
        HTML.HEADER.setImpl(HtmlHeaderImpl.class);
        HTML.HGROUP.setImpl(HtmlHgroupImpl.class);
        HTML.HR.setImpl(HtmlHrImpl.class);
        HTML.I.setImpl(HtmlIImpl.class);
        HTML.IFRAME.setImpl(HtmlIframeImpl.class);
        HTML.IMG.setImpl(HtmlImgImpl.class);
        HTML.INPUT.setImpl(HtmlInputImpl.class);
        HTML.INS.setImpl(HtmlInsImpl.class);
        HTML.ISINDEX.setImpl(HtmlIsindexImpl.class);
        HTML.KDB.setImpl(HtmlKdbImpl.class);
        HTML.LABEL.setImpl(HtmlLabelImpl.class);
        HTML.LEGEND.setImpl(HtmlLegendImpl.class);
        HTML.LI.setImpl(HtmlLiImpl.class);
        HTML.LINK.setImpl(HtmlLinkImpl.class);
        HTML.MARK.setImpl(HtmlMarkImpl.class);
        HTML.MAP.setImpl(HtmlMapImpl.class);
        HTML.MENU.setImpl(HtmlMenuImpl.class);
        HTML.META.setImpl(HtmlMetaImpl.class);
        HTML.METER.setImpl(HtmlMeterImpl.class);
        HTML.NAV.setImpl(HtmlNavImpl.class);
        HTML.NOFRAMES.setImpl(HtmlNoframesImpl.class);
        HTML.NOSCRIPT.setImpl(HtmlNoscriptImpl.class);
        HTML.OBJECT.setImpl(HtmlObjectImpl.class);
        HTML.OL.setImpl(HtmlOlImpl.class);
        HTML.OPTGROUP.setImpl(HtmlOptgroupImpl.class);
        HTML.OPTION.setImpl(HtmlOptionImpl.class);
        HTML.OUTPUT.setImpl(HtmlOutputImpl.class);
        HTML.P.setImpl(HtmlPImpl.class);
        HTML.PARAM.setImpl(HtmlParamImpl.class);
        HTML.PRE.setImpl(HtmlPreImpl.class);
        HTML.PROGRESS.setImpl(HtmlProgressImpl.class);
        HTML.Q.setImpl(HtmlQImpl.class);
        HTML.RUBY.setImpl(HtmlRubyImpl.class);
        HTML.RP.setImpl(HtmlRpImpl.class);
        HTML.RT.setImpl(HtmlRtImpl.class);
        HTML.S.setImpl(HtmlSImpl.class);
        HTML.SAMP.setImpl(HtmlSampImpl.class);
        HTML.SCRIPT.setImpl(HtmlScriptImpl.class);
        HTML.SECTION.setImpl(HtmlSectionImpl.class);
        HTML.SELECT.setImpl(HtmlSelectImpl.class);
        HTML.SMALL.setImpl(HtmlSmallImpl.class);
        HTML.SOURCE.setImpl(HtmlSourceImpl.class);
        HTML.SPAN.setImpl(HtmlSpanImpl.class);
        HTML.STRIKE.setImpl(HtmlStrikeImpl.class);
        HTML.STRONG.setImpl(HtmlStrongImpl.class);
        HTML.STYLE.setImpl(HtmlStyleImpl.class);
        HTML.SUB.setImpl(HtmlSubImpl.class);
        HTML.SUP.setImpl(HtmlSupImpl.class);
        HTML.TABLE.setImpl(HtmlTableImpl.class);
        HTML.TBODY.setImpl(HtmlTbodyImpl.class);
        HTML.TD.setImpl(HtmlTdImpl.class);
        HTML.TEXTAREA.setImpl(HtmlTextareaImpl.class);
        HTML.TFOOT.setImpl(HtmlTfootImpl.class);
        HTML.TH.setImpl(HtmlThImpl.class);
        HTML.THEAD.setImpl(HtmlTheadImpl.class);
        HTML.TIME.setImpl(HtmlTimeImpl.class);
        HTML.TITLE.setImpl(HtmlTitleImpl.class);
        HTML.TR.setImpl(HtmlTrImpl.class);
        HTML.TT.setImpl(HtmlTtImpl.class);
        HTML.U.setImpl(HtmlUImpl.class);
        HTML.UL.setImpl(HtmlUlImpl.class);
        HTML.VAR.setImpl(HtmlVarImpl.class);
        HTML.VIDEO.setImpl(HtmlVideoImpl.class);
        HTML.XMP.setImpl(HtmlXmpImpl.class);

    }

    @Override
    public <T> T createInstance(HTML html, HTML.Object... params) throws Exception {
        Class<?> classFor = html.getImpl();
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

    static class ElementImpl implements Element {

        static private final AtomicInteger ID = new AtomicInteger(100);
        static public final String[] INDENT = new String[]{"  ", "    ", "      ", "        ", "          ", "            ", "              "};
        static public final String LINEFEED = "\n";
        private static final Logger LGR = LoggerFactory.getLogger(ElementImpl.class);

        protected Element parent;
        protected Integer level = 0;
        protected String id;
        protected String tag;
        protected String textContent = "";
        protected List<Element> children = new ArrayList<>();
        protected List<Attribute> attributes = new ArrayList<>();

        ElementImpl(String tag) {
            if (tag == null) {
                this.tag = "root";
            } else {
                this.tag = tag;
            }
            this.parent = this;
        }

        public void setLevel(Integer level) {
            this.level = level;
            this.children.forEach(e -> ((ElementImpl) e).setLevel(level + 1));
        }

        @Override
        public String tag() {
            return this.tag;
        }

        @Override
        public Element setTextContent(String content) {
            this.textContent = content;
            return this;
        }

        @Override
        public Element insert(Element element) throws HTML.InvalidContentException {
            //validateContent(this, element);
            if (element instanceof CustomElement) {
                element = ((CustomElement) element).owner();
            }
            ElementImpl elementImpl = (ElementImpl) element;
            if (elementImpl.parent != element) {
                ((ElementImpl) elementImpl.parent).children.remove(element);
            }
            elementImpl.parent = this;
            this.children.add(0, element);
            return this;
        }

        @Override
        public Element append(Element element) throws HTML.InvalidContentException {
            //validateContent(this, element);
            if (element instanceof CustomElement) {
                element = ((CustomElement) element).owner();
            }
            ElementImpl elementImpl = (ElementImpl) element;
            if (elementImpl.parent != element) {
                ((ElementImpl) elementImpl.parent).children.remove(element);
            }
            elementImpl.parent = this;
            this.children.add(element);
            return this;
        }

        @Override
        public Element addClass(String className) {
            List<Attribute> attrs = this.attributes.stream()
                    .filter(a -> a.name().equals(HTML.GlobalAttributes.CLASS.tag()))
                    .collect(Collectors.toList());
            if (attrs.isEmpty()) {
                try {
                    this.addAttribute0(Attribute.create(HTML.GlobalAttributes.CLASS, className));
                } catch (Exception ex) {
                    LGR.error(ToString.stackTrace(ex));
                }
            } else {
                attrs.stream().forEach(a -> a.addValue(className));
            }
            return this;
        }

        @Override
        public Element addAttribute(HTML.Attribute attribute) throws HTML.InvalidAttributeException {
            this.addAttribute0(attribute);
            return this;
        }

        @Override
        public List<Attribute> attributes() {
            return this.attributes;
        }

        @Override
        public Element setAutoCapitalize(HTML.AutoCapitalize autoCapitalize) {
            this.attributes.add(autoCapitalize.asAttribute());
            return this;
        }

        @Override
        public Element setContentEditable(boolean flag) {
            try {
                this.attributes.add(HTML.Attribute.create(HTML.GlobalAttributes.CONTENTEDITABLE.tag(), flag ? "true" : "false"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return this;
        }

        @Override
        public Element setDraggable(boolean flag) {
            try {
                this.addAttribute(HTML.Attribute.create(HTML.GlobalAttributes.DRAGGABLE.tag(), flag ? "true" : "false"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return this;
        }

        @Override
        public Element setHidden(boolean flag) {
            try {
                this.addAttribute(HTML.Attribute.create(HTML.GlobalAttributes.HIDDEN.tag(), flag ? "true" : "false"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return this;
        }

        @Override
        public Element setInputMode(HTML.InputMode inputMode) {
            try {
                this.addAttribute(inputMode.asAttribute());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return this;
        }

        @Override
        public Element setId(String value) throws Exception {
            this.id = value;
            if (value != null) {
                this.addAttribute0(Attribute.create(HTML.GlobalAttributes.ID, value));
            }
            return this;
        }

        @Override
        public String getId() {
            Optional<Attribute> id = this.attributes.stream().filter(a -> a.name().equalsIgnoreCase("id")).findFirst();
            if (id.isPresent()) {
                return id.get().value().toString();
            }
            return "";
        }

        @Override
        public String render() {
            StringBuilder builder = new StringBuilder();
            builder.append(renderIndent());
            builder.append(renderStartTag());
            builder.append(renderContent());
            if (!this.children.isEmpty()) {
                builder.append(renderLinefeed());
                builder.append(renderChildren());
                builder.append(renderIndent());
            }
            builder.append(renderEndTag());
            builder.append(renderLinefeed());
            return builder.toString();
        }

        @Override
        public Element parent() {
            return this.parent;
        }

        @Override
        public List<Element> children() {
            return Collections.unmodifiableList(this.children);
        }

        @Override
        public String toString() {
            try {
                return render();
            } catch (Exception ex) {
                LGR.error(ex.getMessage());
            }
            return "";
        }

        protected final void addAttribute0(Attribute attribute) throws HTML.InvalidAttributeException {
            if (attribute.render() != null) {
                List<Attribute> existing
                        = this.attributes.stream()
                                .filter(a -> a.name().equals(attribute.name()))
                                .collect(Collectors.toList());
                if (existing.isEmpty()) {
                    this.attributes.add(attribute);
                } else {
                    existing.forEach(a -> a.setValue(attribute.value()));
                }
            }
        }

        protected String renderIndent() {
            return INDENT[this.level];
        }

        protected String renderLinefeed() {
            return LINEFEED;
        }

        protected String renderAttributes() {
            StringJoiner joiner = new StringJoiner(" ", "", "");
            attributes.forEach((attribute) -> {
                joiner.add(attribute.toString());
            });
            return joiner.toString();
        }

        protected String renderStartTag() {
            switch (this.tagOmission()) {
                case EndMustBeOmitted:
                    if (this.attributes.isEmpty()) {
                        return String.format("<%s />", this.tag);
                    } else {
                        return String.format("<%s %s />", this.tag, this.renderAttributes());
                    }
                //case None:
                default:
                    if (this.attributes.isEmpty()) {
                        return String.format("<%s>", this.tag);
                    } else {
                        return String.format("<%s %s>", this.tag, this.renderAttributes());
                    }
            }
        }

        protected String renderContent() {
            return this.textContent != null ? this.textContent : "";
        }

        protected String renderChildren() {
            StringBuilder builder = new StringBuilder();
            if (this.children != null && !this.children.isEmpty()) {
                /*
                children.stream()
                        .filter(e -> ((HtmlElementImpl) e).parent == this)
                        .forEach(e -> {
                            try {
                                builder.append(e.render());
                            } catch (Exception ex) {

                            }
                        });
                 */
                for (Element element : this.children) {
                    if (element.parent() == this) {
                        builder.append(element.render());
                    }
                }
            }
            return builder.toString();
        }

        protected String renderEndTag() {
            switch (this.tagOmission()) {
                case EndMustBeOmitted:
                    return "";
                //case None:
                default:
                    return String.format("</%s>", this.tag);

            }
        }

    }

    static class HtmlElementImpl extends ElementImpl {

        public HtmlElementImpl(String tag) {
            super(tag);
        }
    }

    static class HtmlSingleElementImpl extends ElementImpl implements SingleElement {

        public HtmlSingleElementImpl(String tag) {
            super(tag);
        }

        @Override
        public Element setTextContent(String content) {
            return this;
        }

        @Override
        public String render() {
            StringBuilder builder = new StringBuilder();
            builder.append(renderIndent());
            builder.append(renderStartTag());
            builder.append(renderLinefeed());
            return builder.toString();
        }

        @Override
        protected String renderStartTag() {
            return String.format("<%s %s />", this.tag, this.renderAttributes());
        }
    }

    static class HtmlPageElementImpl extends ElementImpl implements HTML.Page {

        private final HTML.Head head;
        private final HTML.Body body;

        HtmlPageElementImpl() {
            super("html");
            HTML.Head head0 = null;
            HTML.Body body0 = null;
            try {
                head0 = HTML.Head.create();
                body0 = HTML.Body.create();
                this.append(head0);
                this.append(body0);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            this.head = head0;
            this.body = body0;
        }

        @Override
        public HTML.Head htmlHead() {
            return this.head;
        }

        @Override
        public HTML.Body htmlBody() {
            return this.body;
        }

    }

    static class HtmlAImpl extends ElementImpl implements A {

        public HtmlAImpl() {
            super("a");
        }
    }

    static class HtmlAbbrImpl extends ElementImpl implements HTML.Abbr {

        public HtmlAbbrImpl() {
            super("abbr");
        }
    }

    static class HtmlAddressImpl extends ElementImpl implements HTML.Address {

        public HtmlAddressImpl() {
            super("address");
        }
    }

    static class HtmlAppletImpl extends ElementImpl implements HTML.Applet {

        public HtmlAppletImpl() {
            super("applet");
        }
    }

    static class HtmlAreaImpl extends ElementImpl implements HTML.Area {

        public HtmlAreaImpl() {
            super("area");
        }
    }

    static class HtmlArticleImpl extends ElementImpl implements HTML.Article {

        public HtmlArticleImpl() {
            super("article");
        }
    }

    static class HtmlAsideImpl extends ElementImpl implements HTML.Aside {

        public HtmlAsideImpl() {
            super("aside");
        }
    }

    static class HtmlAudioImpl extends ElementImpl implements HTML.Audio {

        public HtmlAudioImpl() {
            super("audio");
        }
    }

    static class HtmlBImpl extends ElementImpl implements HTML.B {

        public HtmlBImpl() {
            super("b");
        }
    }

    static class HtmlBaseImpl extends ElementImpl implements HTML.Base {

        public HtmlBaseImpl() {
            super("base");
        }
    }

    static class HtmlBasefontImpl extends ElementImpl implements HTML.Basefont {

        public HtmlBasefontImpl() {
            super("basefont");
        }
    }

    static class HtmlBbImpl extends ElementImpl implements HTML.Bb {

        public HtmlBbImpl() {
            super("bb");
        }
    }

    static class HtmlBdoImpl extends ElementImpl implements HTML.Bdo {

        public HtmlBdoImpl() {
            super("bdo");
        }
    }

    static class HtmlBigImpl extends ElementImpl implements HTML.Big {

        public HtmlBigImpl() {
            super("big");
        }
    }

    static class HtmlBlockquoteImpl extends ElementImpl implements HTML.Blockquote {

        public HtmlBlockquoteImpl() {
            super("blockquote");
        }
    }

    static class HtmlBodyImpl extends ElementImpl implements HTML.Body {

        public HtmlBodyImpl() {
            super("body");
        }
    }

    static class HtmlBrImpl extends ElementImpl implements HTML.Br {

        public HtmlBrImpl() {
            super("br");
        }
    }

    static class HtmlButtonImpl extends ElementImpl implements HTML.Button {

        public HtmlButtonImpl() {
            super("button");
        }
    }

    static class HtmlCanvasImpl extends ElementImpl implements HTML.Canvas {

        public HtmlCanvasImpl() {
            super("canvas");
        }
    }

    static class HtmlCaptionImpl extends ElementImpl implements HTML.Caption {

        public HtmlCaptionImpl() {
            super("caption");
        }
    }

    static class HtmlCenterImpl extends ElementImpl implements HTML.Center {

        public HtmlCenterImpl() {
            super("center");
        }
    }

    static class HtmlCiteImpl extends ElementImpl implements HTML.Cite {

        public HtmlCiteImpl() {
            super("cite");
        }
    }

    static class HtmlCodeImpl extends ElementImpl implements HTML.Code {

        public HtmlCodeImpl() {
            super("code");
        }
    }

    static class HtmlColImpl extends ElementImpl implements HTML.Col {

        public HtmlColImpl() {
            super("col");
        }
    }

    static class HtmlColgroupImpl extends ElementImpl implements HTML.Colgroup {

        public HtmlColgroupImpl() {
            super("colgroup");
        }
    }

    static class HtmlCommandImpl extends ElementImpl implements HTML.Command {

        public HtmlCommandImpl() {
            super("command");
        }
    }

    static class HtmlDatagridImpl extends ElementImpl implements HTML.Datagrid {

        public HtmlDatagridImpl() {
            super("datagrid");
        }
    }

    static class HtmlDatalistImpl extends ElementImpl implements HTML.Datalist {

        public HtmlDatalistImpl() {
            super("datalist");
        }
    }

    static class HtmlDdImpl extends ElementImpl implements HTML.Dd {

        public HtmlDdImpl() {
            super("dd");
        }
    }

    static class HtmlDelImpl extends ElementImpl implements HTML.Del {

        public HtmlDelImpl() {
            super("del");
        }
    }

    static class HtmlDetailsImpl extends ElementImpl implements HTML.Details {

        public HtmlDetailsImpl() {
            super("details");
        }
    }

    static class HtmlDialogImpl extends ElementImpl implements HTML.Dialog {

        public HtmlDialogImpl() {
            super("dialog");
        }
    }

    static class HtmlDirImpl extends ElementImpl implements HTML.Dir {

        public HtmlDirImpl() {
            super("dir");
        }
    }

    static class HtmlDivImpl extends ElementImpl implements HTML.Div {

        public HtmlDivImpl() {
            super("div");
        }
    }

    static class HtmlDfnImpl extends ElementImpl implements HTML.Dfn {

        public HtmlDfnImpl() {
            super("dfn");
        }
    }

    static class HtmlDlImpl extends ElementImpl implements HTML.Dl {

        public HtmlDlImpl() {
            super("dl");
        }
    }

    static class HtmlDtImpl extends ElementImpl implements HTML.Dt {

        public HtmlDtImpl() {
            super("dt");
        }
    }

    static class HtmlEmImpl extends ElementImpl implements HTML.Em {

        public HtmlEmImpl() {
            super("em");
        }
    }

    static class HtmlEmbedImpl extends ElementImpl implements HTML.Embed {

        public HtmlEmbedImpl() {
            super("embed");
        }
    }

    static class HtmlFieldsetImpl extends ElementImpl implements HTML.Fieldset {

        public HtmlFieldsetImpl() {
            super("fieldset");
        }
    }

    static class HtmlFigureImpl extends ElementImpl implements HTML.Figure {

        public HtmlFigureImpl() {
            super("figure");
        }
    }

    static class HtmlFontImpl extends ElementImpl implements HTML.Font {

        public HtmlFontImpl() {
            super("font");
        }
    }

    static class HtmlFooterImpl extends ElementImpl implements HTML.Footer {

        public HtmlFooterImpl() {
            super("footer");
        }
    }

    static class HtmlFormImpl extends ElementImpl implements HTML.Form {

        public HtmlFormImpl() {
            super("form");
        }
    }

    static class HtmlFrameImpl extends ElementImpl implements HTML.Frame {

        public HtmlFrameImpl() {
            super("frame");
        }
    }

    static class HtmlFramesetImpl extends ElementImpl implements HTML.Frameset {

        public HtmlFramesetImpl() {
            super("frameset");
        }
    }

    static class HtmlH1Impl extends ElementImpl implements HTML.H1 {

        public HtmlH1Impl() {
            super("h1");
        }
    }

    static class HtmlH2Impl extends ElementImpl implements HTML.H2 {

        public HtmlH2Impl() {
            super("h2");
        }
    }

    static class HtmlH3Impl extends ElementImpl implements HTML.H3 {

        public HtmlH3Impl() {
            super("h3");
        }
    }

    static class HtmlH4Impl extends ElementImpl implements HTML.H4 {

        public HtmlH4Impl() {
            super("h4");
        }
    }

    static class HtmlH5Impl extends ElementImpl implements HTML.H5 {

        public HtmlH5Impl() {
            super("h5");
        }
    }

    static class HtmlH6Impl extends ElementImpl implements HTML.H6 {

        public HtmlH6Impl() {
            super("h6");
        }
    }

    static class HtmlHeadImpl extends ElementImpl implements HTML.Head {

        public HtmlHeadImpl() {
            super("head");
        }
    }

    static class HtmlHeaderImpl extends ElementImpl implements HTML.Header {

        public HtmlHeaderImpl() {
            super("header");
        }
    }

    static class HtmlHgroupImpl extends ElementImpl implements HTML.Hgroup {

        public HtmlHgroupImpl() {
            super("hgroup");
        }
    }

    static class HtmlHrImpl extends ElementImpl implements HTML.Hr {

        public HtmlHrImpl() {
            super("hr");
        }
    }

    static class HtmlIImpl extends ElementImpl implements HTML.I {

        public HtmlIImpl() {
            super("i");
        }
    }

    static class HtmlIframeImpl extends ElementImpl implements HTML.Iframe {

        public HtmlIframeImpl() {
            super("iframe");
        }
    }

    static class HtmlImgImpl extends ElementImpl implements HTML.Img {

        public HtmlImgImpl() {
            super("img");
        }
    }

    static class HtmlInputImpl extends ElementImpl implements HTML.Input {

        public HtmlInputImpl() {
            super("input");
        }

        @Override
        public Element setName(String name) {
            try {
                this.addAttribute(HTML.Attribute.create("name", name));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return this;
        }

        @Override
        public HTML.TagOmission tagOmission() {
            return HTML.TagOmission.EndMustBeOmitted;
        }

    }

    static class HtmlInsImpl extends ElementImpl implements HTML.Ins {

        public HtmlInsImpl() {
            super("ins");
        }
    }

    static class HtmlIsindexImpl extends ElementImpl implements HTML.Isindex {

        public HtmlIsindexImpl() {
            super("isindex");
        }
    }

    static class HtmlKdbImpl extends ElementImpl implements HTML.Kdb {

        public HtmlKdbImpl() {
            super("kdb");
        }
    }

    static class HtmlLabelImpl extends ElementImpl implements HTML.Label {

        public HtmlLabelImpl() {
            super("label");
        }
    }

    static class HtmlLegendImpl extends ElementImpl implements HTML.Legend {

        public HtmlLegendImpl() {
            super("legend");
        }
    }

    static class HtmlLiImpl extends ElementImpl implements HTML.Li {

        public HtmlLiImpl() {
            super("li");
        }
    }

    static class HtmlLinkImpl extends ElementImpl implements HTML.Link {

        public HtmlLinkImpl() {
            super("link");
        }
    }

    static class HtmlMarkImpl extends ElementImpl implements HTML.Mark {

        public HtmlMarkImpl() {
            super("mark");
        }
    }

    static class HtmlMapImpl extends ElementImpl implements HTML.Map {

        public HtmlMapImpl() {
            super("map");
        }
    }

    static class HtmlMenuImpl extends ElementImpl implements HTML.Menu {

        public HtmlMenuImpl() {
            super("menu");
        }
    }

    static class HtmlMetaImpl extends ElementImpl implements HTML.Meta {

        public HtmlMetaImpl() {
            super("meta");
        }

        @Override
        public HTML.Meta setCharset(String charset) throws Exception {
            return this;
        }
    }

    static class HtmlMeterImpl extends ElementImpl implements HTML.Meter {

        public HtmlMeterImpl() {
            super("meter");
        }
    }

    static class HtmlNavImpl extends ElementImpl implements HTML.Nav {

        public HtmlNavImpl() {
            super("nav");
        }
    }

    static class HtmlNoframesImpl extends ElementImpl implements HTML.Noframes {

        public HtmlNoframesImpl() {
            super("noframes");
        }
    }

    static class HtmlNoscriptImpl extends ElementImpl implements HTML.Noscript {

        public HtmlNoscriptImpl() {
            super("noscript");
        }
    }

    static class HtmlObjectImpl extends ElementImpl implements HTML.Object {

        public HtmlObjectImpl() {
            super("object");
        }
    }

    static class HtmlOlImpl extends ElementImpl implements HTML.Ol {

        public HtmlOlImpl() {
            super("ol");
        }
    }

    static class HtmlOptgroupImpl extends ElementImpl implements HTML.Optgroup {

        public HtmlOptgroupImpl() {
            super("optgroup");
        }
    }

    static class HtmlOptionImpl extends ElementImpl implements HTML.Option {

        public HtmlOptionImpl() {
            super("option");
        }

        public Element setValue(String value) {
            try {
                this.addAttribute(HTML.Attribute.create("value", value));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return this;
        }
        public Element setLabel(String label) {
            try {
                this.setTextContent(label);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return this;
        }

        @Override
        public Element setTextContent(String content) {
            this.textContent = content;
            return this;
        }

    }

    static class HtmlOutputImpl extends ElementImpl implements HTML.Output {

        public HtmlOutputImpl() {
            super("output");
        }
    }

    static class HtmlPImpl extends ElementImpl implements HTML.P {

        public HtmlPImpl() {
            super("p");
        }
    }

    static class HtmlParamImpl extends ElementImpl implements HTML.Param {

        public HtmlParamImpl() {
            super("param");
        }
    }

    static class HtmlPreImpl extends ElementImpl implements HTML.Pre {

        public HtmlPreImpl() {
            super("pre");
        }
    }

    static class HtmlProgressImpl extends ElementImpl implements HTML.Progress {

        public HtmlProgressImpl() {
            super("progress");
        }
    }

    static class HtmlQImpl extends ElementImpl implements HTML.Q {

        public HtmlQImpl() {
            super("q");
        }
    }

    static class HtmlRubyImpl extends ElementImpl implements HTML.Ruby {

        public HtmlRubyImpl() {
            super("ruby");
        }
    }

    static class HtmlRpImpl extends ElementImpl implements HTML.Rp {

        public HtmlRpImpl() {
            super("rp");
        }
    }

    static class HtmlRtImpl extends ElementImpl implements HTML.Rt {

        public HtmlRtImpl() {
            super("rt");
        }
    }

    static class HtmlSImpl extends ElementImpl implements HTML.S {

        public HtmlSImpl() {
            super("s");
        }
    }

    static class HtmlSampImpl extends ElementImpl implements HTML.Samp {

        public HtmlSampImpl() {
            super("samp");
        }
    }

    static class HtmlScriptImpl extends ElementImpl implements HTML.Script {

        public HtmlScriptImpl() {
            super("script");
        }

        @Override
        public void append(String code) {

        }
    }

    static class HtmlSectionImpl extends ElementImpl implements HTML.Section {

        public HtmlSectionImpl() {
            super("section");
        }
    }

    static class HtmlSelectImpl extends ElementImpl implements HTML.Select {

        public HtmlSelectImpl() {
            super("select");
        }
    }

    static class HtmlSmallImpl extends ElementImpl implements HTML.Small {

        public HtmlSmallImpl() {
            super("small");
        }
    }

    static class HtmlSourceImpl extends ElementImpl implements HTML.Source {

        public HtmlSourceImpl() {
            super("source");
        }
    }

    static class HtmlSpanImpl extends ElementImpl implements HTML.Span {

        public HtmlSpanImpl() {
            super("span");
        }
    }

    static class HtmlStrikeImpl extends ElementImpl implements HTML.Strike {

        public HtmlStrikeImpl() {
            super("strike");
        }
    }

    static class HtmlStrongImpl extends ElementImpl implements HTML.Strong {

        public HtmlStrongImpl() {
            super("strong");
        }
    }

    static class HtmlStyleImpl extends ElementImpl implements HTML.Style {

        private final List<CSS.Element> cssElements = new ArrayList<>();

        public HtmlStyleImpl() {
            super("style");
        }

        @Override
        public void append(CSS.Element cssElement) {
            this.cssElements.add(cssElement);
        }
        @Override
        public String render() {
            StringBuilder builder = new StringBuilder();
            builder.append(renderIndent());
            builder.append(renderStartTag());
            builder.append(renderContent());
            if (!this.cssElements.isEmpty()) {
                builder.append(renderLinefeed());
                builder.append(renderChildren());
                builder.append(renderIndent());
            }
            builder.append(renderEndTag());
            builder.append(renderLinefeed());
            return builder.toString();
        }

        @Override
        protected String renderChildren() {
            StringBuilder builder = new StringBuilder();
            if (this.cssElements != null && !this.cssElements.isEmpty()) {
                cssElements.stream()
                        .forEachOrdered(e -> {
                            try {
                                builder.append(e.render());
                            } catch (Exception ex) {

                            }
                        });
            }
            return builder.toString();
        }
    }

    static class HtmlSubImpl extends ElementImpl implements HTML.Sub {

        public HtmlSubImpl() {
            super("sub");
        }
    }

    static class HtmlSupImpl extends ElementImpl implements HTML.Sup {

        public HtmlSupImpl() {
            super("sup");
        }
    }

    static class HtmlTableImpl extends ElementImpl implements HTML.Table {

        public HtmlTableImpl() {
            super("table");
        }
    }

    static class HtmlTbodyImpl extends ElementImpl implements HTML.Tbody {

        public HtmlTbodyImpl() {
            super("tbody");
        }
    }

    static class HtmlTdImpl extends ElementImpl implements HTML.Td {

        public HtmlTdImpl() {
            super("td");
        }
    }

    static class HtmlTextareaImpl extends ElementImpl implements HTML.Textarea {

        public HtmlTextareaImpl() {
            super("textarea");
        }
    }

    static class HtmlTfootImpl extends ElementImpl implements HTML.Tfoot {

        public HtmlTfootImpl() {
            super("tfoot");
        }
    }

    static class HtmlThImpl extends ElementImpl implements HTML.Th {

        public HtmlThImpl() {
            super("th");
        }
    }

    static class HtmlTheadImpl extends ElementImpl implements HTML.Thead {

        public HtmlTheadImpl() {
            super("thead");
        }
    }

    static class HtmlTimeImpl extends ElementImpl implements HTML.Time {

        public HtmlTimeImpl() {
            super("time");
        }
    }

    static class HtmlTitleImpl extends ElementImpl implements HTML.Title {

        public HtmlTitleImpl() {
            super("title");
        }
    }

    static class HtmlTrImpl extends ElementImpl implements HTML.Tr {

        public HtmlTrImpl() {
            super("tr");
        }
    }

    static class HtmlTtImpl extends ElementImpl implements HTML.Tt {

        public HtmlTtImpl() {
            super("tt");
        }
    }

    static class HtmlUImpl extends ElementImpl implements HTML.U {

        public HtmlUImpl() {
            super("u");
        }
    }

    static class HtmlUlImpl extends ElementImpl implements HTML.Ul {

        public HtmlUlImpl() {
            super("ul");
        }
    }

    static class HtmlVarImpl extends ElementImpl implements HTML.Var {

        public HtmlVarImpl() {
            super("var");
        }
    }

    static class HtmlVideoImpl extends ElementImpl implements HTML.Video {

        public HtmlVideoImpl() {
            super("video");
        }
    }

    static class HtmlXmpImpl extends ElementImpl implements HTML.Xmp {

        public HtmlXmpImpl() {
            super("xmp");
        }
    }

}
