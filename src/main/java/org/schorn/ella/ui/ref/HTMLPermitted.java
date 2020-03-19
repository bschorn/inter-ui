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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.schorn.ella.ui.html.HTML;

/**
 *
 * @author bschorn
 */
public class HTMLPermitted {

    private final PermittedContent defaultPermittedContent;
    private final Map<String, PermittedContent> permittedContentMap = new HashMap<>();

    private final PermittedParents defaultPermittedParents;
    private final Map<String, PermittedParents> permittedParentsMap = new HashMap<>();

    public HTMLPermitted() {
        this.defaultPermittedContent = null;
        this.defaultPermittedParents = null;
    }

    public void add(String tag, PermittedContent content, PermittedParents parents) {
        this.permittedContentMap.put(tag, content);
        this.permittedParentsMap.put(tag, parents);
    }

    public PermittedContent getPermittedContent(String tag) {
        PermittedContent permittedContent = this.permittedContentMap.get(tag);
        if (permittedContent == null) {
            return this.defaultPermittedContent;
        }
        return permittedContent;
    }

    public PermittedParents getPermittedParents(String tag) {
        PermittedParents permittedParents = this.permittedParentsMap.get(tag);
        if (permittedParents == null) {
            return this.defaultPermittedParents;
        }
        return permittedParents;
    }

    public enum Restriction {
        OPTIONAL,
        ZERO_OR_MORE,
        ONE_OR_MORE,
        EITHER_ONE_OF,
        LIST_ORDERED,
        NONE;
    }

    interface Content {

        Restriction rule();
    }

    static public class TagContent implements Content {

        private final String tag;
        private final Restriction rule;

        public TagContent(Restriction rule, String tag) {
            this.rule = rule;
            this.tag = tag;
        }

        public String tag() {
            return this.tag;
        }

        @Override
        public Restriction rule() {
            return this.rule;
        }
    }

    static public class ListContent implements Content {

        private final List<String> tags;
        private final Restriction rule;

        public ListContent(Restriction rule, String... tags) {
            this.tags = Arrays.asList(tags);
            this.rule = rule;
        }

        public List<String> tags() {
            return this.tags;
        }

        @Override
        public Restriction rule() {
            return this.rule;
        }
    }

    static public class CategoryContent implements Content {

        private final HTML.ContentCategory category;
        private final Restriction rule;

        public CategoryContent(Restriction rule, HTML.ContentCategory category) {
            this.rule = rule;
            this.category = category;
        }

        public HTML.ContentCategory category() {
            return this.category;
        }

        @Override
        public Restriction rule() {
            return this.rule;
        }
    }

    static public class PermittedContent {

        private final List<Content> acceptables = new ArrayList<>();
        private final List<Restriction> rules = new ArrayList<>();

        public void add(Content content) {
            this.acceptables.add(content);
        }

        public boolean acceptable(HTML.Element target, String candidateTag) {
            return true;
        }
    }

    static public class PermittedParents {

    }
}
