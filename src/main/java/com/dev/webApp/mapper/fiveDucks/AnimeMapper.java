package com.dev.webApp.mapper.fiveDucks;

import com.dev.webApp.domain.fiveDuck.dto.*;
import com.dev.webApp.domain.fiveDuck.vo.AnimeVO;

import java.util.List;

public interface AnimeMapper {

    int getAnimeTotalCnt();

    int deleteAll();

    List<AnimeVO> selectAllAnimeList();

    List<AnimeVO> selectAnimeList(SelectDramaPaginationDTO selectDramaPaginationDTO);

    Integer insertAnimeInfo(InsertAnimeInfoDTO insertAnimeInfoDTO);

    int updateAnimeInfo(UpdateAnimeInfoDTO updateAnimeInfoDTO);

    int updateAnimeState(UpdateAnimeStateDTO updateAnimeStateDTO);

    int deleteAnimeInfo(Integer animeNo);
}
