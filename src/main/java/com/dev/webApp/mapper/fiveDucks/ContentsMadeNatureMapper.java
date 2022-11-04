package com.dev.webApp.mapper.fiveDucks;

import com.dev.webApp.domain.fiveDuck.dto.request.*;
import com.dev.webApp.domain.fiveDuck.vo.ContentsVO;

import java.util.List;

public interface ContentsMadeNatureMapper {

    int getContentsTotalCnt();

    int deleteAll();

    List<ContentsVO> selectAllContentsList();

    List<ContentsVO> selectContentsList(SelectContentsPaginationRequestDTO selectContentsPaginationRequestDTO);

    ContentsVO selectContents(SelectContentsInfoRequestDTO selectContentsInfoRequestDTO);

    Integer insertContentsInfo(InsertContentsInfoRequestDTO insertContentsInfoRequestDTO);

    int updateContentsInfo(UpdateContentsInfoRequestDTO updateContentsInfoRequestDTO);

    int updateContentsState(UpdateContentsStateRequestDTO updateContentsStateRequestDTO);

    int deleteContentsInfo(Integer contentsNo);
}
