package com.backstage.management.dao;


import com.backstage.management.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ProjectName: app
 * @Package: com.doctor.app.dao
 * @ClassName: ContentDao
 * @Author: ywj
 * @Description:
 * @Date: 2020/11/9 20:35
 */
@Mapper
public interface ContentDao {
    int insertContent(Content content);

    int updateContentSql(Content content);

    int deleteContent(Integer id);

    List<Content> selectAllContent();

    Content selectContentById(Integer id);

    List<Content> selectContentByColumnId(Integer column_id);

    int updateContentLiuLan(Integer id);


    List<Content> getAllLiuYanSql();

    List<Column> selectAllTwo(Integer column_id);

    List<Content> selectLeaveByIp(String leaveIp);

    List<Column> selectAllOne(Integer columnOne);

    Integer selectArticleNum();

    Integer selectBrowseNum();

    Integer selectLeaveNum();

    int insertThreeTitle(ThreeTitle threeTitle);

    int insertInvestInfo(Invest invest);

    List<ThreeTitle> selectAllThreeTitle();

    List<Invest> selectAllInvest();

    int updateInvestInfoSql(Invest invest);

    int deleteInvestInfoSql(Integer id);

    int updateThreeTitle(ThreeTitle threeTitle);

    int deleteThreeTitleSql(Integer id);

    int insertAdministrativeSql(Administrative administrative);

    int updateAdministrativeSql(Administrative administrative);

    int deleteAdministrativeSql(Integer id);

    List<Administrative> selectAllAdministrative();

    List<Administrative> selectAdministrativeByCid(Integer column_id);

    List<Administrative> selectAdministrativeById(Integer id);
}
