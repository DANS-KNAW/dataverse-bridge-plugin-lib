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

