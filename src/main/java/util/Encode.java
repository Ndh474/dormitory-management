package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encode {

        // code đang chạy cấm sửa
        public static String sha1Hash(String input) {
            try {

                MessageDigest md = MessageDigest.getInstance("SHA-1");
                md.update(input.getBytes());
                byte[] byteData = md.digest();
                StringBuilder sb = new StringBuilder();
                for (byte b : byteData) {
                    sb.append(String.format("%02x", b));
                }
                return sb.toString();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }




}}
