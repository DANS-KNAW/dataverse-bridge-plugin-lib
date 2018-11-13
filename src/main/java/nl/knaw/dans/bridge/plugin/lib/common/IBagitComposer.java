package nl.knaw.dans.bridge.plugin.lib.common;

import nl.knaw.dans.bridge.plugin.lib.exception.BridgeException;

import java.io.File;
import java.net.URL;
import java.util.Map;

/*
 * @author: Eko Indarto
 */
public interface IBagitComposer {
    File buildBag(String bagitBaseDir, URL srcExportedUrl, Map<String, String> transformedXml, SourceFileList sourceFileList) throws BridgeException;
}
