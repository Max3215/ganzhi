package com.ynyes.ganzhi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.ganzhi.entity.TdWarehouse;

/**
 * TdWarehouse 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdWarehouseRepo extends
		PagingAndSortingRepository<TdWarehouse, Long>,
		JpaSpecificationExecutor<TdWarehouse> 
{
    Page<TdWarehouse> findByTitleContainingOrProvinceContainingOrCityContainingOrAddressContainingOrderBySortIdAsc(String keywords1, String keywords2, String keywords3, String keywords4, Pageable page);
    TdWarehouse findByTitle(String title);
    TdWarehouse findByTitleAndIdNot(String title, Long id);
}
