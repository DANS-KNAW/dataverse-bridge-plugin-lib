package nl.knaw.dans.dataverse.bridge.plugin.common;

import nl.knaw.dans.dataverse.bridge.plugin.exception.BridgeException;

import java.io.File;
import java.util.Map;

/*
 * @author: Eko Indarto
 */
public interface IBagitComposer {
    File buildBag(String bagitBaseDir, String srcExportedUrl, Map<String, String> transformedXml, DvFileList dvFileList) throws BridgeException;
}
