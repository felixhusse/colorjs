/*
 * Copyright 2014 fatalix.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.fatalix.vaadin.addon.jscolor;

import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractJavaScriptComponent;
import com.vaadin.ui.JavaScriptFunction;
import java.awt.Color;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *
 * @author felix.husse
 */
@JavaScript({"vaadin://jscolor/jscolor.js","jscolor-connector.js"})
public class JSColor extends AbstractJavaScriptComponent{
    private static int componentCount = 0;
    
    private Color color;
    
    private final int componentId;
    
    public JSColor() {
        super();
        componentId = componentCount;
        componentCount++;
        JSColorData data = new JSColorData();
        data.id = componentId;
        getState().codeData = data;
        addFunction("onColorChanged", new JavaScriptFunction() {
            @Override
            public void call(JSONArray arguments) throws JSONException {
                JSONArray rgbArray = arguments.getJSONArray(0);     
                color = new Color((float)rgbArray.getDouble(0), (float)rgbArray.getDouble(1), (float)rgbArray.getDouble(2));
            }
        });
    }
    
    public Color getColor() {
        return color;
    }
    
    public void setColor(Color color) {
        this.color = color;
        JSColorData data = new JSColorData();
        data.id = componentId;
        data.state = "COLOR";
        double[] colorArray = new double[3];
        colorArray[0] = (color.getRed()/255);
        colorArray[1] = (color.getGreen()/255);
        colorArray[2] = (color.getBlue()/255);
        data.color = colorArray;
        getState().codeData = data;
    }
    
    @Override
    protected JSColorState getState() {
        return (JSColorState) super.getState(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
