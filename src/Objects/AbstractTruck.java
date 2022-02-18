package Objects;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Abstract class that contains all shared methods and variables among trucks, extends AbstractMotorVehicle
 */
abstract class AbstractTruck extends AbstractMotorVehicle {

    /**
     * Constructor of AbstractTruck
     * @param currentSpeed is the current speed of the truck
     * @param color is the color of the truck
     * @param modelName is the model name of the truck
     * @param dx direction in the x-axis
     * @param dy direction in the y-axis
     * @param x position x of the truck
     * @param y position y of the truck
     * @param nrDoors number of doors of the truck
     * @param enginePower engine power of the truck
     */
    public AbstractTruck(int nrDoors, int enginePower, double currentSpeed, Color color, String modelName, int dx, int dy, double x, double y) {
        super(currentSpeed, color, modelName, dx, dy, x, y, enginePower, nrDoors);
    }
}