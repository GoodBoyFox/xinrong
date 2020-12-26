package com.backstage.management.dao;


import com.backstage.management.entity.Column;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ProjectName: Zhixiang
 * @Package: com.doctor.app.dao
 * @ClassName: ArticleDao
 * @Author: gwl
 * @Description:  内容和文章
 * @Date: 2020/11/9 20:23
 * @Version: 1.0
 */
@Mapper
@Component
public interface ArticleDao {

     @Select("select * from `column` where del =1" )
     List<Column> finaAllColumn();

     @Select("select * from `column` where id=#{id} and del =1")
     Column findColumnBuId(Integer id);

     @Update("update `column` set c_name=#{c_name},level=#{level},type=#{type},fid=#{fid} where id=#{id}")
     int updateColumn(Column column);

     @Insert("insert into `column` values(null,#{c_name},#{level},#{type},1,#{fid})")
     int insertColumn(Column column);

     @Update("update `column` set del=2 where id=#{id}")
     int deleteColumn(Integer id);

     @Select("select * from `column` where level=1 and del =1")
     List<Column> getAllOneColumn();
}
