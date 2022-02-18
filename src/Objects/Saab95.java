package Objects;

import java.awt.*;

/**
 * A car class Saab95, extending the class AbstractCar
 */
public class Saab95 extends AbstractCar{

    private boolean turboOn; /** A speed boost */

    public Saab95() {
        super(2,125, 0, Color.red, "Saab95", 1, 0, 0, 100);
        setTurboOff();
        stopEngine();
    }

    /** Sets turbo on */
    public void setTurboOn() {
        turboOn = true;
    }

    /** Sets turbo off */
    public void setTurboOff() {
        turboOn = false;
    }

    /**
     * Determines the speed used to move the car
     * @return the speedFactor is based on whether turbo is on or off
     */
    @Override
    public double speedFactor() {
        double turbo = 1;
        if (turboOn) turbo = 120;
        return getEnginePower() * 0.01 * turbo;
    }
}