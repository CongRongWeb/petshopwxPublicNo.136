package com.cl.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.cl.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cl.annotation.IgnoreAuth;
import com.cl.annotation.SysLog;

import com.cl.entity.YuyuefuwuEntity;
import com.cl.entity.view.YuyuefuwuView;

import com.cl.service.YuyuefuwuService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 预约服务
 * 后端接口
 * @author 
 * @email 
 * @date 2024-05-10 17:33:33
 */
@RestController
@RequestMapping("/yuyuefuwu")
public class YuyuefuwuController {
    @Autowired
    private YuyuefuwuService yuyuefuwuService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,YuyuefuwuEntity yuyuefuwu,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("huiyuan")) {
			yuyuefuwu.setHuiyuanzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<YuyuefuwuEntity> ew = new EntityWrapper<YuyuefuwuEntity>();

		PageUtils page = yuyuefuwuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yuyuefuwu), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,YuyuefuwuEntity yuyuefuwu, 
		HttpServletRequest request){
        EntityWrapper<YuyuefuwuEntity> ew = new EntityWrapper<YuyuefuwuEntity>();

		PageUtils page = yuyuefuwuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yuyuefuwu), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( YuyuefuwuEntity yuyuefuwu){
       	EntityWrapper<YuyuefuwuEntity> ew = new EntityWrapper<YuyuefuwuEntity>();
      	ew.allEq(MPUtil.allEQMapPre( yuyuefuwu, "yuyuefuwu")); 
        return R.ok().put("data", yuyuefuwuService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(YuyuefuwuEntity yuyuefuwu){
        EntityWrapper< YuyuefuwuEntity> ew = new EntityWrapper< YuyuefuwuEntity>();
 		ew.allEq(MPUtil.allEQMapPre( yuyuefuwu, "yuyuefuwu")); 
		YuyuefuwuView yuyuefuwuView =  yuyuefuwuService.selectView(ew);
		return R.ok("查询预约服务成功").put("data", yuyuefuwuView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        YuyuefuwuEntity yuyuefuwu = yuyuefuwuService.selectById(id);
		yuyuefuwu = yuyuefuwuService.selectView(new EntityWrapper<YuyuefuwuEntity>().eq("id", id));
        return R.ok().put("data", yuyuefuwu);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        YuyuefuwuEntity yuyuefuwu = yuyuefuwuService.selectById(id);
		yuyuefuwu = yuyuefuwuService.selectView(new EntityWrapper<YuyuefuwuEntity>().eq("id", id));
        return R.ok().put("data", yuyuefuwu);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增预约服务")
    public R save(@RequestBody YuyuefuwuEntity yuyuefuwu, HttpServletRequest request){
    	yuyuefuwu.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(yuyuefuwu);
        yuyuefuwuService.insert(yuyuefuwu);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增预约服务")
    @RequestMapping("/add")
    public R add(@RequestBody YuyuefuwuEntity yuyuefuwu, HttpServletRequest request){
    	yuyuefuwu.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(yuyuefuwu);
        yuyuefuwuService.insert(yuyuefuwu);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改预约服务")
    public R update(@RequestBody YuyuefuwuEntity yuyuefuwu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yuyuefuwu);
        yuyuefuwuService.updateById(yuyuefuwu);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除预约服务")
    public R delete(@RequestBody Long[] ids){
        yuyuefuwuService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	








}
