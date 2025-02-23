package files;

public class CodeFile {
    private String path;
    int physicalLines = 0;
    int logicalLines = 0;

    public CodeFile(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public boolean exists() {
        return new java.io.File(path).exists();
    }

    public boolean isDirectory() {
        return new java.io.File(path).isDirectory();
    }

    public boolean isFile() {
        return new java.io.File(path).isFile();
    }

    public void setLogicalLines(int logicalLines) {
        this.logicalLines = logicalLines;
    }

    public void setPhysicalLines(int physicalLines) {
        this.physicalLines = physicalLines;
    }

    public int getLogicalLines() {
        return logicalLines;
    }

    public int getPhysicalLines() {
        return physicalLines;
    }
}