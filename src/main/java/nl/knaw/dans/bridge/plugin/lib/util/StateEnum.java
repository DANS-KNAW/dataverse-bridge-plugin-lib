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
package nl.knaw.dans.bridge.plugin.lib.util;

/*
 * @author: Eko Indarto
 */
public enum StateEnum {
    BAD_REQUEST("400"), UNKNOWN("UNKNOWN"), ERROR("ERROR"), IN_PROGRESS("IN-PROGRESS"), REGISTERED("REGISTERED"), UPDATED("UPDATED"), SUBMITTED("SUBMITTED"), REJECTED(
            "REJECTED"), FAILED("FAILED"), INVALID("INVALID"), ARCHIVED("ARCHIVED");

    private String value;

    StateEnum(String value) {
        this.value = value;
    }

    public String toString() {
        return String.valueOf(value);
    }

    public static StateEnum fromValue(String text) {
        for (StateEnum b : StateEnum.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        throw new IllegalArgumentException("No matching constant for [" + text + "]");
    }
}
