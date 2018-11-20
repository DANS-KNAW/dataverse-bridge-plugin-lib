dataverse-bridge-plugin-lib
=============================

The _bridge-plugin-lib_ mainly consists of interfaces that are used by the bridge-service in order to identify and treat all plugins the same way. 
The plugins need to implement the interfaces.

Building from source
--------------------

Prerequisites:

* Java 8 or higher
* Maven 3.3.3 or higher

Steps:

        git clone https://github.com/DANS-KNAW/dataverse-bridge-plugin-lib.git
        cd dataverse-bridge-plugin-lib
        mvn install

Usage
-----

To use this libary in a Maven-based project:

1. Include in your `pom.xml` a declaration for the DANS maven repository:

        <repositories>
            <!-- possibly other repository declartions here ... -->
            <repository>
                <id>DANS</id>
                <releases>
                    <enabled>true</enabled>
                </releases>
                <url>http://maven.dans.knaw.nl/</url>
            </repository>
        </repositories>

2. Include a dependency on this library. The version should of course be
   set to the latest version (or left out, if it is managed by an ancestor `pom.xml`).

        <dependency>
            <groupId>nl.knaw.dans.bridge.plugin.lib</groupId>
            <artifactId>bridge-plugin-lib</artifactId>
            <version>1.0</version>
        </dependency>
