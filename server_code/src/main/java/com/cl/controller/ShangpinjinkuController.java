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

import com.cl.entity.ShangpinjinkuEntity;
import com.cl.entity.view.ShangpinjinkuView;

import com.cl.service.ShangpinjinkuService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 商品进库
 * 后端接口
 * @author 
 * @email 
 * @date 2024-05-10 17:33:33
 */
@RestController
@RequestMapping("/shangpinjinku")
public class ShangpinjinkuController {
    @Autowired
    private ShangpinjinkuService shangpinjinkuService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ShangpinjinkuEntity shangpinjinku,
		HttpServletRequest request){
        EntityWrapper<ShangpinjinkuEntity> ew = new EntityWrapper<ShangpinjinkuEntity>();

		PageUtils page = shangpinjinkuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shangpinjinku), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ShangpinjinkuEntity shangpinjinku, 
		HttpServletRequest request){
        EntityWrapper<ShangpinjinkuEntity> ew = new EntityWrapper<ShangpinjinkuEntity>();

		PageUtils page = shangpinjinkuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shangpinjinku), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ShangpinjinkuEntity shangpinjinku){
       	EntityWrapper<ShangpinjinkuEntity> ew = new EntityWrapper<ShangpinjinkuEntity>();
      	ew.allEq(MPUtil.allEQMapPre( shangpinjinku, "shangpinjinku")); 
        return R.ok().put("data", shangpinjinkuService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ShangpinjinkuEntity shangpinjinku){
        EntityWrapper< ShangpinjinkuEntity> ew = new EntityWrapper< ShangpinjinkuEntity>();
 		ew.allEq(MPUtil.allEQMapPre( shangpinjinku, "shangpinjinku")); 
		ShangpinjinkuView shangpinjinkuView =  shangpinjinkuService.selectView(ew);
		return R.ok("查询商品进库成功").put("data", shangpinjinkuView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ShangpinjinkuEntity shangpinjinku = shangpinjinkuService.selectById(id);
		shangpinjinku = shangpinjinkuService.selectView(new EntityWrapper<ShangpinjinkuEntity>().eq("id", id));
        return R.ok().put("data", shangpinjinku);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ShangpinjinkuEntity shangpinjinku = shangpinjinkuService.selectById(id);
		shangpinjinku = shangpinjinkuService.selectView(new EntityWrapper<ShangpinjinkuEntity>().eq("id", id));
        return R.ok().put("data", shangpinjinku);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增商品进库")
    public R save(@RequestBody ShangpinjinkuEntity shangpinjinku, HttpServletRequest request){
    	shangpinjinku.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(shangpinjinku);
        shangpinjinkuService.insert(shangpinjinku);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增商品进库")
    @RequestMapping("/add")
    public R add(@RequestBody ShangpinjinkuEntity shangpinjinku, HttpServletRequest request){
    	shangpinjinku.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(shangpinjinku);
        shangpinjinkuService.insert(shangpinjinku);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改商品进库")
    public R update(@RequestBody ShangpinjinkuEntity shangpinjinku, HttpServletRequest request){
        //ValidatorUtils.validateEntity(shangpinjinku);
        shangpinjinkuService.updateById(shangpinjinku);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除商品进库")
    public R delete(@RequestBody Long[] ids){
        shangpinjinkuService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	








}
