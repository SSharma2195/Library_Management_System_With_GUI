package server;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum LoanStatus {
    OVERDUE("Overdue"),
    RETURN("Return"),
    ON_LOAN("On loan");

    private static final Map<String,LoanStatus> ENUM_MAP;

    private final String text;

    LoanStatus(String text) {
        this.text = text;
    }

    public String getValue(){
        return text;
    }

    @Override
    public String toString() {
        return text;
    }

    static {
        Map<String,LoanStatus> map = new ConcurrentHashMap<>();
        map.put("Overdue", OVERDUE);
        map.put("Returned", RETURN);
        map.put("On loan", ON_LOAN);
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static LoanStatus get (String name) {
        return ENUM_MAP.get(name);
    }
}
