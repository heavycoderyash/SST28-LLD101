public class LightsPanel implements Powerable, Dimmable {
    // By implementing only Dimmable and Powerable, 
    // I no longer have to return a dummy 0 for attendance scanning.

    @Override public void powerOn() { }
    @Override public void powerOff() { System.out.println("Lights OFF"); }

    @Override public void setBrightness(int pct) { System.out.println("Lights set to " + pct + "%"); }
}

/* initial code
public class LightsPanel implements SmartClassroomDevice {
    @Override public void powerOn() { }
    @Override public void powerOff() { System.out.println("Lights OFF"); }
    @Override public void setBrightness(int pct) { System.out.println("Lights set to " + pct + "%"); }
    @Override public void setTemperatureC(int c) { }
    @Override public int scanAttendance() { return 0; }
    @Override public void connectInput(String port) { }
}
*/