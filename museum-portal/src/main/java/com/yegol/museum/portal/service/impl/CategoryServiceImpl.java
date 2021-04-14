package com.yegol.museum.portal.service.impl;

import com.yegol.museum.portal.model.Category;
import com.yegol.museum.portal.mapper.CategoryMapper;
import com.yegol.museum.portal.service.ICategoryService;
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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

}
