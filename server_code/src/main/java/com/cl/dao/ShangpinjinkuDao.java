package com.cl.dao;

import com.cl.entity.ShangpinjinkuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ShangpinjinkuView;


/**
 * 商品进库
 * 
 * @author 
 * @email 
 * @date 2024-05-10 17:33:33
 */
public interface ShangpinjinkuDao extends BaseMapper<ShangpinjinkuEntity> {
	
	List<ShangpinjinkuView> selectListView(@Param("ew") Wrapper<ShangpinjinkuEntity> wrapper);

	List<ShangpinjinkuView> selectListView(Pagination page,@Param("ew") Wrapper<ShangpinjinkuEntity> wrapper);
	
	ShangpinjinkuView selectView(@Param("ew") Wrapper<ShangpinjinkuEntity> wrapper);
	

}
