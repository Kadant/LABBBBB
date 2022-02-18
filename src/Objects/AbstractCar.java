package Objects;

import Objects.AbstractMotorVehicle;

import java.awt.*;

/**
 * Abstract class that contains all shared methods and variables among cars, extends AbstractMotorVehicle
 */
public abstract class AbstractCar extends AbstractMotorVehicle {

    /**
     * Constructor of AbstractCar
     * @param currentSpeed is the current speed of the car
     * @param color is the color of the car
     * @param modelName is the model name of the car
     * @param dx direction in the x-axis
     * @param dy direction in the y-axis
     * @param x position x of the vehicle
     * @param y position y of the vehicle
     * @param nrDoors number of doors of the car
     * @param enginePower engine power of the car
     */
    public AbstractCar (int nrDoors, int enginePower, double currentSpeed, Color color, String modelName, int dx, int dy, double x, double y) {
        super(currentSpeed, color, modelName, dx, dy, x, y, nrDoors, enginePower);
    }

}
