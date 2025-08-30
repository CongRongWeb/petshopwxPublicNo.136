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


import com.cl.dao.ShangpinDao;
import com.cl.entity.ShangpinEntity;
import com.cl.service.ShangpinService;
import com.cl.entity.view.ShangpinView;

@Service("shangpinService")
public class ShangpinServiceImpl extends ServiceImpl<ShangpinDao, ShangpinEntity> implements ShangpinService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ShangpinEntity> page = this.selectPage(
                new Query<ShangpinEntity>(params).getPage(),
                new EntityWrapper<ShangpinEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ShangpinEntity> wrapper) {
		  Page<ShangpinView> page =new Query<ShangpinView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<ShangpinView> selectListView(Wrapper<ShangpinEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ShangpinView selectView(Wrapper<ShangpinEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
