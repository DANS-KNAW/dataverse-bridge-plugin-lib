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
import java.util.List;
import java.util.Map;
import java.util.Optional;

// TODO add javadoc
/**
 * Class IAction
 *
 * @author: Eko Indarto.
 *
 * Some discussion about using Optional in parameters:
 * https://stackoverflow.com/questions/31922866/why-should-java-8s-optional-not-be-used-in-arguments
 * https://softwareengineering.stackexchange.com/questions/238954/is-there-any-reason-not-to-use-optional-as-a-method-argument-in-the-case-where-y
 * I have chosen to use Optional in parameter.
 */
public interface IAction {
    default Optional<Map<String, String>> transform(SourceDar sourceDar, DestinationDar destinationDar, List<XslTransformer> xslTransformerList)  throws BridgeException {
        return Optional.empty();
    }

    default Optional<File> composeBag(SourceDar sourceDar, String bagitBaseDir, Map<String, String> transformedMetadata)  throws BridgeException {
        return Optional.empty();
    }

    IResponseData execute(DestinationDar destinationDar, Optional<File> bagitZipFile, Optional<Map<String, String>> transformedMetadata) throws BridgeException;
}
