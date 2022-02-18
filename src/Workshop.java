import Objects.AbstractCar;

import java.util.ArrayList;
import java.util.List;

public class Workshop <T extends AbstractCar> {
    private final int maxNum;
    public List<T> carList;

    /**
     * Constructor to Workshop
     * @param maxNum is the max amount of cars the workshop can have
     */
    public Workshop(int maxNum) {
        this.maxNum = maxNum;
        carList = new ArrayList<>();
    }

    /**
     * Adds the car to the workshop
     * @param car is the car that gets added
     */
    public void addCar(T car) throws Exception {
        if (maxNum > carList.size()) {
            carList.add(car);
        }
        else {
            throw new Exception("Workshop is full");
        }
    }

    /**
     * Removes the car from the workshop
     * @param index is the cars index in the workshops list of cars
     * @return car, the car that was removed
     */
    public T removeCar(int index) throws Exception {
        if (carList.size() != 0){
            T car = carList.get(index);
            carList.remove(index);
            return car;
        } else {
            throw new Exception("The workshop doesn't have any cars to be removed");
        }
    }
}