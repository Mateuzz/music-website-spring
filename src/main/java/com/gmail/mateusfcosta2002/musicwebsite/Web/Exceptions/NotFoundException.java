package com.gmail.mateusfcosta2002.musicwebsite.Web.Exceptions;

import org.springframework.http.HttpStatusCode;

public class NotFoundException extends AppResponseException {
    public NotFoundException(String message) {
        super(HttpStatusCode.valueOf(404), message);
    }
}
