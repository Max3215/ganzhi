package com.ynyes.ganzhi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.ganzhi.entity.TdUserReturn;
import com.ynyes.ganzhi.repository.TdUserReturnRepo;

/**
 * TdUserReturn 服务类
 * 
 * @author Sharon
 *
 */

@Service
@Transactional
public class TdUserReturnService {
    
    @Autowired
    TdUserReturnRepo repository;
    
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
    public void delete(TdUserReturn e)
    {
        if (null != e)
        {
            repository.delete(e);
        }
    }
    
    public void delete(List<TdUserReturn> entities)
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
    public TdUserReturn findOne(Long id)
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
    public List<TdUserReturn> findAll(Iterable<Long> ids)
    {
        return (List<TdUserReturn>) repository.findAll(ids);
    }
    
    public List<TdUserReturn> findByUsername(String username)
    {
        return repository.findByUsernameOrderByIdDesc(username);
    }
    
    public Page<TdUserReturn> findByUsername(String username, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        return repository.findByUsernameOrderByIdDesc(username, pageRequest);
    }
    
    public Page<TdUserReturn> findByUsernameAndSearch(String username, String keywords, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        return repository.findByUsernameAndGoodsTitleContainingOrderByIdDesc(username, keywords, pageRequest);
    }
    
    public Page<TdUserReturn> findAllOrderBySortIdAsc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "sortId"));
        
        return repository.findAll(pageRequest);
    }
    
    public Page<TdUserReturn> findByStatusIdOrderBySortIdAsc(Long statusId, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByStatusIdOrderBySortIdAsc(statusId, pageRequest);
    }
    
    public Page<TdUserReturn> searchAndFindByStatusIdOrderBySortIdAsc(String keywords, Long statusId, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByUsernameContainingAndStatusIdOrGoodsTitleContainingAndStatusIdOrOrderNumberContainingAndStatusIdOrderBySortIdAsc(keywords, statusId, keywords, statusId, keywords, statusId, pageRequest);
    }
    
    public Page<TdUserReturn> searchAndOrderBySortIdAsc(String keywords, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByUsernameContainingOrGoodsTitleContainingOrOrderNumberContainingOrderBySortIdAsc(keywords, keywords, keywords, pageRequest);
    }
    
    /**
     * 保存
     * 
     * @param e
     * @return
     */
    public TdUserReturn save(TdUserReturn e)
    {
        
        return repository.save(e);
    }
    
    public List<TdUserReturn> save(List<TdUserReturn> entities)
    {
        
        return (List<TdUserReturn>) repository.save(entities);
    }
}
