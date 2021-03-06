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
    Broker broker = new Broker(controller);
    while(eventList.size() > 0) {
      // Make a copy so you're not modifying the list
      // while you're selecting the elements in it:
      for (Event e : new ArrayList<Event>(eventList))
        if (e.ready()) {
          System.out.println(e);
          Platform.runLater(() -> {
            broker.checkEvent(e);
          });
          e.action();
          eventList.remove(e);
        }
    }
  }
} ///:~