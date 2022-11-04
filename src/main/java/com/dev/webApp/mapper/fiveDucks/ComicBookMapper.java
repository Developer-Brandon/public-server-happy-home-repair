package com.dev.webApp.mapper.fiveDucks;

import com.dev.webApp.domain.fiveDuck.dto.*;
import com.dev.webApp.domain.fiveDuck.vo.ComicBookVO;

import java.util.List;

public interface ComicBookMapper {

    int getComicBookTotalCnt();

    int deleteAll();

    List<ComicBookVO> selectAllComicBookList();

    List<ComicBookVO> selectComicBookList(SelectComicBookPaginationDTO selectComicBookPaginationDTO);

    Integer insertComicBookInfo(InsertComicBookInfoDTO insertComicBookInfoDTO);

    int updateComicBookInfo(UpdateComicBookInfoDTO updateComicBookInfoDTO);

    int updateComicBookState(UpdateComicBookStateDTO updateComicBookStateDTO);

    int deleteComicBookInfo(Integer bookNo);
}
