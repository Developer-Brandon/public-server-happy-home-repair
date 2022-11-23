package com.dev.webApp.service.fiveDucks;


import com.dev.webApp.domain.fiveDuck.dto.request.*;
import com.dev.webApp.domain.fiveDuck.vo.MovieVO;

import java.util.List;

public interface MovieService {

    Integer selectMovieTotalCnt();

    void removeAllMovieInfoList();

    List<MovieVO> selectMovieList(SelectMoviePaginationRequestDTO selectMoviePaginationRequestDTO);

    List<MovieVO> selectAllMovieInfoList();

    MovieVO selectMovieInfo(SelectMovieInfoRequestDTO selectMovieInfoRequestDTO);

    void registerMovieInfo(InsertMovieInfoRequestDTO insertMovieInfoRequestDTO);

    void modifyMovieInfo(UpdateMovieInfoRequestDTO updateMovieInfoRequestDTO);

    void modifyMovieState(UpdateMovieStateRequestDTO updateMovieStateRequestDTO);

    void removeMovieInfo(Integer movieNo);
}
