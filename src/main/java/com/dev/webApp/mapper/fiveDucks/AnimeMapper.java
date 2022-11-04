package com.dev.webApp.mapper.fiveDucks;

import com.dev.webApp.domain.fiveDuck.dto.SelectDramaPaginationDTO;
import com.dev.webApp.domain.fiveDuck.dto.request.*;
import com.dev.webApp.domain.fiveDuck.vo.AnimeVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnimeMapper {

    int getAnimeTotalCnt();

    int deleteAll();

    List<AnimeVO> selectAllAnimeList();

    List<AnimeVO> selectAnimeList(SelectAnimePaginationRequestDTO selectAnimePaginationRequestDTO);

    AnimeVO selectAnimeInfo(SelectAnimeInfoRequestDTO selectAnimeInfoRequestDTO);

    Integer insertAnimeInfo(InsertAnimeInfoRequestDTO insertAnimeInfoRequestDTO);

    int updateAnimeInfo(UpdateAnimeInfoRequestDTO updateAnimeInfoRequestDTO);

    int updateAnimeState(UpdateAnimeStateRequestDTO updateAnimeStateRequestDTO);

    // TODO: @Param을 생략해도 test가 실행되는지 추후 test case작성 후 검증 예정입니다.
    int deleteAnimeInfo(@Param("animeNo") Integer animeNo);
}
