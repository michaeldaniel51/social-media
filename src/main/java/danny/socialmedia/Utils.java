package danny.socialmedia;

import java.util.UUID;

public class Utils {




    public static String generateNum(){

       return String
               .valueOf(UUID.randomUUID()
               .getMostSignificantBits())
               .replace("-", "")
               .substring(0,5);
    }





}
