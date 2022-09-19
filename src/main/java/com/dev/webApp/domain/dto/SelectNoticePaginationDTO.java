package com.dev.webApp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SelectNoticePaginationDTO {
    Integer currentPage;
    Integer offset;
    Integer pageSize;
}
