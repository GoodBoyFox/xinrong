package com.backstage.management.service.Impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.backstage.management.dao.CommonDao;
import com.backstage.management.entity.Basic;
import com.backstage.management.entity.Rotation;
import com.backstage.management.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: Zhixiang
 * @Package: com.doctor.app.service.Impl
 * @ClassName: CommonServiceImpl
 * @Author: gwl
 * @Description:
 * @Date: 2020/11/9 20:12
 * @Version: 1.0
 */

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    CommonDao commonDao;

    @Override
    public Basic getAllBasic() {

        Basic allBasic = commonDao.findAllBasic();

        List<Basic> basicList =commonDao.findAllBasicList();

        allBasic.setBasicList(basicList);

        return allBasic;
    }

    @Override
    @Transactional
    public int updateBasic(Basic basic) {

        int i = commonDao.updateBasic(basic);
        for (Basic basic1 : basic.getBasicList()) {

            if (basic1.getDel()==null){
                //新增
                int insertNum = commonDao.insertBasic(basic1);
            }else if (basic1.getDel()==1){
                //修改
                int updateNum = commonDao.updateBasic2(basic1);
            }else {
                //删除
                int delNum =commonDao.deleteBasicById(basic1.getId());
            }
        }
        return i;
    }

    @Override
    public List<Rotation> getAllRotation(Integer type) {
        List<Rotation> allRotation =new ArrayList<>();
        if (type==0){
            allRotation = commonDao.findAllRotation();
        }else {
            allRotation = commonDao.findRotationByType(type);
        }
        return allRotation;
    }

    @Override
    public int insertRotation(Rotation rotation) {
        return commonDao.insertRotation(rotation);
    }

    @Override
    public int ddelRotation(Integer id) {
        return commonDao.ddelRotation(id);
    }

    @Override
    public int updateRotation(Rotation rotation) {
        return commonDao.updateRotation(rotation);
    }

    @Override
    public Rotation getRotationById(Integer id) {
        return commonDao.getRotationById(id);
    }
}
