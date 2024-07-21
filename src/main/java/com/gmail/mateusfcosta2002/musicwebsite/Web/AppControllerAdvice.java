package com.gmail.mateusfcosta2002.musicwebsite.Web;

import org.springframework.context.ApplicationContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.gmail.mateusfcosta2002.musicwebsite.Controllers.Extra.CommonResponses.MessageResponse;
import com.gmail.mateusfcosta2002.musicwebsite.Web.Exceptions.AppResponseException;

@ControllerAdvice(basePackages = "com.gmail.mateusfcosta2002.musicwebsite.Controllers")
public class AppControllerAdvice {
    private static final JsonNodeFactory jn = JsonNodeFactory.instance;

    private ApplicationContext ctx;

    public AppControllerAdvice(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @ExceptionHandler(AppResponseException.class)
    public ResponseEntity<MessageResponse> handleResponseException(AppResponseException e) {
        return ResponseEntity
            .status(e.getCode())
            .body(new MessageResponse(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException e) {
        var locale = LocaleContextHolder.getLocale();
        var fieldErrors = e.getFieldErrors();
        var globalError = e.getGlobalError();

        var root = jn.objectNode();
        var errors = jn.objectNode();

        for (var error : fieldErrors) {
            errors.put(error.getField(), ctx.getMessage(error, locale));
        }

        root.set("errors", errors);
        if (globalError != null)
            root.put("message", ctx.getMessage(globalError, locale));

        return ResponseEntity
            .unprocessableEntity()
            .body(root);
    }
}
