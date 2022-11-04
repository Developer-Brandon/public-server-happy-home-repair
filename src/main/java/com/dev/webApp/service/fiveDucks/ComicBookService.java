package com.dev.webApp.service.fiveDucks;

import com.dev.webApp.domain.fiveDuck.dto.request.InsertComicBookInfoRequestDTO;
import com.dev.webApp.domain.fiveDuck.dto.request.SelectComicBookInfoRequestDTO;
import com.dev.webApp.domain.fiveDuck.dto.request.SelectComicBookPaginationRequestDTO;
import com.dev.webApp.domain.fiveDuck.dto.request.UpdateComicBookInfoRequestDTO;
import com.dev.webApp.domain.fiveDuck.vo.ComicBookVO;

import java.util.List;

public interface ComicBookService {

    Integer selectComicBookTotalCnt();

    void removeAllComicBookInfoList();

    List<ComicBookVO> selectComicBookList(SelectComicBookPaginationRequestDTO selectComicBookPaginationRequestDTO);

    List<ComicBookVO> selectAllComicBookInfoList();

    ComicBookVO selectComicBookInfo(SelectComicBookInfoRequestDTO selectComicBookInfoRequestDTO);

    void registerComicBookInfo(InsertComicBookInfoRequestDTO insertComicBookInfoRequestDTO);

    void modifyComicBookInfo(UpdateComicBookInfoRequestDTO updateComicBookInfoRequestDTO);

    void removeComicBookInfo(Integer bookNo);
}
