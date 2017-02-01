package com.hadas.krzysztof.utils;

import com.hadas.krzysztof.exceptions.HttpBadResponseException;
import com.hadas.krzysztof.exceptions.UncheckedUnirestException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.BaseRequest;
import com.owlike.genson.GenericType;
import com.owlike.genson.Genson;

public class RestHelper {

    private static final int STATUS_200 = 200;
    private static final int STATUS_201 = 201;
    private static final int STATUS_204 = 204;

    private Genson genson = new Genson();

    public void checkResponseStatus(HttpResponse<JsonNode> jsonResponse) {
        if (jsonResponse.getStatus() != STATUS_200 &&
                jsonResponse.getStatus() != STATUS_201 &&
                jsonResponse.getStatus() != STATUS_204) {
            System.err.println("Bad status: " + jsonResponse.getStatus());
            throw new HttpBadResponseException("Status: " + jsonResponse.getStatus() +
                    " " + jsonResponse.getStatusText() +
                    " Body: " + jsonResponse.getBody());
        }
    }

    public <T> T checkAndDeserialize(HttpResponse<JsonNode> jsonResponse, Class<T> type) {
        checkResponseStatus(jsonResponse);
        return genson.deserialize(jsonResponse.getRawBody(), type);
    }

    public <T> T checkAndDeserialize(HttpResponse<JsonNode> jsonResponse, GenericType<T> type) {
        checkResponseStatus(jsonResponse);
        return genson.deserialize(jsonResponse.getRawBody(), type);
    }

    public <T extends BaseRequest> HttpResponse<JsonNode> executeRequest(T request) {
        try {
            return request.asJson();
        } catch (UnirestException e) {
            System.err.println("Problem occurred with executed request" + request);
            throw new UncheckedUnirestException(e);
        }
    }
}
