package tech.wvs.movieflix2.controller.dto.response;

import java.util.List;

public record ApiResponse<T>(
        List<T> content,
        PaginationResponse pagination) {
}
