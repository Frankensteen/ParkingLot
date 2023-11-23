import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ParkingLotTest {
    private Owner owner;

    @BeforeEach
    void setUp() {
        owner= Mockito.mock(Owner.class);
    }

    @Test
    void shouldThrowExceptionWhenParkingLotIsAlreadyFull() {
        ParkingLot parkingLot = new ParkingLot(0, owner);
        Vehicle vehicle = new Vehicle();
        Assertions.assertThrows(ParkingFullException.class, () -> parkingLot.park(vehicle));

    }

    @Test
    void shouldParkTheVehicleWhenParkingLotHasSpace() throws ParkingFullException, VehicleAlreadyParkedException {
        ParkingLot pk = new ParkingLot(1, owner);
        Vehicle vehicle = new Vehicle();
        pk.park(vehicle);
        Assertions.assertTrue(true);
    }

    @Test
    void shouldThrowExceptionWhenCapacityIsExhausted() throws ParkingFullException, VehicleAlreadyParkedException {

        ParkingLot pk = new ParkingLot(1, owner);
        Vehicle vehicle = new Vehicle();
        pk.park(vehicle);
        Assertions.assertThrows(ParkingFullException.class,() -> pk.park(new Vehicle()) );

    }

    @Test
    void shouldUnparktheCarWhenDriverWantsToUnpark() {
        ParkingLot pk = new ParkingLot(1, owner);
        Vehicle vehicle = new Vehicle();
        pk.unPark(vehicle);

        Assertions.assertFalse(pk.isParked(vehicle));

    }


    @Test
    void shouldNotifyOwnerWhenParkingBecomesFull() throws ParkingFullException, VehicleAlreadyParkedException {

        ParkingLot pk = new ParkingLot(2, owner);
        Vehicle vehicle = new Vehicle();
        pk.park(vehicle);
        pk.park(new Vehicle());

        Mockito.verify(owner).notifyParkingFull();

    }

    @Test
    void shouldNotifyOwnerWhenUnPark() throws ParkingFullException, VehicleAlreadyParkedException {

        ParkingLot pk = new ParkingLot(2, owner);
        Vehicle vehicle = new Vehicle();
        pk.unPark(vehicle);

        Mockito.verify(owner).notifyUnPark();

    }


}
