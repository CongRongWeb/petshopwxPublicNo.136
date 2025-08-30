package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.FuwuEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.FuwuView;


/**
 * 服务
 *
 * @author 
 * @email 
 * @date 2024-05-10 17:33:33
 */
public interface FuwuService extends IService<FuwuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<FuwuView> selectListView(Wrapper<FuwuEntity> wrapper);
   	
   	FuwuView selectView(@Param("ew") Wrapper<FuwuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<FuwuEntity> wrapper);
   	

}

