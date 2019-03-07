package com.ynyes.ganzhi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.ganzhi.entity.TdSite;
import com.ynyes.ganzhi.repository.TdSiteRepo;

/**
 * TdSite 服务类
 * 
 * @author Sharon
 *
 */

@Service
@Transactional
public class TdSiteService {
    
    @Autowired
    TdSiteRepo repository;
    
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
    public void delete(TdSite e)
    {
        if (null != e)
        {
            repository.delete(e);
        }
    }
    
    public void delete(List<TdSite> entities)
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
    public TdSite findOne(Long id)
    {
        if (null == id)
        {
            return null;
        }
        
        return repository.findOne(id);
    }
    
    public TdSite findByTitle(String title)
    {
        if (null == title)
        {
            return null;
        }
        
        return repository.findByTitle(title);
    }
    
    public TdSite findByTitleAndIdNot(String title, Long id)
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
    public List<TdSite> findAll(Iterable<Long> ids)
    {
        return (List<TdSite>) repository.findAll(ids);
    }
    
    public List<TdSite> findAll()
    {
        return (List<TdSite>) repository.findAll();
    }
    
    public List<TdSite> findByIsEnableTrue()
    {
        return repository.findByIsEnableTrue();
    }
    
    public Page<TdSite> findAllOrderBySortIdAsc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "sortId"));
        
        return repository.findAll(pageRequest);
    }
    
    public Page<TdSite> searchAndOrderBySortIdAsc(String keywords, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByTitleContainingOrderBySortIdAsc(keywords, pageRequest);
    }
    
    /**
     * 保存
     * 
     * @param e
     * @return
     */
    public TdSite save(TdSite e)
    {
        
        return repository.save(e);
    }
    
    public List<TdSite> save(List<TdSite> entities)
    {
        
        return (List<TdSite>) repository.save(entities);
    }
}
