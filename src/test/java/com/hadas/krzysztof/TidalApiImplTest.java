package com.hadas.krzysztof;

import com.hadas.krzysztof.exceptions.HttpBadResponseException;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Test;

public class TidalApiImplTest {

    private static final String BAD_USERNAME = "badusername";
    private static final String BAD_PASSWORD = "badpassword";

    private TidalApiImpl tidalApiImpl = new TidalApiImpl();

    @Test(expected = HttpBadResponseException.class)
    public void shouldThrowExcOnUnsuccessfulLogin() throws UnirestException {
        tidalApiImpl.login(BAD_USERNAME, BAD_PASSWORD);
    }
}