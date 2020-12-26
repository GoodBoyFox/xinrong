package com.backstage.management.controller;

import com.backstage.management.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ywj
 * @version 1.0
 * @date 2020/11/7 19:40
 */
@RestController
public class TestController {

    @Autowired
    private TestService testService;

   /* @RequestMapping("/hello")
    @ResponseBody
    public List<Menu> getHello(){

       List<Menu> testEntityList = testService.getEntity();

        return testEntityList;
    }*/

    @RequestMapping("/hello2")
    @ResponseBody
    public String getHello2(){
        System.out.println(123);
        return "hello";
    }

}
