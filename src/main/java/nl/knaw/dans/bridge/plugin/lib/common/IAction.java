package nl.knaw.dans.bridge.plugin.lib.common;

import nl.knaw.dans.bridge.plugin.lib.exception.BridgeException;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Class IAction
 * Created by Eko Indarto.
 * Some discussion about using Optional in parameters:
 * https://stackoverflow.com/questions/31922866/why-should-java-8s-optional-not-be-used-in-arguments
 * https://softwareengineering.stackexchange.com/questions/238954/is-there-any-reason-not-to-use-optional-as-a-method-argument-in-the-case-where-y
 * I have chosen to use Optional in parameter.
 */
public interface IAction {
    default Optional<Map<String, String>> transform(SourceDar sourceDar, DestinationDar destinationDar, List<XslTransformer> xslTransformerList)  throws BridgeException {
        return Optional.empty();
    }

    default Optional<File> composeBag(SourceDar sourceDar, String bagitBaseDir, Map<String, String> transformedMetadata)  throws BridgeException {
        return Optional.empty();
    }

    IResponseData execute(DestinationDar destinationDar, Optional<File> bagitZipFile, Optional<Map<String, String>> transformedMetadata) throws BridgeException;
}