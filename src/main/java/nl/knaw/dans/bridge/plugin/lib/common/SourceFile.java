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

/**
 * Class SourceFile Created by Eko Indarto.
 */
public class SourceFile {
    private String name;
    private boolean restricted;
    private long size;
    private String source;

    public SourceFile(String name, boolean restricted, long size, String source) {
        this.name = name;
        this.restricted = restricted;
        this.size = size;
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public boolean isRestricted() {
        return restricted;
    }

    public long getSize() {
        return size;
    }

    public String getSource() {
        return source;
    }

}
