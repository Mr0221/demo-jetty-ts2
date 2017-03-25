package yufa;

public enum Enum {
SUCCESS(1,"success"),FAIL(2,"faild");
private int code;
private String strCode;
Enum(final int code, final String strCode){
    this.code = code;
    this.strCode = strCode;
}
public int getCode() {
    return code;
}
public void setCode(final int code) {
    this.code = code;
}
public String getStrCode() {
    return strCode;
}
public void setStrCode(final String strCode) {
    this.strCode = strCode;
}

public static Enum stateOf(final int index) {
    for (final Enum state : values()) {
        if (state.getCode() == index) {
            return state;
        }
    }
    return null;
}
}
