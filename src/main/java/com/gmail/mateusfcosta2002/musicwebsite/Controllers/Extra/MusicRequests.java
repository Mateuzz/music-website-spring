package com.gmail.mateusfcosta2002.musicwebsite.Controllers.Extra;

import org.springframework.web.multipart.MultipartFile;

import com.gmail.mateusfcosta2002.musicwebsite.Repositories.CustomMusicRepository.MusicOrder;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MusicRequests {
    public record MusicPost(
            @Size(max = 150) @NotNull String name,
            long authorID,
            long categoryID,
            @NotNull MultipartFile file,
            String[] tags) {
    }

    public record MusicSearchRequest(
            String search,
            Long authorID,
            Long categoryID,
            MusicOrder order,
            @Size(min = 1) @NotNull int page,
            @Size(max = 100) Integer pageSize,
            String[] tags) {
    }

    // public static class MusicSearchRequestValidator implements Validator {
    //     private final static List<String> ALLOWED_ORDERS = List.of("latest", "oldest", "popular");

    //     @Override
    //     public boolean supports(Class<?> clazz) {
    //         return MusicSearchRequest.class.equals(clazz);
    //     }

    //     @Override
    //     public void validate(Object target, Errors errors) {
    //         var obj = (MusicSearchRequest) target;

    //         if (!ALLOWED_ORDERS.contains(obj.order())) {
    //             errors.rejectValue("order", "order.allowed-values", new Object[] { ALLOWED_ORDERS },
    //                     "Order must be one of the values " + ALLOWED_ORDERS);
    //         }
    //     }
    // }

}
