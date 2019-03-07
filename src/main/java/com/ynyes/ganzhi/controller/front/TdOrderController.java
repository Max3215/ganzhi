package com.ynyes.ganzhi.controller.front;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ynyes.ganzhi.entity.TdCartGoods;
import com.ynyes.ganzhi.entity.TdCoupon;
import com.ynyes.ganzhi.entity.TdCouponType;
import com.ynyes.ganzhi.entity.TdDeliveryType;
import com.ynyes.ganzhi.entity.TdGoods;
import com.ynyes.ganzhi.entity.TdOrder;
import com.ynyes.ganzhi.entity.TdOrderGoods;
import com.ynyes.ganzhi.entity.TdPayType;
import com.ynyes.ganzhi.entity.TdShippingAddress;
import com.ynyes.ganzhi.entity.TdUser;
import com.ynyes.ganzhi.entity.TdUserPoint;
import com.ynyes.ganzhi.service.TdCartGoodsService;
import com.ynyes.ganzhi.service.TdCommonService;
import com.ynyes.ganzhi.service.TdCouponService;
import com.ynyes.ganzhi.service.TdCouponTypeService;
import com.ynyes.ganzhi.service.TdDeliveryTypeService;
import com.ynyes.ganzhi.service.TdDiySiteService;
import com.ynyes.ganzhi.service.TdGoodsService;
import com.ynyes.ganzhi.service.TdOrderGoodsService;
import com.ynyes.ganzhi.service.TdOrderService;
import com.ynyes.ganzhi.service.TdPayTypeService;
import com.ynyes.ganzhi.service.TdShippingAddressService;
import com.ynyes.ganzhi.service.TdUserPointService;
import com.ynyes.ganzhi.service.TdUserService;

/**
 * 订单
 *
 */
@Controller
@RequestMapping("/touch/order")
public class TdOrderController {

    @Autowired
    private TdCartGoodsService tdCartGoodsService;

    @Autowired
    private TdUserService tdUserService;

    @Autowired
    private TdGoodsService tdGoodsService;

    @Autowired
    private TdPayTypeService tdPayTypeService;

    @Autowired
    private TdOrderGoodsService tdOrderGoodsService;

    @Autowired
    private TdOrderService tdOrderService;

    @Autowired
    private TdDeliveryTypeService tdDeliveryTypeService;

    @Autowired
    private TdCommonService tdCommonService;

    @Autowired
    private TdUserPointService tdUserPointService;
    
    @Autowired
    private TdCouponService tdCouponService;
    
    @Autowired
    private TdCouponTypeService tdCouponTypeService;
    
    @Autowired
    private TdDiySiteService tdDiySiteService;
    
    //用户联系方式 zhangji
    @Autowired
    private TdShippingAddressService tdShippingAddressService;

    @RequestMapping(value = "/info")
    public String orderInfo(HttpServletRequest req, HttpServletResponse resp,
            ModelMap map) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/touch/login";
//        	  username = req.getSession().getId();  //取消强制登陆 zhangji
        }

        // 把所有的购物车项转到该登陆用户下
        String sessionId = req.getSession().getId();

        List<TdCartGoods> cartGoodsList = tdCartGoodsService
                .findByUsername(sessionId);

        if (null != cartGoodsList && cartGoodsList.size() > 0) {
            for (TdCartGoods cartGoods : cartGoodsList) {
                cartGoods.setUsername(username);
                cartGoods.setIsLoggedIn(true);
            }
            tdCartGoodsService.save(cartGoodsList);
        }

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);

        if (null != user) {
            map.addAttribute("user", user);
        }

        List<TdCartGoods> selectedGoodsList = tdCartGoodsService
                .findByUsernameAndIsSelectedTrue(username);

        Long totalPointLimited = 0L;

        // 积分限制总和
        if (null != selectedGoodsList) {
            for (TdCartGoods cg : selectedGoodsList) {
                TdGoods goods = tdGoodsService.findOne(cg.getGoodsId());

                if (null != goods && null != goods.getPointLimited()) {
                    totalPointLimited += goods.getPointLimited()
                            * cg.getQuantity();
                }
            }
        }
        
        // 优惠券
        map.addAttribute("coupon_list", tdCouponService.findByUsernameAndIsUseable(username));

        // 积分限额
        map.addAttribute("total_point_limit", totalPointLimited);
        
        // 支付类型
        map.addAttribute("pay_type_list", tdPayTypeService.findByIsEnableTrue());
        
        // 配送方式
        map.addAttribute("delivery_type_list",
                tdDeliveryTypeService.findByIsEnableTrue());
        
        // 选中商品
        map.addAttribute("selected_goods_list", selectedGoodsList);
        
        // 同盟店
        map.addAttribute("diy_site_list",
                        tdDiySiteService.findByIsEnableTrue());

        tdCommonService.setHeader(map, req);

        return "/touch/submit_order_info";
    }

    @RequestMapping(value = "/goods/{type}")
    public String orderEdit(HttpServletRequest req, HttpServletResponse resp,
            @PathVariable String type, Long gid, ModelMap map) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/touch/login";
        }

        // 把所有的购物车项转到该登陆用户下
        List<TdCartGoods> cgList = tdCartGoodsService
                .findByUsernameAndIsSelectedTrue(username);

        if (null != cgList && null != type && null != gid) {
            for (TdCartGoods cg : cgList) {
                if (gid.equals(cg.getGoodsId())) {
                    TdGoods goods = tdGoodsService.findOne(cg.getGoodsId());

                    if (null != goods) {
                        if (type.equalsIgnoreCase("plus")) 
                        {
                            // 闪购
                            if (goods.getIsFlashSale()
                                    && goods.getFlashSaleStartTime().before(new Date())
                                    && goods.getFlashSaleStopTime().after(new Date())
                                    && cg.getPrice().equals(goods.getFlashSalePrice())) 
                            {
                                if (cg.getQuantity().compareTo(goods.getFlashSaleLeftNumber()) < 0) {
                                    cg.setQuantity(cg.getQuantity() + 1L);
                                }
                            }
                            else
                            {
                                if (cg.getQuantity().compareTo(goods.getLeftNumber()) < 0) {
                                    cg.setQuantity(cg.getQuantity() + 1L);
                                }
                            }
                        } else {
                            if (cg.getQuantity().compareTo(1L) > 0) {
                                cg.setQuantity(cg.getQuantity() - 1L);
                            }
                        }

                        tdCartGoodsService.save(cg);
                        break;
                    }
                }
            }
        }
//        
//        List<TdCartGoods> selectedGoodsList = tdCartGoodsService.findByUsernameAndIsSelectedTrue(username);
//        map.addAttribute("selected_goods_list", selectedGoodsList);

        return "redirect:/touch/order/info";
    }
    
    /**
     * 结算送餐费
     * @author Zhangji
     * @param id
     * @param req
     * @param map
     * @return
     */
    @RequestMapping(value = "/selectDelivery", method = RequestMethod.POST)
    public String selectDelivery(Long id, 
	    						HttpServletRequest req, 
	    						ModelMap map){
    	 String username = (String) req.getSession().getAttribute("username");
    	 
    	 if (null == username) {
             return "redirect:/touch/login";
//         	  username = req.getSession().getId();  //取消强制登陆 zhangji
         }

         // 把所有的购物车项转到该登陆用户下
         String sessionId = req.getSession().getId();

         List<TdCartGoods> cartGoodsList = tdCartGoodsService
                 .findByUsername(sessionId);

         if (null != cartGoodsList && cartGoodsList.size() > 0) {
             for (TdCartGoods cartGoods : cartGoodsList) {
                 cartGoods.setUsername(username);
                 cartGoods.setIsLoggedIn(true);
             }
             tdCartGoodsService.save(cartGoodsList);
         }

         TdUser user = tdUserService.findByUsernameAndIsEnabled(username);

         if (null != user) {
             map.addAttribute("user", user);
         }

         List<TdCartGoods> selectedGoodsList = tdCartGoodsService
                 .findByUsernameAndIsSelectedTrue(username);

         Long totalPointLimited = 0L;

         // 积分限制总和
         if (null != selectedGoodsList) {
             for (TdCartGoods cg : selectedGoodsList) {
                 TdGoods goods = tdGoodsService.findOne(cg.getGoodsId());

                 if (null != goods && null != goods.getPointLimited()) {
                     totalPointLimited += goods.getPointLimited()
                             * cg.getQuantity();
                 }
             }
         }
         
         // 优惠券
         map.addAttribute("coupon_list", tdCouponService.findByUsernameAndIsUseable(username));

         // 积分限额
         map.addAttribute("total_point_limit", totalPointLimited);
         
         // 支付类型
         map.addAttribute("pay_type_list", tdPayTypeService.findByIsEnableTrue());
         
         // 配送方式
         map.addAttribute("delivery_type_list",
                 tdDeliveryTypeService.findByIsEnableTrue());
         
         // 选中商品
         map.addAttribute("selected_goods_list", selectedGoodsList);
         
         // 同盟店
         map.addAttribute("diy_site_list",
                         tdDiySiteService.findByIsEnableTrue());

         tdCommonService.setHeader(map, req);
    	 
    	 TdDeliveryType delivery = tdDeliveryTypeService.findOne(id);
    	 map.addAttribute("delivery_type", delivery);
    	 
//    	 return "/touch/submit_order_info";
    	 return "/touch/submit_footer"; 	
    }
    
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String submit(Long addressId,    // 送货地址
                    Long payTypeId,         // 支付方式ID
                    Long deliveryTypeId,    // 配送方式ID
                    Long pointUse,          // 使用积分
                    Boolean isNeedInvoice,  // 是否需要发票
                    String invoiceTitle,    // 发票抬头
                    String userMessage,     // 用户留言
                    Long couponId,          // 优惠券ID
                    String deliveryAddress, // 外卖地址 zhangji
                    String appointmentTime, 
                    HttpServletRequest req, 
                    ModelMap map) {
        String username = (String) req.getSession().getAttribute("username");

        if (null == username) {
            return "redirect:/touch/login";
//            username = req.getSession().getId();  //取消强制登陆 zhangji
        }

        if (null == addressId) {
            return "redirect:/touch/order/info";
        }

        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
        
        double payTypeFee = 0.0;
        double deliveryTypeFee = 0.0;
        double pointFee = 0.0;
        double couponFee = 0.0;
        
        
        TdShippingAddress address = null;

        if (null == pointUse) {
            pointUse = 0L;
        }
        
        if (null != user.getTotalPoints())
        {
            if (pointUse.compareTo(user.getTotalPoints()) >= 0) {
                pointUse = user.getTotalPoints();
            }
        }

        List<TdShippingAddress> addressList = user.getShippingAddressList();

        for (TdShippingAddress add : addressList) {
            if (add.getId().equals(addressId)) {
                address = add;
                break;
            }
        }

        List<TdCartGoods> cartGoodsList = tdCartGoodsService
                .findByUsernameAndIsSelectedTrue(username);
        List<TdOrderGoods> orderGoodsList = new ArrayList<TdOrderGoods>();

        Double totalPrice = 0.0;

        if (null != cartGoodsList) {
            for (TdCartGoods cartGoods : cartGoodsList) {
                if (cartGoods.getIsSelected()) {
                    TdGoods goods = tdGoodsService.findOne(cartGoods
                            .getGoodsId());

                    if (null == goods) {
                        continue;
                    }

                    TdOrderGoods orderGoods = new TdOrderGoods();
                    orderGoods.setGoodsId(goods.getId());
                    orderGoods.setGoodsTitle(goods.getTitle());
                    orderGoods.setGoodsSubTitle(goods.getSubTitle());
                    orderGoods.setGoodsCoverImageUri(goods.getCoverImageUri());
                    orderGoods.setPrice(cartGoods.getPrice());
                    orderGoods.setIsReturnApplied(false);

                    long quantity = 0;
                    
                    // 团购
//                    if (goods.getIsFlashSale()
//                            && goods.getFlashSaleStartTime().before(new Date())
//                            && goods.getFlashSaleStopTime().after(new Date())
//                            && cartGoods.getPrice().equals(goods.getFlashSalePrice())) 
//                    {
//                        quantity = Math.min(cartGoods.getQuantity(), goods.getFlashSaleLeftNumber());
//                    } 
//                    else 
//                    {
                        quantity = Math.min(cartGoods.getQuantity(), goods.getLeftNumber());
//                    }

                    orderGoods.setQuantity(quantity);
                    orderGoods.setPoints(goods.getReturnPoints());

                    totalPrice += cartGoods.getPrice()
                            * cartGoods.getQuantity();

                    orderGoodsList.add(orderGoods);
                    tdGoodsService.save(goods, username);
                }
            }
        }

        TdOrder tdOrder = new TdOrder();

        Date current = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String curStr = sdf.format(current);
        Random random = new Random();
        
        // 预约时间
        if (null != appointmentTime) {
            sdf = new SimpleDateFormat("HH:mm"); // 小写的mm表示的是分钟

            try {
                Date appTime = sdf.parse(appointmentTime);

                tdOrder.setAppointmentTime(appTime);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        // 基本信息
        tdOrder.setUsername(username);
        tdOrder.setOrderTime(current);
        
        // 订单号
        tdOrder.setOrderNumber("P" + curStr + random.nextInt(999));

        // 收货地址
        tdOrder.setPostalCode(address.getPostcode());
        tdOrder.setShippingName(address.getReceiverName());
        tdOrder.setShippingPhone(address.getReceiverMobile());
        tdOrder.setShippingAddress(address.getProvince() + address.getCity()
                + address.getDisctrict() + address.getDetailAddress());

        if (null != payTypeId)
        {
            TdPayType payType = tdPayTypeService.findOne(payTypeId);
            
            if (payType.getIsOnlinePay()) {
                tdOrder.setStatusId(2L); // 待付款
            } else {
                tdOrder.setStatusId(1L); // 待确认
            }
            
            // 支付类型
            tdOrder.setPayTypeId(payType.getId());
            tdOrder.setPayTypeTitle(payType.getTitle());
            tdOrder.setPayTypeFee(payType.getFee());
            tdOrder.setIsOnlinePay(payType.getIsOnlinePay());
            
            payTypeFee = payType.getFee();
        }
        
        // 配送方式
        if (null != deliveryTypeId)
        {
            TdDeliveryType deliveryType = tdDeliveryTypeService.findOne(deliveryTypeId);
            tdOrder.setDeliverTypeId(deliveryType.getId());
            tdOrder.setDeliverTypeTitle(deliveryType.getTitle());
            tdOrder.setDeliverTypeFee(deliveryType.getFee());
            deliveryTypeFee = deliveryType.getFee();
            tdOrder.setStatusId(4L);
        }
        
        // 使用积分
        tdOrder.setPointUse(pointUse);
        
        // 用户留言
        tdOrder.setUserRemarkInfo(userMessage);
  
        // 优惠券
        if (null != couponId)
        {
            TdCoupon coupon = tdCouponService.findOne(couponId);
            
            if (null != coupon)
            {
                TdCouponType couponType = tdCouponTypeService.findOne(coupon.getId());
                
                couponFee = couponType.getPrice();
            }
        }
        
        pointFee = pointUse * 1;
        
        tdOrder.setTotalPrice(totalPrice + payTypeFee + deliveryTypeFee - pointFee - couponFee);
        
        // 发票
        if (null != isNeedInvoice)
        {
            tdOrder.setIsNeedInvoice(isNeedInvoice);
            tdOrder.setInvoiceTitle(invoiceTitle);
        }
        else
        {
            tdOrder.setIsNeedInvoice(false);
        }

        // 订单商品
        tdOrder.setOrderGoodsList(orderGoodsList);
        tdOrder.setTotalGoodsPrice(totalPrice);

        // 积分奖励
        tdOrder.setPoints(0L);

        // 保存订单
        tdOrderGoodsService.save(orderGoodsList);
        tdOrder = tdOrderService.save(tdOrder);
        
        // 优惠券
        if (null != couponId)
        {
            TdCoupon coupon = tdCouponService.findOne(couponId);
            
            if (null != coupon)
            {
                coupon.setIsUsed(true);
                tdCouponService.save(coupon);
            }
        }

        if (null != user) {
            if (pointUse.compareTo(0L) >= 0
                    && null != user.getTotalPoints()
                    && user.getTotalPoints().compareTo(pointUse) >= 0) {
                TdUserPoint userPoint = new TdUserPoint();
                userPoint.setDetail("购买商品使用积分抵扣");
                userPoint.setOrderNumber(tdOrder.getOrderNumber());
                userPoint.setPoint(0 - pointUse);
                userPoint.setPointTime(new Date());
                userPoint.setUsername(username);
                userPoint.setTotalPoint(user.getTotalPoints() - pointUse);
                tdUserPointService.save(userPoint);

                user.setTotalPoints(user.getTotalPoints() - pointUse);
                tdUserService.save(user);
            }
        }

        // 删除已生成订单的购物车项
        tdCartGoodsService.delete(cartGoodsList);

//        if (tdOrder.getIsOnlinePay()) {
//            return "redirect:/order/pay?orderId=" + tdOrder.getId();
//        }
        if(null == cartGoodsList){
        	return "/touch/submit_order_empty";
        }

        return "redirect:/touch/order/success?orderId=" + tdOrder.getId();
    }

//    @RequestMapping(value = "/pay")
//    public String pay(Long orderId, ModelMap map, HttpServletRequest req) {
//
//        tdCommonService.setHeader(map, req);
//
//        if (null == orderId) {
//            return "/client/error_404";
//        }
//        
//        map.addAttribute("order", tdOrderService.findOne(orderId));
//
//        return "/touch/order_pay";
//    }
    
    /**
     * 设置默认用户信息
     * @author Zhangji
     * @param addressId
     * @param map
     * @param req
     * @return
     */
    @RequestMapping(value = "/default")
    public String setDefault(Long addressId, ModelMap map, HttpServletRequest req) {
    	String username = (String) req.getSession().getAttribute("username");
        
        if (null == username)
        {
            return "redirect:/touch/login";
        }
        
        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
    	
    	tdCommonService.setHeader(map, req);
    	List<TdShippingAddress> addressList = user.getShippingAddressList();
	    //设置默认 zhangji
	    for(TdShippingAddress add : addressList){
	    	if(add.getId().equals(addressId)){
	    		add.setIsDefaultAddress(true);
	    		tdShippingAddressService.save(add);
	    	}
	    	else{
	    		add.setIsDefaultAddress(false);
	    		tdShippingAddressService.save(add);
	    	}
	    		        	
	    }
	    return "touch/default";
    }

    @RequestMapping(value = "/success")
    public String success(Long orderId, ModelMap map, HttpServletRequest req) {

        tdCommonService.setHeader(map, req);

        if (null == orderId) {
            return "/touch/error_404";
        }

        map.addAttribute("order", tdOrderService.findOne(orderId));
        
        String msg = "订餐成功！";
        map.addAttribute("order_message", msg);
        return "/touch/menu_cart";
    }
    
    //订单详情 zhangji
    @RequestMapping(value = "/detail")
    public String detail(Long orderId, ModelMap map, HttpServletRequest req) {
    	String username = (String) req.getSession().getAttribute("username");
        
        if (null == username)
        {
            return "redirect:/touch/login";
        }   	
    	
        tdCommonService.setHeader(map, req);

        if (null == orderId) {
            return "/touch/error_404";
        }

        map.addAttribute("order", tdOrderService.findOne(orderId));
        
        return "/touch/menu_cart";
    }

    @RequestMapping(value = "/pay/success")
    public String paySuccess(ModelMap map, HttpServletRequest req) {

        tdCommonService.setHeader(map, req);

        return "/touch/order_pay_success";
    }
    
    //删除用户信息 zhangji
    @RequestMapping(value="/address/delete")
    public String deleteAddress(Long id,ModelMap map,HttpServletRequest req){
    	String username = (String) req.getSession().getAttribute("username");
        
        if (null == username)
        {
            return "redirect:/touch/login";
        }
        
        TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
    	
    	tdCommonService.setHeader(map, req);
    	
        List<TdShippingAddress> addressList = user.getShippingAddressList();
    	if (null != id)
        {
            for (TdShippingAddress add : addressList)
            {
                if (add.getId().equals(id))
                {
                    addressList.remove(id);
                    user.setShippingAddressList(addressList);
                    tdShippingAddressService.delete(add);    
                }
            }
        }
        return "/touch/default";
    }
}
