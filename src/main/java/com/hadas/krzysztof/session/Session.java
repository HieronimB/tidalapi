package com.hadas.krzysztof.session;

import com.hadas.krzysztof.utils.RestHelper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.HttpRequestWithBody;

public class Session {

    private static final String TIDAL_SESSION_HEADER = "X-Tidal-SessionId";
    private static final String COUNTRY_CODE = "countryCode";
    public static final String API_URL = "https://api.tidalhifi.com/v1/";

    private String countryCode;
    private String sessionId;
    private String userId;

    public static Session login(String username, String password, RestHelper restHelper) {
        HttpResponse<JsonNode> jsonResponse = restHelper.executeRequest(Unirest.post(API_URL + "login/username")
                .header("X-Tidal-Token", "wdgaB1CilGA-S_s2")
                .field("username", username)
                .field("password", password));

        return restHelper.checkAndDeserialize(jsonResponse, Session.class);
    }

    public static Session login(String username, String password) {
        RestHelper restHelper = new RestHelper();
        return login(username, password, restHelper);
    }

    public HttpRequest get(String url) {
        return Unirest.get(API_URL + url)
                      .header(TIDAL_SESSION_HEADER, sessionId)
                      .queryString(COUNTRY_CODE, countryCode);
    }

    public HttpRequest delete(String url) {
        return Unirest.delete(API_URL + url)
                .header(TIDAL_SESSION_HEADER, sessionId)
                .queryString(COUNTRY_CODE, countryCode);
    }

    public HttpRequestWithBody post(String url) {
        return Unirest.post(API_URL + url)
                      .header(TIDAL_SESSION_HEADER, sessionId)
                      .queryString(COUNTRY_CODE, countryCode);
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
