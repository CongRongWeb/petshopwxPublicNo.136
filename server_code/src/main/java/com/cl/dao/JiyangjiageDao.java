package com.cl.dao;

import com.cl.entity.JiyangjiageEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.JiyangjiageView;


/**
 * 寄养价格
 * 
 * @author 
 * @email 
 * @date 2024-05-10 17:33:34
 */
public interface JiyangjiageDao extends BaseMapper<JiyangjiageEntity> {
	
	List<JiyangjiageView> selectListView(@Param("ew") Wrapper<JiyangjiageEntity> wrapper);

	List<JiyangjiageView> selectListView(Pagination page,@Param("ew") Wrapper<JiyangjiageEntity> wrapper);
	
	JiyangjiageView selectView(@Param("ew") Wrapper<JiyangjiageEntity> wrapper);
	

}
