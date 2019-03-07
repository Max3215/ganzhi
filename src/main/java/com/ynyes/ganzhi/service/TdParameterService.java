package com.ynyes.ganzhi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.ganzhi.entity.TdParameter;
import com.ynyes.ganzhi.entity.TdParameterCategory;
import com.ynyes.ganzhi.repository.TdParameterRepo;

/**
 * TdParameter 服务类
 * 
 * @author Sharon
 *
 */

@Service
@Transactional
public class TdParameterService {
    
    @Autowired
    TdParameterRepo repository;
    
    @Autowired
    TdParameterCategoryService tdParameterCategoryService;
    
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
    public void delete(TdParameter e)
    {
        if (null != e)
        {
            repository.delete(e);
        }
    }
    
    public void delete(List<TdParameter> entities)
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
    public TdParameter findOne(Long id)
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
    public List<TdParameter> findAll(Iterable<Long> ids)
    {
        return (List<TdParameter>) repository.findAll(ids);
    }
    
    public List<TdParameter> findAll()
    {
        return (List<TdParameter>) repository.findAll();
    }
    
    public List<TdParameter> findByCategoryTreeContainingAndIsSearchableTrue(Long categoryId)
    {
        if (null == categoryId)
        {
            return null;
        }
        
        return repository.findByCategoryTreeContainingAndInputTypeAndIsSearchableTrue("["+categoryId+"]", 1L);
    }
    
    public Page<TdParameter> findAllOrderBySortIdAsc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "sortId"));
        
        return repository.findAll(pageRequest);
    }
    
    public TdParameter findByTitleAndCategoryId(String title, Long categoryId)
    {
        if (null == title || null == categoryId)
        {
            return null;
        }
        
        return repository.findByTitleAndCategoryId(title, categoryId);
    }
    
    
    public Page<TdParameter> findByCategoryTreeContainingOrderBySortIdAsc(Long categoryId, int page, int size)
    {
        if (null == categoryId)
        {
            return null;
        }
        
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByCategoryTreeContainingOrderBySortIdAsc("[" + categoryId + "]", pageRequest);
    }
    
    public TdParameter findByTitleAndCategoryIdAndIdNot(String title, Long categoryId, Long id)
    {
        if (null == title || null == categoryId || null == id)
        {
            return null;
        }
        
        return repository.findByTitleAndCategoryIdAndIdNot(title, categoryId, id);
    }
    
    public Page<TdParameter> searchOrderBySortIdAsc(String keywords, int page, int size)
    {
        if (null == keywords)
        {
            return null;
        }
        
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByTitleContainingOrderBySortIdAsc(keywords, pageRequest);
    }
    
    public List<TdParameter> findByCategoryId(Long categoryId)
    {
        if (null == categoryId)
        {
            return null;
        }
        return repository.findByCategoryId(categoryId);
    }
    
    public List<TdParameter> findByCategoryTreeContaining(Long categoryId)
    {
        if (null == categoryId)
        {
            return null;
        }
        
        return repository.findByCategoryTreeContaining("[" + categoryId + "]");
    }
    
    public int countByCategoryTreeContaining(Long categoryId)
    {
        if (null == categoryId)
        {
            return 0;
        }
        
        return repository.countByCategoryTreeContaining("[" + categoryId + "]");
    }
    
    /**
     * 保存
     * 
     * @param e
     * @return
     */
    public TdParameter save(TdParameter e)
    {
        if (null != e.getCategoryId())
        {
            TdParameterCategory cat = tdParameterCategoryService.findOne(e.getCategoryId());
            
            if (null != cat)
            {
                e.setCategoryTree(cat.getParentTree());
                e.setCategoryTitle(cat.getTitle());
            }
        }
        
        return repository.save(e);
    }
    
    public List<TdParameter> save(List<TdParameter> entities)
    {
        
        return (List<TdParameter>) repository.save(entities);
    }
}
