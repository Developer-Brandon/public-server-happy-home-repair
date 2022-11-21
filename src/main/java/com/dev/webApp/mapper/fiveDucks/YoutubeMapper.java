package com.dev.webApp.mapper.fiveDucks;

import com.dev.webApp.domain.fiveDuck.dto.request.InsertYoutubeInfoRequestDTO;
import com.dev.webApp.domain.fiveDuck.dto.request.UpdateYoutubeInfoRequestDTO;
import com.dev.webApp.domain.fiveDuck.dto.request.UpdateYoutubeStateRequestDTO;
import com.dev.webApp.domain.fiveDuck.vo.YoutubeVO;

import java.util.List;

public interface YoutubeMapper {

    int getYoutubeTotalCnt();

    int deleteAll();

    List<YoutubeVO> selectAllYoutubeList();

    List<YoutubeVO> selectYoutubeList();

    Integer insertYoutubeInfo(InsertYoutubeInfoRequestDTO insertYoutubeInfoRequestDTO);

    int updateYoutubeInfo(UpdateYoutubeInfoRequestDTO updateYoutubeInfoRequestDTO);

    int updateYoutubeState(UpdateYoutubeStateRequestDTO updateYoutubeStateRequestDTO);

    int deleteYoutubeInfo(Integer bookNo);
}
