package com.ynyes.ganzhi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.ganzhi.entity.TdParameterCategory;

/**
 * TdParameterCategory 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdParameterCategoryRepo extends
		PagingAndSortingRepository<TdParameterCategory, Long>,
		JpaSpecificationExecutor<TdParameterCategory> 
{
    List<TdParameterCategory> findByParentIdIsNullOrderBySortIdAsc();
    List<TdParameterCategory> findByParentIdOrderBySortIdAsc(Long parentId);
    
    TdParameterCategory findByTitleAndParentIdIsNull(String title);
    TdParameterCategory findByTitleAndParentId(String title, Long parentId);
    
    TdParameterCategory findByTitleAndParentIdIsNullAndIdNot(String title, Long id);
    TdParameterCategory findByTitleAndParentIdAndIdNot(String title, Long parentId, Long id);
}
