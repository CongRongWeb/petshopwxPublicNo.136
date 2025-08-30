package com.cl.dao;

import com.cl.entity.FuwuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.FuwuView;


/**
 * 服务
 * 
 * @author 
 * @email 
 * @date 2024-05-10 17:33:33
 */
public interface FuwuDao extends BaseMapper<FuwuEntity> {
	
	List<FuwuView> selectListView(@Param("ew") Wrapper<FuwuEntity> wrapper);

	List<FuwuView> selectListView(Pagination page,@Param("ew") Wrapper<FuwuEntity> wrapper);
	
	FuwuView selectView(@Param("ew") Wrapper<FuwuEntity> wrapper);
	

}
