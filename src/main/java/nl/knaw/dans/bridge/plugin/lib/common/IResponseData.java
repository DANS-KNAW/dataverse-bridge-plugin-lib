package nl.knaw.dans.bridge.plugin.lib.common;

import nl.knaw.dans.bridge.plugin.lib.util.StateEnum;

import java.net.URL;
import java.util.Optional;

/*
 * Created by Eko Indarto
 */
public interface IResponseData {

    String getResponse();

    default Optional<StateEnum> getState()  {
        return Optional.empty();
    }

    default Optional<String> getPid() {
        return Optional.empty();
    }

    default Optional<URL> getPidLandingPage() {
        return Optional.empty();
    }

    default Optional<URL> getDarLandingPage() {
        return Optional.empty();
    }

}
