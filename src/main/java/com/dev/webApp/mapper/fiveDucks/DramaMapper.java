package com.dev.webApp.mapper.fiveDucks;

import com.dev.webApp.domain.fiveDuck.dto.request.InsertDramaInfoRequestDTO;
import com.dev.webApp.domain.fiveDuck.dto.request.UpdateDramaInfoRequestDTO;
import com.dev.webApp.domain.fiveDuck.dto.request.UpdateDramaStateRequestDTO;
import com.dev.webApp.domain.fiveDuck.vo.DramaVO;

import java.util.List;

public interface DramaMapper {

    int getDramaTotalCnt();

    int deleteAll();

    List<DramaVO> selectAllDramaList();

    List<DramaVO> selectDramaList();

    Integer insertDramaInfo(InsertDramaInfoRequestDTO insertDramaInfoRequestDTO);

    int updateDramaInfo(UpdateDramaInfoRequestDTO updateDramaInfoRequestDTO);

    int updateDramaState(UpdateDramaStateRequestDTO updateDramaStateRequestDTO);

    int deleteDramaInfo(Integer bookNo);
}
