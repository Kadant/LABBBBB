package Objects;

import java.awt.*;

/**
 * A car class Volvo240, extending the class AbstractCar
 */
public class Volvo240 extends AbstractCar{

    /** A factor that determines how much faster the car will travel */
    private final static double trimFactor = 1.25;

     public Volvo240(){
        super(4,125,0,Color.blue, "Volvo240",1,0,0,0);
        stopEngine();
    }

    /**
     * Determines the speed used to move the car
     * @return the speedFactor is based on trimFactor
     */
    @Override
    public double speedFactor(){
        return getEnginePower() * 0.01 * trimFactor;
    }

}