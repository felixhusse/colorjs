package de.fatalix.vaadin.addon.jscolor.demo;


import javax.servlet.annotation.WebServlet;


import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Property;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ColorPickerArea;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.colorpicker.ColorChangeEvent;
import com.vaadin.ui.components.colorpicker.ColorChangeListener;
import de.fatalix.vaadin.addon.jscolor.JSColor;
import java.awt.Color;
import java.util.Arrays;


@Theme("demo")
@Title("JSColor Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI {

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(final VaadinRequest request) {
        try {
            
            // Show it in the middle of the screen
            final VerticalLayout layout = new VerticalLayout();
            layout.setStyleName("demoContentLayout");
            final JSColor jsColor = new JSColor();
            
            Button getColor = new Button("Get Color", new Button.ClickListener() {

                @Override
                public void buttonClick(Button.ClickEvent event) {
                    Notification.show("Color: " + jsColor.getColor().toString());
                }
            });
            
            Button setColor = new Button("Set Color", new Button.ClickListener() {

                @Override
                public void buttonClick(Button.ClickEvent event) {
                    
                    jsColor.setColor(Color.yellow);
                }
            });
            final ColorPickerArea colorPicker = new ColorPickerArea(null, com.vaadin.shared.ui.colorpicker.Color.BLACK);
            colorPicker.addColorChangeListener(new ColorChangeListener() {

                @Override
                public void colorChanged(ColorChangeEvent event) {
                    Color color = new Color(event.getColor().getRGB());
                    jsColor.setColor(color);
                }
            });
    
            HorizontalLayout buttonLayout = new HorizontalLayout(getColor,setColor,colorPicker);
            layout.addComponents(buttonLayout,jsColor);
            layout.setExpandRatio(jsColor, 1.0f);
            layout.setComponentAlignment(jsColor, Alignment.MIDDLE_CENTER);
            layout.setSizeFull();
            
            
            setContent(layout);

        } catch(Exception ex) {
            ex.printStackTrace(System.err);
        }
    }
}
