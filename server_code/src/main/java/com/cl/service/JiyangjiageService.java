package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.JiyangjiageEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.JiyangjiageView;


/**
 * 寄养价格
 *
 * @author 
 * @email 
 * @date 2024-05-10 17:33:34
 */
public interface JiyangjiageService extends IService<JiyangjiageEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<JiyangjiageView> selectListView(Wrapper<JiyangjiageEntity> wrapper);
   	
   	JiyangjiageView selectView(@Param("ew") Wrapper<JiyangjiageEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<JiyangjiageEntity> wrapper);
   	

}

