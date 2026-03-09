public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) { this.reg = reg; }

    public void startClass() {
        // I now request the specific capability I need from the registry. 
        // This ensures I only call methods the device actually supports.
        
        Powerable pjPower = reg.getFirstOfType(Projector.class);
        pjPower.powerOn();
        
        Connectable pjInput = reg.getFirstOfType(Projector.class);
        pjInput.connectInput("HDMI-1");

        Dimmable lights = reg.getFirstOfType(LightsPanel.class);
        lights.setBrightness(60);

        ClimateControl ac = reg.getFirstOfType(AirConditioner.class);
        ac.setTemperatureC(24);

        Scanner scan = reg.getFirstOfType(AttendanceScanner.class);
        System.out.println("Attendance scanned: present=" + scan.scanAttendance());
    }

    public void endClass() {
        System.out.println("Shutdown sequence:");
        reg.getFirstOfType(Projector.class).powerOff();
        reg.getFirstOfType(LightsPanel.class).powerOff();
        reg.getFirstOfType(AirConditioner.class).powerOff();
    }
}

/* initial code
public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) { this.reg = reg; }

    public void startClass() {
        SmartClassroomDevice pj = reg.getFirstOfType("Projector");
        pj.powerOn();
        pj.connectInput("HDMI-1");

        SmartClassroomDevice lights = reg.getFirstOfType("LightsPanel");
        lights.setBrightness(60);

        SmartClassroomDevice ac = reg.getFirstOfType("AirConditioner");
        ac.setTemperatureC(24);

        SmartClassroomDevice scan = reg.getFirstOfType("AttendanceScanner");
        System.out.println("Attendance scanned: present=" + scan.scanAttendance());
    }

    public void endClass() {
        System.out.println("Shutdown sequence:");
        reg.getFirstOfType("Projector").powerOff();
        reg.getFirstOfType("LightsPanel").powerOff();
        reg.getFirstOfType("AirConditioner").powerOff();
    }
}
*/