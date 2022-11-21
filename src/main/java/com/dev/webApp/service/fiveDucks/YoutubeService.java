package com.dev.webApp.service.fiveDucks;


import com.dev.webApp.domain.fiveDuck.dto.request.*;
import com.dev.webApp.domain.fiveDuck.vo.Mp3VO;

import java.util.List;

public interface YoutubeService {

    Integer selectMp3TotalCnt();

    void removeAllMp3InfoList();

    List<Mp3VO> selectMp3List(SelectMp3PaginationRequestDTO selectMp3PaginationRequestDTO);

    List<Mp3VO> selectAllMp3InfoList();

    Mp3VO selectMp3Info(SelectMp3InfoRequestDTO selectMp3InfoRequestDTO);

    void registerMp3Info(InsertMp3InfoRequestDTO insertMp3InfoRequestDTO);

    void modifyMp3Info(UpdateMp3InfoRequestDTO updateMp3InfoRequestDTO);

    void modifyMp3State(UpdateMp3StateRequestDTO updateMp3StateRequestDTO);

    void removeMp3Info(Integer Mp3No);
}
