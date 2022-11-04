package com.dev.webApp.mapper.fiveDucks;

import com.dev.webApp.domain.fiveDuck.dto.InsertMovieInfoDTO;
import com.dev.webApp.domain.fiveDuck.dto.SelectMoviePaginationDTO;
import com.dev.webApp.domain.fiveDuck.dto.UpdateMovieInfoDTO;
import com.dev.webApp.domain.fiveDuck.dto.UpdateMovieStateDTO;
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
