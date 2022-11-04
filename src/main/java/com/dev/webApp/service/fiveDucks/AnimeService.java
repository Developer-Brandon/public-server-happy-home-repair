package com.dev.webApp.service.fiveDucks;

import com.dev.webApp.domain.fiveDuck.dto.request.*;
import com.dev.webApp.domain.fiveDuck.vo.AnimeVO;

import java.util.List;

public interface AnimeService {

    Integer selectAnimeTotalCnt();

    void removeAllAnimeInfoList();

    List<AnimeVO> selectAnimeList(SelectAnimePaginationRequestDTO selectAnimePaginationRequestDTO);

    List<AnimeVO> selectAllAnimeInfoList();

    AnimeVO selectAnimeInfo(SelectAnimeInfoRequestDTO selectAnimeInfoRequestDTO);

    void registerAnimeInfo(InsertAnimeInfoRequestDTO insertAnimeInfoRequestDTO);

    void modifyAnimeInfo(UpdateAnimeInfoRequestDTO updateAnimeInfoRequestDTO);

    void removeAnimeInfo(RemoveAnimeInfoRequestDTO removeAnimeRequestDTO);
}
