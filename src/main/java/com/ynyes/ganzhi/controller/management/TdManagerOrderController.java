package com.ynyes.ganzhi.controller.management;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.ganzhi.entity.TdDeliveryType;
import com.ynyes.ganzhi.entity.TdDemand;
import com.ynyes.ganzhi.entity.TdDiySite;
import com.ynyes.ganzhi.entity.TdOrder;
import com.ynyes.ganzhi.entity.TdPayType;
import com.ynyes.ganzhi.entity.TdUser;
import com.ynyes.ganzhi.service.TdArticleService;
import com.ynyes.ganzhi.service.TdDeliveryTypeService;
import com.ynyes.ganzhi.service.TdDemandService;
import com.ynyes.ganzhi.service.TdDiySiteService;
import com.ynyes.ganzhi.service.TdGoodsService;
import com.ynyes.ganzhi.service.TdManagerLogService;
import com.ynyes.ganzhi.service.TdOrderService;
import com.ynyes.ganzhi.service.TdPayTypeService;
import com.ynyes.ganzhi.service.TdProductCategoryService;
import com.ynyes.ganzhi.service.TdUserService;
import com.ynyes.ganzhi.util.SMSUtil;
import com.ynyes.ganzhi.util.SiteMagConstant;

/**
 * 后台首页控制器
 * 
 * @author Sharon
 */

@Controller
@RequestMapping(value="/Verwalter/order")
public class TdManagerOrderController {
    
    @Autowired
    TdProductCategoryService tdProductCategoryService;
    
    @Autowired
    TdArticleService tdArticleService;
    
    @Autowired
    TdGoodsService tdGoodsService;
    
    @Autowired
    TdPayTypeService tdPayTypeService;
    
    @Autowired
    TdDeliveryTypeService tdDeliveryTypeService;
    
    @Autowired
    TdDiySiteService tdDiySiteService;
    
    @Autowired
    TdOrderService tdOrderService;
    
    @Autowired
    TdUserService tdUserService;
    
    @Autowired
    TdManagerLogService tdManagerLogService;
    
    @Autowired
    TdDemandService tdDemandService;
    
    // 订单设置
//    @RequestMapping(value="/setting/{type}/list")
//    public String setting(@PathVariable String type, 
//                          Integer page,
//                          Integer size,
//                          String keywords,
//                          String __EVENTTARGET,
//                          String __EVENTARGUMENT,
//                          String __VIEWSTATE,
//                          Long[] listId,
//                          Integer[] listChkId,
//                          Long[] listSortId,
//                          ModelMap map,
//                          HttpServletRequest req){
//        String username = (String) req.getSession().getAttribute("manager");
//        if (null == username) {
//            return "redirect:/Verwalter/login";
//        }
//        
//        if (null != __EVENTTARGET)
//        {
//            if (__EVENTTARGET.equalsIgnoreCase("btnDelete"))
//            {
//                btnDelete(type, listId, listChkId);
//                
//                if (type.equalsIgnoreCase("pay"))
//                {
//                    tdManagerLogService.addLog("delete", "删除支付方式", req);
//                }
//                else if (type.equalsIgnoreCase("delivery"))
//                {
//                    tdManagerLogService.addLog("delete", "删除配送方式", req);
//                }
//                else if (type.equalsIgnoreCase("diysite"))
//                {
//                    tdManagerLogService.addLog("delete", "删除自提点", req);
//                }
//            }
//            else if (__EVENTTARGET.equalsIgnoreCase("btnSave"))
//            {
//                btnSave(type, listId, listSortId);
//                
//                if (type.equalsIgnoreCase("pay"))
//                {
//                    tdManagerLogService.addLog("edit", "修改支付方式", req);
//                }
//                else if (type.equalsIgnoreCase("delivery"))
//                {
//                    tdManagerLogService.addLog("edit", "修改配送方式", req);
//                }
//                else if (type.equalsIgnoreCase("diysite"))
//                {
//                    tdManagerLogService.addLog("edit", "修改自提点", req);
//                }
//            }
//            else if (__EVENTTARGET.equalsIgnoreCase("btnPage"))
//            {
//                if (null != __EVENTARGUMENT)
//                {
//                    page = Integer.parseInt(__EVENTARGUMENT);
//                } 
//            }
//        }
//        
//        if (null == page || page < 0)
//        {
//            page = 0;
//        }
//        
//        if (null == size || size <= 0)
//        {
//            size = SiteMagConstant.pageSize;;
//        }
//        
//        map.addAttribute("page", page);
//        map.addAttribute("size", size);
//        map.addAttribute("keywords", keywords);
//        map.addAttribute("__EVENTTARGET", __EVENTTARGET);
//        map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
//        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
//                
//        if (null != type)
//        {
//            if (type.equalsIgnoreCase("pay")) // 支付方式
//            {
//                if (null == keywords)
//                {
//                    map.addAttribute("pay_type_page", 
//                            tdPayTypeService.findAllOrderBySortIdAsc(page, size));
//                }
//                else
//                {
//                    map.addAttribute("pay_type_page", 
//                            tdPayTypeService.searchAllOrderBySortIdAsc(keywords, page, size));
//                }
//                
//                return "/site_mag/pay_type_list";
//            }
//            else if (type.equalsIgnoreCase("delivery")) // 配送方式
//            {
//                if (null == keywords)
//                {
//                    map.addAttribute("delivery_type_page", 
//                            tdDeliveryTypeService.findAllOrderBySortIdAsc(page, size));
//                }
//                else
//                {
//                    map.addAttribute("delivery_type_page", 
//                            tdDeliveryTypeService.searchAllOrderBySortIdAsc(keywords, page, size));
//                }
//                
//                return "/site_mag/delivery_type_list";
//            }
//            else if (type.equalsIgnoreCase("diysite")) // 配送方式
//            {
//                if (null == keywords)
//                {
//                    map.addAttribute("diy_site_page", 
//                            tdDiySiteService.findAllOrderBySortIdAsc(page, size));
//                }
//                else
//                {
//                    map.addAttribute("diy_site_page", 
//                            tdDiySiteService.searchAllOrderBySortIdAsc(keywords, page, size));
//                }
//                
//                return "/site_mag/diy_site_list";
//            }
//        }
//        
//        return "/site_mag/pay_type_list";
//    }
//    
//    // 订单设置编辑
//    @RequestMapping(value="/setting/{type}/edit")
//    public String edit(@PathVariable String type, 
//                        Long id,
//                        String __VIEWSTATE,
//                        ModelMap map,
//                        HttpServletRequest req){
//        String username = (String) req.getSession().getAttribute("manager");
//        if (null == username)
//        {
//            return "redirect:/Verwalter/login";
//        }
//        
//        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
//        
//        if (null != type)
//        {
//            if (type.equalsIgnoreCase("pay")) // 支付方式
//            {
//                if (null != id)
//                {
//                    map.addAttribute("pay_type", tdPayTypeService.findOne(id));
//                }
//                
//                return "/site_mag/pay_type_edit";
//            }
//            else if (type.equalsIgnoreCase("delivery")) // 配送方式
//            {
//                if (null != id)
//                {
//                    map.addAttribute("delivery_type", tdDeliveryTypeService.findOne(id));
//                }
//                
//                return "/site_mag/delivery_type_edit";
//            }
//            else if (type.equalsIgnoreCase("diysite")) // 自提点
//            {
//                if (null != id)
//                {
//                    map.addAttribute("diy_site", tdDiySiteService.findOne(id));
//                }
//                
//                return "/site_mag/diy_site_edit";
//            }
//        }
//        
//        return "/site_mag/pay_type_edit";
//    }
//    
//    // 订单设置编辑
//    @RequestMapping(value = "/setting/diysite/check", method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String, String> validateForm(String param, Long id) {
//        Map<String, String> res = new HashMap<String, String>();
//
//        res.put("status", "n");
//
//        if (null == param || param.isEmpty()) {
//            res.put("info", "该字段不能为空");
//            return res;
//        }
//        
//        TdUser tdUser = tdUserService.findByUsername(param);
//        
//        if (null == id) // 新增
//        {
//            if (null != tdUser) {
//                res.put("info", "该登录名不能使用");
//                return res;
//            }
//        } 
//        else // 修改，查找除当前ID的所有
//        {
//            TdDiySite dSite = tdDiySiteService.findOne(id);
//            
//            if (null == dSite)
//            {
//                if (null != tdUser && tdUser.getRoleId() == 2L) {
//                    res.put("info", "该登录名不能使用");
//                    return res;
//                }
//            }
//            else
//            {
//                if (null != tdUser && tdUser.getUsername() != dSite.getUsername() && tdUser.getRoleId()!=2L) {
//                    res.put("info", "该登录名不能使用");
//                    return res;
//                }
//            }
//        }
//
//        res.put("status", "y");
//
//        return res;
//    }
    
    @RequestMapping(value="/edit")
    public String orderEdit(Long id,
                        Long statusId,
                        String __VIEWSTATE,
                        ModelMap map,
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        map.addAttribute("statusId", statusId);
        if (null != id)
        {
            map.addAttribute("course", tdDemandService.findOne(id));
        }
        return "/site_mag/order_edit";
    }
    
    @RequestMapping(value="/save")
    public String orderEdit(TdOrder tdOrder,
                        Long statusId,
                        String __VIEWSTATE,
                        ModelMap map,
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        map.addAttribute("statusId", statusId);
        
        tdOrderService.save(tdOrder);
        
        tdManagerLogService.addLog("edit", "修改订单", req);
        
        return "redirect:/Verwalter/order/list/"+statusId;
    }
    
    
    // 订单列表
    @RequestMapping(value="/list/{statusId}")
    public String goodsListDialog(String keywords,
                                @PathVariable Long statusId,
                                Integer page, 
                                Integer size,
                                String __EVENTTARGET,
                                String __EVENTARGUMENT,
                                String __VIEWSTATE,
                                Long[] listId,
                                Integer[] listChkId,
                                ModelMap map,
//                                String dateId,
                                HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        if (null != __EVENTTARGET)
        {
            if (__EVENTTARGET.equalsIgnoreCase("btnCancel"))
            {
                btnCancel(listId, listChkId);
                tdManagerLogService.addLog("cancel", "取消订单", req);
            }
            else if (__EVENTTARGET.equalsIgnoreCase("btnConfirm"))
            {
                btnConfirm(listId, listChkId);
                tdManagerLogService.addLog("confirm", "确认订单", req);
            }
            else if (__EVENTTARGET.equalsIgnoreCase("btnDelete"))
            {
                btnDelete(listId, listChkId);
                tdManagerLogService.addLog("delete", "删除订单", req);
            }
            else if (__EVENTTARGET.equalsIgnoreCase("btnPage"))
            {
                if (null != __EVENTARGUMENT)
                {
                    page = Integer.parseInt(__EVENTARGUMENT);
                } 
            }
        }
        
        if (null == page || page < 0)
        {
            page = 0;
        }
        
        if (null == size || size <= 0)
        {
            size = SiteMagConstant.pageSize;;
        }
        /**
         * @author libiao
         * 添加订单金额统计
         */
        Double price = new Double(0.00);
        if (null != statusId)
        {
            if (statusId.equals(0L)) // 判断为全部订单
            {
            	List<TdDemand> list = tdDemandService.findAll();
                map.addAttribute("course_page", tdDemandService.findAllOrderByIdDesc(page, size));
            }
            else
            {
            	//判断为状态订单（1:待确认 2:待付款 3:待发货 4:待收货 5: 待评价 6: 已完成 7: 已取消8: 支付取消(失败)）
            	List<TdDemand> courseList = tdDemandService.findByStatusId(statusId);

                map.addAttribute("course_page", tdDemandService.findByStatusIdOrderByIdDesc(statusId, page, size));
            }
        }
        
        // 参数注回
//        map.addAttribute("dateId",dateId);
        map.addAttribute("price",price);
        map.addAttribute("page", page);
        map.addAttribute("size", size);
        map.addAttribute("keywords", keywords);
        map.addAttribute("statusId", statusId);
        map.addAttribute("__EVENTTARGET", __EVENTTARGET);
        map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        
        return "/site_mag/order_list";
    }
    
    @RequestMapping(value="/setting/pay/save", method = RequestMethod.POST)
    public String save(TdPayType tdPayType,
                        ModelMap map,
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        if (null == tdPayType.getId())
        {
            tdManagerLogService.addLog("add", "新增支付方式", req);
        }
        else
        {
            tdManagerLogService.addLog("edit", "修改支付方式", req);
        }
        tdPayTypeService.save(tdPayType);
        
        return "redirect:/Verwalter/order/setting/pay/list";
    }
    
    @RequestMapping(value="/setting/delivery/save", method = RequestMethod.POST)
    public String save(TdDeliveryType tdDeliveryType,
                        ModelMap map,
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        if (null == tdDeliveryType.getId())
        {
            tdManagerLogService.addLog("add", "新增配送方式", req);
        }
        else
        {
            tdManagerLogService.addLog("edit", "修改配送方式", req);
        }
        
        tdDeliveryTypeService.save(tdDeliveryType);
        
        return "redirect:/Verwalter/order/setting/delivery/list";
    }
    
    @RequestMapping(value="/setting/diysite/save", method = RequestMethod.POST)
    public String save(TdDiySite tdDiySite,
                        String[] hid_photo_name_show360,
                        ModelMap map,
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        String uris = parsePicUris(hid_photo_name_show360);
        
        tdDiySite.setShowPictures(uris);
        
        if (null == tdDiySite.getId())
        {
            tdManagerLogService.addLog("add", "新增同盟店", req);
        }
        else
        {
            tdManagerLogService.addLog("edit", "修改同盟店", req);
        }
        
        tdDiySiteService.save(tdDiySite);
        
        return "redirect:/Verwalter/order/setting/diysite/list";
    }
    
    @RequestMapping(value="/dialog/contact")
    public String addressDialog(ModelMap map,
            HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        
        return "/site_mag/dialog_contact";
    }
    
    @RequestMapping(value="/dialog/delivery")
    public String sendDialog(String orderNumber, ModelMap map,
            HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        
        if (null != orderNumber && !orderNumber.isEmpty())
        {
            map.addAttribute("order", tdOrderService.findByOrderNumber(orderNumber));
        }
        
        map.addAttribute("delivery_type_list", tdDeliveryTypeService.findByIsEnableTrue());
        
        return "/site_mag/dialog_delivery";
    }
    
    @RequestMapping(value="/dialog/print")
    public String printDialog(String orderNumber, ModelMap map,
            HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        
        if (null != orderNumber && !orderNumber.isEmpty())
        {
            TdOrder order = tdOrderService.findByOrderNumber(orderNumber);
            map.addAttribute("order", order);
            map.addAttribute("now", new Date());
            map.addAttribute("manager", req.getSession().getAttribute("manager"));
            
            if (null != order)
            {
                map.addAttribute("user", tdUserService.findByUsernameAndIsEnabled(order.getUsername()));
            }
        }
        
        return "/site_mag/dialog_order_print";
    }
    
    @RequestMapping(value="/param/edit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> paramEdit(Long id,
                        String type,
                        String data,
                        String name,
                        String address,
                        String postal,
                        String mobile,
                        String expressNumber,
                        Long deliverTypeId,
                        ModelMap map,
                        HttpServletRequest req){
        
        Map<String, Object> res = new HashMap<String, Object>();
        
        res.put("code", 1);
        
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            res.put("message", "请重新登录");
            return res;
        }
        
        if (null != id  && null != type && !type.isEmpty())
        {           
            TdDemand demand = tdDemandService.findOne(id);
            // 修改备注
            if (type.equalsIgnoreCase("editMark"))
            {
                demand.setRemarkInfo(data);
            }
 
            // 确认完成
            else if (type.equalsIgnoreCase("orderFinish"))
            {
                	demand.setStatusId(6L);
            }
            // 确认取消
            else if (type.equalsIgnoreCase("orderCancel"))
            {
                if (demand.getStatusId().equals(1L) ||
                		demand.getStatusId().equals(2L))
                {
                	demand.setStatusId(7L);
                }
            }
            
            tdDemandService.save(demand);
            tdManagerLogService.addLog("edit", "修改课程预约", req);
            
            res.put("code", 0);
            res.put("message", "修改成功!");
            return res;
        }
        
        res.put("message", "参数错误!");
        return res;
    }
    
    @RequestMapping(value = "order/sumPrice" , method = RequestMethod.GET)
    public String sumPrice(String date,String date1,HttpServletRequest request){
    	
    	
    	return "/";
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @ModelAttribute
    public void getModel(@RequestParam(value = "payTypeId", required = false) Long payTypeId,
                    @RequestParam(value = "deliveryTypeId", required = false) Long deliveryTypeId,
                    @RequestParam(value = "diySiteId", required = false) Long diySiteId,
                        Model model) {
        if (null != payTypeId) {
            model.addAttribute("tdPayType", tdPayTypeService.findOne(payTypeId));
        }
        
        if (null != deliveryTypeId) {
            model.addAttribute("tdDeliveryType", tdDeliveryTypeService.findOne(deliveryTypeId));
        }
        
        if (null != diySiteId) {
            model.addAttribute("tdDiySite", tdDiySiteService.findOne(diySiteId));
        }
    }
    
    private void btnSave(String type, Long[] ids, Long[] sortIds)
    {
        if (null == type || type.isEmpty())
        {
            return;
        }
        
        if (null == ids || null == sortIds
                || ids.length < 1 || sortIds.length < 1)
        {
            return;
        }
        
        for (int i = 0; i < ids.length; i++)
        {
            Long id = ids[i];
            
            if (type.equalsIgnoreCase("pay"))
            {
                TdPayType e = tdPayTypeService.findOne(id);
                
                if (null != e)
                {
                    if (sortIds.length > i)
                    {
                        e.setSortId(sortIds[i]);
                        tdPayTypeService.save(e);
                    }
                }
            }
            else if (type.equalsIgnoreCase("delivery"))
            {
                TdDeliveryType e = tdDeliveryTypeService.findOne(id);
                
                if (null != e)
                {
                    if (sortIds.length > i)
                    {
                        e.setSortId(sortIds[i]);
                        tdDeliveryTypeService.save(e);
                    }
                }
            }
            else if (type.equalsIgnoreCase("diysite"))
            {
                TdDiySite e = tdDiySiteService.findOne(id);
                
                if (null != e)
                {
                    if (sortIds.length > i)
                    {
                        e.setSortId(sortIds[i]);
                        tdDiySiteService.save(e);
                    }
                }
            }
        }
    }
    
    private void btnDelete(String type, Long[] ids, Integer[] chkIds)
    {
        if (null == type || type.isEmpty())
        {
            return;
        }
        
        if (null == ids || null == chkIds
                || ids.length < 1 || chkIds.length < 1)
        {
            return;
        }
        
        for (int chkId : chkIds)
        {
            if (chkId >=0 && ids.length > chkId)
            {
                Long id = ids[chkId];
                
                if (type.equalsIgnoreCase("pay"))
                {
                    tdPayTypeService.delete(id);
                }
                else if (type.equalsIgnoreCase("delivery"))
                {
                    tdDeliveryTypeService.delete(id);
                }
                else if (type.equalsIgnoreCase("diysite"))
                {
                    tdDiySiteService.delete(id);
                }
            }
        }
    }
    
    private void btnConfirm(Long[] ids, Integer[] chkIds)
    {
        if (null == ids || null == chkIds
                || ids.length < 1 || chkIds.length < 1)
        {
            return;
        }
        
        for (int chkId : chkIds)
        {
            if (chkId >=0 && ids.length > chkId)
            {
                Long id = ids[chkId];
                
                TdOrder tdOrder= tdOrderService.findOne(id);
                
                // 只有待确认(1L)订单能进行确认，确认后状态为待发货(3L)
                if (tdOrder.getStatusId().equals(1L))
                {
                    tdOrder.setStatusId(3L);
                    tdOrder.setCheckTime(new Date()); // 确认时间
                    tdOrderService.save(tdOrder);
                }
            }
        }
    }
    
    private void btnCancel(Long[] ids, Integer[] chkIds)
    {
        if (null == ids || null == chkIds
                || ids.length < 1 || chkIds.length < 1)
        {
            return;
        }
        
        for (int chkId : chkIds)
        {
            if (chkId >=0 && ids.length > chkId)
            {
                Long id = ids[chkId];
                
                TdOrder tdOrder= tdOrderService.findOne(id);
                
                // 只有待确认(1L)、待付款(2L)订单能进行删除，确认后状态为已取消(7L)
                if (tdOrder.getStatusId().equals(1L) ||
                        tdOrder.getStatusId().equals(2L))
                {
                    tdOrder.setStatusId(7L);
                    tdOrder.setCancelTime(new Date()); // 取消时间
                    tdOrderService.save(tdOrder);
                }
            }
        }
    }
    
    private void btnDelete(Long[] ids, Integer[] chkIds)
    {
        if (null == ids || null == chkIds
                || ids.length < 1 || chkIds.length < 1)
        {
            return;
        }
        
        for (int chkId : chkIds)
        {
            if (chkId >=0 && ids.length > chkId)
            {
                Long id = ids[chkId];
                
                TdOrder tdOrder= tdOrderService.findOne(id);
                
                // 只有已取消(7L)订单能进行删除
                if (tdOrder.getStatusId().equals(7L))
                {
                    tdOrderService.delete(tdOrder);
                }
            }
        }
    }
    
    /**
     * 图片地址字符串整理，多张图片用,隔开
     * 
     * @param params
     * @return
     */
    private String parsePicUris(String[] uris)
    {
        if (null == uris || 0 == uris.length)
        {
            return null;
        }
        
        String res = "";
        
        for (String item : uris)
        {
            String uri = item.substring(item.indexOf("|")+1, item.indexOf("|", 2));
            
            if (null != uri)
            {
                res += uri;
                res += ",";
            }
        }
        
        return res;
    }
}
