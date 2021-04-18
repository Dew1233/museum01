package com.yegol.museum.portal.controller;


import com.github.pagehelper.PageInfo;
import com.yegol.museum.portal.model.Announcement;
import com.yegol.museum.portal.service.IAnnouncementService;
import com.yegol.museum.portal.service.ICategoryService;
import com.yegol.museum.portal.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author com.yegol
 * @since 2021-04-14
 */
@RestController
@RequestMapping("/v1/announcement")
public class AnnouncementController {

    @Autowired
    private IAnnouncementService announcementService;
    @GetMapping("")
    public R<PageInfo<Announcement>> anno(
            Integer pageNum
    ){
        Integer pageSize=8;
        PageInfo<Announcement> pageInfo = announcementService.getAnnouncement(pageNum,pageSize);
        return R.ok(pageInfo);
    }
}
