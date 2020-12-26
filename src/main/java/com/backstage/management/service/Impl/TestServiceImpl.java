package com.backstage.management.service.Impl;

import com.backstage.management.dao.TestDao;
import com.backstage.management.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ywj
 * @version 1.0
 * @date 2020/11/7 19:41
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;


}
