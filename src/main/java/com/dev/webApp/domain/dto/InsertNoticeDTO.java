package com.dev.webApp.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class InsertNoticeDTO {
    Boolean manyNoticeOrNot;
    Integer noticeSize;
}
