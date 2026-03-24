import java.util.*;

public class DeviceRegistry {
    // I changed the list to hold object because devices no longer share a single fat interface. 
    // This allows any capability-based class to be stored.
    private final List<Object> devices = new ArrayList<>();

    public void add(Object d) { devices.add(d); }

    // I converted this to a generic method. 
    // Instead of returning a SmartClassroomDevice,
    // I return the specific capability interface requested.
    public <T> T getFirstOfType(Class<T> type) {
        for (Object d : devices) {
            if (type.isInstance(d)) return type.cast(d);
        }
        throw new IllegalStateException("Device missing");
    }
}

/* initial code
public class DeviceRegistry {
    private final java.util.List<SmartClassroomDevice> devices = new ArrayList<>();
    public void add(SmartClassroomDevice d) { devices.add(d); }
    public SmartClassroomDevice getFirstOfType(String simpleName) {
        for (SmartClassroomDevice d : devices) {
            if (d.getClass().getSimpleName().equals(simpleName)) return d;
        }
        throw new IllegalStateException("Missing: " + simpleName);
    }
}
*/