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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author bschorn
 */
public enum WRITE {
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

    public interface WriteFactory {

        public void register();

        public <T> T createInstance(WRITE input, Object... params) throws Exception;
    }

    private <T> T create(Object... params) throws Exception {
        return (T) FACTORY.createInstance(this, params);
    }

    static final Logger LGR = LoggerFactory.getLogger(WRITE.class);

    static final WriteFactory FACTORY;

    static {
        FACTORY = UIProvider.provider().getInputFactory();
    }

    public interface Input extends Widget {

    }

    public interface Button extends Input {

        static public Button create(Object... params) throws Exception {
            return WRITE.BUTTON.create(params);
        }
    }

    public interface CheckBoxGroup extends Input {

        static public CheckBoxGroup create(Object... params) throws Exception {
            return WRITE.CHECKBOXGROUP.create(params);
        }
    }

    public interface ComboBox extends Input {

        static public ComboBox create(Object... params) throws Exception {
            return WRITE.COMBOBOX.create(params);
        }
    }

    public interface DataGrid extends Input {
        static public DataGrid create(Object... params) throws Exception {
            return WRITE.DATAGRID.create(params);
        }
    }

    public interface DatePicker extends Input {
        static public DatePicker create(Object... params) throws Exception {
            return WRITE.DATEPICKER.create(params);
        }
    }

    public interface DateRangePicker extends Input {
        static public DateRangePicker create(Object... params) throws Exception {
            return WRITE.DATERANGEPICKER.create(params);
        }
    }

    public interface RadioBoxGroup extends Input {
        static public RadioBoxGroup create(Object... params) throws Exception {
            return WRITE.RADIOBOXGROUP.create(params);
        }
    }

    public interface TextBox extends Input {

        static public TextBox create(Object... params) throws Exception {
            return WRITE.TEXTBOX.create(params);
        }
    }

}
