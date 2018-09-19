package nl.knaw.dans.dataverse.bridge.plugin;

import nl.knaw.dans.dataverse.bridge.plugin.exception.BridgeException;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BridgeExceptionTest {

    public class TestedObject {
        public void aMethod(int i) throws BridgeException {
            if (i < 1)
                throw new BridgeException("Exception is thrown", BridgeExceptionTest.class);
        }
    }

    @Test
    public void whenExceptionThrown_thenAssertionSucceeds() {
        TestedObject testedObject = new TestedObject();
        BridgeException thrown = assertThrows(BridgeException.class, () -> testedObject.aMethod(0));

        assertEquals("Exception is thrown", thrown.getMessage());
        assertEquals("nl.knaw.dans.dataverse.bridge.plugin.BridgeExceptionTest", thrown.getClassName());
    }

}

