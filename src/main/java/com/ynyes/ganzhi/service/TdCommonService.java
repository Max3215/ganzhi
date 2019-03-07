package com.ynyes.ganzhi.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.ynyes.ganzhi.entity.TdAdType;
import com.ynyes.ganzhi.entity.TdArticle;
import com.ynyes.ganzhi.entity.TdArticleCategory;
import com.ynyes.ganzhi.entity.TdDemand;
import com.ynyes.ganzhi.entity.TdProductCategory;
import com.ynyes.ganzhi.entity.TdSetting;
import com.ynyes.ganzhi.util.ClientConstant;

@Service
public class TdCommonService {

    @Autowired
    private TdSettingService tdSettingService;

    @Autowired
    private TdSettleService tdSettleService;

    @Autowired
    private TdCartGoodsService tdCartGoodsService;

    @Autowired
    private TdNaviBarItemService tdNaviBarItemService;

    @Autowired
    private TdArticleCategoryService tdArticleCategoryService;

    @Autowired
    private TdArticleService tdArticleService;

    @Autowired
    private TdProductCategoryService tdProductCategoryService;

    @Autowired
    private TdSiteLinkService tdSiteLinkService;

    @Autowired
    private TdUserService tdUserService;
    
    @Autowired
    private TdServiceItemService tdServiceItemService;
    
    @Autowired
    private TdAdTypeService tdAdTypeService;
    
    @Autowired
    private TdAdService tdAdService;
    
    //团购 zhangji
    @Autowired
    private TdDemandService tdDemandService;

    public void setHeader(ModelMap map, HttpServletRequest req) {
        String username = (String) req.getSession().getAttribute("username");

        // 网站基本信息
        TdSetting setting = tdSettingService.findTopBy();
        
        // 统计访问量
        if (null != setting && null == req.getSession().getAttribute("countedTotalVisits"))
        {
            req.getSession().setAttribute("countedTotalVisits", "yes");
            if (null == setting.getTotalVisits())
            {
                setting.setTotalVisits(1L);
            }
            else
            {
                setting.setTotalVisits(setting.getTotalVisits() + 1L);
            }
            setting = tdSettingService.save(setting);
        }
        
//         统计在线人数
        if (null != setting && null == req.getSession().getAttribute("countedTotalOnlines"))
        {
            req.getSession().setAttribute("countedTotalOnlines", "yes");
            if (null == setting.getTotalOnlines())
            {
                setting.setTotalOnlines(1L);
            }
            else
            {
                setting.setTotalOnlines(setting.getTotalOnlines() + 1L);
            }
            setting = tdSettingService.save(setting);
        }

        map.addAttribute("site", setting);
        map.addAttribute("keywords_list",
                tdSettleService.findByIsEnableTrueOrderBySortIdAsc());

        // 导航菜单
        map.addAttribute("navi_item_list",
                tdNaviBarItemService.findByPCTdNaviBarItem());
        
        // 商城服务
        map.addAttribute("service_item_list", tdServiceItemService.findByIsEnableTrueOrderBySortIdAsc());

        // 友情链接
        map.addAttribute("site_link_list",
                tdSiteLinkService.findByIsEnableTrueOrderBySortIdAsc());
        
        //团购留言     
//        List<TdDemand> tdDemand = tdDemandService.findByStatusIdAndIsShowable();
//        map.addAttribute("demand_list",tdDemand);
        
        //新闻中心
        List<TdArticleCategory> newsArticle = tdArticleCategoryService.findByMenuId(8L);
        map.addAttribute("news_list", newsArticle);
        
        //园区概况
        List<TdArticleCategory> profileArticle = tdArticleCategoryService.findByMenuId(11L);
        map.addAttribute("profile_list", profileArticle);
        
        //优惠政策
        List<TdArticleCategory> policyArticle = tdArticleCategoryService.findByMenuId(86L);
        map.addAttribute("policy_list", policyArticle);

        //联系我们
        List<TdArticleCategory> contactArticle = tdArticleCategoryService.findByMenuId(89L);
        map.addAttribute("contact_list", contactArticle);
        
        //合作机构
        List<TdArticleCategory> workArticle = tdArticleCategoryService.findByMenuId(83L);
        map.addAttribute("work_list", workArticle);
        
        //创客空间
        List<TdArticleCategory> spaceArticle = tdArticleCategoryService.findByMenuId(12L);
        map.addAttribute("space_list", spaceArticle);
        
        //服务体系
        List<TdArticle> serviceArticle = tdArticleService.findByCategoryId(52L);
        map.addAttribute("service_list", serviceArticle);
        
        //入驻申请
        List<TdArticle> cooArticle = tdArticleService.findByCategoryId(56L);
        map.addAttribute("coo_list", cooArticle);
        
        //功能信息
        List<TdArticleCategory> infoList = tdArticleCategoryService
                .findByMenuId(10L);

        if (null != infoList && infoList.size() > 0) {
            for (TdArticleCategory tdCat : infoList)
            {
                if (null != tdCat.getTitle() && tdCat.getTitle().equals("公司简介"))
                {
                    map.addAttribute("profile_page", tdArticleService
                            .findByMenuIdAndCategoryIdAndIsEnableOrderByIdAsc(10L,
                                    tdCat.getId(), 0, ClientConstant.pageSize));
                    
                }
                if (null != tdCat.getTitle() && tdCat.getTitle().equals("服务项目"))
                {
                    map.addAttribute("service_page", tdArticleService
                            .findByMenuIdAndCategoryIdAndIsEnableOrderByIdAsc(10L,
                                    tdCat.getId(), 0, ClientConstant.pageSize));
                 
                }             
            }
        }
    }

}
