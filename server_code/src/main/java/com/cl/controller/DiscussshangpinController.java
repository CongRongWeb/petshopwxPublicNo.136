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

import com.cl.entity.DiscussshangpinEntity;
import com.cl.entity.view.DiscussshangpinView;

import com.cl.service.DiscussshangpinService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 商品评论表
 * 后端接口
 * @author 
 * @email 
 * @date 2024-05-10 17:33:34
 */
@RestController
@RequestMapping("/discussshangpin")
public class DiscussshangpinController {
    @Autowired
    private DiscussshangpinService discussshangpinService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscussshangpinEntity discussshangpin,
		HttpServletRequest request){
        EntityWrapper<DiscussshangpinEntity> ew = new EntityWrapper<DiscussshangpinEntity>();

		PageUtils page = discussshangpinService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussshangpin), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,DiscussshangpinEntity discussshangpin, 
		HttpServletRequest request){
        EntityWrapper<DiscussshangpinEntity> ew = new EntityWrapper<DiscussshangpinEntity>();

		PageUtils page = discussshangpinService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussshangpin), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscussshangpinEntity discussshangpin){
       	EntityWrapper<DiscussshangpinEntity> ew = new EntityWrapper<DiscussshangpinEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discussshangpin, "discussshangpin")); 
        return R.ok().put("data", discussshangpinService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscussshangpinEntity discussshangpin){
        EntityWrapper< DiscussshangpinEntity> ew = new EntityWrapper< DiscussshangpinEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discussshangpin, "discussshangpin")); 
		DiscussshangpinView discussshangpinView =  discussshangpinService.selectView(ew);
		return R.ok("查询商品评论表成功").put("data", discussshangpinView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscussshangpinEntity discussshangpin = discussshangpinService.selectById(id);
		discussshangpin = discussshangpinService.selectView(new EntityWrapper<DiscussshangpinEntity>().eq("id", id));
        return R.ok().put("data", discussshangpin);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscussshangpinEntity discussshangpin = discussshangpinService.selectById(id);
		discussshangpin = discussshangpinService.selectView(new EntityWrapper<DiscussshangpinEntity>().eq("id", id));
        return R.ok().put("data", discussshangpin);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增商品评论表")
    public R save(@RequestBody DiscussshangpinEntity discussshangpin, HttpServletRequest request){
    	discussshangpin.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(discussshangpin);
        discussshangpinService.insert(discussshangpin);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增商品评论表")
    @RequestMapping("/add")
    public R add(@RequestBody DiscussshangpinEntity discussshangpin, HttpServletRequest request){
    	discussshangpin.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(discussshangpin);
        discussshangpinService.insert(discussshangpin);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody DiscussshangpinEntity discussshangpin, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussshangpin);
        discussshangpinService.updateById(discussshangpin);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除商品评论表")
    public R delete(@RequestBody Long[] ids){
        discussshangpinService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	
	/**
     * 前端智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,DiscussshangpinEntity discussshangpin, HttpServletRequest request,String pre){
        EntityWrapper<DiscussshangpinEntity> ew = new EntityWrapper<DiscussshangpinEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicktime");
        params.put("order", "desc");
		PageUtils page = discussshangpinService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussshangpin), params), params));
        return R.ok().put("data", page);
    }








}
