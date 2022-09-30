package com.dev.webApp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SelectFaqPaginationDTO {

    Integer currentPage;

    Integer offset;

    Integer pageSize;
}
