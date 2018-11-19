package nl.knaw.dans.bridge.plugin.lib.common;

import java.net.URL;

/**
 * XslTransformer
 * @Created by: Eko Indarto
 */
public class XslTransformer {
  private String name;
  private URL url;

  public XslTransformer(String name, URL url) {
    this.name = name;
    this.url = url;
  }

  public XslTransformer xslName(String xslName) {
    this.name = xslName;
    return this;
  }

  /**
   * Get name
   *
   * @return name
   **/
  public String getName() {
    return name;
  }

  public URL getUrl() {
    return url;
  }
}



