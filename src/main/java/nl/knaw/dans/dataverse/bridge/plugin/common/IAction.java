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
 */
public interface IAction {
    Map<String, String> transform(String ddiExportUrl, String apiToken, List<XslStreamSource> xslStreamSourceList) throws BridgeException;

    default Optional<File> composeBagit(String bagitBaseDir, String apiToken, String srcXml, Map<String, String> transformedXml) throws BridgeException {
        return Optional.empty();
    }

    IResponseData execute(Optional<File> bagitZipFile, IRI colIri, String uid, Optional<String> pwd) throws BridgeException;
}