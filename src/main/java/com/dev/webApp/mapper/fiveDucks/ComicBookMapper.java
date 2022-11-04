package com.dev.webApp.mapper.fiveDucks;

import com.dev.webApp.domain.fiveDuck.dto.request.InsertComicBookInfoRequestDTO;
import com.dev.webApp.domain.fiveDuck.dto.request.SelectComicBookInfoRequestDTO;
import com.dev.webApp.domain.fiveDuck.dto.request.SelectComicBookPaginationRequestDTO;
import com.dev.webApp.domain.fiveDuck.dto.request.UpdateComicBookInfoRequestDTO;
import com.dev.webApp.domain.fiveDuck.dto.request.UpdateComicBookStateRequestDTO;
import com.dev.webApp.domain.fiveDuck.vo.ComicBookVO;

import java.util.List;

public interface ComicBookMapper {

    int getComicBookTotalCnt();

    int deleteAll();

    List<ComicBookVO> selectAllComicBookList();

    List<ComicBookVO> selectComicBookList(SelectComicBookPaginationRequestDTO selectComicBookPaginationRequestDTO);

    ComicBookVO selectComicBookInfo(SelectComicBookInfoRequestDTO selectComicBookInfoRequestDTO);

    Integer insertComicBookInfo(InsertComicBookInfoRequestDTO insertComicBookInfoRequestDTO);

    int updateComicBookInfo(UpdateComicBookInfoRequestDTO updateComicBookInfoRequestDTO);

    int updateComicBookState(UpdateComicBookStateRequestDTO updateComicBookStateRequestDTO);

    int deleteComicBookInfo(Integer bookNo);
}
