package com.dev.webApp.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InsertNoticeDTO {
    Boolean manyNoticeOrNot;
    Integer noticeSize;
}
