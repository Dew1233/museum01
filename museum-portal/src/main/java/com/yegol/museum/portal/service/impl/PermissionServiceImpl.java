package com.yegol.museum.portal.service.impl;

import com.yegol.museum.portal.model.Permission;
import com.yegol.museum.portal.mapper.PermissionMapper;
import com.yegol.museum.portal.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author com.yegol
 * @since 2021-04-14
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
