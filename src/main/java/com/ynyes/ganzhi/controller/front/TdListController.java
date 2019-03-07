package com.ynyes.ganzhi.controller.front;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ynyes.ganzhi.entity.TdArticleCategory;
import com.ynyes.ganzhi.entity.TdBrand;
import com.ynyes.ganzhi.entity.TdGoods;
import com.ynyes.ganzhi.entity.TdParameter;
import com.ynyes.ganzhi.entity.TdProductCategory;
import com.ynyes.ganzhi.service.TdArticleCategoryService;
import com.ynyes.ganzhi.service.TdArticleService;
import com.ynyes.ganzhi.service.TdBrandService;
import com.ynyes.ganzhi.service.TdCommonService;
import com.ynyes.ganzhi.service.TdGoodsService;
import com.ynyes.ganzhi.service.TdParameterService;
import com.ynyes.ganzhi.service.TdProductCategoryService;
import com.ynyes.ganzhi.service.TdUserRecentVisitService;
import com.ynyes.ganzhi.util.ClientConstant;


@Controller
public class TdListController {
    @Autowired
    private TdGoodsService tdGoodsService;
    
    @Autowired
    private TdProductCategoryService tdProductCategoryService;
    
    @Autowired
    private TdArticleService tdArticleService;
    
    @Autowired
    private TdCommonService tdCommonService;
    
    @Autowired
    private TdBrandService tdBrandService;
    
    @Autowired
    private TdParameterService tdParameterService;
    
    @Autowired
    private TdUserRecentVisitService tdUserRecentVisitService;
    
    @Autowired
    private TdArticleCategoryService tdArticleCategoryService;
    
    // 组成：typeID-brandIndex-[paramIndex]-[排序字段]-[销量排序标志]-[价格排序标志]-[上架时间排序标志]-[页号]_[价格低值]-[价格高值]
    @RequestMapping("/list/{listStr}")
    public String list(@PathVariable String listStr, ModelMap map, HttpServletRequest req){
        
        tdCommonService.setHeader(map, req);
        
        if (null == listStr || "".equals(listStr))
        {
            return "/client/error_404";
        }
        
        // 商城资讯
        List<TdArticleCategory> articleCatList = tdArticleCategoryService
                .findByMenuId(10L);

        if (null != articleCatList && articleCatList.size() > 0) {
            Long articleCatId = articleCatList.get(0).getId();

            map.addAttribute("news_page", tdArticleService
                    .findByMenuIdAndCategoryIdAndIsEnableOrderByIdDesc(10L,
                            articleCatId, 0, ClientConstant.pageSize));
        }
        
        Integer priceLow = null; // 价格低值
        Integer priceHigh = null; // 价格高值
        
        if (listStr.contains("_")) // 包括价格区间
        {
            String[] listGroup = listStr.split("_");
            
            if (listGroup.length > 1)
            {
                String[] priceGroup = listGroup[1].split("-");
                
                if (priceGroup.length > 1)
                {
                    priceLow = Integer.parseInt(priceGroup[0]);
                    priceHigh = Integer.parseInt(priceGroup[1]);
                }
            }
            
            listStr = listGroup[0];
        }
        
        map.addAttribute("priceLow", priceLow);
        map.addAttribute("priceHigh", priceHigh);
        
        String[] numberGroup = listStr.split("-");
        
        Long categoryId = null;
        
        if (numberGroup.length <= 0)
        {
            return "/client/error_404";
        }
        
        // 分类ID
        categoryId = Long.parseLong(numberGroup[0]);
        
        map.addAttribute("categoryId", categoryId);
        
        TdProductCategory tdProductCategory = tdProductCategoryService.findOne(categoryId);
        
        if (null == tdProductCategory)
        {
            return "/client/error_404";
        }
        
        map.addAttribute("productCategory", tdProductCategory);
        
        // 品牌
        Integer brandIndex = 0;
        
        if (numberGroup.length > 1) // 解析品牌
        {
            brandIndex = Integer.parseInt(numberGroup[1]);
        }
        
        map.addAttribute("brandIndex", brandIndex);
        
        // 品牌列表
        List<TdBrand> brandList = tdBrandService.findByStatusIdAndProductCategoryTreeContaining(1L, categoryId);
        
        map.addAttribute("brand_list", brandList);
        
        // 品牌ID
        Long brandId = null;
        
        if (brandIndex.intValue() > 0 && brandList.size() >= brandIndex.intValue())
        {
            TdBrand brand = brandList.get(brandIndex - 1);
            brandId = brand.getId();
        }
        
        // 筛选参数个数
        Integer paramCount = 0;
        List<Integer> paramIndexList = new ArrayList<Integer>();
        List<String> paramValueList = new ArrayList<String>();
        
        // 参数列表
        if (null != tdProductCategory.getParamCategoryId())
        {
            Long paramCategoryId = tdProductCategory.getParamCategoryId();
            
            List<TdParameter> paramList = tdParameterService.findByCategoryTreeContainingAndIsSearchableTrue(paramCategoryId);
        
            paramCount = paramList.size();
            
            if (numberGroup.length >= paramCount + 2) // 解析参数
            {
                for (int i=0; i<paramCount; i++)
                {
                    String indexStr = numberGroup[2 + i];
                    TdParameter param = paramList.get(i);
                    
                    if (null != indexStr)
                    {
                        Integer paramIndex = Integer.parseInt(indexStr);
                        paramIndexList.add(paramIndex);
                        
                        if (paramIndex > 0 && null != param.getValueList() && !"".equals(param.getValueList()))
                        {
                            String[] values = param.getValueList().split(",");
                            
                            if (values.length >= paramIndex)
                            {
                                String value = values[paramIndex-1].trim();
                                paramValueList.add(value);
                            }
                        }
                    }
                }
            }
            else
            {
                for (int i=0; i<paramCount; i++)
                {
                    paramIndexList.add(0);
                }
            }
            
            map.addAttribute("param_list", paramList);
        }
        
        map.addAttribute("param_count", paramCount);
        map.addAttribute("param_index_list", paramIndexList);
        
        // 排序字段
        Integer orderId = 0;
        
        if (numberGroup.length >= paramCount + 3)
        {
            String orderIdStr = numberGroup[2 + paramCount];
            
            if (null != orderIdStr)
            {
                orderId = Integer.parseInt(orderIdStr);
            }
        }
        
        map.addAttribute("orderId", orderId);
        
        //  销量排序标志
        Integer soldId = 0;
        
        if (numberGroup.length >= paramCount + 4)
        {
            String soldIdStr = numberGroup[3 + paramCount];
            
            if (null != soldIdStr)
            {
                soldId = Integer.parseInt(soldIdStr);
            }
        }
        
        map.addAttribute("soldId", soldId);
        
        // 价格排序标志
        Integer priceId = 0;
        
        if (numberGroup.length >= paramCount + 5)
        {
            String priceIdStr = numberGroup[4 + paramCount];
            
            if (null != priceIdStr)
            {
                priceId = Integer.parseInt(priceIdStr);
            }
        }
        
        map.addAttribute("priceId", priceId);
        
        // 上架时间排序标志
        Integer timeId = 0;
        
        if (numberGroup.length >= paramCount + 6)
        {
            String timeIdStr = numberGroup[5 + paramCount];
            
            if (null != timeIdStr)
            {
                timeId = Integer.parseInt(timeIdStr);
            }
        }
        
        map.addAttribute("timeId", timeId);
        
        // 页号
        Integer pageId = 0;
        
        if (numberGroup.length >= paramCount + 7)
        {
            String pageIdStr = numberGroup[6 + paramCount];
            
            if (null != pageIdStr)
            {
                pageId = Integer.parseInt(pageIdStr);
            }
        }
        
        map.addAttribute("pageId", pageId);
        
        // 是否有货
        Integer leftId = 0;
        
        if (numberGroup.length >= paramCount + 8)
        {
            String leftIdStr = numberGroup[7 + paramCount];
            
            if (null != leftIdStr)
            {
                leftId = Integer.parseInt(leftIdStr);
            }
        }
        
        map.addAttribute("leftId", leftId);
        
        // 获取该类型所有父类型
        if (null != tdProductCategory)
        {
            if (null != tdProductCategory.getParentTree() && !"".equals(tdProductCategory.getParentTree()))
            {
                List<TdProductCategory> catList = new ArrayList<TdProductCategory>();
                
                for (String cid : tdProductCategory.getParentTree().split(","))
                {
                    if (!"".equals(cid))
                    {
                        // 去除方括号
                        cid = cid.replace("[", "");
                        cid = cid.replace("]", "");
                        
                        TdProductCategory tpc = tdProductCategoryService.findOne(Long.parseLong(cid));
                        
                        if (null != tpc)
                        {
                            catList.add(tpc);
                        }
                    }
                }
                
                map.addAttribute("category_tree_list", catList);
            }
        }
        
        // 分类热卖推荐
        map.addAttribute("hot_sale_list", tdGoodsService.findByCategoryIdAndIsRecommendTypeTrueAndIsOnSaleTrueOrderByIdDesc(categoryId, 0, 10).getContent());   
        
        // 销量排行
        map.addAttribute("most_sold_list", tdGoodsService.findByCategoryIdAndIsOnSaleTrueOrderBySoldNumberDesc(categoryId, 0, 10).getContent());   
        
        // 新品推荐
        map.addAttribute("newest_list", tdGoodsService.findByCategoryIdAndIsOnSaleTrueOrderByOnSaleTimeDesc(categoryId, 0, 10).getContent());   
        
        // 浏览记录
        String username = (String) req.getSession().getAttribute("username");
        if (null == username)
        {
            map.addAttribute("recent_page", tdUserRecentVisitService.findByUsernameOrderByVisitTimeDesc(req.getSession().getId(), 0, ClientConstant.pageSize));
        }
        else
        {
            map.addAttribute("recent_page", tdUserRecentVisitService.findByUsernameOrderByVisitTimeDesc(username, 0, ClientConstant.pageSize));
        }
        
        // 查找商品
        Page<TdGoods> goodsPage = null;
        
        // 仅显示有货
        if (leftId.intValue() > 0)
        {
            // 按价格区间
            if (null != priceLow && null != priceHigh)
            {
                // 全部品牌
                if (0 == brandIndex.intValue())
                {
                    // 有货、价格区间、按销量排序
                    if (0 == orderId.intValue())
                    {
                        
                        if (0 == soldId.intValue())
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndLeftNumberGreaterThanZeroAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrueOrderBySoldNumberDesc(
                                        categoryId, priceLow, priceHigh, pageId, ClientConstant.pageSize, paramValueList);
                    
                        }
                        else
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndLeftNumberGreaterThanZeroAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrueOrderBySoldNumberAsc(
                                    categoryId, priceLow, priceHigh, pageId, ClientConstant.pageSize, paramValueList);
                
                        }
                    }
                    // 有货、价格区间、按价格排序
                    else if (1 == orderId.intValue())
                    {
                        if (0 == priceId.intValue())
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndLeftNumberGreaterThanZeroAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrueOrderBySalePriceDesc(
                                        categoryId, priceLow, priceHigh, pageId, ClientConstant.pageSize, paramValueList);
                    
                        }
                        else
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndLeftNumberGreaterThanZeroAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrueOrderBySalePriceAsc(
                                    categoryId, priceLow, priceHigh, pageId, ClientConstant.pageSize, paramValueList);
                
                        }
                    }
                    // 有货、价格区间、按上架时间排序
                    else 
                    {
                        if (0 == timeId.intValue())
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndLeftNumberGreaterThanZeroAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrueOrderByOnSaleTimeDesc(
                                        categoryId, priceLow, priceHigh, pageId, ClientConstant.pageSize, paramValueList);
                    
                        }
                        else
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndLeftNumberGreaterThanZeroAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrueOrderByOnSaleTimeAsc(
                                    categoryId, priceLow, priceHigh, pageId, ClientConstant.pageSize, paramValueList);
                
                        }
                    }
                }
                else
                {
                    // 品牌、有货、价格区间、按销量排序
                    if (0 == orderId.intValue())
                    {
                        if (0 == soldId.intValue())
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndBrandIdAndLeftNumberGreaterThanZeroAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrueOrderBySoldNumberDesc(
                                        categoryId, brandId, priceLow, priceHigh, pageId, ClientConstant.pageSize, paramValueList);
                    
                        }
                        else
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndBrandIdAndLeftNumberGreaterThanZeroAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrueOrderBySoldNumberAsc(
                                    categoryId, brandId, priceLow, priceHigh, pageId, ClientConstant.pageSize, paramValueList);
                
                        }
                    }
                    // 品牌、有货、价格区间、按价格排序
                    else if (1 == orderId.intValue())
                    {
                        if (0 == priceId.intValue())
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndBrandIdAndLeftNumberGreaterThanZeroAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrueOrderBySalePriceDesc(
                                        categoryId, brandId, priceLow, priceHigh, pageId, ClientConstant.pageSize, paramValueList);
                    
                        }
                        else
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndBrandIdAndLeftNumberGreaterThanZeroAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrueOrderBySalePriceAsc(
                                    categoryId, brandId, priceLow, priceHigh, pageId, ClientConstant.pageSize, paramValueList);
                
                        }
                    }
                    // 品牌、有货、价格区间、按上架时间排序
                    else 
                    {
                        if (0 == timeId.intValue())
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndBrandIdAndLeftNumberGreaterThanZeroAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrueOrderByOnSaleTimeDesc(
                                        categoryId, brandId, priceLow, priceHigh, pageId, ClientConstant.pageSize, paramValueList);
                    
                        }
                        else
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndBrandIdAndLeftNumberGreaterThanZeroAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrueOrderByOnSaleTimeAsc(
                                    categoryId, brandId, priceLow, priceHigh, pageId, ClientConstant.pageSize, paramValueList);
                
                        }
                    }
                }
            }
            // 所有价格
            else
            {
                // 全部品牌
                if (0 == brandIndex.intValue())
                {
                    // 按销量排序
                    if (0 == orderId.intValue())
                    {
                        if (0 == soldId.intValue())
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndLeftNumberGreaterThanZeroAndParamsLikeAndIsOnSaleTrueOrderBySoldNumberDesc(
                                        categoryId, pageId, ClientConstant.pageSize, paramValueList);
                    
                        }
                        else
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndLeftNumberGreaterThanZeroAndParamsLikeAndIsOnSaleTrueOrderBySoldNumberAsc(
                                    categoryId, pageId, ClientConstant.pageSize, paramValueList);
                
                        }
                    }
                    // 按价格排序
                    else if (1 == orderId.intValue())
                    {
                        if (0 == priceId.intValue())
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndLeftNumberGreaterThanZeroAndParamsLikeAndIsOnSaleTrueOrderBySalePriceDesc(
                                        categoryId, pageId, ClientConstant.pageSize, paramValueList);
                    
                        }
                        else
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndLeftNumberGreaterThanZeroAndParamsLikeAndIsOnSaleTrueOrderBySalePriceAsc(
                                    categoryId, pageId, ClientConstant.pageSize, paramValueList);
                
                        }
                    }
                    // 按上架时间排序
                    else 
                    {
                        if (0 == timeId.intValue())
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndLeftNumberGreaterThanZeroAndParamsLikeAndIsOnSaleTrueOrderByOnSaleTimeDesc(
                                        categoryId, pageId, ClientConstant.pageSize, paramValueList);
                    
                        }
                        else
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndLeftNumberGreaterThanZeroAndParamsLikeAndIsOnSaleTrueOrderByOnSaleTimeAsc(
                                    categoryId, pageId, ClientConstant.pageSize, paramValueList);
                
                        }
                    }
                }
                else
                {
                    // 品牌、有货、按销量排序
                    if (0 == orderId.intValue())
                    {
                        if (0 == soldId.intValue())
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndBrandIdAndLeftNumberGreaterThanZeroAndParamsLikeAndIsOnSaleTrueOrderBySoldNumberDesc(
                                        categoryId, brandId, pageId, ClientConstant.pageSize, paramValueList);
                    
                        }
                        else
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndBrandIdAndLeftNumberGreaterThanZeroAndParamsLikeAndIsOnSaleTrueOrderBySoldNumberAsc(
                                    categoryId, brandId, pageId, ClientConstant.pageSize, paramValueList);
                
                        }
                    }
                    // 品牌、有货、按价格排序
                    else if (1 == orderId.intValue())
                    {
                        if (0 == priceId.intValue())
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndBrandIdAndLeftNumberGreaterThanZeroAndParamsLikeAndIsOnSaleTrueOrderBySalePriceDesc(
                                        categoryId, brandId, pageId, ClientConstant.pageSize, paramValueList);
                    
                        }
                        else
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndBrandIdAndLeftNumberGreaterThanZeroAndParamsLikeAndIsOnSaleTrueOrderBySalePriceAsc(
                                    categoryId, brandId, pageId, ClientConstant.pageSize, paramValueList);
                
                        }
                    }
                    // 品牌、有货、按上架时间排序
                    else 
                    {
                        if (0 == timeId.intValue())
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndBrandIdAndLeftNumberGreaterThanZeroAndParamsLikeAndIsOnSaleTrueOrderByOnSaleTimeDesc(
                                        categoryId, brandId, pageId, ClientConstant.pageSize, paramValueList);
                    
                        }
                        else
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndBrandIdAndLeftNumberGreaterThanZeroAndParamsLikeAndIsOnSaleTrueOrderByOnSaleTimeAsc(
                                    categoryId, brandId, pageId, ClientConstant.pageSize, paramValueList);
                
                        }
                    }
                }
            }
        }
        // 所有商品
        else
        {
         // 按价格区间
            if (null != priceLow && null != priceHigh)
            {
                // 全部品牌
                if (0 == brandIndex.intValue())
                {
                    // 价格区间、按销量排序
                    if (0 == orderId.intValue())
                    {
                        
                        if (0 == soldId.intValue())
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrueOrderBySoldNumberDesc(
                                        categoryId, priceLow, priceHigh, pageId, ClientConstant.pageSize, paramValueList);
                    
                        }
                        else
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrueOrderBySoldNumberAsc(
                                    categoryId, priceLow, priceHigh, pageId, ClientConstant.pageSize, paramValueList);
                
                        }
                    }
                    // 价格区间、按价格排序
                    else if (1 == orderId.intValue())
                    {
                        if (0 == priceId.intValue())
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrueOrderBySalePriceDesc(
                                        categoryId, priceLow, priceHigh, pageId, ClientConstant.pageSize, paramValueList);
                    
                        }
                        else
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrueOrderBySalePriceAsc(
                                    categoryId, priceLow, priceHigh, pageId, ClientConstant.pageSize, paramValueList);
                
                        }
                    }
                    // 价格区间、按上架时间排序
                    else 
                    {
                        if (0 == timeId.intValue())
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrueOrderByOnSaleTimeDesc(
                                        categoryId, priceLow, priceHigh, pageId, ClientConstant.pageSize, paramValueList);
                    
                        }
                        else
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrueOrderByOnSaleTimeAsc(
                                    categoryId, priceLow, priceHigh, pageId, ClientConstant.pageSize, paramValueList);
                
                        }
                    }
                }
                else
                {
                    // 品牌、价格区间、按销量排序
                    if (0 == orderId.intValue())
                    {
                        if (0 == soldId.intValue())
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndBrandIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrueOrderBySoldNumberDesc(
                                        categoryId, brandId, priceLow, priceHigh, pageId, ClientConstant.pageSize, paramValueList);
                    
                        }
                        else
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndBrandIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrueOrderBySoldNumberAsc(
                                    categoryId, brandId, priceLow, priceHigh, pageId, ClientConstant.pageSize, paramValueList);
                
                        }
                    }
                    // 品牌、价格区间、按价格排序
                    else if (1 == orderId.intValue())
                    {
                        if (0 == priceId.intValue())
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndBrandIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrueOrderBySalePriceDesc(
                                        categoryId, brandId, priceLow, priceHigh, pageId, ClientConstant.pageSize, paramValueList);
                    
                        }
                        else
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndBrandIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrueOrderBySalePriceAsc(
                                    categoryId, brandId, priceLow, priceHigh, pageId, ClientConstant.pageSize, paramValueList);
                
                        }
                    }
                    // 品牌、价格区间、按上架时间排序
                    else 
                    {
                        if (0 == timeId.intValue())
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndBrandIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrueOrderByOnSaleTimeDesc(
                                        categoryId, brandId, priceLow, priceHigh, pageId, ClientConstant.pageSize, paramValueList);
                    
                        }
                        else
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndBrandIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrueOrderByOnSaleTimeAsc(
                                    categoryId, brandId, priceLow, priceHigh, pageId, ClientConstant.pageSize, paramValueList);
                
                        }
                    }
                }
            }
            // 所有价格
            else
            {
                // 全部品牌
                if (0 == brandIndex.intValue())
                {
                    // 按销量排序
                    if (0 == orderId.intValue())
                    {
                        if (0 == soldId.intValue())
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndParamsLikeAndIsOnSaleTrueOrderBySoldNumberDesc(
                                        categoryId, pageId, ClientConstant.pageSize, paramValueList);
                    
                        }
                        else
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndParamsLikeAndIsOnSaleTrueOrderBySoldNumberAsc(
                                    categoryId, pageId, ClientConstant.pageSize, paramValueList);
                
                        }
                    }
                    // 按价格排序
                    else if (1 == orderId.intValue())
                    {
                        if (0 == priceId.intValue())
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndParamsLikeAndIsOnSaleTrueOrderBySalePriceDesc(
                                        categoryId, pageId, ClientConstant.pageSize, paramValueList);
                    
                        }
                        else
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndParamsLikeAndIsOnSaleTrueOrderBySalePriceAsc(
                                    categoryId, pageId, ClientConstant.pageSize, paramValueList);
                
                        }
                    }
                    // 按上架时间排序
                    else 
                    {
                        if (0 == timeId.intValue())
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndParamsLikeAndIsOnSaleTrueOrderByOnSaleTimeDesc(
                                        categoryId, pageId, ClientConstant.pageSize, paramValueList);
                    
                        }
                        else
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndParamsLikeAndIsOnSaleTrueOrderByOnSaleTimeAsc(
                                    categoryId, pageId, ClientConstant.pageSize, paramValueList);
                
                        }
                    }
                }
                else
                {
                    // 品牌、按销量排序
                    if (0 == orderId.intValue())
                    {
                        if (0 == soldId.intValue())
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndBrandIdAndParamsLikeAndIsOnSaleTrueOrderBySoldNumberDesc(
                                        categoryId, brandId, pageId, ClientConstant.pageSize, paramValueList);
                    
                        }
                        else
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndBrandIdAndParamsLikeAndIsOnSaleTrueOrderBySoldNumberAsc(
                                    categoryId, brandId, pageId, ClientConstant.pageSize, paramValueList);
                
                        }
                    }
                    // 品牌、按价格排序
                    else if (1 == orderId.intValue())
                    {
                        if (0 == priceId.intValue())
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndBrandIdAndParamsLikeAndIsOnSaleTrueOrderBySalePriceDesc(
                                        categoryId, brandId, pageId, ClientConstant.pageSize, paramValueList);
                    
                        }
                        else
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndBrandIdAndParamsLikeAndIsOnSaleTrueOrderBySalePriceAsc(
                                    categoryId, brandId, pageId, ClientConstant.pageSize, paramValueList);
                
                        }
                    }
                    // 品牌、按上架时间排序
                    else 
                    {
                        if (0 == timeId.intValue())
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndBrandIdAndParamsLikeAndIsOnSaleTrueOrderByOnSaleTimeDesc(
                                        categoryId, brandId, pageId, ClientConstant.pageSize, paramValueList);
                    
                        }
                        else
                        {
                            goodsPage = tdGoodsService.findByCategoryIdAndBrandIdAndParamsLikeAndIsOnSaleTrueOrderByOnSaleTimeAsc(
                                    categoryId, brandId, pageId, ClientConstant.pageSize, paramValueList);
                
                        }
                    }
                }
            }
        }
        
        map.addAttribute("goods_page", goodsPage);   
        
        return "/client/goods_list";
    }
}
