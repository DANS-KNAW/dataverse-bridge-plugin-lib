package nl.knaw.dans.dataverse.bridge.plugin.common;

import nl.knaw.dans.dataverse.bridge.plugin.exception.BridgeException;

import java.io.InputStream;
import java.util.Optional;

/*
 * Created by Eko Indarto
 */
public interface IResponseData {
    void init(InputStream inputStream) throws BridgeException;
    String getState();

    default Optional<String> getPid() {
        return Optional.empty();
    }

    default Optional<String> getLandingPage() {
        return Optional.empty();
    }

    default Optional<String> getFeedXml() {
        return Optional.empty();
    }
}
