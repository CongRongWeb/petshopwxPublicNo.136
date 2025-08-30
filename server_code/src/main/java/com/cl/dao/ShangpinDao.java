package com.cl.dao;

import com.cl.entity.ShangpinEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ShangpinView;


/**
 * 商品
 * 
 * @author 
 * @email 
 * @date 2024-05-10 17:33:32
 */
public interface ShangpinDao extends BaseMapper<ShangpinEntity> {
	
	List<ShangpinView> selectListView(@Param("ew") Wrapper<ShangpinEntity> wrapper);

	List<ShangpinView> selectListView(Pagination page,@Param("ew") Wrapper<ShangpinEntity> wrapper);
	
	ShangpinView selectView(@Param("ew") Wrapper<ShangpinEntity> wrapper);
	

}
