package com.backstage.management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ProjectName: Zhixiang
 * @Package: com.doctor.app.entity
 * @ClassName: Basic
 * @Author: gwl
 * @Description:  基本设置
 * @Date: 2020/11/9 19:21
 * @Version: 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Basic {

    private Integer id;
    private String corporate_name;
    private String company_url;
    private String title;
    private String copyright;
    private String record_no;
    private String phones;
    private Integer del;
    private List<Basic> basicList;
}
