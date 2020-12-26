package com.backstage.management.controller;

import com.alibaba.fastjson.JSON;
import com.backstage.management.entity.Column;
import com.backstage.management.service.ArticleService;
import com.backstage.management.util.Page;
import com.backstage.management.util.ResultCode;
import com.backstage.management.util.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ProjectName: Zhixiang
 * @Package: com.doctor.app.controller
 * @ClassName: ArticleController
 * @Author: gwl
 * @Description:
 * @Date: 2020/11/9 20:46
 * @Version: 1.0
 */
@CrossOrigin
@RestController
public class ArticleController {

    @Autowired
    ArticleService articleService;


    @GetMapping("/ArticleController/finaAllColumn/{CurrentPage}/{all}")
    public JSON  finaAllColumn(@PathVariable("CurrentPage") Integer CurrentPage,@PathVariable("all") Integer all){
        Page<Column> allColumn = articleService.getAllColumn(CurrentPage,all);
        return ResultData.getResponseData(allColumn, ResultCode.QUERY_SUCCESS);
    }

    @GetMapping("/ArticleController/findColumnBuId/{id}")
    public JSON  findColumnBuId(@PathVariable("id") Integer id){
        Column columnBuId = articleService.getColumnBuId(id);
        return ResultData.getResponseData(columnBuId, ResultCode.QUERY_SUCCESS);
    }

    @RequestMapping(value = "/ArticleController/updateColumn",method = RequestMethod.POST)
    public JSON  updateColumn(@RequestBody Column column){
        int i = articleService.updateColumn(column);
        if (i>0){
            return ResultData.getResponseData("修改成功", ResultCode.UPDATE_SUCCESS);
        }else {
            return ResultData.getResponseData("修改失败", ResultCode.UPDATE_ERROR);
        }
    }

    @RequestMapping(value = "/ArticleController/insertColumn",method = RequestMethod.POST)
    public JSON insertColumn(@RequestBody Column column){
        int i = articleService.insertColumn(column);
        if (i>0){
            return ResultData.getResponseData(i, ResultCode.INSERT_SUCCESS);
        }else {
            return ResultData.getResponseData(null, ResultCode.INSERT_ERROR);
        }
    }

    @GetMapping("/ArticleController/deleteColumn/{id}")
    public JSON deleteColumn(@PathVariable("id") Integer id){
        System.out.println("id》》》》"+id);
        int i = articleService.deleteColumn(id);
        if (i>0){
            return ResultData.getResponseData("删除成功", ResultCode.DELETE_SUCCESS);
        }else {
            return ResultData.getResponseData("删除失败", ResultCode.DELETE_ERROR);
        }
    }

    /**
     * 查询所有一级栏目
     */
    @GetMapping("/ArticleController/findAllOneColumn")
    public JSON  findAllOneColumn(){
        List<Column> columnBuId = articleService.getAllOneColumn();
        return ResultData.getResponseData(columnBuId, ResultCode.QUERY_SUCCESS);
    }


    /**
     * 查询所有一级栏目
     */
    @GetMapping("/ArticleController/findXiaoAllColumn")
    public JSON  findXiaoAllColumn(){
        List<Column> columnBuId = articleService.getXiaoAllColumn();
        return ResultData.getResponseData(columnBuId, ResultCode.QUERY_SUCCESS);
    }



}
