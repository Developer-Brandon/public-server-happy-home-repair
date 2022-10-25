package com.dev.webApp.mapper;

import com.dev.webApp.domain.fiveDuck.dto.InsertYoutubeInfoDTO;
import com.dev.webApp.domain.fiveDuck.dto.UpdateYoutubeInfoDTO;
import com.dev.webApp.domain.fiveDuck.dto.UpdateYoutubeStateDTO;
import com.dev.webApp.domain.fiveDuck.vo.YoutubeVO;

import java.util.List;

public interface YoutubeMapper {

    int getYoutubeTotalCnt();

    int deleteAll();

    List<YoutubeVO> selectAllYoutubeList();

    List<YoutubeVO> selectYoutubeList();

    Integer insertYoutubeInfo(InsertYoutubeInfoDTO insertYoutubeInfoDTO);

    int updateYoutubeInfo(UpdateYoutubeInfoDTO updateYoutubeInfoDTO);

    int updateYoutubeState(UpdateYoutubeStateDTO updateYoutubeStateDTO);

    int deleteYoutubeInfo(Integer bookNo);
}
