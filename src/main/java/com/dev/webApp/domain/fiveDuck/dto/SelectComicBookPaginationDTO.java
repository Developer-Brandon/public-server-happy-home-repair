package com.dev.webApp.domain.fiveDuck.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SelectComicBookPaginationDTO {

    @Builder.Default
    Integer currentPage = 1;

    Integer offset;

    @Builder.Default
    Integer pageSize = 10;
}
