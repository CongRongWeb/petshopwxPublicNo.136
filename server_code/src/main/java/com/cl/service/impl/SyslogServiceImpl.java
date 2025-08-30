package com.cl.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cl.utils.PageUtils;
import com.cl.utils.Query;


import com.cl.dao.SyslogDao;
import com.cl.entity.SyslogEntity;
import com.cl.service.SyslogService;
import com.cl.entity.view.SyslogView;

@Service("syslogService")
public class SyslogServiceImpl extends ServiceImpl<SyslogDao, SyslogEntity> implements SyslogService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SyslogEntity> page = this.selectPage(
                new Query<SyslogEntity>(params).getPage(),
                new EntityWrapper<SyslogEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<SyslogEntity> wrapper) {
		  Page<SyslogView> page =new Query<SyslogView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<SyslogView> selectListView(Wrapper<SyslogEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public SyslogView selectView(Wrapper<SyslogEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
