package Objects;

import java.awt.*;

public class Scania extends AbstractTruck {

    public double degree;   /** Current degree of the ramp */

    public Scania() {
        super(2,770, 0, Color.red, "Scania", 1, 0, 0, 200);
        degree = 0;
        stopEngine();
}
    /**
     * Checks if the Scania is moving
     * @return true if Scania is moving, false if not
     */
    private boolean isMoving () {
        if (getCurrentSpeed() != 0){
            degree = Math.min(0 ,degree);
            return true;
        } else{
            return false;
        }
    }

    /**
     * Raise the ramp of the truck
     * @param maxDegree, the degree we want to raise to
     */
    public void liftRampTruck (int maxDegree) throws Exception{
        if (!isMoving() && degree < 70 && degree != maxDegree) {
            degree++;
        } else if (maxDegree > 70){
            throw new Exception("Can't raise the ramp to more than 70 degrees.");
        } else if (isMoving()){
            throw new Exception("Can't raise the ramp while the truck is moving.");
        }
    }

    /**
     * Lower the ramp of the truck
     * @param minDegree, the degree we want to lower to
     */
    public void lowerRampTruck (int minDegree) throws Exception{
        if (!isMoving() && degree > 0 && degree != minDegree){
            degree--;
        } else if (degree < 0) {
            throw new Exception("Can't lower the ramp to less than 0 degrees.");
        } else if (isMoving()) {
            throw new Exception("Can't lower the ramp while the truck is moving.");
        }
    }

    /**
     * Move only if the ramp degree is equal to 0
     */
    @Override
    public void move () {
        if(degree == 0) {
            super.move();
        }
    }
}