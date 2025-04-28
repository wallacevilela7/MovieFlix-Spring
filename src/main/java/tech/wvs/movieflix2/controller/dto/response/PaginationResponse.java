package tech.wvs.movieflix2.controller.dto.response;

public record PaginationResponse(Integer page,
                                 Integer pageSize,
                                 Long totalElements,
                                 Integer totalPages) {
}
