package com.backstage.management.dao;


import com.backstage.management.entity.Basic;
import com.backstage.management.entity.Rotation;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ProjectName: Zhixiang
 * @Package: com.doctor.app.dao
 * @ClassName: Common
 * @Author: gwl
 * @Description:
 * @Date: 2020/11/9 19:58
 * @Version: 1.0
 */
@Mapper
@Component
public interface CommonDao {

    @Select("select * from basic where id =2")
    Basic findAllBasic();

    @Update("update basic set corporate_name=#{corporate_name},company_url=#{company_url},title=#{title},copyright=#{copyright},record_no=#{record_no},phones=#{phones} where id=2")
    int updateBasic(Basic basic);

    @Select("select * from rotation where del=1")
    List<Rotation> findAllRotation();

    @Insert("insert into rotation values(null,#{name},#{url},#{remarks},#{type},1)")
    int insertRotation(Rotation rotation);

    @Delete("delete from rotation where id=#{id}")
    int ddelRotation(Integer id);

    @Update("update rotation set name=#{name},url=#{url},remarks=#{remarks},type=#{type} where id=#{id}")
    int updateRotation(Rotation rotation);

    @Select("select * from rotation where id = #{id}")
    Rotation getRotationById(Integer id);

    @Select("select * from rotation where type=#{type} and del=1 ")
    List<Rotation> findRotationByType(Integer type);

    @Delete("delete from basic where id = #{id}")
    int deleteBasicById(Integer id);

    @Insert("insert into basic values(null,#{corporate_name},#{company_url},#{title},#{copyright},#{record_no},#{phones},1)")
    int insertBasic(Basic basic1);

    @Update("update basic set corporate_name=#{corporate_name},company_url=#{company_url},title=#{title},copyright=#{copyright},record_no=#{record_no},phones=#{phones} where id=#{id}")
    int updateBasic2(Basic basic1);

    @Select("select id,corporate_name,company_url,del from basic where id !=2")
    List<Basic> findAllBasicList();
}
