package com.yegol.museum.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yegol.museum.portal.model.Announcement;
import com.yegol.museum.portal.mapper.AnnouncementMapper;
import com.yegol.museum.portal.service.IAnnouncementService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementMapper, Announcement> implements IAnnouncementService {

    @Autowired
    private AnnouncementMapper announcementMapper;
    @Override
    public PageInfo<Announcement> getAnnouncement(
            Integer pageNum,Integer pageSize
    ) {
        QueryWrapper<Announcement> query=new QueryWrapper<>();
        PageHelper.startPage(pageNum,pageSize);
        //2.设置查询announcement的条件
        List<Announcement> list = announcementMapper.selectList(query);
        log.debug("找到的公告:{}",list.size());

        return new PageInfo<Announcement>(list);
    }
}
