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

import com.cl.entity.FuwuEntity;
import com.cl.entity.view.FuwuView;

import com.cl.service.FuwuService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 服务
 * 后端接口
 * @author 
 * @email 
 * @date 2024-05-10 17:33:33
 */
@RestController
@RequestMapping("/fuwu")
public class FuwuController {
    @Autowired
    private FuwuService fuwuService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,FuwuEntity fuwu,
		HttpServletRequest request){
        EntityWrapper<FuwuEntity> ew = new EntityWrapper<FuwuEntity>();

		PageUtils page = fuwuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, fuwu), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,FuwuEntity fuwu, 
		HttpServletRequest request){
        EntityWrapper<FuwuEntity> ew = new EntityWrapper<FuwuEntity>();

		PageUtils page = fuwuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, fuwu), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( FuwuEntity fuwu){
       	EntityWrapper<FuwuEntity> ew = new EntityWrapper<FuwuEntity>();
      	ew.allEq(MPUtil.allEQMapPre( fuwu, "fuwu")); 
        return R.ok().put("data", fuwuService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(FuwuEntity fuwu){
        EntityWrapper< FuwuEntity> ew = new EntityWrapper< FuwuEntity>();
 		ew.allEq(MPUtil.allEQMapPre( fuwu, "fuwu")); 
		FuwuView fuwuView =  fuwuService.selectView(ew);
		return R.ok("查询服务成功").put("data", fuwuView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        FuwuEntity fuwu = fuwuService.selectById(id);
		fuwu = fuwuService.selectView(new EntityWrapper<FuwuEntity>().eq("id", id));
        return R.ok().put("data", fuwu);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        FuwuEntity fuwu = fuwuService.selectById(id);
		fuwu = fuwuService.selectView(new EntityWrapper<FuwuEntity>().eq("id", id));
        return R.ok().put("data", fuwu);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增服务")
    public R save(@RequestBody FuwuEntity fuwu, HttpServletRequest request){
    	fuwu.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(fuwu);
        fuwuService.insert(fuwu);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增服务")
    @RequestMapping("/add")
    public R add(@RequestBody FuwuEntity fuwu, HttpServletRequest request){
    	fuwu.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(fuwu);
        fuwuService.insert(fuwu);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改服务")
    public R update(@RequestBody FuwuEntity fuwu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(fuwu);
        fuwuService.updateById(fuwu);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除服务")
    public R delete(@RequestBody Long[] ids){
        fuwuService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	








}
