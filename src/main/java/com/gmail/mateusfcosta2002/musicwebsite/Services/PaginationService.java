package com.gmail.mateusfcosta2002.musicwebsite.Services;

import java.net.URI;

import org.springframework.web.util.UriComponentsBuilder;

import com.gmail.mateusfcosta2002.musicwebsite.WebProperties;
import com.gmail.mateusfcosta2002.musicwebsite.Controllers.Extra.CommonResponses.Pagination;
import com.gmail.mateusfcosta2002.musicwebsite.Repositories.Config.PageResult;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class PaginationService {
    private URI appURI;

    public PaginationService(WebProperties webProperties) {
        this.appURI = webProperties.APP_URI;
    }

    public<T> Pagination<T> build(PageResult<? extends Iterable<T>, T> page, HttpServletRequest req) {
        var userUri = appURI.resolve(req.getRequestURI());
        var uriBuilder = UriComponentsBuilder
            .fromUri(userUri)
            .query(req.getQueryString());

        var previousPageNumber = page.pageNumber() - 1 ;
        var nextPageNumber = page.pageNumber() + 1;

        var firstPage = uriBuilder.replaceQueryParam("page", 1).build().encode().toString();
        var lastPage = uriBuilder.replaceQueryParam("page", page.totalPages()).build().encode().toString();
        String nextPage = null;
        String previousPage = null;

        if (nextPageNumber <= page.totalPages()) {
            nextPage = uriBuilder.replaceQueryParam("page", nextPageNumber).build().encode().toString();
        }
        if (previousPageNumber >= 1) {
            previousPage = uriBuilder.replaceQueryParam("page", previousPageNumber).build().encode().toString();
        }

        return new Pagination<>(
            page.data(),
            page.totalItems(),
            page.pageSize(),
            page.totalPages(),
            page.pageNumber(),
            firstPage,
            lastPage,
            nextPage,
            previousPage
        );
    }
}
