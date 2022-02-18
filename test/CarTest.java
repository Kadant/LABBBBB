import Objects.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests various methods
 */
public class CarTest {

    private Saab95 saab;
    private Volvo240 volvo;
    private Scania scania;
    private double amount;
    private CarTransport carTransport;
    private Workshop<Volvo240> workshopVolvo;
    private Workshop<AbstractCar> workshopAbstractCar;
    private Workshop<Saab95> workshopSaab;

    @Before
    public void init() {
        saab = new Saab95();
        volvo = new Volvo240();
        scania = new Scania();
        carTransport = new CarTransport();
        amount = 0.5;
        workshopVolvo = new Workshop<Volvo240>(10);
        workshopAbstractCar = new Workshop<AbstractCar>(4);
        workshopSaab = new Workshop<Saab95>(2);
    }

    @Test
    public void move() {
        saab.startEngine();
        saab.move();
        assert (saab.getX() == 0.1 && saab.getY() == 0);
    }

    @Test
    public void gasVolvo() throws Exception {
        volvo.startEngine();
        volvo.gas(0.5);
        assert (volvo.getCurrentSpeed() == Math.min(0.1 + (volvo.getEnginePower() * 0.01 * 1.25) * amount, volvo.getEnginePower()));
    }

    @Test
    public void gasSaab() throws Exception {
        saab.startEngine();
        saab.gas(0.5);
        assert (saab.getCurrentSpeed() == 0.1 + saab.speedFactor() * amount);
    }

    @Test
    public void turboSaab() throws Exception {
        saab.startEngine();
        saab.setTurboOn();
        saab.gas(0.5);
        assert (saab.getCurrentSpeed() == (Math.min(0.1 + saab.speedFactor() * amount, saab.getEnginePower())));
    }

    @Test
    public void breakVolvo() throws Exception {
        volvo.setCurrentSpeed(0.5);
        volvo.brake(0.5);
        assert (volvo.getCurrentSpeed() == 0);
    }

    @Test
    public void leftVolvo(){
        volvo.turnLeft();
        assert(volvo.getDx() == 0 && volvo.getDy() == 1);
    }

    @Test
    public void rightSaab(){
        saab.turnRight();
        assert(saab.getDx() == 0 && saab.getDy() == 1);
    }

    @Test
    public void stopEngine(){
        saab.startEngine();
        saab.move();
        saab.stopEngine();
        assert(saab.getCurrentSpeed() == 0);
    }

    @Test
    public void moveScaniaZeroDegrees() {
        scania.degree = 0;
        scania.setCurrentSpeed(2);
        scania.move();
        System.out.println(scania.getX());
        System.out.println(scania.getY());
        assert (scania.getX() != 0 && scania.getY() == 0);
    }

    @Test
    public void liftAndLowerScaniaRamp() throws Exception {
        scania.degree = 50;
        scania.liftRampTruck(51);
        scania.liftRampTruck(51);
        scania.lowerRampTruck(49);
        scania.lowerRampTruck(49);
        scania.lowerRampTruck(49);
        assert(scania.degree == 49);
    }

    @Test
    public void liftTransportRamp(){
        carTransport.rampUp = false;
        carTransport.setCurrentSpeed(0);
        carTransport.liftRampTransport();
        assert (carTransport.rampUp);
    }

    @Test
    public void lowerTransportRamp(){
        carTransport.rampUp = true;
        carTransport.setCurrentSpeed(1);
        carTransport.lowerRampTransport();
        assert (carTransport.rampUp);
    }

    @Test
    public void loadCarToTransport() throws Exception {
        carTransport.loadCar(volvo);
        carTransport.loadCar(saab);
        assert(carTransport.transported.size() == 2);
    }

    @Test
    public void unloadCarFromTransport() throws Exception {
        carTransport.loadCar(volvo);
        carTransport.unloadCar();
        assert(carTransport.transported.size() == 0);
    }

    @Test
    public void isCloseToFalse() {
        volvo.setX(3);
        volvo.setY(3);
        assert(!carTransport.isCloseTo(volvo));
    }

    @Test
    public void isCloseToTrue() {
        volvo.setX(2);
        volvo.setY(2);
        assert (carTransport.isCloseTo(volvo));
    }

    @Test
    public void addCarToWorkshop() throws Exception {
        workshopVolvo.addCar(volvo);
        assert(workshopVolvo.carList.size() == 1);
    }

    @Test
    public void removeToWorkshop() throws Exception {
        workshopSaab.addCar(saab);
        workshopSaab.removeCar(0);
        assert(workshopSaab.carList.size() == 0);
    }

    @Test
    public void addBothCarsToWorkshop() throws Exception {
        workshopAbstractCar.addCar(saab);
        workshopAbstractCar.addCar(volvo);
        assert(workshopAbstractCar.carList.size() == 2);
    }
}
