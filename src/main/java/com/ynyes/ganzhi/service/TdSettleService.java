package com.ynyes.ganzhi.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.ganzhi.entity.TdSettle;
import com.ynyes.ganzhi.repository.TdSettleRepo;

/**
 * TdKeywords 服务类
 * 
 * @author Sharon
 *
 */

@Service
@Transactional
public class TdSettleService {
    
    @Autowired
    TdSettleRepo repository;
    
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
    public void delete(TdSettle e)
    {
        if (null != e)
        {
            repository.delete(e);
        }
    }
    
    public void delete(List<TdSettle> entities)
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
    public TdSettle findOne(Long id)
    {
        if (null == id)
        {
            return null;
        }
        
        return repository.findOne(id);
    }
    
    /**
     * 查找
     * 
     * @param ids
     * @return
     */
    public List<TdSettle> findAll(Iterable<Long> ids)
    {
        return (List<TdSettle>) repository.findAll(ids);
    }
    
    public List<TdSettle> findAll()
    {
        return (List<TdSettle>) repository.findAll();
    }
    
    public Page<TdSettle> findAllOrderBySortIdAsc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "sortId"));
        
        return repository.findAll(pageRequest);
    }
    
    public List<TdSettle> findAllOrderBySortIdAsc()
    {
        return (List<TdSettle>) repository.findAll(new Sort(Direction.ASC, "sortId"));
    }
    
    public List<TdSettle> findByIsEnableTrueOrderBySortIdAsc()
    {
        return repository.findByIsEnableTrueOrderBySortIdAsc();
    }
    

    
    /**
     * 保存
     * 
     * @param e
     * @return
     */
    public TdSettle save(TdSettle e)
    {
        return repository.save(e);
    }
    
    public List<TdSettle> save(List<TdSettle> entities)
    {
        
        return (List<TdSettle>) repository.save(entities);
    }
    
}
