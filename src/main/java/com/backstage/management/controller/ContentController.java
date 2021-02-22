package com.backstage.management.controller;

import com.alibaba.fastjson.JSON;
import com.backstage.management.entity.Administrative;
import com.backstage.management.entity.Content;
import com.backstage.management.entity.Invest;
import com.backstage.management.entity.ThreeTitle;
import com.backstage.management.service.ContentService;
import com.backstage.management.util.IPUtil;
import com.backstage.management.util.Page;
import com.backstage.management.util.ResultCode;
import com.backstage.management.util.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;

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
@Slf4j
public class ContentController {

    @Autowired
    private ContentService contentService;


    /**
     * 插入内容
     */
    @RequestMapping(value = "/setContent",method = RequestMethod.POST)
    public JSON setContent(@RequestBody Content content){
        System.out.println("content》》》"+content);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
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
     * 回显内容 根据ID 查询  不更新浏览量
     */
    @RequestMapping(value = "/getContentById",method = RequestMethod.GET)
    public JSON getContentById(@RequestParam("id") Integer id){

        Content content = contentService.selectContentById(id);
        if (content!=null){
            return ResultData.getResponseData(content,ResultCode.QUERY_SUCCESS); //503
        }
        return ResultData.getResponseData(null,ResultCode.QUERY_SUCCESS); //503
    }


    /**
     * 根据ID 查询内容  更新浏览量
     */
    @RequestMapping(value = "/getContentByIdUpdateBrowse",method = RequestMethod.GET)
    public JSON getContentByIdUpdateBrowse(@RequestParam("id") Integer id){

        Content content = contentService.selectContentByIdUpdateBrowse(id);
        if (content!=null){
            return ResultData.getResponseData(content,ResultCode.QUERY_SUCCESS); //503
        }
        return ResultData.getResponseData(null,ResultCode.QUERY_ERROR); //503
    }



    //------------------------------------------------------------小程序 ----  首页内容---------------------------------------------------
    /**
     * 根据栏目查找相应内容
     */
    @RequestMapping(value = "/getContentByColumnId",method = RequestMethod.GET)
    public JSON getContentByColumnId(@RequestParam("column_id") Integer column_id,@RequestParam("CurrentPage") Integer CurrentPage){
        Page<Content> list = contentService.selectContentByColumnId(column_id,CurrentPage);
        if (list!=null){
            return ResultData.getResponseData(list,ResultCode.QUERY_SUCCESS);
        }
        return ResultData.getResponseData(null,ResultCode.QUERY_ERROR);
    }


    /**
    *  查询留言列表
    */
    @RequestMapping(value = "/getAllLiuYan",method = RequestMethod.GET)
    public JSON getAllLiuYan(){
        Map<String,List<Content>> map = contentService.getAllLiuYanSql();
        if (map!=null){
            return ResultData.getResponseData(map,ResultCode.QUERY_SUCCESS);
        }
        return ResultData.getResponseData(null,ResultCode.QUERY_ERROR);
    }

    /**
    * @FunctionName:
    * @author: Ywj
    * @Param: 留言功能
    * @Return:
    */
    @RequestMapping(value = "/leaveSession",method = RequestMethod.POST)
    public JSON leaveSession(@RequestBody Content content){
        System.out.println("content>>>"+content);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        content.setReleasedate(sdf.format(new Date()));
        //新增到数据库
        int i =  contentService.insertContent(content);
        if (i>0){
            return ResultData.getResponseData("留言成功",ResultCode.INSERT_SUCCESS);
        }
        return ResultData.getResponseData("留言失败",ResultCode.INSERT_ERROR);
    }

    @GetMapping(value = "/getLeaveByIp/{leaveIp}")
    public JSON getLeaveByIp(@PathVariable("leaveIp") String leaveIp){

        List<Content> leaveList = contentService.selectLeaveByIp(leaveIp);
        if (leaveList.size()>0){
            return ResultData.getResponseData(leaveList,ResultCode.QUERY_SUCCESS);
        }
        return ResultData.getResponseData("暂无留言记录",ResultCode.QUERY_ERROR);
    }

    /** 
    * @FunctionName: 获取ip
    * @author: Ywj
    * @Param: 
    * @Return: 
    */
    @RequestMapping(value = "/getIp",method = RequestMethod.GET)
    public JSON getIp(HttpServletRequest request){
        String replace = UUID.randomUUID().toString().replace("-", "");
        String substring = replace.substring(0, 16);
        String userIpAddr = getRealIpAddr(request)+"-"+substring;
        log.info("intranetIp》》》"+userIpAddr);
        if (userIpAddr!=""&&userIpAddr!=null){
            System.out.println("userIpAddr》》"+userIpAddr);
            return ResultData.getResponseData(userIpAddr,ResultCode.QUERY_SUCCESS);
        }
        return ResultData.getResponseData("获取IP失败",ResultCode.QUERY_ERROR);
    }

    public static String getRealIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }




    /** 
    * @FunctionName: 获取文章总数 总浏览量 总得留言数
    * @author: Ywj
    * @Param: 
    * @Return: 
    */
    @RequestMapping(value = "/getContentNum")
    public JSON getContentNum(){

        Map<String,Integer> map = contentService.getContentNum();

        if (map!=null){
            return ResultData.getResponseData(map,ResultCode.QUERY_SUCCESS);
        }
        return ResultData.getResponseData("获取IP失败",ResultCode.QUERY_ERROR);
    }


    /*----------------------------- 投资指南 -------------------------------*/
    /** 
    * @FunctionName: 新增指南内容
    * @author: Ywj
    * @Param: 
    * @Return: 
    */
    @RequestMapping(value = "/addInvestInfo",method = RequestMethod.POST)
    public JSON addInvestInfo(@RequestBody Invest invest){
        invest.setCreation_time(new Date());
        //新增到数据库
        int i =  contentService.insertInvest(invest);
        if (i>0){
            return ResultData.getResponseData("新增成功",ResultCode.INSERT_SUCCESS);
        }
        return ResultData.getResponseData("新增失败",ResultCode.INSERT_ERROR);
    }

    /** 
    * @FunctionName: 查询投资指南内容
    * @author: Ywj
    * @Param: 
    * @Return: 
    */
    @RequestMapping(value = "/getInvestInfo",method = RequestMethod.GET)
    public JSON getInvestInfo(){
        //新增到数据库
        List<ThreeTitle> threeTitle =  contentService.selectInvest();
        if (threeTitle.size()>0){
            return ResultData.getResponseData(threeTitle,ResultCode.QUERY_SUCCESS);
        }
        return ResultData.getResponseData("暂无数据",ResultCode.QUERY_ERROR);
    }

    /** 
    * @FunctionName: 查询三级名称
    * @author: Ywj
    * @Param: 
    * @Return: 
    */
    @RequestMapping(value = "/getThreeTitle",method = RequestMethod.GET)
    public JSON getThreeTitle(){
        //新增到数据库
        List<ThreeTitle> threeTitle =  contentService.selectThreeTitle();
        if (threeTitle.size()>0){
            return ResultData.getResponseData(threeTitle,ResultCode.QUERY_SUCCESS);
        }
        return ResultData.getResponseData("暂无数据",ResultCode.QUERY_ERROR);
    }

    /**
    * @FunctionName: 修改指南内容
    * @author: Ywj
    * @Param:
    * @Return:
    */
    @RequestMapping(value = "/updateInvestInfo",method = RequestMethod.POST)
    public JSON updateInvestInfo(@RequestBody Invest invest){
        //新增到数据库
        int i =  contentService.updateInvestInfoSql(invest);
        if (i>0){
            return ResultData.getResponseData(i,ResultCode.UPDATE_SUCCESS);
        }
        return ResultData.getResponseData(null,ResultCode.UPDATE_ERROR);
    }
    
    /** 
    * @FunctionName: 删除指南内容
    * @author: Ywj
    * @Param: 
    * @Return: 
    */
    @RequestMapping(value = "/deleteInvestInfo",method = RequestMethod.GET)
    public JSON deleteInvestInfo(@RequestParam("id") Integer id){
        //删除指南
        int i =  contentService.deleteInvestInfoSql(id);
        if (i>0){
            return ResultData.getResponseData(i,ResultCode.DELETE_SUCCESS);
        }
        return ResultData.getResponseData("暂无数据",ResultCode.DELETE_ERROR);
    }

    /** 
    * @FunctionName: 修改三级标题
    * @author: Ywj
    * @Param: 
    * @Return: 
    */
    @RequestMapping(value = "/updateThreeTitle",method = RequestMethod.POST)
    public JSON updateThreeTitle(@RequestBody ThreeTitle threeTitle){
        //修改三级标题
        int i =  contentService.updateThreeTitle(threeTitle);
        if (i>0){
            return ResultData.getResponseData(i,ResultCode.UPDATE_SUCCESS);
        }
        return ResultData.getResponseData(null,ResultCode.UPDATE_ERROR);

    }

    /** 
    * @FunctionName: 删除三级标题
    * @author: Ywj
    * @Param: 
    * @Return: 
    */
    @RequestMapping(value = "/deleteThreeTitle",method = RequestMethod.GET)
    public JSON deleteThreeTitle(@RequestParam("id") Integer id){
        //删除三级标题
        int i =  contentService.deleteThreeTitleSql(id);
        if (i>0){
            return ResultData.getResponseData(i,ResultCode.DELETE_SUCCESS);
        }
        return ResultData.getResponseData(null,ResultCode.DELETE_ERROR);
    }

    /*-------------------------------- 行政服务 ------------------------------------*/
    /**
     * @FunctionName: 新增指南内容
     * @author: Ywj
     * @Param:
     * @Return:
     */
    @RequestMapping(value = "/addAdministrative",method = RequestMethod.POST)
    public JSON addAdministrative(@RequestBody Administrative administrative){
        administrative.setCreate_time(new Date());
        //新增到数据库
        int i =  contentService.insertAdministrative(administrative);
        if (i>0){
            return ResultData.getResponseData("新增成功",ResultCode.INSERT_SUCCESS);
        }
        return ResultData.getResponseData("新增失败",ResultCode.INSERT_ERROR);
    }

    /**
    * @FunctionName: 更新行政内容
    * @author: Ywj
    * @Param:
    * @Return:
    */
    @RequestMapping(value = "/updateAdministrative",method = RequestMethod.POST)
    public JSON updateAdministrative(@RequestBody Administrative administrative){
        //修改三级标题
        administrative.setCreate_time(new Date());
        int i =  contentService.updateAdministrativeSql(administrative);
        if (i>0){
            return ResultData.getResponseData(i,ResultCode.UPDATE_SUCCESS);
        }
        return ResultData.getResponseData(null,ResultCode.UPDATE_ERROR);
    }
    /** 
    * @FunctionName: 删除行政内容
    * @author: Ywj
    * @Param: 
    * @Return: 
    */
    @RequestMapping(value = "/deleteAdministrative",method = RequestMethod.GET)
    public JSON deleteAdministrative(@RequestParam("id") Integer id){
        //删除三级标题
        int i =  contentService.deleteAdministrativeSql(id);
        if (i>0){
            return ResultData.getResponseData(i,ResultCode.DELETE_SUCCESS);
        }
        return ResultData.getResponseData(null,ResultCode.DELETE_ERROR);
    }
    
    /** 
    * @FunctionName: 分页查询所有行政内容
    * @author: Ywj
    * @Param: 
    * @Return: 
    */
    @RequestMapping(value = "/getAllAdministrative",method = RequestMethod.GET)
    public JSON getAllAdministrative(@RequestParam("CurrentPage") Integer CurrentPage){

        Page<Administrative> list = contentService.selectAllAdministrative(CurrentPage);

        if (list!=null){
            return ResultData.getResponseData(list,ResultCode.QUERY_SUCCESS);
        }
        return ResultData.getResponseData(null,ResultCode.QUERY_ERROR);
    }

    /**
     * @FunctionName: 根据id或者栏目id查询行政内容
     * @author: Ywj
     * @Param:
     * @Return:
     */
    @RequestMapping(value = "/getAdministrativeById",method = RequestMethod.GET)
    public JSON getAdministrativeById(@RequestParam("id") Integer id,@RequestParam("column_id") Integer column_id){

        List<Administrative> list = contentService.selectAdministrativeById(id,column_id);

        if (list!=null){
            return ResultData.getResponseData(list,ResultCode.QUERY_SUCCESS);
        }
        return ResultData.getResponseData(null,ResultCode.QUERY_ERROR);
    }
}

