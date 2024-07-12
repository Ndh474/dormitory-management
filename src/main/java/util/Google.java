package util;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import entity.GooglePOJO;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

public class Google {

    public String getToken(String code) throws IOException {
        String response = Request.Post(GoogleConstant.TOKEN)
                .bodyForm(
                        Form.form()
                                .add("client_id", GoogleConstant.CLIENT_ID)
                                .add("client_secret", GoogleConstant.CLIENT_SECRET)
                                .add("redirect_uri", GoogleConstant.REDIRECT_URI)
                                .add("code", code)
                                .add("grant_type", GoogleConstant.GRANT_TYPE)
                                .build()
                ).execute().returnContent().asString();

        JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
        return jsonObject.get("access_token").getAsString();
    }


    public String getInfo(String token) throws IOException {
        String link = GoogleConstant.USER_INFO + token;
        String response = Request.Get(link).execute().returnContent().asString();
        GooglePOJO googlePOJO = new Gson().fromJson(response, GooglePOJO.class);
        return googlePOJO.getEmail();
    }
}


