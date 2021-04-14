package com.yegol.museum.portal.mapper;

import com.yegol.museum.portal.model.Permission;
import com.yegol.museum.portal.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* <p>
    *  Mapper 接口
    * </p>
*
* @author com.yegol
* @since 2021-04-14
*/
    @Repository
    public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where username=#{username}")
    User findUserUserName(String username);

    //根据用户id查询所有权限
    @Select("select" +
            " p.id , p.name" +
            " from user u" +
            " left join user_role ur on u.id=ur.user_id" +
            " left join role r   on r.id=ur.role_id" +
            " left join role_permission rp on r.id=rp.role_id" +
            " left join permission p on p.id=rp.permission_id" +
            " where u.id=#{id}")
    List<Permission> findUserPermissionsById(Integer id);
}
