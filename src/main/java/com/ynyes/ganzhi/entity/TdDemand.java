package com.ynyes.ganzhi.entity;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;
/**
 * 车友还想团购
 * 
 * @author zhangji
 */
@Entity
public class TdDemand {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//要求团购的内容
	@Column
	private String content;
	
	//提交时间
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date time;
	
	//称呼
	@Column
	private String name;
	
	//邮箱
	@Column
	private String mail;
	
	//联系方式:电话
	@Column
	private String mobile;
	
	//联系方式：qq
	@Column
	private String qq;
	
	//课程名称
	@Column
	private String courseName;
	
    // 显示状态
    @Column
    private Long statusId;
    
    //课程id
  	@Column
  	private Long courseId;
  	
    //备注
  	@Column
  	private String remarkInfo;
    	
	
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public String getRemarkInfo() {
		return remarkInfo;
	}

	public void setRemarkInfo(String remarkInfo) {
		this.remarkInfo = remarkInfo;
	}
	
    
}
