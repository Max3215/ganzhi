package com.ynyes.ganzhi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.ganzhi.entity.TdUserCashReward;

/**
 * TdUserCashReward 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdUserCashRewardRepo extends
		PagingAndSortingRepository<TdUserCashReward, Long>,
		JpaSpecificationExecutor<TdUserCashReward> 
{
    Page<TdUserCashReward> findByUsernameOrderByIdDesc(String username, Pageable page);
    
    List<TdUserCashReward> findByUsername(String username);
}
