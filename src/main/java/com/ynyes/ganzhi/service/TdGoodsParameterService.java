package com.ynyes.ganzhi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.ganzhi.entity.TdGoodsParameter;
import com.ynyes.ganzhi.repository.TdGoodsParameterRepo;

/**
 * TdArticleCategory 服务类
 * 
 * @author Sharon
 *
 */

@Service
@Transactional
public class TdGoodsParameterService {
    @Autowired
    TdGoodsParameterRepo repository;
    
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
    public void delete(TdGoodsParameter e)
    {
        if (null != e)
        {
            repository.delete(e);
        }
    }
    
    public void delete(List<TdGoodsParameter> entities)
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
    public TdGoodsParameter findOne(Long id)
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
    public List<TdGoodsParameter> findAll(Iterable<Long> ids)
    {
        return (List<TdGoodsParameter>) repository.findAll(ids);
    }
    
    /**
     * 保存
     * 
     * @param e
     * @return
     */
    public TdGoodsParameter save(TdGoodsParameter e)
    {
        
        return repository.save(e);
    }
    
    public List<TdGoodsParameter> save(List<TdGoodsParameter> entities)
    {
        
        return (List<TdGoodsParameter>) repository.save(entities);
    }
}
