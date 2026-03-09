public class EvaluationPipeline {
    private final IChecker checker;
    private final IGrader grader;
    private final IWriter writer;

    // I modified this constructor to use Dependency Injection. 
    // Instead of the pipeline creating its own tools (tight coupling) it now receives them from the outside. 
    // This follows the Dependency Inversion Principle because now this depends on interfaces rather than concrete classes.
    public EvaluationPipeline(IChecker checker, IGrader grader, IWriter writer) {
        this.checker = checker;
        this.grader = grader;
        this.writer = writer;
    }

    public void evaluate(Submission sub) {
        Rubric rubric = new Rubric();

        int plag = checker.check(sub);
        System.out.println("PlagiarismScore=" + plag);

        int code = grader.grade(sub, rubric);
        System.out.println("CodeScore=" + code);

        String reportName = writer.write(sub, plag, code);
        System.out.println("Report written: " + reportName);

        int total = plag + code;
        String result = (total >= 90) ? "PASS" : "FAIL";
        System.out.println("FINAL: " + result + " (total=" + total + ")");
    }
}

/* initial code
public class EvaluationPipeline {
    public void evaluate(Submission sub) {
        Rubric rubric = new Rubric();
        PlagiarismChecker pc = new PlagiarismChecker();
        CodeGrader grader = new CodeGrader();
        ReportWriter writer = new ReportWriter();

        int plag = pc.check(sub);
        System.out.println("PlagiarismScore=" + plag);

        int code = grader.grade(sub, rubric);
        System.out.println("CodeScore=" + code);

        String reportName = writer.write(sub, plag, code);
        System.out.println("Report written: " + reportName);

        int total = plag + code;
        String result = (total >= 90) ? "PASS" : "FAIL";
        System.out.println("FINAL: " + result + " (total=" + total + ")");
    }
}
*/