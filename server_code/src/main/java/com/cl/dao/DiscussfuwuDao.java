package com.cl.dao;

import com.cl.entity.DiscussfuwuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscussfuwuView;


/**
 * 服务评论表
 * 
 * @author 
 * @email 
 * @date 2024-05-10 17:33:34
 */
public interface DiscussfuwuDao extends BaseMapper<DiscussfuwuEntity> {
	
	List<DiscussfuwuView> selectListView(@Param("ew") Wrapper<DiscussfuwuEntity> wrapper);

	List<DiscussfuwuView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussfuwuEntity> wrapper);
	
	DiscussfuwuView selectView(@Param("ew") Wrapper<DiscussfuwuEntity> wrapper);
	

}
