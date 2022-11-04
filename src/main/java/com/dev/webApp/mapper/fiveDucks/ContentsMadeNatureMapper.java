package com.dev.webApp.mapper.fiveDucks;

import com.dev.webApp.domain.fiveDuck.dto.InsertContentsInfoDTO;
import com.dev.webApp.domain.fiveDuck.dto.SelectContentsPaginationDTO;
import com.dev.webApp.domain.fiveDuck.dto.UpdateContentsInfoDTO;
import com.dev.webApp.domain.fiveDuck.dto.UpdateContentsStateDTO;
import com.dev.webApp.domain.fiveDuck.vo.ContentsVO;

import java.util.List;

public interface ContentsMadeNatureMapper {

    int getContentsTotalCnt();

    int deleteAll();

    List<ContentsVO> selectAllContentsList();

    List<ContentsVO> selectContentsList(SelectContentsPaginationDTO selectContentsPaginationDTO);

    Integer insertContentsInfo(InsertContentsInfoDTO insertContentsInfoDTO);

    int updateContentsInfo(UpdateContentsInfoDTO updateContentsInfoDTO);

    int updateContentsState(UpdateContentsStateDTO updateContentsStateDTO);

    int deleteContentsInfo(Integer contentsNo);
}
