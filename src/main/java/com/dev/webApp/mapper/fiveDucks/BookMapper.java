package com.dev.webApp.mapper.fiveDucks;

import com.dev.webApp.domain.fiveDuck.dto.request.*;
import com.dev.webApp.domain.fiveDuck.vo.BookVO;

import java.util.List;

public interface BookMapper {

    int getBookTotalCnt();

    int deleteAll();

    List<BookVO> selectAllBookList();

    List<BookVO> selectBookList(SelectBookPaginationRequestDTO selectBookPaginationRequestDTO);

    BookVO selectBookInfo(SelectBookInfoRequestDTO selectBookInfoRequestDTO);

    Integer insertBookInfo(InsertBookInfoRequestDTO insertBookInfoRequestDTO);

    int updateBookInfo(UpdateBookInfoRequestDTO updateBookInfoRequestDTO);

    int updateBookState(UpdateBookStateRequestDTO updateBookStateRequestDTO);

    int deleteBookInfo(Integer bookNo);
}
