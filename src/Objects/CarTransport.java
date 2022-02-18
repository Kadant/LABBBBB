package Objects;

import java.awt.*;
import java.util.Stack;

/**
 * Class that contains all shared methods and variables among car transports
 */
public class CarTransport extends AbstractTruck {

    public boolean rampUp;                                  /** Whether the ramp ur raised or lowered */
    public Stack<AbstractCar> transported = new Stack<>();  /** Stack of cars representing the cars loaded on the transport */

    public CarTransport() {
        super(2, 125, 0, Color.red, "Scania142", 1, 0, 0, 0);
        rampUp = false;
        stopEngine();
    }

    /**
     * Loads a car on the transport and sets its x and y to the same as the transports x and y
     * @param car is the car being loaded
     */
    public void loadCar (AbstractCar car) throws Exception {
        if(!rampUp && getCurrentSpeed() == 0 && super.isCloseTo(car)) {
            transported.push(car);
            car.setX(getX());
            car.setY(getY());
        } else {
            throw new Exception("Can't load a transport on a transport");
        }
    }

    /**
     * Unloads a car off the transport at the position 2 x- and y "units" away from the transport
     * @return is the unloaded car
     */
    public AbstractCar unloadCar () throws Exception {
        AbstractCar unloadedCar;
        if (!rampUp && getCurrentSpeed() == 0) {
            unloadedCar = transported.pop();
            unloadedCar.setX(getX() - 2);
            unloadedCar.setY(getY() - 2);
        } else if (rampUp){
            throw new Exception("Can't unload since the ramp is up");
        } else {
            throw new Exception("Can't unload while the transport is moving");
        }
        return unloadedCar;
    }

    /**
     * Lift the ramp of the transport
     */
    public void liftRampTransport(){
        if(!rampUp && getCurrentSpeed() == 0){
            rampUp = true;
        }
    }

    /**
     * Lower the ramp of the transport
     */
    public void lowerRampTransport(){
        if(rampUp && getCurrentSpeed() == 0){
            rampUp = false;
        }
    }

    @Override
    public void move () {
        super.move();
        for (AbstractCar car : transported) {
            car.setX(getX());
            car.setY(getY());
        }
    }
}