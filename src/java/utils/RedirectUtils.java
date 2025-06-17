
package utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class RedirectUtils {
    
    public static String buildMessageRedirectUrl(String basePath, String message, String type){
        try {
            String encodedMessage = URLEncoder.encode(message, "UTF-8");
            String encodedType = URLEncoder.encode(type, "UTF-8");

            return basePath + "?message=" + encodedMessage + "&type=" + encodedType;
        } catch (UnsupportedEncodingException e) {

            return basePath;
        }
    }
    
}
