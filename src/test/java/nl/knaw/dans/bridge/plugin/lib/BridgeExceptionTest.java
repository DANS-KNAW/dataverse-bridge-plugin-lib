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
package nl.knaw.dans.bridge.plugin.lib;

import nl.knaw.dans.bridge.plugin.lib.exception.BridgeException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BridgeExceptionTest {

    @Test
    public void whenExceptionThrown_thenAssertionSucceeds() {
        TestedObject testedObject = new TestedObject();
        BridgeException thrown = assertThrows(BridgeException.class, () -> testedObject.aMethod(0));

        assertEquals("Exception is thrown", thrown.getMessage());
        assertEquals("nl.knaw.dans.bridge.plugin.lib.BridgeExceptionTest", thrown.getClassName());
    }

    public class TestedObject {
        public void aMethod(int i) throws BridgeException {
            if (i < 1)
                throw new BridgeException("Exception is thrown", BridgeExceptionTest.class);
        }
    }

}
