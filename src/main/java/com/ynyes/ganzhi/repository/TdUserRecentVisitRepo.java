package com.ynyes.ganzhi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.ganzhi.entity.TdUserRecentVisit;

/**
 * TdUserRecentVisit 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdUserRecentVisitRepo extends
		PagingAndSortingRepository<TdUserRecentVisit, Long>,
		JpaSpecificationExecutor<TdUserRecentVisit> 
{
    Page<TdUserRecentVisit> findByUsernameOrderByVisitTimeDesc(String username, Pageable page);
    
    Page<TdUserRecentVisit> findByUsernameAndGoodsTitleContainingOrderByVisitTimeDesc(String username, String keywords, Pageable page);
    
    List<TdUserRecentVisit> findByUsername(String username);
    
    TdUserRecentVisit findByUsernameAndGoodsId(String username, Long goodsId);
}
