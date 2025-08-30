package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.JiyangdingdanEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.JiyangdingdanView;


/**
 * 寄养订单
 *
 * @author 
 * @email 
 * @date 2024-05-10 17:33:33
 */
public interface JiyangdingdanService extends IService<JiyangdingdanEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<JiyangdingdanView> selectListView(Wrapper<JiyangdingdanEntity> wrapper);
   	
   	JiyangdingdanView selectView(@Param("ew") Wrapper<JiyangdingdanEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<JiyangdingdanEntity> wrapper);
   	

    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<JiyangdingdanEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<JiyangdingdanEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<JiyangdingdanEntity> wrapper);



}

