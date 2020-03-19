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

/**
 *
 * @author bschorn
 */
@Deprecated
public class HtmlHelper {

    private final String htmlInterface;
    private final String htmlImpl;
    private final String htmlRegister = "        HTML.{TAG}.setImpl(Html{Element}Impl.class);";
    private final String htmlEnum = "   {TAG}({Element}.class, DOM.HTMLElement),";


    HtmlHelper() {
        StringBuilder inter = new StringBuilder();
        inter.append("    public interface {Element} extends Element {\n\n");
        inter.append("        static public {Element} create(Object... params) throws Exception {\n");
        inter.append("            return HTML.{TAG}.create({Element}.class, params);\n");
        inter.append("        }\n");
        inter.append("    }\n");
        htmlInterface = inter.toString();
        StringBuilder impl = new StringBuilder();
        impl.append("    static class Html{Element}Impl extends ElementImpl implements HTML.{Element} {\n\n");
        impl.append("        public Html{Element}Impl() {\n");
        impl.append("            super(\"{tag}\");\n");
        impl.append("        }\n");
        impl.append("    }\n");
        htmlImpl = impl.toString();

    }

    public void createInterfaces() {
        for (HTML html : HTML.values()) {
            String output = htmlInterface.replace("{TAG}", html.name());
            String element = html.name().toLowerCase();
            element = String.format("%s%s",
                    String.valueOf(element.charAt(0)).toUpperCase(),
                    element.substring(1));
            output = output.replace("{Element}", element);
            System.out.println(output);
        }
    }

    public void createImplementations() {
        for (HTML html : HTML.values()) {
            String tag = html.name().toLowerCase();
            String output = htmlImpl.replace("{tag}", tag);
            String element = String.format("%s%s",
                    String.valueOf(tag.charAt(0)).toUpperCase(),
                    tag.substring(1));
            output = output.replace("{Element}", element);
            System.out.println(output);
        }
    }

    public void createRegister() {
        for (HTML html : HTML.values()) {
            String tag = html.name();
            String output = htmlRegister.replace("{TAG}", tag);
            String element = String.format("%s%s",
                    String.valueOf(tag.charAt(0)),
                    tag.substring(1).toLowerCase());
            output = output.replace("{Element}", element);
            System.out.println(output);
        }
    }

    public void createEnum() {
        for (HTML html : HTML.values()) {
            String tag = html.name();
            String output = htmlEnum.replace("{TAG}", tag);
            String element = String.format("%s%s",
                    String.valueOf(tag.charAt(0)),
                    tag.substring(1).toLowerCase());
            output = output.replace("{Element}", element);
            System.out.println(output);
        }
    }

    static public void main(String[] args) {
        HtmlHelper htmlHelper = new HtmlHelper();
        //htmlHelper.createInterfaces();
        //htmlHelper.createRegister();
        //htmlHelper.createImplementations();
        htmlHelper.createEnum();

    }
}
