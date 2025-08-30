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

import com.cl.entity.JiyangjiageEntity;
import com.cl.entity.view.JiyangjiageView;

import com.cl.service.JiyangjiageService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 寄养价格
 * 后端接口
 * @author 
 * @email 
 * @date 2024-05-10 17:33:34
 */
@RestController
@RequestMapping("/jiyangjiage")
public class JiyangjiageController {
    @Autowired
    private JiyangjiageService jiyangjiageService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JiyangjiageEntity jiyangjiage,
		HttpServletRequest request){
        EntityWrapper<JiyangjiageEntity> ew = new EntityWrapper<JiyangjiageEntity>();

		PageUtils page = jiyangjiageService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiyangjiage), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,JiyangjiageEntity jiyangjiage, 
		HttpServletRequest request){
        EntityWrapper<JiyangjiageEntity> ew = new EntityWrapper<JiyangjiageEntity>();

		PageUtils page = jiyangjiageService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiyangjiage), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( JiyangjiageEntity jiyangjiage){
       	EntityWrapper<JiyangjiageEntity> ew = new EntityWrapper<JiyangjiageEntity>();
      	ew.allEq(MPUtil.allEQMapPre( jiyangjiage, "jiyangjiage")); 
        return R.ok().put("data", jiyangjiageService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JiyangjiageEntity jiyangjiage){
        EntityWrapper< JiyangjiageEntity> ew = new EntityWrapper< JiyangjiageEntity>();
 		ew.allEq(MPUtil.allEQMapPre( jiyangjiage, "jiyangjiage")); 
		JiyangjiageView jiyangjiageView =  jiyangjiageService.selectView(ew);
		return R.ok("查询寄养价格成功").put("data", jiyangjiageView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JiyangjiageEntity jiyangjiage = jiyangjiageService.selectById(id);
		jiyangjiage = jiyangjiageService.selectView(new EntityWrapper<JiyangjiageEntity>().eq("id", id));
        return R.ok().put("data", jiyangjiage);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JiyangjiageEntity jiyangjiage = jiyangjiageService.selectById(id);
		jiyangjiage = jiyangjiageService.selectView(new EntityWrapper<JiyangjiageEntity>().eq("id", id));
        return R.ok().put("data", jiyangjiage);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增寄养价格")
    public R save(@RequestBody JiyangjiageEntity jiyangjiage, HttpServletRequest request){
    	jiyangjiage.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(jiyangjiage);
        jiyangjiageService.insert(jiyangjiage);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增寄养价格")
    @RequestMapping("/add")
    public R add(@RequestBody JiyangjiageEntity jiyangjiage, HttpServletRequest request){
    	jiyangjiage.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(jiyangjiage);
        jiyangjiageService.insert(jiyangjiage);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改寄养价格")
    public R update(@RequestBody JiyangjiageEntity jiyangjiage, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jiyangjiage);
        jiyangjiageService.updateById(jiyangjiage);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除寄养价格")
    public R delete(@RequestBody Long[] ids){
        jiyangjiageService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	








}
