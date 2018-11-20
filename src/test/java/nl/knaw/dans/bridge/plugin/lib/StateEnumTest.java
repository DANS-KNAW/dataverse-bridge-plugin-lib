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
        // Given
        when(StateEnum.fromValue(Mockito.anyString())).thenReturn(expectedStateEnum);
        // When
        StateEnum stateEnum = StateEnum.fromValue(Mockito.anyString());
        // Then
        assertEquals(stateEnum, StateEnum.ARCHIVED);
    }
}
