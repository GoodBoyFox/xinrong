package com.backstage.management.service;


import com.backstage.management.entity.Content;
import com.backstage.management.util.Page;

import java.util.List;

/**
 * @ProjectName: app
 * @Package: com.doctor.app.service
 * @ClassName: ContentService
 * @Author: ywj
 * @Description:
 * @Date: 2020/11/9 20:29
 */
public interface ContentService {

    int insertContent(Content content);

    int updateContentSql(Content content);

    int deleteContent(Integer id);

    Page<Content> selectAllContent(Integer CurrentPage,Integer all);

    Content selectContentById(Integer id);

    List<Content> selectContentByColumnId(Integer column_id);

    Page<Content> getAllLiuYanSql(Integer CurrentPage);
}
