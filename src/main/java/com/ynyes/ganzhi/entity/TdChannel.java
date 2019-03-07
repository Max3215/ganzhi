package com.ynyes.ganzhi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * 频道
 * 
 * @author Sharon
 *
 */

@Entity
public class TdChannel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    // 名称
    @Column
    private String name;
    
    // 标题
    @Column
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
