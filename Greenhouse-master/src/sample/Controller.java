package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import model.GreenhouseControls;
import model.Event;

public class Controller {
    public Circle lightCircle;
    public Circle waterCircle;
    public Circle thermCircle;
    public Label label;

    public void powerUpButton(ActionEvent actionEvent) {
        Thread thread = new Thread(() -> {
            GreenhouseControls gc = new GreenhouseControls(this);
            // Instead of hard-wiring, you could parse
            // configuration information from a text file here:
            gc.addEvent(gc.new Bell(900000000));
            Event[] eventList = {
                    gc.new ThermostatNight(0),
                    gc.new LightOn(200000000),
                    gc.new LightOff(400000000),
                    gc.new WaterOn(600000000),
                    gc.new WaterOff(800000000),
                    gc.new ThermostatDay(1400000000)
            };
            gc.addEvent(gc.new Restart(2000000000, eventList));
            gc.run();
        });
        thread.start();
    }
}
