package com.dev.webApp.service.fiveDucks;


import com.dev.webApp.domain.fiveDuck.dto.request.*;
import com.dev.webApp.domain.fiveDuck.vo.YoutubeVO;

import java.util.List;

public interface YoutubeService {

    Integer selectYoutubeTotalCnt();

    void removeAllYoutubeInfoList();

    List<YoutubeVO> selectYoutubeList(SelectYoutubePaginationRequestDTO selectYoutubePaginationRequestDTO);

    List<YoutubeVO> selectAllYoutubeInfoList();

    YoutubeVO selectYoutubeInfo(SelectYoutubeInfoRequestDTO selectYoutubeInfoRequestDTO);

    void registerYoutubeInfo(InsertYoutubeInfoRequestDTO insertYoutubeInfoRequestDTO);

    void modifyYoutubeInfo(UpdateYoutubeInfoRequestDTO updateYoutubeInfoRequestDTO);

    void modifyYoutubeState(UpdateYoutubeStateRequestDTO updateYoutubeStateRequestDTO);

    void removeYoutubeInfo(Integer YoutubeNo);
}
