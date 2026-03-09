public abstract class Exporter {
    public final ExportResult export(ExportRequest req) {
        if (req == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }

        if (req.body.length() > maxBodyLength()) {
            throw new IllegalArgumentException(getFormatName() + " cannot handle content > " + maxBodyLength() + " chars");
        }

        return doExport(req);
    }

    protected abstract String getFormatName();

    protected int maxBodyLength() {
        return Integer.MAX_VALUE;
    }

    protected abstract ExportResult doExport(ExportRequest req);
}