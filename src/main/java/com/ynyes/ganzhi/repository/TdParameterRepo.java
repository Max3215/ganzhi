package com.ynyes.ganzhi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.ganzhi.entity.TdParameter;

/**
 * TdParameter 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdParameterRepo extends
		PagingAndSortingRepository<TdParameter, Long>,
		JpaSpecificationExecutor<TdParameter> 
{
    TdParameter findByTitleAndCategoryId(String title, Long categoryId);
    
    TdParameter findByTitleAndCategoryIdAndIdNot(String title, Long categoryId, Long id);
    
    List<TdParameter> findByCategoryId(Long categoryId);
    
    List<TdParameter> findByCategoryTreeContaining(String categoryId);
    
    List<TdParameter> findByCategoryTreeContainingAndInputTypeAndIsSearchableTrue(String categoryId, Long inputTypeId);
    
    int countByCategoryTreeContaining(String categoryId);
    
    Page<TdParameter> findByTitleContainingOrderBySortIdAsc(String keywords, Pageable page);
    
    Page<TdParameter> findByCategoryTreeContainingOrderBySortIdAsc(String catId, Pageable page);
}
