package com.dev.webApp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SelectNoticeDTO {
    Integer offset;
    Integer currentPage;
    Integer noticeSize;
}
