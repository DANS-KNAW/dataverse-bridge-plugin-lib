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
package nl.knaw.dans.bridge.plugin.lib.util;

import gov.loc.repository.bagit.Bag;
import gov.loc.repository.bagit.BagFactory;
import gov.loc.repository.bagit.BagInfoTxt;
import gov.loc.repository.bagit.transformer.Completer;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

/**
 * Class BagInfoCompleter Created by Eko Indarto on 08/05/17.
 */
public class BagInfoCompleter implements Completer {
    private BagFactory bagFactory;

    public BagInfoCompleter(BagFactory bagFactory) {
        this.bagFactory = bagFactory;
    }

    @Override
    public Bag complete(Bag bag) {
        Bag newBag = bagFactory.createBag(bag);

        // copy files from bag to newBag
        newBag.putBagFiles(bag.getPayload());
        newBag.putBagFiles(bag.getTags());
        // create a BagInfoTxt based on the old one
        Bag.BagPartFactory bagPartFactory = bagFactory.getBagPartFactory();
        BagInfoTxt bagInfo = bagPartFactory.createBagInfoTxt(bag.getBagInfoTxt());

        // add the CREATED field
        DateTime dt = new DateTime();

        bagInfo.put("Created", ISODateTimeFormat.dateTime().print(dt));

        // add the new BagInfoTxt to the newBag
        newBag.putBagFile(bagInfo);
        return newBag;
    }

}
