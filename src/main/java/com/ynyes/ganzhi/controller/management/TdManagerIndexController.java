package com.ynyes.ganzhi.controller.management;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.solr.common.params.CommonParams.EchoParamStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.ganzhi.entity.TdDemand;
import com.ynyes.ganzhi.entity.TdManager;
import com.ynyes.ganzhi.entity.TdManagerRole;
import com.ynyes.ganzhi.entity.TdNavigationMenu;
import com.ynyes.ganzhi.entity.TdOrder;
import com.ynyes.ganzhi.entity.TdSetting;
import com.ynyes.ganzhi.entity.TdUserComment;
import com.ynyes.ganzhi.entity.TdUserConsult;
import com.ynyes.ganzhi.entity.TdUserSuggestion;
import com.ynyes.ganzhi.service.TdDemandService;
import com.ynyes.ganzhi.service.TdManagerRoleService;
import com.ynyes.ganzhi.service.TdManagerService;
import com.ynyes.ganzhi.service.TdNavigationMenuService;
import com.ynyes.ganzhi.service.TdOrderService;
import com.ynyes.ganzhi.service.TdSettingService;
import com.ynyes.ganzhi.service.TdUserCommentService;
import com.ynyes.ganzhi.service.TdUserConsultService;
import com.ynyes.ganzhi.service.TdUserSuggestionService;

/**
 * 后台首页控制器
 * 
 * @author Sharon
 */
@Controller
public class TdManagerIndexController {

    @Autowired
    TdNavigationMenuService tdNavigationMenuService;

    @Autowired
    TdManagerService tdManagerService;

    @Autowired
    TdSettingService tdSettingService;
    
    @Autowired
    TdOrderService tdOrderService;
    
    @Autowired
    TdUserCommentService tdUserCommentService;
    
    @Autowired
    TdUserConsultService tdUserConsultService;
    
    @Autowired
    TdUserSuggestionService tdUserSuggestionService;
    
    @Autowired
    TdDemandService tdDemandService;
    
    @Autowired
    TdManagerRoleService tdManagerRoleService;

    @RequestMapping(value = "/Verwalter")
    public String index(ModelMap map, HttpServletRequest req) {
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        
        /**
		 * @author lc
		 * @注释：管理员角色判断
		 */
        TdManager tdManager = tdManagerService.findByUsernameAndIsEnableTrue(username);
        TdManagerRole tdManagerRole = null;
        
        if (null != tdManager &&null != tdManager.getRoleId())
        {
            tdManagerRole = tdManagerRoleService.findOne(tdManager.getRoleId());
        }
        
        if (null != tdManagerRole && !tdManagerRole.getIsSys()) {
        	List<TdNavigationMenu> rootMenuList = tdNavigationMenuService
                    .findByParentIdAndSort(0L);
        	TdNavigationMenu rootmenuList[] = null;
        	if (null !=rootMenuList ) {
        		//将list中的数据存入数组中
                rootmenuList = new TdNavigationMenu[rootMenuList.size()];
            	for(int i = 0; i < rootMenuList.size(); i++){
            		rootmenuList[i] = rootMenuList.get(i);
            	}
			}
        	
        	int tempNumber = 0;
        	int total_index = 0;
			for(int i = 0; i < rootmenuList.length && total_index < tdManagerRole.getTotalPermission(); i++){
					if (total_index >= tdManagerRole.getPermissionList().size()) {
						//rootMenuList.remove(i);
						rootmenuList[i] = null;
					}else{
						if (null!=(tdManagerRole.getPermissionList().get(total_index).getIsView()) && (tdManagerRole.getPermissionList().get(total_index).getIsView())==false) {
							rootmenuList[i] = null;
						}
					}
	
					total_index = total_index + 1;
					TdNavigationMenu rootMenu = null;
					if (i < rootmenuList.length) {
					    rootMenu = rootMenuList.get(i);
					}
					
	                // 取一级菜单列表
	                List<TdNavigationMenu> level0MenuList = null;
	                if (null != rootMenu) {
	                	level0MenuList = tdNavigationMenuService
		                        .findByParentIdAndSort(rootMenu.getId());
					}else{
						
					}
	                
	                TdNavigationMenu level0menuList[] = null;
	                if (null != level0MenuList) {
	                	//将list中的数据存入数组中
		                level0menuList = new TdNavigationMenu[level0MenuList.size()];
		            	for(int a = 0; a < level0MenuList.size(); a++){
		            		level0menuList[a] = level0MenuList.get(a);
		            	}
					}
	                
	                if (null != level0menuList && level0menuList.length > 0){
		                for(int j = 0; j < level0menuList.length && total_index < tdManagerRole.getTotalPermission(); j++){
		                	if (total_index >= tdManagerRole.getPermissionList().size()) {
		                		level0menuList[j] = null;
							}else{
								if(null!=tdManagerRole.getPermissionList().get(total_index)){
			                		if (null!=(tdManagerRole.getPermissionList().get(total_index).getIsView()) && (tdManagerRole.getPermissionList().get(total_index).getIsView())==false) {
			                			level0menuList[j] = null;
				    				}
			                	}
							}
		                	
			                	total_index = total_index + 1;
			                	
			                	TdNavigationMenu level0Menu = null;
			                	if (j < level0menuList.length) {
			                		level0Menu = level0MenuList.get(j);
								}			                	
			                 
			                    // 取二级菜单列表
			                	
			                    List<TdNavigationMenu> level1MenuList = null;
			                    if(null != level0Menu){
			                    	level1MenuList = tdNavigationMenuService
				                            .findByParentIdAndSort(level0Menu.getId());
			                    }
			                    
			                    TdNavigationMenu level1menuList[] = null;
			                    if (null != level1MenuList) {
			                    	//将list中的数据存入数组中
					            	level1menuList = new TdNavigationMenu[level1MenuList.size()];
					            	for(int b = 0; b < level1MenuList.size(); b++){
					            		level1menuList[b] = level1MenuList.get(b);
					            	}
								}
			                    
			                    if (null != level1menuList && level1menuList.length > 0) {
				                    for(int c = 0; c < level1menuList.length && total_index < tdManagerRole.getTotalPermission(); c++){
				                    	if (total_index >= tdManagerRole.getPermissionList().size()) {
				                    		level1menuList[c] = null;
										}else{
											if(null!=tdManagerRole.getPermissionList().get(total_index)){
					                    		if (null!=(tdManagerRole.getPermissionList().get(total_index).getIsView()) && (tdManagerRole.getPermissionList().get(total_index).getIsView())==false) {
					                    			level1menuList[c] = null;
						        				}
						                    	
					                    	}
										}
				                    	
				                    	total_index = total_index + 1;
				                    }
				                    
				                    change(level1MenuList, level1menuList);
				                    if (null != level1MenuList && level1MenuList.size() > 0) {
					                    map.addAttribute("level_" + i + j + "_menu_list",
					                                level1MenuList);
				                    }
			                    }
	
		                }
		                change(level0MenuList, level0menuList);
		                if (null != level0MenuList && level0MenuList.size() > 0){
			                map.addAttribute("level_" + i + "_menu_list",
			                        level0MenuList);
			                }
	                }    		
				
			}
			change(rootMenuList, rootmenuList);
			if (null != rootMenuList && rootMenuList.size() > 0){
				map.addAttribute("root_menu_list", rootMenuList);
		    }
		}
        else{
        	List<TdNavigationMenu> rootMenuList = tdNavigationMenuService
                    .findByParentIdAndSort(0L);

            if (null != rootMenuList && rootMenuList.size() > 0) {
                for (int i = 0; i < rootMenuList.size(); i++) {
                    TdNavigationMenu rootMenu = rootMenuList.get(i);

                    // 取一级菜单列表
                    List<TdNavigationMenu> level0MenuList = tdNavigationMenuService
                            .findByParentIdAndSort(rootMenu.getId());

                    if (null != level0MenuList && level0MenuList.size() > 0) {
                        map.addAttribute("level_" + i + "_menu_list",
                                level0MenuList);

                        for (int j = 0; j < level0MenuList.size(); j++) {
                            TdNavigationMenu level0Menu = level0MenuList.get(j);

                            // 取二级菜单列表
                            List<TdNavigationMenu> level1MenuList = tdNavigationMenuService
                                    .findByParentIdAndSort(level0Menu.getId());

                            if (null != level1MenuList && level1MenuList.size() > 0) {
                                map.addAttribute("level_" + i + j + "_menu_list",
                                        level1MenuList);
                            }
                        }
                    }
                }
            }

            map.addAttribute("root_menu_list", rootMenuList);
        }
                            
        return "/site_mag/frame";
    }
    
    /**
	 * @author lc
	 * @注释：
	 */
    public List<TdNavigationMenu> change(List<TdNavigationMenu> list, TdNavigationMenu shu[]){
    	list.clear();
    	for(int i = 0; i < shu.length; i++){
    		if (null != shu[i]) {
				list.add(shu[i]);
			}
    	}
    	
    	return list;
    } 
    
    /**
	 * @author lc
	 * @注释：自动提醒数据验证
	 */
	@RequestMapping(value = "/Verwalter/automaticRemind", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> automaticRemind(String alipayuser_id, String type, String code,
			Boolean isSave, HttpServletRequest req) {
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("code", 1);
		String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
        	res.put("msg", "请登陆！");
            return res;
        }
        
        //新订单数量查询
        List<TdOrder> tdOrdersnew = tdOrderService.findByStatusId(2L);
        if (null != tdOrdersnew) {
        	res.put("ordernumber", tdOrdersnew.size());
		}else{
			res.put("ordernumber", 0);
		}        
        
        //支付订单数量查询
        List<TdOrder> tdOrderspay = tdOrderService.findByStatusId(3L);
        List<TdOrder> tdOrderspayleft = tdOrderService.findByStatusId(4L);
        if (null != tdOrderspay && null == tdOrderspayleft) {
        	res.put("ordernumberpay", tdOrderspay.size());
		}else if (null != tdOrderspay && null != tdOrderspayleft) {
			res.put("ordernumberpay", tdOrderspay.size()+tdOrderspayleft.size());
		}else{
			res.put("ordernumberpay", 0);
		} 
//               
//        //咨询查询
//        List<TdUserConsult> tdUserConsults = tdUserConsultService.findAll();
//        if (null != tdUserConsults) {
//			res.put("consults", tdUserConsults.size());
//		}else{
//			res.put("consults", 0);
//		}
//        //评论查询
//        List<TdUserComment> tdUserComments = tdUserCommentService.findAll();
//        if (null != tdUserComments) {
//			res.put("comments", tdUserComments.size());
//		}else{
//			res.put("comments", 0);
//		}
        //投诉查询
        List<TdUserSuggestion> tdUserSuggestions = tdUserSuggestionService.findAll();
        if (null != tdUserSuggestions) {
			res.put("suggestions", tdUserSuggestions.size());
		}else{
			res.put("suggestions", 0);
		}
        //还想团查询
        List<TdDemand> tdDemands = tdDemandService.findAll();
        if (null != tdDemands) {
			res.put("demands", tdDemands.size());
		}else{
			res.put("demands", 0);
		}
        res.put("code", 0);
		return res;
	}
    @RequestMapping(value = "/Verwalter/center")
    public String center(ModelMap map, HttpServletRequest req) {
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        Properties props = System.getProperties();

        map.addAttribute(
                "os_name",
                props.getProperty("os.name") + " "
                        + props.getProperty("os.version"));
        map.addAttribute("java_home", props.getProperty("java.home"));
        map.addAttribute("java_version", props.getProperty("java.version"));
        map.addAttribute("remote_ip", req.getRemoteAddr());
        map.addAttribute("server_name", req.getServerName());
        map.addAttribute("server_ip", req.getLocalAddr());
        map.addAttribute("server_port", req.getServerPort());

        TdSetting setting = tdSettingService.findTopBy();

        if (null != setting) 
        {
            map.addAttribute("site_name", setting.getTitle());
            map.addAttribute("company_name", setting.getCompany());
        }

        if (!username.equalsIgnoreCase("admin")) {
            TdManager manager = tdManagerService
                    .findByUsernameAndIsEnableTrue(username);
            map.addAttribute("last_ip", manager.getLastLoginIp());
            map.addAttribute("last_login_time", manager.getLastLoginTime());
        }

        String ip = req.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknow".equalsIgnoreCase(ip)) {
            ip = req.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getRemoteAddr();
        }

        map.addAttribute("client_ip", ip);

        return "/site_mag/center";
    }

}
