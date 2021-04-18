package com.yegol.museum.portal.controller;


import com.yegol.museum.portal.model.Category;
import com.yegol.museum.portal.service.ICategoryService;
import com.yegol.museum.portal.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author com.yegol
 * @since 2021-04-14
 */
@RestController
@RequestMapping("/v1/category")
public class CategoryController {


    @Autowired
    private ICategoryService categoryService;
    @GetMapping("")
    public R<List<Category>> categs(){
        List<Category> categs = categoryService.getCategs();
        return R.ok(categs);
    }
}
