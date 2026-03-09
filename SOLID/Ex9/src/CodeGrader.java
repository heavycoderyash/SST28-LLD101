public class CodeGrader implements IGrader {
    // I added implements IGrader so this class can be used interchangeably with any other grading strategy.
    public int grade(Submission s, Rubric r) {
        int base = Math.min(80, 50 + s.code.length() % 40);
        return base + r.bonus;
    }
}

/* initial code
public class CodeGrader {
    public int grade(Submission s, Rubric r) {
        int base = Math.min(80, 50 + s.code.length() % 40);
        return base + r.bonus;
    }
}
*/