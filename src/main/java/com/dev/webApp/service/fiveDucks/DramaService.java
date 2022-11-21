package com.dev.webApp.service.fiveDucks;


import com.dev.webApp.domain.fiveDuck.dto.request.*;
import com.dev.webApp.domain.fiveDuck.vo.DramaVO;

import java.util.List;

public interface DramaService {

    Integer selectDramaTotalCnt();

    void removeAllDramaInfoList();

    List<DramaVO> selectDramaList(SelectDramaPaginationRequestDTO selectDramaPaginationRequestDTO);

    List<DramaVO> selectAllDramaInfoList();

    DramaVO selectDramaInfo(SelectDramaInfoRequestDTO selectDramaInfoRequestDTO);

    void registerDramaInfo(InsertDramaInfoRequestDTO insertDramaInfoRequestDTO);

    void modifyDramaInfo(UpdateDramaInfoRequestDTO updateDramaInfoRequestDTO);

    void modifyDramaState(UpdateDramaStateRequestDTO updateDramaStateRequestDTO);

    void removeDramaInfo(Integer dramaNo);
}
