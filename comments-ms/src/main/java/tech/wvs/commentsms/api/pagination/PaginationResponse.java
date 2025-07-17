package tech.wvs.commentsms.api.pagination;

public record PaginationResponse(
        Integer page,
        Integer pageSize,
        Long totalElements,
        Integer totalPages) {
}
