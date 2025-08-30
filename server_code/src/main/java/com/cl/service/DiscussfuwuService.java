package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.DiscussfuwuEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscussfuwuView;


/**
 * 服务评论表
 *
 * @author 
 * @email 
 * @date 2024-05-10 17:33:34
 */
public interface DiscussfuwuService extends IService<DiscussfuwuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscussfuwuView> selectListView(Wrapper<DiscussfuwuEntity> wrapper);
   	
   	DiscussfuwuView selectView(@Param("ew") Wrapper<DiscussfuwuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussfuwuEntity> wrapper);
   	

}

