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

import com.cl.entity.JiyangrizhiEntity;
import com.cl.entity.view.JiyangrizhiView;

import com.cl.service.JiyangrizhiService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 寄养日志
 * 后端接口
 * @author 
 * @email 
 * @date 2024-05-10 17:33:34
 */
@RestController
@RequestMapping("/jiyangrizhi")
public class JiyangrizhiController {
    @Autowired
    private JiyangrizhiService jiyangrizhiService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JiyangrizhiEntity jiyangrizhi,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yuangong")) {
			jiyangrizhi.setYuangongzhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("huiyuan")) {
			jiyangrizhi.setHuiyuanzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<JiyangrizhiEntity> ew = new EntityWrapper<JiyangrizhiEntity>();

		PageUtils page = jiyangrizhiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiyangrizhi), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,JiyangrizhiEntity jiyangrizhi, 
		HttpServletRequest request){
        EntityWrapper<JiyangrizhiEntity> ew = new EntityWrapper<JiyangrizhiEntity>();

		PageUtils page = jiyangrizhiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiyangrizhi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( JiyangrizhiEntity jiyangrizhi){
       	EntityWrapper<JiyangrizhiEntity> ew = new EntityWrapper<JiyangrizhiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( jiyangrizhi, "jiyangrizhi")); 
        return R.ok().put("data", jiyangrizhiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JiyangrizhiEntity jiyangrizhi){
        EntityWrapper< JiyangrizhiEntity> ew = new EntityWrapper< JiyangrizhiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( jiyangrizhi, "jiyangrizhi")); 
		JiyangrizhiView jiyangrizhiView =  jiyangrizhiService.selectView(ew);
		return R.ok("查询寄养日志成功").put("data", jiyangrizhiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JiyangrizhiEntity jiyangrizhi = jiyangrizhiService.selectById(id);
		jiyangrizhi = jiyangrizhiService.selectView(new EntityWrapper<JiyangrizhiEntity>().eq("id", id));
        return R.ok().put("data", jiyangrizhi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JiyangrizhiEntity jiyangrizhi = jiyangrizhiService.selectById(id);
		jiyangrizhi = jiyangrizhiService.selectView(new EntityWrapper<JiyangrizhiEntity>().eq("id", id));
        return R.ok().put("data", jiyangrizhi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增寄养日志")
    public R save(@RequestBody JiyangrizhiEntity jiyangrizhi, HttpServletRequest request){
    	jiyangrizhi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(jiyangrizhi);
        jiyangrizhiService.insert(jiyangrizhi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增寄养日志")
    @RequestMapping("/add")
    public R add(@RequestBody JiyangrizhiEntity jiyangrizhi, HttpServletRequest request){
    	jiyangrizhi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(jiyangrizhi);
        jiyangrizhiService.insert(jiyangrizhi);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改寄养日志")
    public R update(@RequestBody JiyangrizhiEntity jiyangrizhi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jiyangrizhi);
        jiyangrizhiService.updateById(jiyangrizhi);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除寄养日志")
    public R delete(@RequestBody Long[] ids){
        jiyangrizhiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	








}
