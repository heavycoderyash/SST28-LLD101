// It is implementing IDriverAllocator. 
// This allows the system to change for a different allocation logic in the future.
public class DriverAllocator implements IDriverAllocator {
    public String allocate(String studentId) {
        return "DRV-17";
    }
}

/* initial code
public class DriverAllocator {
    public String allocate(String studentId) {
        // fake deterministic driver
        return "DRV-17";
    }
}
*/