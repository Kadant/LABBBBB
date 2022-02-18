package Objects;

import Interfaces.Movable;

import java.awt.*;

/**
 * Abstract class that contains all shared methods and variables among motor driven vehicles
 */
public abstract class AbstractMotorVehicle implements Movable {

    private double currentSpeed;
    private Color color;
    private final String modelName;
    private int dx;
    private int dy;
    private double x;
    private double y;
    private int nrDoors;
    private int enginePower;

    /**
     * Constructor to AbstractMotorVehicle, the motor driven vehicles
     * @param currentSpeed is the current speed of the vehicle
     * @param color is the color of the vehicle
     * @param modelName is the model name of the vehicle
     * @param dx direction in the x-axis
     * @param dy direction in the y-axis
     * @param x position x of the vehicle
     * @param y position y of the vehicle
     * @param nrDoors number of doors of the vehicle
     * @param enginePower engine power of the vehicle
     */

    public AbstractMotorVehicle(double currentSpeed, Color color, String modelName, int dx, int dy, double x, double y, int nrDoors, int enginePower) {
        this.currentSpeed = currentSpeed;
        this.color = color;
        this.modelName = modelName;
        this.dx = dx;
        this.dy = dy;
        this.x = x;
        this.y = y;
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
    }

    /** Getters / Setters */
    public int getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public void setCurrentSpeed(double d){
        currentSpeed = d;
    }

    public int getDx(){
        return dx;
    }

    public int getDy(){
        return dy;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }
    
    public void setX(double d){
        x = d;
    }

    public void setY(double d){
        y = d;
    }

    public void setDx(int d){
        dx = d;
    }

    public void setDy(int d){
        dy = d;
    }

    /**
     * Starts the engine of the vehicle
     */
    public void startEngine(){
        currentSpeed = 0.1;
    }

    /**
     * Stops the engine of the vehicle
     */
    public void stopEngine(){
        currentSpeed = 0;
    }


    /**
     * Turning the vehicle 90 degrees to the left
     */
    public void turnLeft () {
        int temp = -dy;
        dy = dx;
        dx = temp;
    }

    /**
     * Turning the vehicle 90 degrees to the right
     */
    public void turnRight () {
        int temp = dy;
        dy = dx;
        dx = temp;
    }

    /**
     * Moving the vehicle based on currentSpeed
     */
    public void move () {
        x += dx * currentSpeed;
        y += dy * currentSpeed;
    }

    /**
     * Determines whether a motor vehicle is close to another motor vehicle
     * @param vehicle is the vehicle whose position is what is used to calculate distance
     * @return true if the midpoint is less than or equal to 1 and false if midpoint is larger than 1
     */
    public boolean isCloseTo(AbstractMotorVehicle vehicle) {
        double midX = (vehicle.getX() + getX())/2;
        double midY = (vehicle.getY() + getY())/2;

        return (Math.abs(midX) <= 1 && Math.abs(midY) <= 1);
    }

    /**
     * Speed factor of the vehicle, value set in subclasses
     */
    public double speedFactor() {
        return getEnginePower() * 0.01;
    }

    /**
     * Represents the rate at which velocity increases
     */
    private void incrementSpeed(double amount) {
        setCurrentSpeed(Math.min(getCurrentSpeed() + speedFactor() * amount, getEnginePower()));
    }

    /**
     * Represents the rate at which velocity decreases
     */
    private void decrementSpeed(double amount) {
        setCurrentSpeed(Math.max(getCurrentSpeed() - speedFactor() * amount,0));
    }

    /**
     * Increasing the speed of the vehicle
     * @param amount which to increase the speed with
     * @throws Exception if amount is not in interval [0, 1]
     */
    public void gas (double amount) throws Exception {
        if (amount >= 0 && amount <= 1) {
            incrementSpeed(amount);
        } else {
            throw new Exception("Error: Value is out of bounds.");
        }
    }

    /**
     * Decreasing the speed of the vehicle
     * @param amount which to decrease the speed with
     * @throws Exception if amount is not in interval [0, 1]
     */
    public void brake (double amount) throws Exception {
        if (amount >= 0 && amount <= 1) {
            decrementSpeed(amount);
        } else {
            throw new Exception("Error: Value is out of bounds.");
        }
    }
}