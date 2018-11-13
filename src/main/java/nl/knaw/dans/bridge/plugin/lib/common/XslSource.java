package nl.knaw.dans.bridge.plugin.lib.common;

import java.util.List;

/**
 * XsltSource
 * @Created by: Eko Indarto
 */
public class XslSource {
    private String name;
    private List<XslTransformer> xslTransformerList;
    public XslSource(String name, List<XslTransformer> xslTransformerList) {
        this.name = name;
        this.xslTransformerList = xslTransformerList;
    }

    public String getName() {
        return name;
    }

    public List<XslTransformer> getXslTransformerList() {
        return xslTransformerList;
    }
}
