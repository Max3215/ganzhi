package com.ynyes.ganzhi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.ganzhi.entity.TdProvider;

/**
 * TdProvider 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdProviderRepo extends
		PagingAndSortingRepository<TdProvider, Long>,
		JpaSpecificationExecutor<TdProvider> 
{
    Page<TdProvider> findByTitleContainingOrProvinceContainingOrCityContainingOrAddressContainingOrderBySortIdAsc(String keywords1, String keywords2, String keywords3, String keywords4, Pageable page);
    TdProvider findByTitle(String title);
    TdProvider findByTitleAndIdNot(String title, Long id);
}
