import java.io.File;

public class File {
    private String path;

    public File(String path) {
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
}