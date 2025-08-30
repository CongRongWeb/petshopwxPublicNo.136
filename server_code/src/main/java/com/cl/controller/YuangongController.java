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

import com.cl.entity.YuangongEntity;
import com.cl.entity.view.YuangongView;

import com.cl.service.YuangongService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 员工
 * 后端接口
 * @author 
 * @email 
 * @date 2024-05-10 17:33:32
 */
@RestController
@RequestMapping("/yuangong")
public class YuangongController {
    @Autowired
    private YuangongService yuangongService;



    
	@Autowired
	private TokenService tokenService;
	
	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		YuangongEntity u = yuangongService.selectOne(new EntityWrapper<YuangongEntity>().eq("yuangongzhanghao", username));
        if(u==null || !u.getMima().equals(password)) {
            return R.error("账号或密码不正确");
        }
		String token = tokenService.generateToken(u.getId(), username,"yuangong",  "员工" );
		return R.ok().put("token", token);
	}


	
	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody YuangongEntity yuangong){
    	//ValidatorUtils.validateEntity(yuangong);
    	YuangongEntity u = yuangongService.selectOne(new EntityWrapper<YuangongEntity>().eq("yuangongzhanghao", yuangong.getYuangongzhanghao()));
		if(u!=null) {
			return R.error("注册用户已存在");
		}
		Long uId = new Date().getTime();
		yuangong.setId(uId);
        yuangongService.insert(yuangong);
        return R.ok();
    }

	
	/**
	 * 退出
	 */
	@RequestMapping("/logout")
	public R logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("退出成功");
	}
	
	/**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
    	Long id = (Long)request.getSession().getAttribute("userId");
        YuangongEntity u = yuangongService.selectById(id);
        return R.ok().put("data", u);
    }
    
    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	YuangongEntity u = yuangongService.selectOne(new EntityWrapper<YuangongEntity>().eq("yuangongzhanghao", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
        u.setMima("123456");
        yuangongService.updateById(u);
        return R.ok("密码已重置为：123456");
    }


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,YuangongEntity yuangong,
		HttpServletRequest request){
        EntityWrapper<YuangongEntity> ew = new EntityWrapper<YuangongEntity>();

		PageUtils page = yuangongService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yuangong), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,YuangongEntity yuangong, 
		HttpServletRequest request){
        EntityWrapper<YuangongEntity> ew = new EntityWrapper<YuangongEntity>();

		PageUtils page = yuangongService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yuangong), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( YuangongEntity yuangong){
       	EntityWrapper<YuangongEntity> ew = new EntityWrapper<YuangongEntity>();
      	ew.allEq(MPUtil.allEQMapPre( yuangong, "yuangong")); 
        return R.ok().put("data", yuangongService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(YuangongEntity yuangong){
        EntityWrapper< YuangongEntity> ew = new EntityWrapper< YuangongEntity>();
 		ew.allEq(MPUtil.allEQMapPre( yuangong, "yuangong")); 
		YuangongView yuangongView =  yuangongService.selectView(ew);
		return R.ok("查询员工成功").put("data", yuangongView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        YuangongEntity yuangong = yuangongService.selectById(id);
		yuangong = yuangongService.selectView(new EntityWrapper<YuangongEntity>().eq("id", id));
        return R.ok().put("data", yuangong);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        YuangongEntity yuangong = yuangongService.selectById(id);
		yuangong = yuangongService.selectView(new EntityWrapper<YuangongEntity>().eq("id", id));
        return R.ok().put("data", yuangong);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增员工")
    public R save(@RequestBody YuangongEntity yuangong, HttpServletRequest request){
        if(yuangongService.selectCount(new EntityWrapper<YuangongEntity>().eq("yuangongzhanghao", yuangong.getYuangongzhanghao()))>0) {
            return R.error("员工账号已存在");
        }
    	yuangong.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(yuangong);
    	YuangongEntity u = yuangongService.selectOne(new EntityWrapper<YuangongEntity>().eq("yuangongzhanghao", yuangong.getYuangongzhanghao()));
		if(u!=null) {
			return R.error("用户已存在");
		}
		yuangong.setId(new Date().getTime());
        yuangongService.insert(yuangong);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增员工")
    @RequestMapping("/add")
    public R add(@RequestBody YuangongEntity yuangong, HttpServletRequest request){
        if(yuangongService.selectCount(new EntityWrapper<YuangongEntity>().eq("yuangongzhanghao", yuangong.getYuangongzhanghao()))>0) {
            return R.error("员工账号已存在");
        }
    	yuangong.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(yuangong);
    	YuangongEntity u = yuangongService.selectOne(new EntityWrapper<YuangongEntity>().eq("yuangongzhanghao", yuangong.getYuangongzhanghao()));
		if(u!=null) {
			return R.error("用户已存在");
		}
		yuangong.setId(new Date().getTime());
        yuangongService.insert(yuangong);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改员工")
    public R update(@RequestBody YuangongEntity yuangong, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yuangong);
        yuangongService.updateById(yuangong);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除员工")
    public R delete(@RequestBody Long[] ids){
        yuangongService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	








}
