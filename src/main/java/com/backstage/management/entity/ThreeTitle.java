package com.backstage.management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Ywj
 * @version 1.0
 * @date 2021/1/25 21:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThreeTitle {

    private Integer id;
    private Integer fid;
    private String three_name;
    private Integer del;

    private List<Invest> investList;

}
