package com.backstage.management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Ywj
 * @version 1.0
 * @date 2021/1/27 22:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Administrative {

    private Integer id;
    private Integer column_id;
    private String headline;
    private Date create_time;
    private String  pic_url;
    private Integer del;
    private String create_time_str;
    private String url;

}
