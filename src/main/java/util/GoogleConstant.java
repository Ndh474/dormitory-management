package util;

public class GoogleConstant {
    private GoogleConstant() {
    }

    public static final String CLIENT_ID = "926657686421-6vsq1hakbn53uuvvih4b03t6c1qrpoio.apps.googleusercontent.com";
    public static final String CLIENT_SECRET = "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";
    public static final String REDIRECT_URI = "http://localhost:9999/google-login-callback";
    public static final String GRANT_TYPE = "authorization_code";
    public static final String TOKEN = "https://accounts.google.com/o/oauth2/token";
    public static final String USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
}
