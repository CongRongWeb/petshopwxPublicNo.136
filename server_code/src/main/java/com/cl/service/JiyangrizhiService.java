package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.JiyangrizhiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.JiyangrizhiView;


/**
 * 寄养日志
 *
 * @author 
 * @email 
 * @date 2024-05-10 17:33:34
 */
public interface JiyangrizhiService extends IService<JiyangrizhiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<JiyangrizhiView> selectListView(Wrapper<JiyangrizhiEntity> wrapper);
   	
   	JiyangrizhiView selectView(@Param("ew") Wrapper<JiyangrizhiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<JiyangrizhiEntity> wrapper);
   	

}

