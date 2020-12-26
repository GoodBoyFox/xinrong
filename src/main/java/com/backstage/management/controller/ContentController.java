package com.backstage.management.controller;

import com.alibaba.fastjson.JSON;
import com.backstage.management.entity.Content;
import com.backstage.management.service.ContentService;
import com.backstage.management.util.Page;
import com.backstage.management.util.ResultCode;
import com.backstage.management.util.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName: app
 * @Package: com.doctor.app.controller
 * @ClassName: ContentController
 * @Author: ywj
 * @Description:
 * @Date: 2020/11/9 20:29
 */
@CrossOrigin
@RestController
public class ContentController {

    @Autowired
    private ContentService contentService;


    /**
     * 插入内容
     */
    @RequestMapping(value = "/setContent",method = RequestMethod.POST)
    public JSON setContent(@RequestBody Content content){
        System.out.println("content》》》"+content);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        content.setReleasedate(format.format(new Date()));
        int i =  contentService.insertContent(content);
        if (i>0){
            return ResultData.getResponseData(i, ResultCode.INSERT_SUCCESS); //503
        }
        return ResultData.getResponseData(i,ResultCode.INSERT_ERROR); //
    }

    /**
     * 更新内容
     */
    @RequestMapping(value = "/updateContent",method = RequestMethod.POST)
    public JSON updateContent(@RequestBody Content content){
        System.out.println("content》》》"+content);
        int i =  contentService.updateContentSql(content);

        if (i>0){
            return ResultData.getResponseData(i,ResultCode.INSERT_SUCCESS); //503
        }
        return ResultData.getResponseData(i,ResultCode.INSERT_ERROR); //
    }

    /**
     * 删除内容
     */
    @RequestMapping(value = "/deleteContent",method = RequestMethod.GET)
    public JSON deleteContent(@RequestParam("id") Integer id){

        int i = contentService.deleteContent(id);
        if (i>0){
            return ResultData.getResponseData(i,ResultCode.DELETE_SUCCESS); //503
        }
        return ResultData.getResponseData(i,ResultCode.DELETE_ERROR); //503
    }

    /**
     * 查询全部内容
     */
    @RequestMapping(value = "/getAllContent",method = RequestMethod.GET)
    public JSON getAllContent(@RequestParam("CurrentPage") Integer CurrentPage,@RequestParam("all") Integer all){

        Page<Content> list = contentService.selectAllContent(CurrentPage,all);

        if (list!=null){
            return ResultData.getResponseData(list,ResultCode.QUERY_SUCCESS);
        }
        return ResultData.getResponseData(null,ResultCode.QUERY_ERROR);
    }

    /**
     * 回显内容 根据ID 查询
     */
    @RequestMapping(value = "/getContentById",method = RequestMethod.GET)
    public JSON getContentById(@RequestParam("id") Integer id){

        Content content = contentService.selectContentById(id);
        if (content!=null){
            return ResultData.getResponseData(content,ResultCode.DELETE_SUCCESS); //503
        }
        return ResultData.getResponseData(null,ResultCode.DELETE_ERROR); //503
    }


    //------------------------------------------------------------小程序 ----  首页内容---------------------------------------------------
    /**
     * 根据栏目查找相应内容  更新浏览量
     */
    @RequestMapping(value = "/getContentByColumnId",method = RequestMethod.GET)
    public JSON getContentByColumnId(@RequestParam("column_id") Integer column_id){
        List<Content> list = contentService.selectContentByColumnId(column_id);
        if (list!=null){
            return ResultData.getResponseData(list,ResultCode.QUERY_SUCCESS);
        }
        return ResultData.getResponseData(null,ResultCode.QUERY_ERROR);
    }


    /**
    *  查询留言列表
    */
    @RequestMapping(value = "/getAllLiuYan",method = RequestMethod.GET)
    public JSON getAllLiuYan(@RequestParam("CurrentPage") Integer CurrentPage){
        Page<Content> contentList = contentService.getAllLiuYanSql(CurrentPage);
        if (contentList!=null){
            return ResultData.getResponseData(contentList,ResultCode.QUERY_SUCCESS);
        }
        return ResultData.getResponseData(null,ResultCode.QUERY_ERROR);
    }



}
