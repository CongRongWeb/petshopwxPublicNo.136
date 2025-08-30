package com.cl.dao;

import com.cl.entity.JiyangrizhiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.JiyangrizhiView;


/**
 * 寄养日志
 * 
 * @author 
 * @email 
 * @date 2024-05-10 17:33:34
 */
public interface JiyangrizhiDao extends BaseMapper<JiyangrizhiEntity> {
	
	List<JiyangrizhiView> selectListView(@Param("ew") Wrapper<JiyangrizhiEntity> wrapper);

	List<JiyangrizhiView> selectListView(Pagination page,@Param("ew") Wrapper<JiyangrizhiEntity> wrapper);
	
	JiyangrizhiView selectView(@Param("ew") Wrapper<JiyangrizhiEntity> wrapper);
	

}
