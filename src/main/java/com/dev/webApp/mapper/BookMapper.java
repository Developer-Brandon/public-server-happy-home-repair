package com.dev.webApp.mapper;

import com.dev.webApp.domain.fiveDuck.dto.InsertBookInfoDTO;
import com.dev.webApp.domain.fiveDuck.dto.UpdateBookInfoDTO;
import com.dev.webApp.domain.fiveDuck.dto.UpdateBookStateDTO;
import com.dev.webApp.domain.fiveDuck.vo.BookVO;

import java.util.List;

public interface BookMapper {

    int getBookTotalCnt();

    int deleteAll();

    List<BookVO> selectAllBookList();

    List<BookVO> selectBookList();

    Integer insertBookInfo(InsertBookInfoDTO insertBookInfoDTO);

    int updateBookInfo(UpdateBookInfoDTO updateBookInfoDTO);

    int updateBookState(UpdateBookStateDTO updateBookStateDTO);

    int deleteBookInfo(Integer bookNo);
}
