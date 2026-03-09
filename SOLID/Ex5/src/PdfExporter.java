import java.nio.charset.StandardCharsets;

public class PdfExporter extends Exporter {
    @Override
    protected String getFormatName() {
        return "PDF";
    }

    @Override
    protected int maxBodyLength() {
        return 20; 
    }

    @Override
    protected ExportResult doExport(ExportRequest req) {
        String fakePdf = "PDF(" + req.title + "):" + req.body;
        return new ExportResult("application/pdf", fakePdf.getBytes(StandardCharsets.UTF_8));
    }
}