package com.ynyes.ganzhi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * 商家
 * 
 * @author Sharon
 *
 */

@Entity
public class TdShop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
    // 名称
    @Column(unique=true)
    private String title;
    
    // 省
    @Column
    private String province;
    
    // 市
    @Column
    private String city;
    
	// 详细地址
	@Column
	private String address;
    
    // 排序号
    @Column
    private Long sortId;

}
