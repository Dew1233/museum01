package com.yegol.museum.portal.service;

import com.github.pagehelper.PageInfo;
import com.yegol.museum.portal.model.Announcement;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author com.yegol
 * @since 2021-04-14
 */
public interface IAnnouncementService extends IService<Announcement> {

    PageInfo<Announcement> getAnnouncement(Integer pageNum, Integer pageSize);
}
