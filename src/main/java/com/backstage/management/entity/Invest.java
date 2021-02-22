package com.backstage.management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Ywj
 * @version 1.0
 * @date 2021/1/25 21:16
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invest {

    private Integer id;
    private Integer fid;
    private Integer three_id;
    private String headline;
    private Date creation_time;
    private String creation_time_str;
    private Integer del;
    private String three_level_name;

}
