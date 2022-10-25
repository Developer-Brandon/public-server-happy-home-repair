package com.dev.webApp.domain.fiveDuck.dto;

import com.dev.webApp.util.MovieUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdateMovieStateDTO {
    Integer movieNo;
    MovieUseYnEnum movieUseYnEnum;
}
