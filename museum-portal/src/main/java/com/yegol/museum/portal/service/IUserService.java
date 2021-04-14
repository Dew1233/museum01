package com.yegol.museum.portal.service;

import com.yegol.museum.portal.model.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yegol.museum.portal.vo.RegisterVo;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author com.yegol
 * @since 2021-04-14
 */
public interface IUserService extends IService<User> {

    UserDetails getUserDetails(String username);
    void registerStudent(RegisterVo registerVo);
}
