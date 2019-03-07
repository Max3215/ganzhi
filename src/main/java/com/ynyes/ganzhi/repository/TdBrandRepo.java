package com.ynyes.ganzhi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.ganzhi.entity.TdBrand;

/**
 * TdBrand 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdBrandRepo extends
		PagingAndSortingRepository<TdBrand, Long>,
		JpaSpecificationExecutor<TdBrand> 
{
    Page<TdBrand> findByStatusIdOrderBySortIdAsc(Long statusId, Pageable page);
    
    List<TdBrand> findByProductCategoryTreeContaining(String productCategoryId);
    
    Page<TdBrand> findByProductCategoryTreeContaining(String productCategoryId, Pageable page);
    
    List<TdBrand> findByStatusIdAndProductCategoryTreeContaining(Long statusId, String productCategoryId);
    
    Page<TdBrand> findByStatusIdAndProductCategoryTreeContaining(Long statusId, String productCategoryId, Pageable page);
    
    TdBrand findByProductCategoryTreeContainingAndTitle(String productCategoryId, String title);
    
    TdBrand findByProductCategoryTreeContainingAndTitleAndIdNot(String productCategoryId, String title, Long id);
    
    TdBrand findByTitle(String title);
    
    TdBrand findByTitleAndIdNot(String title, Long id);
    
    Page<TdBrand> findByTitleContainingOrderBySortIdAsc(String keywords, Pageable page);
}
