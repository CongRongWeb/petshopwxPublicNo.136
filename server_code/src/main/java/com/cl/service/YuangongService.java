package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.YuangongEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.YuangongView;


/**
 * 员工
 *
 * @author 
 * @email 
 * @date 2024-05-10 17:33:32
 */
public interface YuangongService extends IService<YuangongEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<YuangongView> selectListView(Wrapper<YuangongEntity> wrapper);
   	
   	YuangongView selectView(@Param("ew") Wrapper<YuangongEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<YuangongEntity> wrapper);
   	

}

