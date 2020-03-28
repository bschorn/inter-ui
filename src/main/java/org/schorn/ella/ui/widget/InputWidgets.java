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

import org.schorn.ella.ui.UIProvider;
import org.schorn.ella.ui.layout.Type;
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

    public interface Button extends Input {
        @Override
        default Type type() {
            return Type.get(Button.class);
        }
        static public Button create(String name, String label) throws Exception {
            return InputWidgets.BUTTON.create(name, label);
        }
    }

    public interface CheckBoxGroup extends Input {
        @Override
        default Type type() {
            return Type.get(Button.class);
        }

        static public CheckBoxGroup create(String name, String label) throws Exception {
            return InputWidgets.CHECKBOXGROUP.create(name, label);
        }
    }

    public interface ComboBox extends Input {
        @Override
        default Type type() {
            return Type.get(Button.class);
        }

        static public ComboBox create(String name, String label, String[] datalist) throws Exception {
            return InputWidgets.COMBOBOX.create(name, label, datalist);
        }
    }

    public interface DataGrid extends Input {

        @Override
        default Type type() {
            return Type.get(DataGrid.class);
        }

        static public DataGrid create(String name, String label) throws Exception {
            return InputWidgets.DATAGRID.create(name, label);
        }
    }

    public interface DatePicker extends Input {
        @Override
        default Type type() {
            return Type.get(Button.class);
        }

        static public DatePicker create(String name, String label) throws Exception {
            return InputWidgets.DATEPICKER.create(name, label);
        }
    }

    public interface DateRangePicker extends Input {
        @Override
        default Type type() {
            return Type.get(DateRangePicker.class);
        }

        static public DateRangePicker create(String name, String label) throws Exception {
            return InputWidgets.DATERANGEPICKER.create(name, label);
        }
    }

    public interface RadioBoxGroup extends Input {
        @Override
        default Type type() {
            return Type.get(RadioBoxGroup.class);
        }

        static public RadioBoxGroup create(String name, String label) throws Exception {
            return InputWidgets.RADIOBOXGROUP.create(name, label);
        }
    }

    public interface TextBox extends Input {
        @Override
        default Type type() {
            return Type.get(TextBox.class);
        }

        static public TextBox create(String name, String label) throws Exception {
            return InputWidgets.TEXTBOX.create(name, label);
        }
    }

}
