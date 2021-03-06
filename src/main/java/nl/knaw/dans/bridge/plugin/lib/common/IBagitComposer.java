/**
 * Copyright (C) 2018 DANS - Data Archiving and Networked Services (info@dans.knaw.nl)
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

import nl.knaw.dans.bridge.plugin.lib.exception.BridgeException;

import java.io.File;
import java.net.URL;
import java.util.Map;

// TODO add javadoc
/**
 * @author Eko Indarto
 */
public interface IBagitComposer {
    File buildBag(String bagitBaseDir, URL srcExportedUrl, Map<String, String> transformedXml, SourceFileList sourceFileList) throws BridgeException;
}
