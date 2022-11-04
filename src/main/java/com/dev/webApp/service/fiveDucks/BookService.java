package com.dev.webApp.service.fiveDucks;

import com.dev.webApp.domain.fiveDuck.dto.request.UpdateBookInfoRequestDTO;
import com.dev.webApp.domain.fiveDuck.dto.request.InsertBookInfoRequestDTO;
import com.dev.webApp.domain.fiveDuck.dto.request.SelectBookInfoRequestDTO;
import com.dev.webApp.domain.fiveDuck.dto.request.SelectBookPaginationRequestDTO;
import com.dev.webApp.domain.fiveDuck.vo.BookVO;

import java.util.List;

public interface BookService {

    Integer selectBookTotalCnt();

    void removeAllBookInfoList();

    List<BookVO> selectBookList(SelectBookPaginationRequestDTO selectBookPaginationRequestDTO);

    List<BookVO> selectAllBookInfoList();

    BookVO selectBookInfo(SelectBookInfoRequestDTO selectBookInfoRequestDTO);

    void registerBookInfo(InsertBookInfoRequestDTO insertBookInfoRequestDTO);

    void modifyBookInfo(UpdateBookInfoRequestDTO updateBookInfoRequestDTO);

    void removeBookInfo(Integer bookNo);
}
