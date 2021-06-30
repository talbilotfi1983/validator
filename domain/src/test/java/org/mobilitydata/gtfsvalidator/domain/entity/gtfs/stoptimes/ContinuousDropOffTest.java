/*
 *  Copyright (c) 2020. MobilityData IO.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.mobilitydata.gtfsvalidator.domain.entity.gtfs.stoptimes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContinuousDropOffTest {

    @Test
    void createContinuousDropOffFromNullValueShouldReturnNoContinuousDropOff() {
        assertEquals(ContinuousDropOff.NO_CONTINUOUS_DROP_OFF, ContinuousDropOff.fromInt(null));
    }

    @Test
    void createContinuousDropOffFromValidValue0ShouldReturnContinuousDropOff() {
        assertEquals(ContinuousDropOff.CONTINUOUS_DROP_OFF, ContinuousDropOff.fromInt(0));
    }

    @Test
    void createContinuousDropOffFromValidValue1ShouldReturnNoContinuousDropOff() {
        assertEquals(ContinuousDropOff.NO_CONTINUOUS_DROP_OFF, ContinuousDropOff.fromInt(1));
    }

    @Test
    void createContinuousDropOffFromValidValue2ShouldReturnMustPhoneAgencyContinuousDropOff() {
        assertEquals(ContinuousDropOff.MUST_PHONE_CONTINUOUS_STOPPING_DROP_OFF, ContinuousDropOff.fromInt(2));
    }

    @Test
    void createContinuousDropOffFromValidValue3ShouldReturnMustAskDriveContinuousDropOff() {
        assertEquals(ContinuousDropOff.MUST_ASK_DRIVER_CONTINUOUS_STOPPING_DROP_OFF, ContinuousDropOff.fromInt(3));
    }

    @Test
    void createContinuousDropOffFromInvalidValue5ShouldReturnNull() {
        assertNull(ContinuousDropOff.fromInt(5));
    }

    @Test
    void createContinuousDropOffFromInvalidValue54ShouldReturnNull() {
        assertNull(ContinuousDropOff.fromInt(54));
    }

    @Test
    void nullValueShouldReturnTrue() {
        assertTrue(ContinuousDropOff.isEnumValid(null));
    }

    @Test
    void validValue2ShouldReturnTrue() {
        assertTrue(ContinuousDropOff.isEnumValid(2));
    }

    @Test
    void invalidValue23ShouldReturnFalse() {
        assertFalse(ContinuousDropOff.isEnumValid(23));
    }
}
