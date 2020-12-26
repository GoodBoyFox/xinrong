package com.backstage.management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: Zhixiang
 * @Package: com.doctor.app.entity
 * @ClassName: Content
 * @Author: gwl
 * @Description:  内容
 * @Date: 2020/11/9 19:27
 * @Version: 1.0
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Content {

    private Integer id;
    private String title;  //标题
    private String column_id;  //l栏目ID
    private String column_name;  //栏目名称
    private String views;  //图片集url
    private String releasedate;  //发布时间
    private String num;  //浏览量
    private String abbreviation_url;  //缩略图
    private String details;  //内容详情
    private String atlas; // 留言内容
    private String name; //留言姓名
    private String phone; //留言手机号
    private Integer del;  //删除

    private Integer type;
}
