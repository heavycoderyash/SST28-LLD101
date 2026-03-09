public class ReportWriter implements IWriter {
    // I added 'implements IWriter' to allow the pipeline to stay decoupled from the specific logic of writing to a file.
    public String write(Submission s, int plag, int code) {
        return "report-" + s.roll + ".txt";
    }
}

/* initial code
public class ReportWriter {
    public String write(Submission s, int plag, int code) {
        return "report-" + s.roll + ".txt";
    }
}
*/