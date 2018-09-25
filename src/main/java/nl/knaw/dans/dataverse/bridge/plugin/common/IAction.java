package nl.knaw.dans.dataverse.bridge.plugin.common;

import nl.knaw.dans.dataverse.bridge.plugin.exception.BridgeException;
import org.apache.abdera.i18n.iri.IRI;

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
    default Optional<Map<String, String>> transform(SourceDar sourceDar, List<XslStreamSource> xslStreamSourceList) throws BridgeException {
        return Optional.empty();
    };

    default Optional<File> composeBagit(SourceDar sourceDar, String bagitBaseDir, Map<String, String> transformedMetadata) throws BridgeException {
        return Optional.empty();
    }

    IResponseData execute(TargetDar targetDar, Optional<File> bagitZipFile, Optional<Map<String, String>> transformedMetadata) throws BridgeException;
}