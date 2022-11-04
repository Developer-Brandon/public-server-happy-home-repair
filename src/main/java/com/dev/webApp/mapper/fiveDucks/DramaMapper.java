package com.dev.webApp.mapper.fiveDucks;

import com.dev.webApp.domain.fiveDuck.dto.InsertDramaInfoDTO;
import com.dev.webApp.domain.fiveDuck.dto.UpdateDramaInfoDTO;
import com.dev.webApp.domain.fiveDuck.dto.UpdateDramaStateDTO;
import com.dev.webApp.domain.fiveDuck.vo.DramaVO;

import java.util.List;

public interface DramaMapper {

    int getDramaTotalCnt();

    int deleteAll();

    List<DramaVO> selectAllDramaList();

    List<DramaVO> selectDramaList();

    Integer insertDramaInfo(InsertDramaInfoDTO insertDramaInfoDTO);

    int updateDramaInfo(UpdateDramaInfoDTO updateDramaInfoDTO);

    int updateDramaState(UpdateDramaStateDTO updateDramaStateDTO);

    int deleteDramaInfo(Integer bookNo);
}
