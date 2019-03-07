package com.ynyes.ganzhi.controller.front;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.ganzhi.entity.TdArticle;
import com.ynyes.ganzhi.entity.TdArticleCategory;
import com.ynyes.ganzhi.entity.TdDemand;
import com.ynyes.ganzhi.entity.TdNavigationMenu;
import com.ynyes.ganzhi.service.TdArticleCategoryService;
import com.ynyes.ganzhi.service.TdArticleService;
import com.ynyes.ganzhi.service.TdCommonService;
import com.ynyes.ganzhi.service.TdDemandService;
import com.ynyes.ganzhi.service.TdNavigationMenuService;

@Controller
@RequestMapping({"/info"})
public class TdInfoController
{

  @Autowired
  private TdArticleService tdArticleService;

  @Autowired
  private TdArticleCategoryService tdArticleCategoryService;

  @Autowired
  private TdNavigationMenuService tdNavigationMenuService;

  @Autowired
  private TdCommonService tdCommonService;

  @Autowired
  private TdDemandService tdDemandService;

  @RequestMapping({"/list/{mid}"})
  public String infoList(@PathVariable Long mid, Long catId, String __EVENTTARGET, String __EVENTARGUMENT, Integer page, ModelMap map, HttpServletRequest req)
  {
    this.tdCommonService.setHeader(map, req);

    if (__EVENTTARGET != null)
    {
      if (__EVENTTARGET.equalsIgnoreCase("btnPage"))
      {
        if (__EVENTARGUMENT != null)
        {
          page = Integer.valueOf(Integer.parseInt(__EVENTARGUMENT));
        }

      }

    }

    if (page == null)
    {
      page = Integer.valueOf(0);
    }

    if (mid == null)
    {
      return "/client/error_404";
    }

    TdNavigationMenu menu = this.tdNavigationMenuService.findOne(mid);

    map.addAttribute("menu_name", menu.getTitle());
    map.addAttribute("menu_id", menu.getId());
    map.addAttribute("menu_sub_name", menu.getName());

    List<TdArticleCategory> catList = this.tdArticleCategoryService.findByMenuId(mid);

    if ((catList != null) && (catList.size() > 0))
    {
      if ((catId == null) || (0L == catId.longValue()))
      {
        if (menu.getTitle().equals("园区概况"))
        {
          map.addAttribute("info_page", this.tdArticleService.findByMenuIdAndIsEnableOrderByIdDesc(mid, page.intValue(), 12));
        }
        else if (menu.getTitle().equals("优惠政策"))
        {
          map.addAttribute("info_page", this.tdArticleService.findByMenuIdAndIsEnableOrderByIdDesc(mid, page.intValue(), 20));
        }
        else
        {
          map.addAttribute("info_page", this.tdArticleService.findByMenuIdAndIsEnableOrderByIdDesc(mid, page.intValue(), 20));
        }

      }
      else if ((menu.getTitle().equals("园区概况")) || (menu.getTitle().equals("服务体系")) || (menu.getTitle().equals("创业导师")))
      {
        map.addAttribute("info_page", this.tdArticleService.findByMenuIdAndCategoryId(mid, catId, page.intValue(), 12));
      }
      else if (menu.getTitle().equals("优惠政策"))
      {
        map.addAttribute("info_page", this.tdArticleService.findByMenuIdAndCategoryIdAndIsEnableOrderByIdDesc(mid, catId, page.intValue(), 20));
      }
      else
      {
        map.addAttribute("info_page", this.tdArticleService.findByMenuIdAndCategoryIdAndIsEnableOrderByIdDesc(mid, catId, page.intValue(), 20));
      }

    }

    if ((13L == mid.longValue()) && (catId == null))
    {
      catId = ((TdArticleCategory)catList.get(0)).getId();
    }

    map.addAttribute("info_name", this.tdArticleCategoryService.findOne(catId));
    map.addAttribute("catId", catId);
    map.addAttribute("mid", mid);
    map.addAttribute("info_category_list", catList);
    map.addAttribute("latest_info_page", this.tdArticleService.findByMenuIdAndIsEnableOrderByIdDesc(mid, page.intValue(), 20));

    return "/client/info_list";
  }

  @RequestMapping({"/list/content/{id}"})
  public String content(@PathVariable Long id, Long mid, ModelMap map, HttpServletRequest req)
  {
    this.tdCommonService.setHeader(map, req);

    if ((id == null) || (mid == null))
    {
      return "/client/error_404";
    }

    TdNavigationMenu menu = this.tdNavigationMenuService.findOne(mid);

    map.addAttribute("menu_name", menu.getTitle());
    map.addAttribute("menu_id", menu.getId());
    map.addAttribute("menu_sub_name", menu.getName());

    List<TdArticleCategory> catList = this.tdArticleCategoryService.findByMenuId(mid);

    map.addAttribute("info_category_list", catList);
    map.addAttribute("mid", mid);

    TdArticle tdArticle = this.tdArticleService.findOne(id);

    Long viewCount = tdArticle.getViewCount();
    tdArticle.setViewCount(Long.valueOf(viewCount.longValue() + 1L));
    this.tdArticleService.save(tdArticle);

    Long catId = tdArticle.getCategoryId();
    map.addAttribute("info_name", this.tdArticleCategoryService.findOne(catId));
    map.addAttribute("catId", catId);

    if (tdArticle != null)
    {
      map.addAttribute("id", id);
      map.addAttribute("info", tdArticle);
      map.addAttribute("prev_info", this.tdArticleService.findPrevOne(id, tdArticle.getCategoryId(), tdArticle.getMenuId()));
      map.addAttribute("next_info", this.tdArticleService.findNextOne(id, tdArticle.getCategoryId(), tdArticle.getMenuId()));
    }

    map.addAttribute("latest_info_page", this.tdArticleService.findByMenuIdAndIsEnableOrderByIdDesc(mid, 0, 6));

    return "/client/info_list_content";
  }

  @RequestMapping({"/list/content/cat/{catId}"})
  public String contentcat(@PathVariable Long catId, Long mid, ModelMap map, HttpServletRequest req)
  {
    this.tdCommonService.setHeader(map, req);

    if ((catId == null) || (mid == null))
    {
      return "/client/error_404";
    }
    Long id = ((TdArticle)this.tdArticleService.findByCategoryId(catId).get(0)).getId();

    TdNavigationMenu menu = this.tdNavigationMenuService.findOne(mid);

    map.addAttribute("menu_name", menu.getTitle());
    map.addAttribute("menu_id", menu.getId());
    map.addAttribute("menu_sub_name", menu.getName());

    List<TdArticleCategory> catList = this.tdArticleCategoryService.findByMenuId(mid);

    map.addAttribute("info_category_list", catList);
    map.addAttribute("mid", mid);

    TdArticle tdArticle = this.tdArticleService.findOne(id);

    Long viewCount = tdArticle.getViewCount();
    tdArticle.setViewCount(Long.valueOf(viewCount.longValue() + 1L));
    this.tdArticleService.save(tdArticle);

    map.addAttribute("info_name", this.tdArticleCategoryService.findOne(catId));
    map.addAttribute("catId", catId);

    if (tdArticle != null)
    {
      map.addAttribute("id", id);
      map.addAttribute("info", tdArticle);
      map.addAttribute("prev_info", this.tdArticleService.findPrevOne(id, tdArticle.getCategoryId(), tdArticle.getMenuId()));
      map.addAttribute("next_info", this.tdArticleService.findNextOne(id, tdArticle.getCategoryId(), tdArticle.getMenuId()));
    }

    map.addAttribute("latest_info_page", this.tdArticleService.findByMenuIdAndIsEnableOrderByIdDesc(mid, 0, 6));

    return "/client/info_list_content";
  }

  @RequestMapping({"/list/select"})
  public String infoListSelect(Long mid, Long catId, String __EVENTTARGET, String __EVENTARGUMENT, Integer page, ModelMap map, HttpServletRequest req)
  {
    this.tdCommonService.setHeader(map, req);

    if (__EVENTTARGET != null)
    {
      if (__EVENTTARGET.equalsIgnoreCase("btnPage"))
      {
        if (__EVENTARGUMENT != null)
        {
          page = Integer.valueOf(Integer.parseInt(__EVENTARGUMENT));
        }

      }

    }

    if (mid == null)
    {
      return "/client/error_404";
    }

    if (page == null)
    {
      page = Integer.valueOf(0);
    }

    TdNavigationMenu menu = this.tdNavigationMenuService.findOne(mid);

    map.addAttribute("menu_name", menu.getTitle());
    map.addAttribute("menu_id", menu.getId());
    map.addAttribute("menu_sub_name", menu.getName());

    List<TdArticleCategory> catList = this.tdArticleCategoryService.findByMenuId(mid);

    if ((catList != null) && (catList.size() > 0))
    {
      if ((catId == null) || (0L == catId.longValue()))
      {
        if ((menu.getTitle().equals("园区概况")) || (menu.getTitle().equals("创客空间")))
        {
          map.addAttribute("info_page", this.tdArticleService.findByMenuId(mid, page.intValue(), 12));
        }
        else if (menu.getTitle().equals("优惠政策"))
        {
          map.addAttribute("info_page", this.tdArticleService.findByMenuIdAndIsEnableOrderByIdDesc(mid, page.intValue(), 20));
        }
        else
        {
          map.addAttribute("info_page", this.tdArticleService.findByMenuIdAndIsEnableOrderByIdDesc(mid, page.intValue(), 20));
        }

      }
      else if ((menu.getTitle().equals("园区概况")) || (menu.getTitle().equals("服务体系")))
      {
        if ((catId.longValue() == 30L) || (catId.longValue() == 52L) || (catId.longValue() == 46L))
        {
          map.addAttribute("info_page", this.tdArticleService.findByMenuIdAndCategoryId(mid, catId, page.intValue(), 12));
        }
        else
        {
          map.addAttribute("info_page", this.tdArticleService.findByMenuIdAndCategoryIdAndIsEnableOrderByIdDesc(mid, catId, page.intValue(), 20));
        }
      }
      else if (menu.getTitle().equals("优惠政策"))
      {
        map.addAttribute("info_page", this.tdArticleService.findByMenuIdAndCategoryIdAndIsEnableOrderByIdDesc(mid, catId, page.intValue(), 20));
      }
      else
      {
        if (catId.longValue() == 59L)
        {
          Long id = ((TdArticle)this.tdArticleService.findByCategoryId(catId).get(0)).getId();
          TdArticle tdArticle = this.tdArticleService.findOne(id);

          Long viewCount = tdArticle.getViewCount();
          tdArticle.setViewCount(Long.valueOf(viewCount.longValue() + 1L));
          this.tdArticleService.save(tdArticle);

          if (tdArticle != null)
          {
            map.addAttribute("id", id);
            map.addAttribute("catId", catId);
            map.addAttribute("mid", mid);
            map.addAttribute("info_name", this.tdArticleCategoryService.findOne(catId));
            map.addAttribute("info", tdArticle);
            map.addAttribute("prev_info", this.tdArticleService.findPrevOne(id, tdArticle.getCategoryId(), tdArticle.getMenuId()));
            map.addAttribute("next_info", this.tdArticleService.findNextOne(id, tdArticle.getCategoryId(), tdArticle.getMenuId()));
          }

          return "/client/info_list_content_detail";
        }

        map.addAttribute("info_page", this.tdArticleService.findByMenuIdAndCategoryIdAndIsEnableOrderByIdDesc(mid, catId, page.intValue(), 20));
      }

    }

    map.addAttribute("info_name", this.tdArticleCategoryService.findOne(catId));
    map.addAttribute("catId", catId);
    map.addAttribute("mid", mid);
    map.addAttribute("info_category_list", catList);
    map.addAttribute("latest_info_page", this.tdArticleService.findByMenuIdAndIsEnableOrderByIdDesc(mid, page.intValue(), 20));

    return "/client/info_list_detail";
  }

  @RequestMapping({"/list/content/select"})
  public String contentSelect(Long id, Long mid, ModelMap map, HttpServletRequest req)
  {
    this.tdCommonService.setHeader(map, req);

    if ((id == null) || (mid == null))
    {
      return "/client/error_404";
    }

    TdNavigationMenu menu = this.tdNavigationMenuService.findOne(mid);

    map.addAttribute("menu_name", menu.getTitle());
    map.addAttribute("menu_id", menu.getId());
    map.addAttribute("menu_sub_name", menu.getName());

    List<TdArticleCategory> catList = this.tdArticleCategoryService.findByMenuId(mid);

    map.addAttribute("info_category_list", catList);
    map.addAttribute("mid", mid);

    TdArticle tdArticle = this.tdArticleService.findOne(id);

    Long catId = tdArticle.getCategoryId();
    map.addAttribute("info_name", this.tdArticleCategoryService.findOne(catId));
    map.addAttribute("catId", catId);

    Long viewCount = tdArticle.getViewCount();
    tdArticle.setViewCount(Long.valueOf(viewCount.longValue() + 1L));
    this.tdArticleService.save(tdArticle);

    if (tdArticle != null)
    {
      map.addAttribute("info", tdArticle);
      map.addAttribute("prev_info", this.tdArticleService.findPrevOne(id, tdArticle.getCategoryId(), tdArticle.getMenuId()));
      map.addAttribute("next_info", this.tdArticleService.findNextOne(id, tdArticle.getCategoryId(), tdArticle.getMenuId()));
    }

    map.addAttribute("latest_info_page", this.tdArticleService.findByMenuIdAndIsEnableOrderByIdDesc(mid, 0, 6));

    return "/client/info_list_content_detail";
  }

  @RequestMapping({"/entry/select"})
  public String serviceList(Long id, Long mid, Integer page, ModelMap map, HttpServletRequest req)
  {
    this.tdCommonService.setHeader(map, req);

    if ((id == null) || (mid == null))
    {
      return "/client/error_404";
    }

    map.addAttribute("mid", mid);

    TdArticle tdArticle = this.tdArticleService.findOne(id);

    Long catId = tdArticle.getCategoryId();
    map.addAttribute("info_name", this.tdArticleCategoryService.findOne(catId));

    map.addAttribute("info_page", this.tdArticleService
      .findByMenuIdAndCategoryIdAndIsEnableOrderByIdDesc(mid, 
      catId, 0, 6));

    if (tdArticle != null)
    {
      map.addAttribute("info", tdArticle);
      map.addAttribute("prev_info", this.tdArticleService.findPrevOne(id, tdArticle.getCategoryId(), tdArticle.getMenuId()));
      map.addAttribute("next_info", this.tdArticleService.findNextOne(id, tdArticle.getCategoryId(), tdArticle.getMenuId()));
    }

    map.addAttribute("latest_info_page", this.tdArticleService.findByMenuIdAndIsEnableOrderByIdDesc(mid, 0, 6));

    return "/client/info_entry_content_detail";
  }

  @RequestMapping({"/entry/content/{id}"})
  public String serviceContent(@PathVariable Long id, Long mid, ModelMap map, HttpServletRequest req)
  {
    this.tdCommonService.setHeader(map, req);

    if ((id == null) || (mid == null))
    {
      return "/client/error_404";
    }

    TdNavigationMenu menu = this.tdNavigationMenuService.findOne(mid);

    map.addAttribute("menu_name", menu.getTitle());
    map.addAttribute("menu_id", menu.getId());
    map.addAttribute("menu_sub_name", menu.getName());

    List<TdArticleCategory> catList = this.tdArticleCategoryService.findByMenuId(mid);

    map.addAttribute("info_category_list", catList);
    map.addAttribute("mid", mid);

    if (-1L == id.longValue())
    {
      for (TdArticleCategory tdCat : catList)
      {
        if ((tdCat.getTitle() != null) && (tdCat.getTitle().equals("关于我们")))
        {
          Long catId = tdCat.getId();
          List<TdArticle> profile = this.tdArticleService.findByCategoryId(catId);
          id = ((TdArticle)profile.get(0)).getId();
          break;
        }
      }

    }

    if (-2L == id.longValue())
    {
      for (TdArticleCategory tdCat : catList)
      {
        if ((tdCat.getTitle() != null) && (tdCat.getTitle().equals("服务项目")))
        {
          Long catId = tdCat.getId();
          List<TdArticle> profile = this.tdArticleService.findByCategoryId(catId);
          id = ((TdArticle)profile.get(0)).getId();
          break;
        }
      }

    }

    TdArticle tdArticle = this.tdArticleService.findOne(id);

    Long catId = tdArticle.getCategoryId();
    map.addAttribute("info_name", this.tdArticleCategoryService.findOne(catId));

    map.addAttribute("info_page", this.tdArticleService
      .findByMenuIdAndCategoryIdAndIsEnableOrderByIdAsc(mid, 
      catId, 0, 6));

    if (tdArticle != null)
    {
      map.addAttribute("info", tdArticle);
      map.addAttribute("prev_info", this.tdArticleService.findPrevOne(id, tdArticle.getCategoryId(), tdArticle.getMenuId()));
      map.addAttribute("next_info", this.tdArticleService.findNextOne(id, tdArticle.getCategoryId(), tdArticle.getMenuId()));
    }

    map.addAttribute("latest_info_page", this.tdArticleService.findByMenuIdAndIsEnableOrderByIdDesc(mid, 0, 6));

    return "/client/info_entry_content";
  }

  @RequestMapping({"/coursechoose"})
  public String coursechoose(Long id, Long mid, ModelMap map, HttpServletRequest req)
  {
    TdArticle article = this.tdArticleService.findOne(id);
    map.addAttribute("coursetake", article.getTitle());
    map.addAttribute("courseId", id);
    map.addAttribute("courseMid", mid);
    map.addAttribute("info", article);

    TdNavigationMenu menu = this.tdNavigationMenuService.findOne(mid);

    map.addAttribute("menu_name", menu.getTitle());
    map.addAttribute("menu_id", menu.getId());
    map.addAttribute("menu_sub_name", menu.getName());
    return "/client/info_list_detail";
  }

  @RequestMapping({"/submit"})
  @ResponseBody
  public Map<String, Object> setConsult(TdDemand userDemand, HttpServletRequest req) {
	  Map<String, Object> res = new HashMap<String, Object>();
    res.put("code", Integer.valueOf(1));
    if ((userDemand.getName() == null) || (userDemand.getName().equals("")))
    {
      res.put("message", "用户名不能为空");
      return res;
    }
    if (userDemand.getMobile().equals(""))
    {
      res.put("message", "手机号不能为空");
      return res;
    }
    if ((userDemand.getContent() == null) || (userDemand.getContent().equals("")))
    {
      res.put("message", "留言内容不能为空");
      return res;
    }

    userDemand.setTime(new Date());
    userDemand.setStatusId(Long.valueOf(1L));
    this.tdDemandService.save(userDemand);
    res.put("code", Integer.valueOf(0));
    return res;
  }

  @RequestMapping({"/coursechoose/content/{id}"})
  public String coursechooseContent(@PathVariable Long id, Long mid, ModelMap map, HttpServletRequest req)
  {
    this.tdCommonService.setHeader(map, req);

    if ((id == null) || (mid == null))
    {
      return "/client/error_404";
    }

    TdNavigationMenu menu = this.tdNavigationMenuService.findOne(mid);

    map.addAttribute("menu_name", "园区概况");
    map.addAttribute("menu_id", menu.getId());
    map.addAttribute("menu_sub_name", menu.getName());

    TdArticle tdArticle = this.tdArticleService.findOne(id);
    map.addAttribute("coursetake", tdArticle.getTitle());
    map.addAttribute("courseId", id);

    Long catId = tdArticle.getCategoryId();
    map.addAttribute("info_name", this.tdArticleCategoryService.findOne(catId));

    map.addAttribute("info_page", this.tdArticleService
      .findByMenuIdAndCategoryIdAndIsEnableOrderByIdAsc(mid, 
      catId, 0, 6));

    List<TdArticleCategory> catList = this.tdArticleCategoryService.findByMenuId(mid);

    map.addAttribute("info_category_list", catList);
    map.addAttribute("mid", mid);

    map.addAttribute("info_name", this.tdArticleCategoryService.findOne(catId));

    if (tdArticle != null)
    {
      map.addAttribute("info", tdArticle);
      map.addAttribute("prev_info", this.tdArticleService.findPrevOne(id, tdArticle.getCategoryId(), tdArticle.getMenuId()));
      map.addAttribute("next_info", this.tdArticleService.findNextOne(id, tdArticle.getCategoryId(), tdArticle.getMenuId()));
    }

    map.addAttribute("latest_info_page", this.tdArticleService.findByMenuIdAndIsEnableOrderByIdDesc(mid, 0, 6));

    return "/client/info_list";
  }

  @RequestMapping({"/map"})
  public String map(ModelMap map, HttpServletRequest req) {
    this.tdCommonService.setHeader(map, req);
    map.addAttribute("menu_name", "交通指南");
    map.addAttribute("menu_id", Long.valueOf(11L));
    map.addAttribute("menu_sub_name", "Map");
    map.addAttribute("message", "地图导航");
    map.addAttribute("back", "退出");
    return "/client/map";
  }
}