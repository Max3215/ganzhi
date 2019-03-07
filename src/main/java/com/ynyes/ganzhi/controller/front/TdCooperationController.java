package com.ynyes.ganzhi.controller.front;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.ganzhi.entity.TdDemand;
import com.ynyes.ganzhi.entity.TdNavigationMenu;
import com.ynyes.ganzhi.entity.TdUserConsult;
import com.ynyes.ganzhi.entity.TdUserSuggestion;
import com.ynyes.ganzhi.service.TdCommonService;
import com.ynyes.ganzhi.service.TdNavigationMenuService;
import com.ynyes.ganzhi.service.TdUserConsultService;
import com.ynyes.ganzhi.service.TdUserSuggestionService;
import com.ynyes.ganzhi.util.SiteMagConstant;

@Controller
public class TdCooperationController {
	
	
	@Autowired
    private TdCommonService tdCommonService;
	
	@Autowired
	private TdUserConsultService tdUserConsultService;
	
	@Autowired
	private TdUserSuggestionService tdUserSuggestionService;
	
	@Autowired
	private TdNavigationMenuService tdNavigationMenuService;
	
	@RequestMapping("/cooperation")
	public String cooperation(HttpServletRequest req,ModelMap map,Integer page)
	{
		tdCommonService.setHeader(map, req);
		Long mid = 10L;
		TdNavigationMenu menu = tdNavigationMenuService.findOne(mid);
	    
	    map.addAttribute("menu_name", "入驻申请");
	    map.addAttribute("menu_id", 10); //菜单id zhangji
	    map.addAttribute("catId", 56); 
	    map.addAttribute("info_name","在线申请"); 
	    map.addAttribute("menu_sub_name", "Cooperation");//英文名称 zhangji
	    map.addAttribute("message", "留言板");
		

		return "/client/cooperation";
	}
	//留言
	@RequestMapping("/suggestion")
	public String suggestion(HttpServletRequest req,ModelMap map,Integer page)
	{
		tdCommonService.setHeader(map, req);
		Long mid = 89L;
		TdNavigationMenu menu = tdNavigationMenuService.findOne(mid);
	    
	    map.addAttribute("menu_name", menu.getTitle());
	    map.addAttribute("menu_id", 89L); //菜单id zhangji
	    map.addAttribute("info_name","留言板"); 
		
		return "/client/cooperation";
	}
	
	
	@RequestMapping("/cooperation/submit")
	@ResponseBody
    public Map<String, Object> setConsult(TdUserConsult userConsult, HttpServletRequest req)
    {
		Map<String, Object> res = new HashMap<String, Object>();
    	res.put("code", 1);
    	if (userConsult.getUsername() == null || userConsult.getUsername().equals(""))
    	{
			res.put("message", "用户名不能为空");
			return res;
		}
    	else if (userConsult.getMobile() == null ||userConsult.getMobile().equals(""))
    	{
			res.put("message", "手机号不能为空");
			return res;
		}
    	else if (userConsult.getContent() ==null|| userConsult.getContent().equals(""))
    	{
			res.put("message", "留言内容不能为空");
			return res;
		}
    	
    	userConsult.setConsultTime(new Date());
    	userConsult.setStatusId(0L);
    	tdUserConsultService.save(userConsult);
    	res.put("code", 0);
    	return res;
    }
	
	@RequestMapping("/suggestion/submit")
	@ResponseBody
    public Map<String, Object> submitSuggestion(TdUserSuggestion userSuggestion, HttpServletRequest req)
    {
		Map<String, Object> res = new HashMap<String, Object>();
    	res.put("code", 1);
    	
    	userSuggestion.setTime(new Date());
    	tdUserSuggestionService.save(userSuggestion);
    	res.put("code", 0);
    	return res;
    }
}
