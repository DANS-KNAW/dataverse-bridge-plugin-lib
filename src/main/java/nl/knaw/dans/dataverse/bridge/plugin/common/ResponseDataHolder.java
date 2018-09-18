package nl.knaw.dans.dataverse.bridge.plugin.common;

import nl.knaw.dans.dataverse.bridge.plugin.exception.BridgeException;
import org.apache.abdera.Abdera;

import java.io.InputStream;

/*
    @author Eko Indarto
 */
public abstract class ResponseDataHolder implements IResponseData{

    private static Abdera abdera = null;

    public ResponseDataHolder(){}

    public ResponseDataHolder(InputStream content) throws BridgeException {
        init(content);
    }

    protected static synchronized Abdera getInstance() {
        if (abdera == null) {
            abdera = new Abdera();
        }
        return abdera;
    }

}
