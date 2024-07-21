package com.gmail.mateusfcosta2002.musicwebsite.Controllers.Extra;

public class CommonResponses {
    public record MessageResponse(String message) {}

    public record Pagination<T>(
        Iterable<T> data,

        long totalItens,
        int perPage,
        int pagesCount,
        int currentPage,

        String firstPageUrl,
        String lastPageUrl,
        String nextPageUrl,
        String previousPageUrl
    ) {}
}
