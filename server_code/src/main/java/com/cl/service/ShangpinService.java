package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.ShangpinEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ShangpinView;


/**
 * 商品
 *
 * @author 
 * @email 
 * @date 2024-05-10 17:33:32
 */
public interface ShangpinService extends IService<ShangpinEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ShangpinView> selectListView(Wrapper<ShangpinEntity> wrapper);
   	
   	ShangpinView selectView(@Param("ew") Wrapper<ShangpinEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ShangpinEntity> wrapper);
   	

}

