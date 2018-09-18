package nl.knaw.dans.dataverse.bridge.plugin.common;

import java.net.URLClassLoader;
import java.util.List;

/**
 * DarPluginConf
 * Created by Eko Indarto
 */

public class DarPluginConf {
    private String darName;
    private String actionClassName;
    private URLClassLoader actionClassLoader;
    private List<XslStreamSource> xsl;

    public DarPluginConf() {
    }

    public DarPluginConf(String darName, String actionClassName, URLClassLoader actionClassLoader, List<XslStreamSource> xsl) {
        this.darName = darName;
        this.actionClassName = actionClassName;
        this.actionClassLoader = actionClassLoader;
        this.xsl = xsl;
    }

    public String getDarName() {
        return darName;
    }

    public void setDarName(String darName) {
        this.darName = darName;
    }

    public URLClassLoader getActionClassLoader() {
        return actionClassLoader;
    }

    public void setActionClassLoader(URLClassLoader actionClassLoader) {
        this.actionClassLoader = actionClassLoader;
    }

    public String getActionClassName() {
        return actionClassName;
    }

    public void setActionClassName(String actionClassName) {
        this.actionClassName = actionClassName;
    }


    public List<XslStreamSource> getXsl() {
        return xsl;
    }

    public void setXsl(List<XslStreamSource> xsl) {
        this.xsl = xsl;
    }

}

