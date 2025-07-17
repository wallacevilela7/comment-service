package tech.wvs.commentsms.api.pagination;

import java.util.List;

public record ApiResponse<T>(
        List<T> content,
        PaginationResponse paginationResponse
) {
}
