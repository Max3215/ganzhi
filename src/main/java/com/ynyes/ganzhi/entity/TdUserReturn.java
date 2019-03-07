package com.ynyes.ganzhi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;


/**
 * 用户退换货记录
 * 
 * @author Sharon
 *
 */

@Entity
public class TdUserReturn {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
	// 用户名
	@Column
	private String username;
	
	// 订单号
    @Column
    private String orderNumber;
    
    // 商品Id
    @Column
    private Long goodsId;
	
	// 商品名称
    @Column
    private String goodsTitle;
    
    // 商品封面
    @Column
    private String goodsCoverImageUri;
	
	// 商品成交价
	@Column(scale=2)
	private Double goodsPrice;
	
	// 退货/换货数量
    @Column
    private Long returnNumber;
	
	// 退货？否时为换货
	@Column
	private Boolean isReturn;
	
	// 退货原因
	@Column
	private String reason;
	
	// 联系电话
	@Column
	private String telephone;
	
	// 退货时间
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date returnTime;
	
	// 商品寄回时间
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date goodsReturnTime;
	
	// 退货物流单号
    @Column
    private String returnDeliveryNumber;
    
    // 换货时新商品发货物流单号
    @Column
    private String newGoodsDeliveryNumber;
    
	// 退货/换货状态
	@Column
	private Long statusId;
	
    // 排序号
    @Column
    private Long sortId;
    
    // 处理详情
    @Column
    private String handleDetail;

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

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsTitle() {
        return goodsTitle;
    }

    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle;
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Long getReturnNumber() {
        return returnNumber;
    }

    public void setReturnNumber(Long returnNumber) {
        this.returnNumber = returnNumber;
    }

    public Boolean getIsReturn() {
        return isReturn;
    }

    public void setIsReturn(Boolean isReturn) {
        this.isReturn = isReturn;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    public Date getGoodsReturnTime() {
        return goodsReturnTime;
    }

    public void setGoodsReturnTime(Date goodsReturnTime) {
        this.goodsReturnTime = goodsReturnTime;
    }

    public String getReturnDeliveryNumber() {
        return returnDeliveryNumber;
    }

    public void setReturnDeliveryNumber(String returnDeliveryNumber) {
        this.returnDeliveryNumber = returnDeliveryNumber;
    }

    public String getNewGoodsDeliveryNumber() {
        return newGoodsDeliveryNumber;
    }

    public void setNewGoodsDeliveryNumber(String newGoodsDeliveryNumber) {
        this.newGoodsDeliveryNumber = newGoodsDeliveryNumber;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public Long getSortId() {
        return sortId;
    }

    public void setSortId(Long sortId) {
        this.sortId = sortId;
    }

    public String getHandleDetail() {
        return handleDetail;
    }

    public void setHandleDetail(String handleDetail) {
        this.handleDetail = handleDetail;
    }

    public String getGoodsCoverImageUri() {
        return goodsCoverImageUri;
    }

    public void setGoodsCoverImageUri(String goodsCoverImageUri) {
        this.goodsCoverImageUri = goodsCoverImageUri;
    }
}
