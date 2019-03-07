package com.ynyes.ganzhi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.ganzhi.entity.TdSettle;

/**
 * TdKeywords 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdSettleRepo extends
		PagingAndSortingRepository<TdSettle, Long>,
		JpaSpecificationExecutor<TdSettle> 
{
    List<TdSettle> findByIsEnableTrueOrderBySortIdAsc();
    
    TdSettle findTopByTitleIgnoreCase(String title);
}
