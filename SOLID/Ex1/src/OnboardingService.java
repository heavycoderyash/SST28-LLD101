import java.util.*;

public class OnboardingService {
    private final StudentStore store;
    private final InputParser parser = new InputParser();
    private final StudentValidator validator = new StudentValidator();
    private final OnboardingPrinter printer = new OnboardingPrinter();

    public OnboardingService(StudentStore store) {
        this.store = store;
    }

    public void registerFromRawInput(String raw) {
        printer.printInput(raw);
        Map<String, String> data = parser.parse(raw);
        List<String> errors = validator.validate(data);
        if (!errors.isEmpty()) {
            printer.printErrors(errors);
            return;
        }
        String id = IdUtil.nextStudentId(store.count());
        StudentRecord rec = new StudentRecord(
                id,
                data.get("name"),
                data.get("email"),
                data.get("phone"),
                data.get("program"));
        store.save(rec);
        printer.printSuccess(rec, store.count());
    }
}