package nl.knaw.dans.bridge.plugin.lib.common;

/**
 * Class SourceFile
 * Created by Eko Indarto.
 */
public class SourceFile {
    private String name;
    private boolean restricted;
    private long size;
    private String source;

    public SourceFile(String name, boolean restricted, long size, String source) {
        this.name = name;
        this.restricted = restricted;
        this.size = size;
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public boolean isRestricted() {
        return restricted;
    }

    public long getSize() {
        return size;
    }

    public String getSource() {
        return source;
    }

}
