package nl.knaw.dans.bridge.plugin.lib.common;

import org.apache.abdera.i18n.iri.IRI;


/**
 * Class DestinationDar
 * Created by Eko Indarto.
 */
public class DestinationDar {
    private IRI darIri;
    private String darUid;
    private String darPwd;
    private String darUserAffiliation;

    public DestinationDar(IRI darIri, String darUid, String darPwd, String darUserAffiliation) {
        this.darIri = darIri;
        this.darUid = darUid;
        this.darPwd = darPwd;
        this.darUserAffiliation = darUserAffiliation;
    }

    public IRI getDarIri() {
        return darIri;
    }

    public String getDarUid() {
        return darUid;
    }

    public String getDarPwd() {
        return darPwd;
    }

    public String getDarUserAffiliation() {
        return darUserAffiliation;
    }
}
