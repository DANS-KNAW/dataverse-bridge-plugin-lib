package nl.knaw.dans.bridge.plugin.lib.common;

import java.net.URL;

/**
 * Class SourceDar
 * Created by Eko Indarto.
 */
public class SourceDar {
    URL metadataUrl;
    String apiToken;

    public SourceDar(URL metadataUrl, String apiToken) {
        this.metadataUrl = metadataUrl;
        this.apiToken = apiToken;
    }

    public URL getMetadataUrl() {
        return metadataUrl;
    }

    public String getApiToken() {
        return apiToken;
    }
}
