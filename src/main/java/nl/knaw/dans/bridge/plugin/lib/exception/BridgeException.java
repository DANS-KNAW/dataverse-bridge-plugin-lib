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
package nl.knaw.dans.bridge.plugin.lib.exception;

/*
 * The BridgeException wraps all checked standard Java exception and enriches them with a custom error code.
 * You can use this code to retrieve localized error messages and to link to the documentation.
 *
 * @author Eko Indarto
 */
public class BridgeException extends Exception {
    private String className;
    public Throwable cause;

    public BridgeException(Throwable cause) {
        this.cause = cause;
    }

    public BridgeException(String message, Class clazz) {
        super(message);
        this.className = clazz.getName();
    }

    public BridgeException(String message, Throwable error, Class clazz) {
        super(message, error);
        this.className = clazz.getName();
    }

    public BridgeException(Throwable e, Class clazz) {
        super(e);
        this.className = clazz.getName();
    }

    public String getClassName() {
        return className;
    }

}
