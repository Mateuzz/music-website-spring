package com.gmail.mateusfcosta2002.musicwebsite.Repositories.Config;

public record PageResult<Iter extends Iterable<? extends T>, T> (
    Iter data,
    long totalItems,
    int pageNumber,
    int pageSize,
    int totalPages
) {}
