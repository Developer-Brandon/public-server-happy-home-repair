package com.dev.webApp.mapper.fiveDucks;

import com.dev.webApp.domain.fiveDuck.dto.request.*;
import com.dev.webApp.domain.fiveDuck.vo.MovieVO;

import java.util.List;

public interface MovieMapper {

    int getMovieTotalCnt();

    int deleteAll();

    List<MovieVO> selectAllMovieList();

    List<MovieVO> selectMovieList(SelectMoviePaginationRequestDTO selectMoviePaginationRequestDTO);

    MovieVO selectMovieInfo(SelectMovieInfoRequestDTO selectMovieInfoRequestDTO);

    Integer insertMovieInfo(InsertMovieInfoRequestDTO insertMovieInfoDTO);

    int updateMovieInfo(UpdateMovieInfoRequestDTO updateMovieInfoRequestDTO);

    int updateMovieState(UpdateMovieStateRequestDTO updateMovieStateRequestDTO);

    int deleteMovieInfo(Integer movieNo);
}
