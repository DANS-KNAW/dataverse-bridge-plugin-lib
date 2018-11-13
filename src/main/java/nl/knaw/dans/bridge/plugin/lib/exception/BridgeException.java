package nl.knaw.dans.bridge.plugin.lib.exception;
/*
 * The BridgeException wraps all checked standard Java exception and enriches them with a custom error code.
 * You can use this code to retrieve localized error messages and to link to the documentation.
 *
 * @author: Eko Indarto
 */
public class BridgeException extends Exception {
    private String className;
    public Throwable cause;

    public BridgeException(Throwable cause) { this.cause = cause; }
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
