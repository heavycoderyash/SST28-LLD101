import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Hostel Fee Calculator ===");
        BookingRequest req = new BookingRequest(LegacyRoomTypes.DOUBLE, List.of(AddOn.LAUNDRY, AddOn.MESS));

        PricingComponent roomRules = new RoomPricing(Map.of(
            LegacyRoomTypes.SINGLE, 14000.0,
            LegacyRoomTypes.DOUBLE, 15000.0,
            LegacyRoomTypes.TRIPLE, 12000.0
        ));

        PricingComponent addOnRules = new AddOnPricing(Map.of(
            AddOn.MESS, 1000.0,
            AddOn.LAUNDRY, 500.0,
            AddOn.GYM, 300.0
        ));

        HostelFeeCalculator calc = new HostelFeeCalculator(
            new FakeBookingRepo(), 
            List.of(roomRules, addOnRules)
        );
        
        calc.process(req);
    }
}