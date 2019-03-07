package com.ynyes.ganzhi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.ganzhi.entity.TdSiteLink;
import com.ynyes.ganzhi.repository.TdSiteLinkRepo;

/**
 * TdSiteLink 服务类
 * 
 * @author Sharon
 *
 */

@Service
@Transactional
public class TdSiteLinkService {
    
    @Autowired
    TdSiteLinkRepo repository;
    
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
    public void delete(TdSiteLink e)
    {
        if (null != e)
        {
            repository.delete(e);
        }
    }
    
    public void delete(List<TdSiteLink> entities)
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
    public TdSiteLink findOne(Long id)
    {
        if (null == id)
        {
            return null;
        }
        
        return repository.findOne(id);
    }
    
    public TdSiteLink findByTitle(String title)
    {
        if (null == title)
        {
            return null;
        }
        
        return repository.findByTitle(title);
    }
    
    public TdSiteLink findByTitleAndIdNot(String title, Long id)
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
    public List<TdSiteLink> findAll(Iterable<Long> ids)
    {
        return (List<TdSiteLink>) repository.findAll(ids);
    }
    
    public List<TdSiteLink> findAll()
    {
        return (List<TdSiteLink>) repository.findAll();
    }
    
    public List<TdSiteLink> findByIsEnableTrueOrderBySortIdAsc()
    {
        return repository.findByIsEnableTrueOrderBySortIdAsc();
    }
    
    public Page<TdSiteLink> findAllOrderBySortIdAsc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "sortId"));
        
        return repository.findAll(pageRequest);
    }
    
    public Page<TdSiteLink> searchAndOrderBySortIdAsc(String keywords, int page, int size)
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
    public TdSiteLink save(TdSiteLink e)
    {
        
        return repository.save(e);
    }
    
    public List<TdSiteLink> save(List<TdSiteLink> entities)
    {
        
        return (List<TdSiteLink>) repository.save(entities);
    }
}
