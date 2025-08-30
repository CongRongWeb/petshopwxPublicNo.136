package com.cl.dao;

import com.cl.entity.FuwuleixingEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.FuwuleixingView;


/**
 * 服务类型
 * 
 * @author 
 * @email 
 * @date 2024-05-10 17:33:33
 */
public interface FuwuleixingDao extends BaseMapper<FuwuleixingEntity> {
	
	List<FuwuleixingView> selectListView(@Param("ew") Wrapper<FuwuleixingEntity> wrapper);

	List<FuwuleixingView> selectListView(Pagination page,@Param("ew") Wrapper<FuwuleixingEntity> wrapper);
	
	FuwuleixingView selectView(@Param("ew") Wrapper<FuwuleixingEntity> wrapper);
	

}
