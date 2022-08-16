package com.dev.webApp.mapper;

import com.dev.webApp.domain.NoticeVO;
import com.mysql.cj.protocol.x.Notice;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface NoticeMapper {

    public List<NoticeVO> selectNoticeList();

    public NoticeVO selectNotice(Long noticeNo);

    public int insertNotice(NoticeVO noticeVO);

    public int updateNotice(NoticeVO noticeNo);

    public int deleteNotice(Long noticeNo);
}
