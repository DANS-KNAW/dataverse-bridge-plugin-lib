package nl.knaw.dans.dataverse.bridge.plugin.common;

import javax.xml.transform.Source;

/**
 * XsltSource
 * @Created by: Eko Indarto
 */
public class XslStreamSource {
  private String xslName;
  private Source xslSource;

  public XslStreamSource(String xslName, Source xslSource) {
    this.xslName = xslName;
    this.xslSource = xslSource;
  }

  public XslStreamSource xslName(String xslName) {
    this.xslName = xslName;
    return this;
  }

  /**
   * Get xslName
   *
   * @return xslName
   **/
  public String getXslName() {
    return xslName;
  }

  public void setXslName(String xslName) {
    this.xslName = xslName;
  }

  public Source getXslSource() {
    return xslSource;
  }

  public void setXslSource(Source xslSource) {
    this.xslSource = xslSource;
  }
}



