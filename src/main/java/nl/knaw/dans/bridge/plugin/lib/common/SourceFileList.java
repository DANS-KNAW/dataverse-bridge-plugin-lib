package nl.knaw.dans.bridge.plugin.lib.common;

import java.util.List;

/**
 * Class SourceFileList
 * Created by Eko Indarto.
 */
public class SourceFileList {
    private String apiToken;
    private List<SourceFile> sourceFiles;

    public SourceFileList(String apiToken, List<SourceFile> sourceFiles) {
        this.apiToken = apiToken;
        this.sourceFiles = sourceFiles;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public List<SourceFile> getSourceFiles() {
        return sourceFiles;
    }

    public void setSourceFiles(List<SourceFile> sourceFiles) {
        this.sourceFiles = sourceFiles;
    }
}
