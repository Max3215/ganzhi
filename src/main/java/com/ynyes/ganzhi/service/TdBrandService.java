package com.ynyes.ganzhi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.ganzhi.entity.TdBrand;
import com.ynyes.ganzhi.entity.TdProductCategory;
import com.ynyes.ganzhi.repository.TdBrandRepo;

/**
 * TdBrand 服务类
 * 
 * @author Sharon
 *
 */

@Service
@Transactional
public class TdBrandService {
    
    @Autowired
    TdBrandRepo repository;
    
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
    public void delete(TdBrand e)
    {
        if (null != e)
        {
            repository.delete(e);
        }
    }
    
    public void delete(List<TdBrand> entities)
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
    public TdBrand findOne(Long id)
    {
        if (null == id)
        {
            return null;
        }
        
        return repository.findOne(id);
    }
    
    public TdBrand findByProductCategoryTreeContainingAndTitle(Long productCategoryId, String title)
    {
        if (null == title || null == productCategoryId)
        {
            return null;
        }
        
        return repository.findByProductCategoryTreeContainingAndTitle("["+productCategoryId+"]", title);
    }
    
    public List<TdBrand> findByProductCategoryTreeContaining(Long productCategoryId)
    {
        if (null == productCategoryId)
        {
            return null;
        }
        
        return repository.findByProductCategoryTreeContaining("["+productCategoryId+"]");
    }
    
    public Page<TdBrand> findByStatusIdAndProductCategoryTreeContaining(Long statusId, Long productCategoryId, int page, int size)
    {
        if (null == productCategoryId && null == statusId)
        {
            return null;
        }
        
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByStatusIdAndProductCategoryTreeContaining(statusId, "["+productCategoryId+"]", pageRequest);
    }
    
    public List<TdBrand> findByStatusIdAndProductCategoryTreeContaining(Long statusId, Long productCategoryId)
    {
        if (null == productCategoryId && null == statusId)
        {
            return null;
        }
        
        return repository.findByStatusIdAndProductCategoryTreeContaining(statusId, "["+productCategoryId+"]");
    }
    
    public TdBrand findByTitle(String title)
    {
        if (null == title)
        {
            return null;
        }
        
        return repository.findByTitle(title);
    }
    
    public TdBrand findByProductCategoryTreeContainingAndTitleAndIdNot(Long productCategoryId, String title, Long id)
    {
        if (null == title || null == productCategoryId || null == id)
        {
            return null;
        }
        
        return repository.findByProductCategoryTreeContainingAndTitleAndIdNot("["+productCategoryId+"]", title, id);
    }
    
    public TdBrand findByTitleAndIdNot(String title, Long id)
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
    public List<TdBrand> findAll(Iterable<Long> ids)
    {
        return (List<TdBrand>) repository.findAll(ids);
    }
    
    public List<TdBrand> findAll()
    {
        return (List<TdBrand>) repository.findAll();
    }
    
    public Page<TdBrand> findByStatusIdOrderBySortIdAsc(Long statusId, int page, int size)
    {
        if (null == statusId)
        {
            return null;
        }
        
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByStatusIdOrderBySortIdAsc(statusId, pageRequest);
    }
    
    public Page<TdBrand> findAllOrderBySortIdAsc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "sortId"));
        
        return repository.findAll(pageRequest);
    }
    
    public Page<TdBrand> searchAndOrderBySortIdAsc(String keywords, int page, int size)
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
    public TdBrand save(TdBrand e)
    {
        
        if (null != e.getProductCategoryId())
        {
            TdProductCategory cat = tdProductCategoryService.findOne(e.getProductCategoryId());
            
            if (null != cat)
            {
                e.setProductCategoryTree(cat.getParentTree());
            }
        }
        
        return repository.save(e);
    }
    
    public List<TdBrand> save(List<TdBrand> entities)
    {
        
        return (List<TdBrand>) repository.save(entities);
    }
}
