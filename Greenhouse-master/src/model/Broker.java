package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import sample.Controller;

import java.util.HashMap;
import java.util.Map;

public class Broker {
    private Controller controller;
    private HashMap<Class<?>, Color> eventColorHashMap = new HashMap<>();
    private HashMap<String, Circle> circlesHashMap = new HashMap<>();

    public Broker(Controller controller) {
        this.controller = controller;
        eventColorHashMap.put(GreenhouseControls.LightOn.class, Color.YELLOW);
        eventColorHashMap.put(GreenhouseControls.LightOff.class, Color.WHITE);
        eventColorHashMap.put(GreenhouseControls.WaterOn.class, Color.BLUE);
        eventColorHashMap.put(GreenhouseControls.WaterOff.class, Color.WHITE);
        eventColorHashMap.put(GreenhouseControls.ThermostatDay.class, Color.YELLOW);
        eventColorHashMap.put(GreenhouseControls.ThermostatNight.class, Color.BLACK);
        circlesHashMap.put("Light", controller.lightCircle);
        circlesHashMap.put("Water", controller.waterCircle);
        circlesHashMap.put("Thermostat", controller.thermCircle);
    }

    public void checkEvent(Event e) {
       controller.label.setText(e.toString());
       for(Map.Entry<Class<?>, Color> entryEvent : eventColorHashMap.entrySet()) {
           if (entryEvent.getKey() == e.getClass()) {
                for(Map.Entry<String, Circle> entryCircles : circlesHashMap.entrySet()) { if (e.toString().startsWith(entryCircles.getKey())) {
                     entryCircles.getValue().setFill(entryEvent.getValue());
                    }
                }
           }
       }
    }
}
