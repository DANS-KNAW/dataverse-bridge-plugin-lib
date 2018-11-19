package nl.knaw.dans.bridge.plugin.lib;

import nl.knaw.dans.bridge.plugin.lib.util.StateEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Unit test for BridgeHelperTest.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({StateEnum.class})
public class StateEnumTest {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Before
    public void before() {
        mockStatic(StateEnum.class);
    }

    @Test
    public void fromValue() {
        StateEnum expectedStateEnum = StateEnum.ARCHIVED;
        //Given
        when(StateEnum.fromValue(Mockito.anyString())).thenReturn(expectedStateEnum);
        //When
        StateEnum stateEnum = StateEnum.fromValue(Mockito.anyString());
        //Then
        assertEquals(stateEnum, StateEnum.ARCHIVED);
    }
}

