package com.yegol.museum.portal.service.impl;

import com.yegol.museum.portal.mapper.UserRoleMapper;
import com.yegol.museum.portal.model.Permission;
import com.yegol.museum.portal.model.User;
import com.yegol.museum.portal.mapper.UserMapper;
import com.yegol.museum.portal.model.UserRole;
import com.yegol.museum.portal.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yegol.museum.portal.service.ServiceException;
import com.yegol.museum.portal.vo.RegisterVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author com.yegol
 * @since 2021-04-14
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Override
    public UserDetails getUserDetails(String username) {
        User user = userMapper.findUserUserName(username);
        if(user==null){
            return null;
        }
//        log.debug("密码问题01：{}",user);
        List<Permission> ps = userMapper
                .findUserPermissionsById(user.getId());
        String[] auth = new String[ps.size()];
        int i = 0;
        for(Permission p :ps){
            auth[i++] = p.getName();
        }
        UserDetails u = org.springframework.security.core.userdetails
                .User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(auth)
                .accountLocked(user.getLocked()==1)
                .disabled(user.getEnabled()==0)
                .build();
        return u;
    }

    @Override
    public void registerStudent(RegisterVo registerVo) {
//        1判断要注册的手机号是否可用
        User user = userMapper.findUserUserName(registerVo.getUsername());
        if(user!=null){
            throw ServiceException.unprocesabelEntity("用户名已经被注册");
        }
//        2收集信息,加密密码
        User stu = new User();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        stu.setUsername(registerVo.getUsername());
        stu.setNickname(registerVo.getNickname());
        stu.setPassword("{bcrypt}"+encoder.encode(registerVo.getPassword()));
        stu.setPhone(registerVo.getPhone());
        stu.setCreatetime(LocalDateTime.now());
        stu.setLocked(0);
        stu.setEnabled(1);
        // 3.执行新增学生到user表
        int num=userMapper.insert(stu);
//        log.debug("新增了:{}",num);
        if(num!=1){
            throw ServiceException.busy();
        }
//        4为新增的学生添加唉user_role表的关系
        UserRole userRole = new UserRole();
        userRole.setUserId(stu.getId());
        userRole.setRoleId(2);
        num = userRoleMapper.insert(userRole);
        if(num!=1){
            throw ServiceException.busy();
        }
    }
}
