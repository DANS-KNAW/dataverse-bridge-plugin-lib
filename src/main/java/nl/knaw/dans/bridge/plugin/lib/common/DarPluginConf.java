/**
 * Copyright (C) ${project.inceptionYear} DANS - Data Archiving and Networked Services (info@dans.knaw.nl)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.knaw.dans.bridge.plugin.lib.common;

import org.apache.abdera.i18n.iri.IRI;

import java.net.URLClassLoader;
import java.util.List;

/**
 * DarPluginConf Created by Eko Indarto
 */

public class DarPluginConf {
    private String darName;
    private IRI darIri;
    // private Type darType;
    private String actionClassName;
    private URLClassLoader actionClassLoader;
    private List<XslSource> xslSourceList;

    public DarPluginConf() {}

    public DarPluginConf(String darName, IRI darIri, String actionClassName, URLClassLoader actionClassLoader, List<XslSource> xslSourceList) {
        this.darName = darName;
        this.darIri = darIri;
        this.actionClassName = actionClassName;
        this.actionClassLoader = actionClassLoader;
        this.xslSourceList = xslSourceList;
    }

    public String getDarName() {
        return darName;
    }

    public void setDarName(String darName) {
        this.darName = darName;
    }

    public IRI getDarIri() {
        return darIri;
    }

    public void setDarIri(IRI darIri) {
        this.darIri = darIri;
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

    public List<XslSource> getXslSourceList() {
        return xslSourceList;
    }

    public void setXslSourceList(List<XslSource> xslSourceList) {
        this.xslSourceList = xslSourceList;
    }
    /*
     * enum Type { ARCHIVER("ARCHIVE"), REGISTRAR("REGISTRAR"); IRODS("IRODS"); private String value; Type(String value) { this.value = value; } public String
     * toString() { return String.valueOf(value); } public static Type fromValue(String text) { for (Type b : Type.values()) { if
     * (String.valueOf(b.value).equals(text)) { return b; } } throw new IllegalArgumentException("No matching constant for [" + text + "]"); } }
     */
}
