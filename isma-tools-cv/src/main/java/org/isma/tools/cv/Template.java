package org.isma.tools.cv;

public class Template {
    private final String directory;
    private final String file;

    public Template(String directory, String file){
        this.directory = directory;
        this.file = file;
    }

    public String getDirectory() {
        return directory;
    }

    public String getFile() {
        return file;
    }
}
