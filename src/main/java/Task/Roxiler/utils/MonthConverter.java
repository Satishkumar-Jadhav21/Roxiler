package Task.Roxiler.utils;


import java.util.HashMap;
import java.util.Map;

public class MonthConverter {
    private static final Map<String, Integer> MONTH_MAP = new HashMap<>();

    static {
        MONTH_MAP.put("january", 1);
        MONTH_MAP.put("february", 2);
        MONTH_MAP.put("march", 3);
        MONTH_MAP.put("april", 4);
        MONTH_MAP.put("may", 5);
        MONTH_MAP.put("june", 6);
        MONTH_MAP.put("july", 7);
        MONTH_MAP.put("august", 8);
        MONTH_MAP.put("september", 9);
        MONTH_MAP.put("october", 10);
        MONTH_MAP.put("november", 11);
        MONTH_MAP.put("december", 12);
    }

    public static int convert(String monthName) {
        Integer month = MONTH_MAP.get(monthName.toLowerCase());
        if (month == null) {
            throw new IllegalArgumentException("Invalid month name");
        }
        return month;
    }
}