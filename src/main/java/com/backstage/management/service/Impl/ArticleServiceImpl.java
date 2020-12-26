package com.backstage.management.service.Impl;


import com.backstage.management.dao.ArticleDao;
import com.backstage.management.entity.Column;
import com.backstage.management.service.ArticleService;
import com.backstage.management.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: Zhixiang
 * @Package: com.doctor.app.service.Impl
 * @ClassName: ArticleServiceImpl
 * @Author: gwl
 * @Description:
 * @Date: 2020/11/9 20:38
 * @Version: 1.0
 */

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleDao articleDao;

    @Override
    public Page<Column> getAllColumn(Integer CurrentPage,Integer all) {

        Page<Column> page = new Page<>();
        if (all==0){
            List<Column> columns = articleDao.finaAllColumn();
            page.setDatalist(columns);
            return page;
        }
        PageHelper.startPage(CurrentPage,10);
        List<Column> columns = articleDao.finaAllColumn();
        PageInfo<Column> info = new PageInfo<>(columns);
        page.setCurrentnumber(info.getPageNum());
        page.setCurrentpage(CurrentPage);
        page.setPagecount(info.getPages());
        page.setTotalnumber((int) info.getTotal());
        page.setDatalist(info.getList());
        return page;
    }

    @Override
    public Column getColumnBuId(Integer id) {
        return articleDao.findColumnBuId(id);
    }

    @Override
    public int updateColumn(Column column) {
        return articleDao.updateColumn(column);
    }

    @Override
    public int insertColumn(Column column) {
        return articleDao.insertColumn(column);
    }

    @Override
    public int deleteColumn(Integer id) {
        return articleDao.deleteColumn(id);
    }

    @Override
    public List<Column> getAllOneColumn() {

        return articleDao.getAllOneColumn();
    }

    @Override
    public List<Column> getXiaoAllColumn() {

        List<Column> allOneColumn = articleDao.finaAllColumn();

        List<Column> list1 = new ArrayList<>();
        List<Column> list2 = new ArrayList<>();

        for (Column column : allOneColumn) {
            if (column.getLevel()==1){
                list1.add(column);
                System.out.println("list1"+list1);
            }else{
                list2.add(column);
                System.out.println("list2"+list2);
            }
        }

        for (Column column1 : list1) {
            List<Column> list3 = new ArrayList<>();
            for (Column column2 : list2) {
                if (column1.getId().equals(column2.getFid()) ){
                    list3.add(column2);
                }
            }
            column1.setList(list3);
        }
        return list1;
    }
}
