package com.ynyes.ganzhi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.ganzhi.entity.TdParameterCategory;
import com.ynyes.ganzhi.repository.TdParameterCategoryRepo;

/**
 * TdParameterCategory 服务类
 * 
 * @author Sharon
 *
 */

@Service
@Transactional
public class TdParameterCategoryService {
    
    @Autowired
    TdParameterCategoryRepo repository;
    
    @Autowired
    TdProductCategoryService tdProductCategoryService;
    
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
    public void delete(TdParameterCategory e)
    {
        if (null != e)
        {
            repository.delete(e);
        }
    }
    
    public void delete(List<TdParameterCategory> entities)
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
    public TdParameterCategory findOne(Long id)
    {
        if (null == id)
        {
            return null;
        }
        
        return repository.findOne(id);
    }
    
    public TdParameterCategory findByTitleAndParentIdIsNull(String title)
    {
        if (null == title)
        {
            return null;
        }
        
        return repository.findByTitleAndParentIdIsNull(title);
    }
    
    public TdParameterCategory findByTitleAndParentIdIsNullAndIdNot(String title, Long id)
    {
        if (null == title || null == id)
        {
            return null;
        }
        
        return repository.findByTitleAndParentIdIsNullAndIdNot(title, id);
    }
    
    public TdParameterCategory findByTitleAndParentId(String title, Long parentId)
    {
        if (null == title || null == parentId)
        {
            return null;
        }
        
        return repository.findByTitleAndParentId(title, parentId);
    }
    
    public TdParameterCategory findByTitleAndParentIdAndIdNot(String title, Long parentId, Long id)
    {
        if (null == title || null == parentId || null == id)
        {
            return null;
        }
        
        return repository.findByTitleAndParentIdAndIdNot(title, parentId, id);
    }
    
    /**
     * 查找
     * 
     * @param ids
     * @return
     */
    public List<TdParameterCategory> findAll(Iterable<Long> ids)
    {
        return (List<TdParameterCategory>) repository.findAll(ids);
    }
    
    public List<TdParameterCategory> findAll()
    {
        List<TdParameterCategory> resList = new ArrayList<TdParameterCategory>();
        List<TdParameterCategory> topList = repository.findByParentIdIsNullOrderBySortIdAsc();
        
        for (TdParameterCategory top : topList)
        {
            resList.add(top);
            
            List<TdParameterCategory> childList = repository.findByParentIdOrderBySortIdAsc(top.getId());
            
            if (null != childList && childList.size() > 0)
            {
                for (TdParameterCategory child : childList)
                {
                    resList.add(child);
                    
                    List<TdParameterCategory> grandChildList = repository.findByParentIdOrderBySortIdAsc(child.getId());
                    
                    if (null != grandChildList && grandChildList.size() > 0)
                    {
                        resList.addAll(grandChildList);
                    }
                }
            }
        }
        
        return resList;
    }
    
    /**
     * 保存
     * 
     * @param e
     * @return
     */
    public TdParameterCategory save(TdParameterCategory e)
    {
        if (null == e.getId())
        {
            e = repository.save(e);
        }
        
        if (null == e.getParentId())
        {
            e.setParentTree("[" + e.getId() + "]");
            e.setLayerCount(1L);
        }
        else
        {
            TdParameterCategory parent = repository.findOne(e.getParentId());
            
            if (null != parent)
            {
                e.setParentTree(parent.getParentTree() + ",[" + e.getId() + "]");
                e.setLayerCount(parent.getLayerCount() + 1L);
            }
        }
        
        
        return repository.save(e);
    }
    
    public List<TdParameterCategory> save(List<TdParameterCategory> entities)
    {
        
        return (List<TdParameterCategory>) repository.save(entities);
    }
}
