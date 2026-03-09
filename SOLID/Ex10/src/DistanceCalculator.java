// It implemented the IDistanceCalculator interface so that the 
// TransportBookingService can use this without being strictly tied to its class name.
public class DistanceCalculator implements IDistanceCalculator {
    public double km(GeoPoint a, GeoPoint b) {
        double d = Math.abs(a.lat - b.lat) + Math.abs(a.lon - b.lon);
        double km = Math.round((d * 200.0) * 10.0) / 10.0;
        return km;
    }
}

/* initial code
public class DistanceCalculator {
    public double km(GeoPoint a, GeoPoint b) {
        double d = Math.abs(a.lat - b.lat) + Math.abs(a.lon - b.lon);
        double km = Math.round((d * 200.0) * 10.0) / 10.0; // produces 6.0 for the demo points
        return km;
    }
}
*/