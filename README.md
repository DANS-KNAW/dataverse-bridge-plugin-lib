# dataverse-bridge-plugin-lib
The _bridge-plugin-lib_ mainly consists of interfaces that are used by the bridge-service in order to identify and treat all plugins the same way. 
The plugins need to implement the interfaces.

### Building from source

Prerequisites:

* Java 8 or higher
* Maven 3.3.3 or higher

Steps:

        git clone https://github.com/DANS-KNAW/dataverse-bridge-plugin-lib.git
        cd dataverse-bridge-plugin-lib
        mvn install
