package com.backstage.management.service;



import com.backstage.management.entity.User;

import java.util.List;

/**
 * @ProjectName: app
 * @Package: com.doctor.app.service
 * @ClassName: UserService
 * @Author: ywj
 * @Description:
 * @Date: 2020/11/9 19:02
 */
public interface UserService {

    boolean userLogin(String user_name, String user_pwd);

    int insertUser(User user);

    List<User> selectAllUser();

    User selectUserById(Integer id);

    int updateUserSql(User user);

    int deleteUserSql(Integer id);
}
