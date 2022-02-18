import Objects.AbstractMotorVehicle;
import Objects.Saab95;
import Objects.Scania;
import Objects.Volvo240;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<AbstractMotorVehicle> cars = new ArrayList<>();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(new Volvo240());
        cc.cars.add(new Saab95());
        cc.cars.add(new Scania());

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (AbstractMotorVehicle car : cars) {
                car.move();
                int x = (int) Math.round(car.getX());
                int y = (int) Math.round(car.getY());
                if (car.getClass() == Saab95.class) {
                    frame.drawPanel.moveitSaab(x, y);
                } else if (car.getClass() == Volvo240.class) {
                    frame.drawPanel.moveitVolvo(x, y);
                } else if (car.getClass() == Scania.class) {
                    frame.drawPanel.moveitScania(x, y);
                }
                hitWalls(frame.drawPanel.volvoImage, car);
                hitWalls(frame.drawPanel.saabImage, car);
                hitWalls(frame.drawPanel.scaniaImage, car);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) throws Exception {
        double gas = ((double) amount) / 100;
        for (AbstractMotorVehicle car : cars
        ) {
            car.gas(gas);
        }
    }

    void brake(int amount) throws Exception {
        double brake = ((double) amount) / 100;
        for (AbstractMotorVehicle car : cars
        ) {
            car.brake(brake);
        }
    }

    void hitWalls(BufferedImage carImage, AbstractMotorVehicle car) {
        if (car.getX() < 0) {
            car.setDx(1);
        } else if (frame.getWidth() - carImage.getWidth() - 20 < car.getX()) {
            car.setDx(-1);
        }
    }
}

