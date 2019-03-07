package com.ynyes.ganzhi.util;

import java.util.Properties;

/**
 * 后台常用常量
 * @author Sharon
 *
 */
public class SiteMagConstant {

	 public static final int pageSize = 20;
	 public static final String templatePath = "src/main/resources/templates/client/";
	 public static final String backupPath;
	 public static final String imagePath;
	 public static final String linkPath;

	  static
	  {
	    Properties props = System.getProperties();
	    String operation = props.getProperty("os.name");
	    if (operation.contains("Linux")) {
	      backupPath = "/mnt/root/backup/ganzhi";
	      imagePath = "/mnt/root/images/goods";
	      linkPath = "/mnt/root/images/goods";
	    } else {
	      backupPath = "src/main/resources/backup/";
	      imagePath = "src/main/resources/static/images";
	      linkPath = "src/main/resources/static/images";
	    }
	  }
    
//    public static final String backupPath = "/root/backup/";
//    public static final String imagePath = "/root/images/goods";
}
