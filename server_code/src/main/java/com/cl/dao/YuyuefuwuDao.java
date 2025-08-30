package com.cl.dao;

import com.cl.entity.YuyuefuwuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.YuyuefuwuView;


/**
 * 预约服务
 * 
 * @author 
 * @email 
 * @date 2024-05-10 17:33:33
 */
public interface YuyuefuwuDao extends BaseMapper<YuyuefuwuEntity> {
	
	List<YuyuefuwuView> selectListView(@Param("ew") Wrapper<YuyuefuwuEntity> wrapper);

	List<YuyuefuwuView> selectListView(Pagination page,@Param("ew") Wrapper<YuyuefuwuEntity> wrapper);
	
	YuyuefuwuView selectView(@Param("ew") Wrapper<YuyuefuwuEntity> wrapper);
	

}
