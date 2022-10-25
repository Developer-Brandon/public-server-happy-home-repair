package com.dev.webApp.mapper;

import com.dev.webApp.domain.fiveDuck.dto.*;
import com.dev.webApp.domain.fiveDuck.vo.AnimeVO;
import com.dev.webApp.domain.fiveDuck.vo.MovieVO;

import java.util.List;

public interface MovieMapper {

    int getMovieTotalCnt();

    int deleteAll();

    List<MovieVO> selectAllMovieList();

    List<MovieVO> selectMovieList(SelectMoviePaginationDTO selectMoviePaginationDTO);

    Integer insertMovieInfo(InsertMovieInfoDTO insertMovieInfoDTO);

    int updateMovieInfo(UpdateMovieInfoDTO updateMovieInfoDTO);

    int updateMovieState(UpdateMovieStateDTO updateMovieStateDTO);

    int deleteMovieInfo(Integer movieNo);
}
