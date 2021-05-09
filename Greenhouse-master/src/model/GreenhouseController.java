//: innerclasses/controller/Controller.java
// The reusable framework for control systems.
package model;

import javafx.application.Platform;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class GreenhouseController {
  // A class from java.util to hold Event objects:
  private List<Event> eventList = new ArrayList<Event>();
  private sample.Controller controller;

  public GreenhouseController(sample.Controller controller) {
    this.controller = controller;
  }

  public void addEvent(Event c) { eventList.add(c); }
  public List<Event> getEvent() { return eventList; }
  public void run() {
    while(eventList.size() > 0) {
      // Make a copy so you're not modifying the list
      // while you're selecting the elements in it:
      for (Event e : new ArrayList<Event>(eventList))
        if (e.ready()) {
          System.out.println(e);
          Platform.runLater(() -> {
            if (e instanceof GreenhouseControls.LightOn) controller.lightCircle.setFill(Color.YELLOW);
            if (e instanceof GreenhouseControls.LightOff) controller.lightCircle.setFill(Color.WHITE);
            if (e instanceof GreenhouseControls.WaterOn) controller.waterCircle.setFill(Color.BLUE);
            if (e instanceof GreenhouseControls.WaterOff) controller.waterCircle.setFill(Color.WHITE);
            if (e instanceof GreenhouseControls.ThermostatDay) controller.thermCircle.setFill(Color.YELLOW);
            if (e instanceof GreenhouseControls.ThermostatNight) controller.thermCircle.setFill(Color.BLACK);
            controller.label.setText(e.toString());
          });
          e.action();
          eventList.remove(e);
        }
    }
  }
} ///:~