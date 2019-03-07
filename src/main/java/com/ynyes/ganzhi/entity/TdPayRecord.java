package com.ynyes.ganzhi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;
/*
 * 在线支付支付记录
 */
@Entity
public class TdPayRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // 订单
    @Column(name="tdOrderId")
    private Long orderId;
    
    // 支付方式
    @Column(name="tdPayTypeId")
    private Long payTypeId;
    
    // 支付状态1: 等待支付2: 支付成功3: 支付失败
    @Column(name="statusCode")
    private Integer statusCode;
    
    //支付时间
    @Column(name="payTime")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getPayTypeId() {
        return payTypeId;
    }

    public void setPayTypeId(Long payTypeId) {
        this.payTypeId = payTypeId;
    }
    
    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
