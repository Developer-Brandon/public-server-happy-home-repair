package com.dev.webApp.domain.fiveDuck.dto.request;

import com.dev.webApp.util.MovieUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdateMovieStateRequestDTO {
    Integer movieNo;
    MovieUseYnEnum movieUseYnEnum;
}
