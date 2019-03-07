package com.ynyes.ganzhi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.ganzhi.entity.CommentTag;
import com.ynyes.ganzhi.repository.CommentTagRepo;

/**
 * 评论标签服务类
 * @author Sharon
 *
 */

@Service
@Transactional
public class CommentTagService {
    @Autowired
    CommentTagRepo repository;
    
    /**
     * 查找所有标签
     * 
     * @return 标签列表
     */
    public List<CommentTag> findAll()
    {
        return (List<CommentTag>) repository.findAll();
    }
    
    /**
     * 保存标签
     * 
     * @param name 标签名
     * @return 保存的标签
     */
    public CommentTag add(String name)
    {
        if (null == name)
        {
            return null;
        }
        
        CommentTag ct = new CommentTag();
        ct.setName(name);
        
        return repository.save(ct);
    }
    
    /**
     * 删除评论标签
     * 
     * @param id: 标签ID
     */
    public void delete(Long id)
    {
        repository.delete(id);
    }
    
    /**
     * 删除评论标签
     * 
     * @param tag: 标签
     */
    public void delete(CommentTag tag)
    {
        repository.delete(tag);
    }
}
