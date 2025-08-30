package com.cl.dao;

import com.cl.entity.DiscussshangpinEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscussshangpinView;


/**
 * 商品评论表
 * 
 * @author 
 * @email 
 * @date 2024-05-10 17:33:34
 */
public interface DiscussshangpinDao extends BaseMapper<DiscussshangpinEntity> {
	
	List<DiscussshangpinView> selectListView(@Param("ew") Wrapper<DiscussshangpinEntity> wrapper);

	List<DiscussshangpinView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussshangpinEntity> wrapper);
	
	DiscussshangpinView selectView(@Param("ew") Wrapper<DiscussshangpinEntity> wrapper);
	

}
