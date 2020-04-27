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
package org.schorn.ella.ui.widget;

import java.util.regex.Pattern;
import org.schorn.ella.ui.UIProvider;
import org.schorn.ella.ui.layout.Identifier;
import org.schorn.ella.ui.layout.Widget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author bschorn
 */
public enum InputWidgets {
    BUTTON,
    CHECKBOXGROUP,
    COMBOBOX,
    DATAGRID,
    DATEPICKER,
    DATERANGEPICKER,
    RADIOBOXGROUP,
    TEXTBOX;

    private Class<?> implOf = null;

    public void setImpl(Class<?> implOf) {
        this.implOf = implOf;
    }

    public Class<?> getImpl() {
        return this.implOf;
    }

    public interface InputFactory {

        public void register();

        public <T> T createInstance(InputWidgets input, Object... params) throws Exception;
    }

    private <T> T create(Object... params) throws Exception {
        return (T) FACTORY.createInstance(this, params);
    }

    static final Logger LGR = LoggerFactory.getLogger(InputWidgets.class);

    static final InputFactory FACTORY;

    static {
        FACTORY = UIProvider.provider().getInputFactory();
    }

    public interface Button extends Widget.Input {
        @Override
        default String widgetName() {
            return Button.class.getSimpleName().toLowerCase();
        }
        static public Button create(Identifier name, String label) throws Exception {
            return InputWidgets.BUTTON.create(name.toString(), label);
        }
    }

    public interface CheckBoxGroup extends Widget.Input {
        @Override
        default String widgetName() {
            return CheckBoxGroup.class.getSimpleName().toLowerCase();
        }

        static public CheckBoxGroup create(Identifier name, String label) throws Exception {
            return InputWidgets.CHECKBOXGROUP.create(name.toString(), label);
        }
    }

    public interface ComboBox extends Widget.Input {
        @Override
        default String widgetName() {
            return ComboBox.class.getSimpleName().toLowerCase();
        }

        static public ComboBox create(Identifier name, String label, String[] datalist) throws Exception {
            return InputWidgets.COMBOBOX.create(name.toString(), label, datalist);
        }
    }

    public interface DataGrid extends Widget.Input {

        @Override
        default String widgetName() {
            return DataGrid.class.getSimpleName().toLowerCase();
        }

        static public DataGrid create(Identifier name, String label) throws Exception {
            return InputWidgets.DATAGRID.create(name.toString(), label);
        }
    }

    public interface DatePicker extends Widget.Input {
        @Override
        default String widgetName() {
            return DatePicker.class.getSimpleName().toLowerCase();
        }

        static public DatePicker create(Identifier name, String label) throws Exception {
            return InputWidgets.DATEPICKER.create(name.toString(), label);
        }
    }

    public interface DateRangePicker extends Widget.Input {
        @Override
        default String widgetName() {
            return DateRangePicker.class.getSimpleName().toLowerCase();
        }

        static public DateRangePicker create(Identifier name, String label) throws Exception {
            return InputWidgets.DATERANGEPICKER.create(name.toString(), label);
        }
    }

    public interface RadioBoxGroup extends Widget.Input {
        @Override
        default String widgetName() {
            return RadioBoxGroup.class.getSimpleName().toLowerCase();
        }

        static public RadioBoxGroup create(Identifier name, String label) throws Exception {
            return InputWidgets.RADIOBOXGROUP.create(name.toString(), label);
        }
    }

    public interface TextBox extends Widget.Input {

        public Integer minLength();

        public Integer maxLength();

        public Pattern pattern();

        public Integer size();

        public Boolean isSpellcheck();

        public void setLength(int minLength, int maxLength);

        public void setPattern(Pattern pattern);

        public void setSize(int size);

        public void setSpellcheck(boolean spellcheck);

        @Override
        default String widgetName() {
            return TextBox.class.getSimpleName().toLowerCase();
        }

        static public TextBox create(Identifier name, String label, Pattern pattern) throws Exception {
            return InputWidgets.TEXTBOX.create(name.toString(), label, pattern);
        }
    }

}
