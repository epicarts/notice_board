package com.osstem.notice.mapper;

import com.osstem.notice.dto.mapper.NoticeDetailDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeMapper {
    NoticeDetailDto selectNoticeDetail(Long noticeId);
}