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


import com.cl.dao.YuyuefuwuDao;
import com.cl.entity.YuyuefuwuEntity;
import com.cl.service.YuyuefuwuService;
import com.cl.entity.view.YuyuefuwuView;

@Service("yuyuefuwuService")
public class YuyuefuwuServiceImpl extends ServiceImpl<YuyuefuwuDao, YuyuefuwuEntity> implements YuyuefuwuService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<YuyuefuwuEntity> page = this.selectPage(
                new Query<YuyuefuwuEntity>(params).getPage(),
                new EntityWrapper<YuyuefuwuEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<YuyuefuwuEntity> wrapper) {
		  Page<YuyuefuwuView> page =new Query<YuyuefuwuView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<YuyuefuwuView> selectListView(Wrapper<YuyuefuwuEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public YuyuefuwuView selectView(Wrapper<YuyuefuwuEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
