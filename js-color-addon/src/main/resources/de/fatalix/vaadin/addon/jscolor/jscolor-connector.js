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
window.de_fatalix_vaadin_addon_jscolor_JSColor = function() {
    var myJsColor;
    var e = this.getElement();
    var self = this;
    
    this.onStateChange = function() {
        var state = this.getState();
        initJSColor(state);
        if (state.codeData.state === 'COLOR') {
            myJsColor.fromRGB(state.codeData.color[0],state.codeData.color[1],state.codeData.color[2])
        }
    };

    initJSColor = function(state) {
        if (typeof myJsColor === 'undefined') {
            e.innerHTML = e.innerHTML + "<input id='js-color-" + state.codeData.id + "' onChange='colorChanged()'>";
            myJsColor = new jscolor.color(document.getElementById('js-color-' + state.codeData.id),{});
            myJsColor.fromString('99FF33')
        }
    };
    
    colorChanged = function() {
        self.onColorChanged(myJsColor.rgb);
    };

};

