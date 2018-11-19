package nl.knaw.dans.bridge.plugin.lib.common;

import nl.knaw.dans.bridge.plugin.lib.exception.BridgeException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/*
 * @author: Eko Indarto
 */
public interface ITransform {
    Map<String, String> transformMetadata(SourceDar sourceDar, DestinationDar destinationDar, List<XslTransformer> xslTransformerList) throws BridgeException;
    default Optional<SourceFileList> getSourceFileList(String apiToken) {
        return Optional.empty();
    }
}
