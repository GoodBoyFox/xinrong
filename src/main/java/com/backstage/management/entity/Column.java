package com.backstage.management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


/**
 * @ProjectName: Zhixiang
 * @Package: com.doctor.app.entity
 * @ClassName: Column
 * @Author: gwl
 * @Description: 栏目
 * @Date: 2020/11/9 19:25
 * @Version: 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Column {

    private Integer id;
    private String c_name;
    private Integer level;
    private String type;
    private Integer del;
    private Integer fid;

    private List<Column> list;

}
