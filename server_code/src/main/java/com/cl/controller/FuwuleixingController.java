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

import com.cl.entity.FuwuleixingEntity;
import com.cl.entity.view.FuwuleixingView;

import com.cl.service.FuwuleixingService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 服务类型
 * 后端接口
 * @author 
 * @email 
 * @date 2024-05-10 17:33:33
 */
@RestController
@RequestMapping("/fuwuleixing")
public class FuwuleixingController {
    @Autowired
    private FuwuleixingService fuwuleixingService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,FuwuleixingEntity fuwuleixing,
		HttpServletRequest request){
        EntityWrapper<FuwuleixingEntity> ew = new EntityWrapper<FuwuleixingEntity>();

		PageUtils page = fuwuleixingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, fuwuleixing), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,FuwuleixingEntity fuwuleixing, 
		HttpServletRequest request){
        EntityWrapper<FuwuleixingEntity> ew = new EntityWrapper<FuwuleixingEntity>();

		PageUtils page = fuwuleixingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, fuwuleixing), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( FuwuleixingEntity fuwuleixing){
       	EntityWrapper<FuwuleixingEntity> ew = new EntityWrapper<FuwuleixingEntity>();
      	ew.allEq(MPUtil.allEQMapPre( fuwuleixing, "fuwuleixing")); 
        return R.ok().put("data", fuwuleixingService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(FuwuleixingEntity fuwuleixing){
        EntityWrapper< FuwuleixingEntity> ew = new EntityWrapper< FuwuleixingEntity>();
 		ew.allEq(MPUtil.allEQMapPre( fuwuleixing, "fuwuleixing")); 
		FuwuleixingView fuwuleixingView =  fuwuleixingService.selectView(ew);
		return R.ok("查询服务类型成功").put("data", fuwuleixingView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        FuwuleixingEntity fuwuleixing = fuwuleixingService.selectById(id);
		fuwuleixing = fuwuleixingService.selectView(new EntityWrapper<FuwuleixingEntity>().eq("id", id));
        return R.ok().put("data", fuwuleixing);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        FuwuleixingEntity fuwuleixing = fuwuleixingService.selectById(id);
		fuwuleixing = fuwuleixingService.selectView(new EntityWrapper<FuwuleixingEntity>().eq("id", id));
        return R.ok().put("data", fuwuleixing);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增服务类型")
    public R save(@RequestBody FuwuleixingEntity fuwuleixing, HttpServletRequest request){
        if(fuwuleixingService.selectCount(new EntityWrapper<FuwuleixingEntity>().eq("leixing", fuwuleixing.getLeixing()))>0) {
            return R.error("类型已存在");
        }
    	fuwuleixing.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(fuwuleixing);
        fuwuleixingService.insert(fuwuleixing);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增服务类型")
    @RequestMapping("/add")
    public R add(@RequestBody FuwuleixingEntity fuwuleixing, HttpServletRequest request){
        if(fuwuleixingService.selectCount(new EntityWrapper<FuwuleixingEntity>().eq("leixing", fuwuleixing.getLeixing()))>0) {
            return R.error("类型已存在");
        }
    	fuwuleixing.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(fuwuleixing);
        fuwuleixingService.insert(fuwuleixing);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改服务类型")
    public R update(@RequestBody FuwuleixingEntity fuwuleixing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(fuwuleixing);
        fuwuleixingService.updateById(fuwuleixing);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除服务类型")
    public R delete(@RequestBody Long[] ids){
        fuwuleixingService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	








}
