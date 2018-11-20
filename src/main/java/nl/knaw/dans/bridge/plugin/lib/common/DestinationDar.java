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

/**
 * Class DestinationDar Created by Eko Indarto.
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
