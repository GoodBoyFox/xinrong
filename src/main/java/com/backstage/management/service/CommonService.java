package com.backstage.management.service;


import com.backstage.management.entity.Basic;
import com.backstage.management.entity.Rotation;

import java.util.List;

/**
 * @ProjectName: Zhixiang
 * @Package: com.doctor.app.service
 * @ClassName: CommonService
 * @Author: gwl
 * @Description:
 * @Date: 2020/11/9 20:12
 * @Version: 1.0
 */
public interface CommonService {

    Basic getAllBasic();

    int updateBasic(Basic basic);

    List<Rotation> getAllRotation();

    int insertRotation(Rotation rotation);

    int ddelRotation(Integer id);

    int updateRotation(Rotation rotation);

    Rotation getRotationById(Integer id);
}
