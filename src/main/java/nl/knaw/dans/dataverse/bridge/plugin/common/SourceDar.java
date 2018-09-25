package nl.knaw.dans.dataverse.bridge.plugin.common;

public class SourceDar {
    String metadataExportUrl;
    String apiToken;

    public SourceDar(String metadataExportUrl, String apiToken) {
        this.metadataExportUrl = metadataExportUrl;
        this.apiToken = apiToken;
    }

    public String getMetadataExportUrl() {
        return metadataExportUrl;
    }

    public String getApiToken() {
        return apiToken;
    }
}
