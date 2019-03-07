package com.ynyes.ganzhi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.ganzhi.entity.TdDemand;
import com.ynyes.ganzhi.entity.TdOrder;
import com.ynyes.ganzhi.repository.TdDemandRepo;


/**
 * TdDemand 服务类
 * @author Zhangji
 *
 */

@Service
@Transactional
public class TdDemandService {
	@Autowired
	TdDemandRepo repository;

	public List<TdDemand> findAll(){
	        return (List<TdDemand>) repository.findAll();
	}
	
	public List<TdDemand> findByNameOrderByTimeDesc(){
		
		return (List<TdDemand>) repository.findAll();		
	}
	
    public List<TdDemand> findByStatusId(Long statusId){
    	return repository.findByStatusId(statusId);
    }

    public Page<TdDemand> findByStatusIdOrderByIdDesc(long statusId, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByStatusIdOrderByIdDesc(statusId, pageRequest);
    }
    
    public Page<TdDemand> findAllOrderByIdDesc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
        
        return repository.findAll(pageRequest);
    }
	
	//筛选statusId为1L的
    public List<TdDemand> findByStatusIdAndIsShowable()
    {

       
        
        return repository.findByStatusIdOrderByIdDesc(1L);
    }
    

	public TdDemand findOne(Long id)
    {
		if(null == id)
		{
			return null;
		}
		
        return repository.findOne(id);
    }
	
	public Page<TdDemand> findAllOrderByTimeDesc(int page,int size){

		PageRequest pageRequest = new PageRequest(page,size,new Sort(Direction.DESC,"time"));
		return repository.findAll(pageRequest);
	}
	



	/**
	 * 添加
	 */

	public void save(TdDemand e){
		repository.save(e);
	}
	
	 public List<TdDemand> save(List<TdDemand> entities)
	 {
	        
	     return (List<TdDemand>) repository.save(entities);
  }
	/**
	 * 删除
	 */
    public void delete(Long id)
    {
        if (null != id)
        {
            repository.delete(id);
        }
    }



}
