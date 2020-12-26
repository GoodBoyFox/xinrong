package com.backstage.management.controller;


import com.alibaba.fastjson.JSON;

import com.backstage.management.entity.Basic;
import com.backstage.management.entity.Rotation;
import com.backstage.management.service.CommonService;
import com.backstage.management.util.FastDFSUtils;
import com.backstage.management.util.ResultCode;
import com.backstage.management.util.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ProjectName: Zhixiang
 * @Package: com.doctor.app.controller
 * @ClassName: CommonController
 * @Author: gwl
 * @Description:
 * @Date: 2020/11/9 20:15
 * @Version: 1.0
 */

@CrossOrigin
@RestController
@Slf4j
public class CommonController {

    @Autowired
    CommonService commonService;

    @Value("${serveraddress}")
    private String serveraddress;

    /**
     * 查找基本信息   ----------------------------------------------------------------------------------------小程序公用
     * */
    @GetMapping("/CommonController/findAllBasic")
    public JSON findAllBasic(){
        Basic allBasic = commonService.getAllBasic();
        return ResultData.getResponseData(allBasic, ResultCode.QUERY_SUCCESS);
    }

    /**
     * 修改基本信息
     * */
    @PostMapping("/CommonController/updateBasic")
    public JSON updateBasic(@ModelAttribute Basic basic){
        int i = commonService.updateBasic(basic);
        if (i>0){
            return ResultData.getResponseData(null, ResultCode.UPDATE_SUCCESS);
        }else {
            return ResultData.getResponseData(null, ResultCode.UPDATE_ERROR);
        }
    }


    /**
     * 查找轮播   ----------------------------------------------------------------------------------------小程序公用
     * */
    @GetMapping("/CommonController/findAllRotation")
    public JSON findAllRotation(){
        List<Rotation> allRotation = commonService.getAllRotation();
        return ResultData.getResponseData(allRotation, ResultCode.QUERY_SUCCESS);

    }


    /**
     * 新增轮播
     * */
    @PostMapping("/CommonController/insertRotation")
    public JSON insertRotation(@ModelAttribute Rotation rotation){
        int i = commonService.insertRotation(rotation);
        if (i>0){
            return ResultData.getResponseData(i, ResultCode.INSERT_SUCCESS);
        }else {
            return ResultData.getResponseData(null, ResultCode.INSERT_ERROR);
        }
    }


    /**
     * 删除轮播
     * */
    @GetMapping("/CommonController/ddelRotation/{id}")
    public JSON ddelRotation(@PathVariable("id") Integer id){
        System.out.println("id》》》"+id);
        int i = commonService.ddelRotation(id);
        if (i>0){
            return ResultData.getResponseData("删除成功", ResultCode.DELETE_SUCCESS);
        }else {
            return ResultData.getResponseData("删除失败", ResultCode.DELETE_ERROR);
        }
    }


    /**
    * @FunctionName: 根据ID 查找轮播
    * @author: Ywj
    * @Param:
    * @Return:
    */
    @GetMapping("/CommonController/findRotationById/{id}")
    public JSON findRotationById(@PathVariable("id") Integer id){
        Rotation allRotation = commonService.getRotationById(id);

        return ResultData.getResponseData(allRotation, ResultCode.QUERY_SUCCESS);

    }

    /**
     * 修改轮播
     * */
    @RequestMapping(value = "/CommonController/updateRotation",method = RequestMethod.POST)
    public JSON updateRotation(@RequestBody Rotation rotation){
        int i = commonService.updateRotation(rotation);
        System.out.println("修改轮播");
        if (i>0){
            return ResultData.getResponseData(i, ResultCode.UPDATE_SUCCESS);
        }else {
            return ResultData.getResponseData(null, ResultCode.UPDATE_ERROR);
        }
    }


    /**
     * 图片上传
     * */
    @PostMapping("/CommonController/IMG")
    public JSON insertCommodityIMG(MultipartFile file){
        //log.info("serveraddress------>"+serveraddress);
        String fileId = FastDFSUtils.upload(file);
        String url = serveraddress + fileId;
        if(url!=null){
            return ResultData.getResponseData(url,ResultCode.IMG_SUCCESS);
        }else {
            return ResultData.getResponseData(null,ResultCode.IMG_ERROR);
        }
    }





}
