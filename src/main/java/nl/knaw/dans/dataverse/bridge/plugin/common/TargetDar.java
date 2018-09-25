package nl.knaw.dans.dataverse.bridge.plugin.common;

import org.apache.abdera.i18n.iri.IRI;

public class TargetDar {
    private IRI darIri;
    private String darUid;
    private String darPwd;

    public TargetDar(IRI darIri, String darUid, String darPwd) {
        this.darIri = darIri;
        this.darUid = darUid;
        this.darPwd = darPwd;
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
}
