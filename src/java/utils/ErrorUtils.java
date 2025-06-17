
package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ErrorUtils {

    public static void addError(HashMap<String, List<String>> errors, String key, String message) {
        errors.computeIfAbsent(key, k -> new ArrayList<>()).add(message);
    }
    
}
