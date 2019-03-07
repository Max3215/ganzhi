package com.ynyes.ganzhi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.ganzhi.entity.TdSiteLink;

/**
 * TdSiteLink 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdSiteLinkRepo extends
		PagingAndSortingRepository<TdSiteLink, Long>,
		JpaSpecificationExecutor<TdSiteLink> 
{
    TdSiteLink findByTitle(String title);
    TdSiteLink findByTitleAndIdNot(String title, Long id);
    
    Page<TdSiteLink> findByTitleContainingOrderBySortIdAsc(String keywords, Pageable page);
    List<TdSiteLink> findByIsEnableTrueOrderBySortIdAsc();
}
