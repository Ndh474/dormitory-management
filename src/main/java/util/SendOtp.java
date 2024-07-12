
package util;

// warning:: code đang chạy đề nghị cấm sửa
import java.util.Properties;
import java.util.Random;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

    public class SendOtp {
        private static final String EMAIL_USERNAME = "phanhoangbao59@gmail.com"; //
        private static final String EMAIL_PASSWORD = "fdgvwjcstmwdykby"; //
        public static String generateOTP() {
            Random random = new Random();
            int otpLength = 6; // Độ dài của mã OTP
            StringBuilder otp = new StringBuilder();
            for (int i = 0; i < otpLength; i++) {
                otp.append(random.nextInt(10));
            }
            return otp.toString();
        }
        public static void sendOtpCode(String recipient, String subject, String body) throws MessagingException {
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com"); // Thay bằng máy chủ SMTP của bạn nếu cần
            properties.put("mail.smtp.port", "587"); // Thay bằng cổng SMTP của bạn nếu cần

            Session session = Session.getInstance(properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(EMAIL_USERNAME, EMAIL_PASSWORD);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
            System.out.println("Email sent successfully to " + recipient);
        }

    }


