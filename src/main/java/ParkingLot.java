import java.util.HashSet;
import java.util.Set;

public class ParkingLot {
    private Set<Vehicle> cars;
    public int capacity;
    private final Owner owner;

    public ParkingLot(int capacity, Owner owner) {
        this.capacity=capacity;
        this.owner = owner;
        cars= new HashSet<>();

    }

    public void park(Vehicle vehicle) throws ParkingFullException , VehicleAlreadyParkedException{
        if(isParked(vehicle) )throw new VehicleAlreadyParkedException();
        if(cars.size() == capacity) throw new ParkingFullException();
        cars.add(vehicle);
        if(cars.size() == capacity) owner.notifyParkingFull();
    }


    public void unPark(Vehicle vehicle) {
        cars.remove(vehicle);
        owner.notifyUnPark();
    }

    public boolean isParked(Vehicle vehicle){
        return cars.contains(vehicle);
    }

}
