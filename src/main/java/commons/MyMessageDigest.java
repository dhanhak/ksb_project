package commons;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class MyMessageDigest {

    private final MessageDigest messageDigest;

    public MyMessageDigest(MessageDigest messageDigest) {
        this.messageDigest = messageDigest;
    }

    public String encode(String plainText){
        byte[] encodedTextStream = messageDigest.digest(plainText.getBytes(StandardCharsets.UTF_8));
        StringBuilder hash = new StringBuilder(2 * encodedTextStream.length);
        for (byte b : encodedTextStream) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1){
                hash.append('0');
            }
            hash.append(hex);
        }
        return hash.toString();
    }
}
