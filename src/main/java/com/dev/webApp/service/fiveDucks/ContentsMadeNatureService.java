package com.dev.webApp.service.fiveDucks;

import com.dev.webApp.domain.fiveDuck.dto.request.*;
import com.dev.webApp.domain.fiveDuck.vo.ContentsVO;

import java.util.List;

public interface ContentsMadeNatureService {

    Integer selectContentsMadeNatureTotalCnt();

    void removeAllContentsMadeNatureInfoList();

    List<ContentsVO> selectContentsMadeNatureList(SelectContentsPaginationRequestDTO selectContentsPaginationRequestDTO);

    List<ContentsVO> selectAllContentsMadeNatureInfoList();

    ContentsVO selectContentsMadeNatureInfo(SelectContentsInfoRequestDTO selectContentsInfoRequestDTO);

    void registerContentsMadeNatureInfo(InsertContentsInfoRequestDTO insertContentsInfoRequestDTO);

    void modifyContentsMadeNatureInfo(UpdateContentsInfoRequestDTO updateContentsInfoRequestDTO);

    void modifyContentsMadeNatureState(UpdateContentsStateRequestDTO updateContentsStateRequestDTO);

    void removeContentsMadeNatureInfo(Integer bookNo);
}
