package org.lukebillington.university.sharesbroker.utils;

import java.util.List;

public class ListUtils {
    public static boolean ContainsPartialString(List<String> list, String partialString) {
        for (String item : list) {
            if (item.contains(partialString))
                return true;
        }

        return false;
    }
}
