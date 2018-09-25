package nl.knaw.dans.dataverse.bridge.plugin.common;

import nl.knaw.dans.dataverse.bridge.plugin.exception.BridgeException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/*
 * @author: Eko Indarto
 */
public interface ITransform {
    Map<String, String> transformMetadata(SourceDar sourceDar, List<XslStreamSource> xslStreamSourceList) throws BridgeException;
    default Optional<DvFileList> getDvFileList(String apiToken) {
        return Optional.empty();
    }
}
