package nl.knaw.dans.bridge.plugin.lib.util;
/*
 * @author: Eko Indarto
 */
public enum StateEnum {
    BAD_REQUEST("400"),
    UNKNOWN("UNKNOWN"),
    ERROR("ERROR"),
    IN_PROGRESS("IN-PROGRESS"),
    REGISTERED("REGISTERED"),
    UPDATED("UPDATED"),
    SUBMITTED("SUBMITTED"),
    REJECTED("REJECTED"),
    FAILED("FAILED"),
    INVALID("INVALID"),
    ARCHIVED("ARCHIVED");


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