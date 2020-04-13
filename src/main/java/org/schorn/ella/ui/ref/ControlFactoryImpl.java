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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.StringJoiner;
import org.schorn.ella.ui.widget.ControlWidgets;

/**
 *
 * @author bschorn
 */
public final class ControlFactoryImpl implements ControlWidgets.ControlFactory {

    static private final ControlFactoryImpl INSTANCE = new ControlFactoryImpl();

    static public ControlWidgets.ControlFactory getFactory() {
        return INSTANCE;
    }

    private ControlFactoryImpl() {
        this.register();
    }

    @Override
    public void register() {
        //READ.CHART.setImpl(ChartImpl.class);
        //READ.IMAGE.setImpl(ImageImpl.class);
        //READ.PROGRESS.setImpl(ProgressImpl.class);
        //READ.SCROLL.setImpl(ScrollImpl.class);
        //READ.TEXT.setImpl(TextImpl.class);
        ControlWidgets.MENU.setImpl(MenuImpl.class);
        ControlWidgets.MENU_ITEM.setImpl(MenuItemImpl.class);
    }

    @Override
    public <T> T createInstance(ControlWidgets output, Object... params) throws Exception {
        Class<?> classFor = output.getImpl();
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
                READ.class.getSimpleName(),
                joiner.toString(),
                output.interfaceOf.getSimpleName());
             */
        }
        return newInstance;
    }

}
