package com.gmail.mateusfcosta2002.musicwebsite.Web.Exceptions;

import org.springframework.http.HttpStatusCode;

public class AppResponseException extends Exception {
    private HttpStatusCode code;

    public AppResponseException(HttpStatusCode code, String message) {
        super(message);
        this.code = code;
    }

    public HttpStatusCode getCode() {
        return code;
    }
}
