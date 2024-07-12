package util;


import jakarta.servlet.http.Part;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class UploadImage {
    public static boolean upload(Part part,String uploadSource) {

            try (OutputStream os = new FileOutputStream(uploadSource);
                 InputStream is = part.getInputStream()) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }

        return false;
    }

}
