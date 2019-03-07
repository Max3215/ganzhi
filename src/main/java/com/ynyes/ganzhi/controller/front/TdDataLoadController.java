package com.ynyes.ganzhi.controller.front;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.ganzhi.entity.TdArticle;
import com.ynyes.ganzhi.entity.TdNavigationMenu;
import com.ynyes.ganzhi.service.TdArticleService;
import com.ynyes.ganzhi.service.TdCommonService;
import com.ynyes.ganzhi.service.TdNavigationMenuService;
import com.ynyes.ganzhi.util.SiteMagConstant;

@Controller
public class TdDataLoadController {
	
	@Autowired
    private TdCommonService tdCommonService;
	
	@Autowired
	private TdArticleService tdArticleService;
	
	@Autowired
	private TdNavigationMenuService tdNavigationMenuService;
	
	String filepath = SiteMagConstant.imagePath;
	@RequestMapping("/download")
	public String  down(HttpServletRequest req,ModelMap map,Integer page)
	{
		tdCommonService.setHeader(map, req);
		
		Long mid = 12L;
		TdNavigationMenu menu = tdNavigationMenuService.findOne(mid);
	    
	    map.addAttribute("menu_name", "资料下载");
	    map.addAttribute("menu_id", menu.getId()); //菜单id zhangji
	    map.addAttribute("menu_sub_name", "Download");//英文名称 zhangji
	    map.addAttribute("message", "下载");
		
		
		if(page != null)
		{
			map.addAttribute("load_data_page",tdArticleService.findByMenuId(83L, page, SiteMagConstant.pageSize));
			return "/client/download_content";
		}
		else
		{
			map.addAttribute("load_data_page",tdArticleService.findByMenuId(83L, 0, SiteMagConstant.pageSize));
		}
		
		return "/client/download";
	}
	
	@RequestMapping(value="/download/data", method = RequestMethod.GET)
    @ResponseBody
    public void download(String name,
                HttpServletResponse resp,
                HttpServletRequest req) throws IOException {
        if (null == name)
        {
            return;
        }
        
        OutputStream os = resp.getOutputStream();  
        
        File file = new File(filepath +"/" + name);
        
        if (file.exists())
        {
            try {
                resp.reset();
                resp.setHeader("Content-Disposition", "attachment; filename="
                        + name);
                resp.setContentType("application/octet-stream; charset=utf-8");
                os.write(FileUtils.readFileToByteArray(file));
                os.flush();
            } finally {
                if (os != null) {
                    os.close();
                }
            }
        }
    }
	
	//下载文件
	@RequestMapping(value="/downloads/info/{id}", method = RequestMethod.GET)
	@ResponseBody
	public void downloads(@PathVariable Long id, HttpServletResponse resp, HttpServletRequest req) throws IOException {
		if (null == id) {
            return;
        }
		
		TdArticle article = tdArticleService.findOne(id);
		if(null == article){
			return ;
		}
		
		String path = SiteMagConstant.linkPath ;

        FileInputStream fis = new FileInputStream(path + article.getLinkUrl());

        int size = fis.available(); // 得到文件大小

        byte data[] = new byte[size];

        fis.read(data); // 读数据

        fis.close();

        resp.setHeader("Content-Disposition", "attachment; filename="
				+URLEncoder.encode(article.getLinkUrl(), "UTF-8"));

        OutputStream os = resp.getOutputStream();
        os.write(data);
        os.flush();
        os.close();
	}
}
