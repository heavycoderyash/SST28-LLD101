public class AirConditioner implements Powerable, ClimateControl {
    // I removed SmartClassroomDevice because it forced me to implement scanAttendance() and setBrightness() which an ac cant do.
    // Now I only implement Powerable and ClimateControl.
    
    @Override public void powerOn() { }
    @Override public void powerOff() { System.out.println("AC OFF"); }

    @Override public void setTemperatureC(int c) { System.out.println("AC set to " + c + "C"); }
}

/* initial code
public class AirConditioner implements SmartClassroomDevice {
    @Override public void powerOn() { }
    @Override public void powerOff() { System.out.println("AC OFF"); }
    @Override public void setBrightness(int pct) { }
    @Override public void setTemperatureC(int c) { System.out.println("AC set to " + c + "C"); }
    @Override public int scanAttendance() { return 0; }
    @Override public void connectInput(String port) { }
}
*/