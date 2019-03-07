package com.ynyes.ganzhi.controller.front;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ynyes.ganzhi.entity.TdAd;
import com.ynyes.ganzhi.entity.TdAdType;
import com.ynyes.ganzhi.entity.TdArticle;
import com.ynyes.ganzhi.entity.TdArticleCategory;
import com.ynyes.ganzhi.entity.TdProductCategory;
import com.ynyes.ganzhi.service.TdAdService;
import com.ynyes.ganzhi.service.TdAdTypeService;
import com.ynyes.ganzhi.service.TdArticleCategoryService;
import com.ynyes.ganzhi.service.TdArticleService;
import com.ynyes.ganzhi.service.TdBrandService;
import com.ynyes.ganzhi.service.TdCommonService;
import com.ynyes.ganzhi.service.TdGoodsService;
import com.ynyes.ganzhi.service.TdProductCategoryService;
import com.ynyes.ganzhi.service.TdSettleService;
import com.ynyes.ganzhi.service.TdSiteLinkService;
import com.ynyes.ganzhi.util.ClientConstant;

/**
 * 前端首页控制
 *
 */
@Controller
@RequestMapping("/")
public class TdIndexController {

    @Autowired
    private TdCommonService tdCommonService;

    @Autowired
    private TdGoodsService tdGoodsService;

    @Autowired
    private TdArticleService tdArticleService;

    @Autowired
    private TdArticleCategoryService tdArticleCategoryService;

    @Autowired
    private TdProductCategoryService tdProductCategoryService;

    @Autowired
    private TdSiteLinkService tdSiteLinkService;

    @Autowired
    private TdAdTypeService tdAdTypeService;

    @Autowired
    private TdAdService tdAdService;

    @Autowired
    private TdBrandService tdBrandService;
    
    @Autowired
    private TdSettleService tdSettleService;
    
    @RequestMapping
    public String index(HttpServletRequest req, Device device, ModelMap map) {        
        
        tdCommonService.setHeader(map, req);              
        
        //新闻
        map.addAttribute("index_news_list", tdArticleService                   		
        		.findByMenuIdOrderByCreateTime(8L));   
        
        //招聘信息
//        /////////
//        map.addAttribute("join_list", tdArticleService                   		
//        		.findByMenuIdOrderByCreateTime(13L));   
//        /////////
        List<TdArticleCategory> joinList = tdArticleCategoryService
                .findByMenuId(13L);

        if (null != joinList && joinList.size() > 0) {
            for (TdArticleCategory tdCat : joinList)
            {
                if (null != tdCat.getTitle() && tdCat.getTitle().equals("人才招聘"))
                {
                    map.addAttribute("join_page", tdArticleService
                            .findByMenuIdAndCategoryIdAndIsEnableOrderByIdAsc(13L,
                                    tdCat.getId(), 0, ClientConstant.pageSize));
                    
                }
                if (null != tdCat.getTitle() && tdCat.getTitle().equals("交通指南"))
                {
                    map.addAttribute("map_page", tdArticleService
                            .findByMenuIdAndCategoryIdAndIsEnableOrderByIdAsc(13L,
                                    tdCat.getId(), 0, ClientConstant.pageSize));
                    
                }
            }
        }
        //园区简介
        map.addAttribute("profile_article",tdArticleService.findOne(tdArticleService.findByCategoryId(59L).get(0).getId()));
        

        //下载
        map.addAttribute("download_list", tdArticleService                   		
        		.findByMenuIdOrderByCreateTime(83L));   
        
        //广告管理
        List<TdAd> ad_list = tdAdService.findByTypeIdOrderBySortIdAsc(1L);
        map.addAttribute("ad_list", ad_list);
        
        //信息
        List<TdArticleCategory> infoList = tdArticleCategoryService
                .findByMenuId(10L);

        if (null != infoList && infoList.size() > 0) {
            for (TdArticleCategory tdCat : infoList)
            {
                if (null != tdCat.getTitle() && tdCat.getTitle().equals("关于我们"))
                {
                    map.addAttribute("about_page", tdArticleService
                            .findByMenuIdAndCategoryIdAndIsEnableOrderByIdAsc(10L,
                                    tdCat.getId(), 0, ClientConstant.pageSize));
                }
                if (null != tdCat.getTitle() && tdCat.getTitle().equals("服务体系"))
                {
                    map.addAttribute("service_article", tdArticleService.findOne(
													tdArticleService.findByCategoryId(
																tdCat.getId()
															).get(0).getId()
													)
												);
                }
                if (null != tdCat.getTitle() && tdCat.getTitle().equals("入驻申请"))
                {
                    map.addAttribute("coo_article", tdArticleService.findOne(
													tdArticleService.findByCategoryId(
																tdCat.getId()
															).get(0).getId()
													)
												);
                }

            }
        }
        //首页新闻
        List<TdArticleCategory> newsList = tdArticleCategoryService
                .findByMenuId(8L);

        if (null != newsList && newsList.size() > 0) {
            for (TdArticleCategory tdCat : newsList)
            {
                if (null != tdCat.getTitle() && tdCat.getTitle().equals("通知公告"))
                {
                    map.addAttribute("inform_page", tdArticleService
                            .findByMenuIdAndCategoryIdAndIsEnableOrderByIdDesc(8L,
                                    tdCat.getId(), 0, 20));
                }
                if (null != tdCat.getTitle() && tdCat.getTitle().equals("园区新闻"))
                {
                    map.addAttribute("news_page", tdArticleService
                            .findByMenuIdAndCategoryIdAndIsEnableOrderByIdDesc(8L,
                                    tdCat.getId(), 0, 20));
                }
            }
        }
        
        //优惠政策
        List<TdArticleCategory> policyList = tdArticleCategoryService
                .findByMenuId(86L);

        if (null != policyList && policyList.size() > 0) {
            for (TdArticleCategory tdCat : policyList)
            {
                if (null != tdCat.getTitle() && tdCat.getTitle().equals("最新政策"))
                {
                    map.addAttribute("policy_page", tdArticleService
                            .findByMenuIdAndCategoryId(86L,
                                    tdCat.getId(), 0, 20));
                }
                if (null != tdCat.getTitle() && tdCat.getTitle().equals("项目申报"))
                {
                    map.addAttribute("project_article", tdArticleService.findOne(
													tdArticleService.findByCategoryId(
																tdCat.getId()
															).get(0).getId()
													)
												);
                }
            }
        }
        
        //入驻企业图标
        map.addAttribute("settle_list", tdSettleService.findAllOrderBySortIdAsc());
        
        // 首页大图轮播广告
        TdAdType adType = tdAdTypeService.findByTitle("首页轮播大图广告");
        map.addAttribute("adtype", adType);   //zhangji
        
        
        if (null != adType) {
            map.addAttribute("big_scroll_ad_list", tdAdService
                    .findByTypeIdOrderBySortIdAsc(adType.getId()));
        }
        
        // 一级分类
        List<TdProductCategory> topCatList = tdProductCategoryService
                .findByParentIdIsNullOrderBySortIdAsc();
        if (null != topCatList && topCatList.size() > 0) {
            map.addAttribute("top_category_list", topCatList);

            for (int i = 0; i < topCatList.size(); i++) {
                TdProductCategory topCat = topCatList.get(i);

                if (null != topCat) {
                    map.addAttribute(
                            "top_cat_goods_page" + i,
                            tdGoodsService
                                    .findByCategoryIdAndIsRecommendIndexTrueAndIsOnSaleTrueOrderByIdDesc(
                                            topCat.getId(), 0, 3));
                }
            }
        }
        return "/client/index";
    }
}
