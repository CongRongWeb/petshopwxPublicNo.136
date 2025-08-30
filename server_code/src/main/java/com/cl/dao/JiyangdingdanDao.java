package com.cl.dao;

import com.cl.entity.JiyangdingdanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.JiyangdingdanView;


/**
 * 寄养订单
 * 
 * @author 
 * @email 
 * @date 2024-05-10 17:33:33
 */
public interface JiyangdingdanDao extends BaseMapper<JiyangdingdanEntity> {
	
	List<JiyangdingdanView> selectListView(@Param("ew") Wrapper<JiyangdingdanEntity> wrapper);

	List<JiyangdingdanView> selectListView(Pagination page,@Param("ew") Wrapper<JiyangdingdanEntity> wrapper);
	
	JiyangdingdanView selectView(@Param("ew") Wrapper<JiyangdingdanEntity> wrapper);
	

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<JiyangdingdanEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<JiyangdingdanEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<JiyangdingdanEntity> wrapper);



}
