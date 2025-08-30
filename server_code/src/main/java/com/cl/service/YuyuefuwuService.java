package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.YuyuefuwuEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.YuyuefuwuView;


/**
 * 预约服务
 *
 * @author 
 * @email 
 * @date 2024-05-10 17:33:33
 */
public interface YuyuefuwuService extends IService<YuyuefuwuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<YuyuefuwuView> selectListView(Wrapper<YuyuefuwuEntity> wrapper);
   	
   	YuyuefuwuView selectView(@Param("ew") Wrapper<YuyuefuwuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<YuyuefuwuEntity> wrapper);
   	

}

