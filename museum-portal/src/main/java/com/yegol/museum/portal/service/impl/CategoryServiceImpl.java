package com.yegol.museum.portal.service.impl;

import com.yegol.museum.portal.model.Category;
import com.yegol.museum.portal.mapper.CategoryMapper;
import com.yegol.museum.portal.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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

    private List<Category> categs = new CopyOnWriteArrayList<>();
    @Override
    public List<Category> getCategs() {
        //先判断tags是不是空,如果是空才需要连接数据库查询
        //           3
        if(categs.isEmpty()){
            //   1   2
            //为了防止多条线程都进行新增操作,这里加锁
            synchronized (categs) {
                //在加锁的情况下判断tags是不是空
                if(categs.isEmpty()) {
                    //如果加锁的情况下还是空,才是真正的第一次请求
                    //利用list()方法向tags赋值
                    categs.addAll(list());
                }
            }
        }
        return categs;
    }
}
