package com.ynyes.ganzhi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.ganzhi.entity.TdWarehouse;
import com.ynyes.ganzhi.repository.TdWarehouseRepo;

/**
 * TdWarehouse 服务类
 * 
 * @author Sharon
 *
 */

@Service
@Transactional
public class TdWarehouseService {
    
    @Autowired
    TdWarehouseRepo repository;
    
    /**
     * 删除
     * 
     * @param id 菜单项ID
     */
    public void delete(Long id)
    {
        if (null != id)
        {
            repository.delete(id);
        }
    }
    
    /**
     * 删除
     * 
     * @param e 菜单项
     */
    public void delete(TdWarehouse e)
    {
        if (null != e)
        {
            repository.delete(e);
        }
    }
    
    public void delete(List<TdWarehouse> entities)
    {
        if (null != entities)
        {
            repository.delete(entities);
        }
    }
    
    /**
     * 查找
     * 
     * @param id ID
     * @return
     */
    public TdWarehouse findOne(Long id)
    {
        if (null == id)
        {
            return null;
        }
        
        return repository.findOne(id);
    }
    
    public TdWarehouse findByTitle(String title)
    {
        if (null == title)
        {
            return null;
        }
        
        return repository.findByTitle(title);
    }
    
    public TdWarehouse findByTitleAndIdNot(String title, Long id)
    {
        if (null == title || null == id)
        {
            return null;
        }
        
        return repository.findByTitleAndIdNot(title, id);
    }
    
    /**
     * 查找
     * 
     * @param ids
     * @return
     */
    public List<TdWarehouse> findAll(Iterable<Long> ids)
    {
        return (List<TdWarehouse>) repository.findAll(ids);
    }
    
    public List<TdWarehouse> findAll()
    {
        return (List<TdWarehouse>) repository.findAll();
    }
    
    public Page<TdWarehouse> findAllOrderBySortIdAsc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "sortId"));
        
        return repository.findAll(pageRequest);
    }
    
    public Page<TdWarehouse> searchAndOrderBySortIdAsc(String keywords, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByTitleContainingOrProvinceContainingOrCityContainingOrAddressContainingOrderBySortIdAsc(keywords, keywords, keywords, keywords, pageRequest);
    }
    
    /**
     * 保存
     * 
     * @param e
     * @return
     */
    public TdWarehouse save(TdWarehouse e)
    {
        
        return repository.save(e);
    }
    
    public List<TdWarehouse> save(List<TdWarehouse> entities)
    {
        
        return (List<TdWarehouse>) repository.save(entities);
    }
}
