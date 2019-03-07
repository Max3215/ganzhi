package com.ynyes.ganzhi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.ganzhi.entity.TdUserCashReward;
import com.ynyes.ganzhi.repository.TdUserCashRewardRepo;

/**
 * TdUserCashReward 服务类
 * 
 * @author Sharon
 *
 */

@Service
@Transactional
public class TdUserCashRewardService {
    
    @Autowired
    TdUserCashRewardRepo repository;
    
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
    public void delete(TdUserCashReward e)
    {
        if (null != e)
        {
            repository.delete(e);
        }
    }
    
    public void delete(List<TdUserCashReward> entities)
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
    public TdUserCashReward findOne(Long id)
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
    public List<TdUserCashReward> findAll(Iterable<Long> ids)
    {
        return (List<TdUserCashReward>) repository.findAll(ids);
    }
    
    public List<TdUserCashReward> findByUsername(String username)
    {
        return repository.findByUsername(username);
    }
    
    public Page<TdUserCashReward> findAllOrderBySortIdAsc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "sortId"));
        
        return repository.findAll(pageRequest);
    }
    
    public Page<TdUserCashReward> findByUsernameOrderByIdDesc(String username, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByUsernameOrderByIdDesc(username, pageRequest);
    }
    
    /**
     * 保存
     * 
     * @param e
     * @return
     */
    public TdUserCashReward save(TdUserCashReward e)
    {
        
        return repository.save(e);
    }
    
    public List<TdUserCashReward> save(List<TdUserCashReward> entities)
    {
        
        return (List<TdUserCashReward>) repository.save(entities);
    }
}
