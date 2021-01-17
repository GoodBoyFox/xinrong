package com.backstage.management.service.Impl;


import com.backstage.management.dao.ContentDao;
import com.backstage.management.entity.Column;
import com.backstage.management.entity.Content;
import com.backstage.management.service.ContentService;
import com.backstage.management.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ProjectName: app
 * @Package: com.doctor.app.service.Impl
 * @ClassName: ContentServiceImpl
 * @Author: ywj
 * @Description:
 * @Date: 2020/11/9 20:33
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    ContentDao contentDao;

    @Override
    public int insertContent(Content content) {

        int i = contentDao.insertContent(content);

        return i;
    }

    @Override
    public int updateContentSql(Content content) {

        int i = contentDao.updateContentSql(content);
        return i;
    }

    @Override
    public int deleteContent(Integer id) {
        int i = contentDao.deleteContent(id);
        return i;
    }

    @Override
    public Page<Content> selectAllContent(Integer CurrentPage, Integer all) {

        Page<Content> page = new Page<>();
        if (all == 0) {
            List<Content> Contents = contentDao.selectAllContent();
            page.setDatalist(Contents);
            return page;
        }
        PageHelper.startPage(CurrentPage, 10);
        List<Content> Contents = contentDao.selectAllContent();
        PageInfo<Content> info = new PageInfo<>(Contents);
        page.setCurrentnumber(info.getPageNum());
        page.setCurrentpage(CurrentPage);
        page.setPagecount(info.getPages());
        page.setTotalnumber((int) info.getTotal());
        page.setDatalist(info.getList());
        return page;
    }

    @Override
    public Content selectContentById(Integer id) {

        return contentDao.selectContentById(id);
    }

    @Override
    public Content selectContentByIdUpdateBrowse(Integer id) {
        int i = contentDao.updateContentLiuLan(id);   // 更新浏览量
        return contentDao.selectContentById(id);
    }

    @Override
    public Map<String, Integer> getContentNum() {

        Map<String, Integer> map = new HashMap<>();

        //查询文章总数
        Integer  articleNum =  contentDao.selectArticleNum();
        //查询总的浏览量
        Integer  browseNum =  contentDao.selectBrowseNum();
        //查询留言总数
        Integer  leaveNum =  contentDao.selectLeaveNum();

        map.put("articleNum",articleNum);
        map.put("browseNum",browseNum);
        map.put("leaveNum",leaveNum);
        return map;
    }


    @Override
    public Page<Content> selectContentByColumnId(Integer column_id, Integer CurrentPage) {
        //查询当前栏目下的所有二级
        List<Column> list = contentDao.selectAllTwo(column_id);
        List<Content> contentList = new ArrayList<>();
        if (list.size() > 0) {
            Page<Content> page = new Page<>();
            PageHelper.startPage(CurrentPage,10);
            for (Column column : list) {
                List<Content> contentList1 = contentDao.selectContentByColumnId(column.getId());
                contentList.addAll(contentList1);
            }
            PageInfo<Content> info = new PageInfo<>(contentList);
            page.setCurrentnumber(info.getPageNum());
            page.setCurrentpage(CurrentPage);
            page.setPagecount(info.getPages());
            page.setTotalnumber((int) info.getTotal());
            page.setDatalist(info.getList());
            return page;
        } else {
            //查询内容根据栏目ID
            Page<Content> page = new Page<>();
            PageHelper.startPage(CurrentPage,10);
            List<Content> contentList2 = contentDao.selectContentByColumnId(column_id);
            PageInfo<Content> info = new PageInfo<>(contentList2);
            page.setCurrentnumber(info.getPageNum());
            page.setCurrentpage(CurrentPage);
            page.setPagecount(info.getPages());
            page.setTotalnumber((int) info.getTotal());
            page.setDatalist(info.getList());
            return page;
        }
    }

    @Override
    public Map<String,List<Content>> getAllLiuYanSql() {
        //Page<Content> page = new Page<>();
        //PageHelper.startPage(CurrentPage, 10);
        List<Content> contents = contentDao.getAllLiuYanSql();
        List<String> list = new ArrayList<>();
        for (Content content : contents) {
            list.add(content.getLeave_ip());
        }
        Map<String,List<Content>> map = new HashMap<>();
        for (String s : list) {
            List<Content> list2 = new ArrayList<>();
            for (Content content : contents) {
                if (s.equals(content.getLeave_ip())){
                    list2.add(content);
                    Collections.reverse(list2);
                    map.put(content.getLeave_ip(),list2);
                }
            }
        }
        System.out.println(map);

        return map;
    }

    @Override
    public List<Content> selectLeaveByIp(String leaveIp) {
        List<Content> contentList = contentDao.selectLeaveByIp(leaveIp);
        return contentList;
    }




}
