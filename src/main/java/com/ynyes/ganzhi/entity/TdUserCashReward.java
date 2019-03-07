package com.ynyes.ganzhi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;


/**
 * 分销商返现记录
 * 
 * @author Sharon
 *
 */

@Entity
public class TdUserCashReward {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    // 分销商用户名
    @Column
    private String username;
    
    // 下级用户名
    @Column
    private String lowerUsername;
    
    // 返现时间
    @Column
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date rewardTime;
    
    // 返现额度
    @Column
    private Double cashReward;
    
    // 该笔积分发生后余额
    @Column
    private Long totalCashReward;
    
    // 涉及订单号
    @Column
    private String orderNumber;
    
    // 银行名
    @Column
    private String bankName;
    
    // 银行卡号
    @Column
    private String bankCardNumber;
    
    // 备注
    @Column
    private String detail;
    
    // 订单总金额
    @Column
    private Double orderPrice;
    
    // 排序号
    @Column
    private Long sortId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLowerUsername() {
        return lowerUsername;
    }

    public void setLowerUsername(String lowerUsername) {
        this.lowerUsername = lowerUsername;
    }

    public Date getRewardTime() {
        return rewardTime;
    }

    public void setRewardTime(Date rewardTime) {
        this.rewardTime = rewardTime;
    }

    public Double getCashReward() {
        return cashReward;
    }

    public void setCashReward(Double cashReward) {
        this.cashReward = cashReward;
    }

    public Long getTotalCashReward() {
        return totalCashReward;
    }

    public void setTotalCashReward(Long totalCashReward) {
        this.totalCashReward = totalCashReward;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Long getSortId() {
        return sortId;
    }

    public void setSortId(Long sortId) {
        this.sortId = sortId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCardNumber() {
        return bankCardNumber;
    }

    public void setBankCardNumber(String bankCardNumber) {
        this.bankCardNumber = bankCardNumber;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
    
}
